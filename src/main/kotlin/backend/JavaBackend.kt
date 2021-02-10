package backend

import Message
import Type
import compiler.Instr
import util.loadResource
import java.io.FileWriter
import java.nio.file.Paths

class JavaBackend(settings: BackendSettings) : Backend(settings) {
    private var writer: FileWriter? = null

    override fun prepare() {
        // TODO: Insert package name
        this.loadResource("java/ISerialize.java")!!
            .copyToFile(settings.outputDirectory, "ISerialize.java")

        this.loadResource("java/IDeserialize.java")!!
            .copyToFile(settings.outputDirectory, "IDeserialize.java")
    }

    override fun compileMessage(msg: Message, serialize: List<Instr>, deserialize: List<Instr>) {
        val file = Paths.get(settings.outputDirectory, msg.name + "Message.java").toFile()
        file.createNewFile()
        file.setWritable(true)

        writer = FileWriter(file)
        // TODO: Insert package name
        // write imports
        writeln("import jdk.jfr.Unsigned;")
        writeln("import java.nio.ByteBuffer;")
        writeln("") // a newline

        // start the class
        writeln("public class ${msg.name}Message implements ISerialize, IDeserialize {")
        // emit the fields
        for (f in msg.fields) {
            if (isTypeUnsigned(f.type))
                writeln("public @Unsigned ${typeToString(f.type)} ${f.name};")
            else
                writeln("public ${typeToString(f.type)} ${f.name};")
        }

        // emit the constructors
        writeln("public ${msg.name}Message() {}") // an empty one

        write("public ${msg.name}Message(")
        for (i in msg.fields.indices) { // arguments
            val f = msg.fields[i]
            write("${typeToString(f.type)} ${f.name}")
            if (i != msg.fields.size - 1)
                write(", ")
        }
        writeln(") {")
        for (f in msg.fields) { // initialization
            writeln("this.${f.name} = ${f.name};")
        }
        writeln("}")

        // emit the serialize and deserialize functions
        writeln("public void serializeInto(ByteBuffer _buf) {")
        compileInstructions(serialize)
        writeln("}")

        writeln("public void deserializeFrom(ByteBuffer _buf) {")
        compileInstructions(deserialize)
        writeln("}")

        writeln("}")

        writer!!.close()
        writer = null
    }

    private fun compileInstructions(instrs: List<Instr>) {
        for (i in instrs) {
            when (i) {
                is Instr.CheckBufSize -> {
                    writeln("assert _buf.capacity() >= ${i.size};")
                }
                is Instr.DeserializePrim -> {
                    val methodName =
                        if (i.type == Type.Int8 || i.type == Type.UInt8) "get"
                        else "get" + typeToString(i.type).capitalize()

                    writeln("${i.fieldName} = _buf.$methodName();")
                }
                is Instr.SerializePrim -> {
                    val methodName =
                        if (i.type == Type.Int8 || i.type == Type.UInt8) "put"
                        else "put" + typeToString(i.type).capitalize()

                    writeln("_buf.$methodName(${i.fieldName});")
                }
                is Instr.SkipPadding -> {
                    // TODO: Maybe use get() when possible instead of this
                    writeln("_buf.position(_buf.position() + ${i.size});")
                }
            }
        }
    }

    private fun write(s: String) {
        writer!!.write(s)
    }

    private fun writeln(s: String) {
        writer!!.write(s)
        writer!!.write(System.lineSeparator())
    }

    private fun typeToString(t: Type): String =
        when (t) {
            is Type.Int8, is Type.UInt8 -> "byte"
            is Type.Int16, is Type.UInt16 -> "short"
            is Type.Int32, is Type.UInt32 -> "int"
            is Type.Int64, is Type.UInt64 -> "long"
        }

    private fun isTypeUnsigned(t: Type): Boolean =
        when (t) {
            is Type.Int8, Type.Int16, Type.Int32, Type.Int64 -> false
            is Type.UInt8, Type.UInt16, Type.UInt32, Type.UInt64 -> true
        }
}