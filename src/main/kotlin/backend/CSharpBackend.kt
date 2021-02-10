package backend

import Message
import Type
import compiler.Instr
import util.loadResource
import java.io.FileWriter
import java.nio.file.Paths

class CSharpBackend(settings: BackendSettings) : Backend(settings) {
    private var writer: FileWriter? = null

    override fun prepare() {
        this.loadResource("csharp/Serialize.cs")!!
            .copyToFile(settings.outputDirectory, "Serialize.cs")

        this.loadResource("csharp/Deserialize.cs")!!
            .copyToFile(settings.outputDirectory, "Deserialize.cs")
    }

    override fun compileMessage(msg: Message, serialize: List<Instr>, deserialize: List<Instr>) {
        val file = Paths.get(settings.outputDirectory, msg.name + "Message.cs").toFile()
        file.createNewFile()
        file.setWritable(true)

        writer = FileWriter(file)
        // write `using`s
        writeln("using System;")
        writeln("using System.Diagnostics.Contracts;")
        writeln("") // a newline

        // start the class
        writeln("class ${msg.name}Message : Serialize, Deserialize {")
        // emit the fields
        for (f in msg.fields) {
            writeln("public ${typeToString(f.type)} ${f.name};")
        }

        // emit the serialize and deserialize functions
        writeln("public void SerializeInto(Span<byte> _mem) {")
        writeln("unsafe { fixed (byte* _p = _mem) {")
        writeln("Contract.Requires((ulong)_p % 8 == 0); // alignment") // alignment
        compileInstructions(serialize)
        writeln("} } }")

        writeln("public void DeserializeFrom(ReadOnlySpan<byte> _mem) {")
        writeln("unsafe { fixed (byte* _p = _mem) {")
        writeln("Contract.Requires((ulong)_p % 8 == 0); // alignment") // alignment
        compileInstructions(deserialize)
        writeln("} } }")

        writeln("}")

        writer!!.close()
        writer = null
    }

    private fun compileInstructions(instrs: List<Instr>) {
        var pos = 0 // position in the byte stream
        for (i in instrs) {
            when (i) {
                is Instr.CheckBufSize -> {
                    writeln("Contract.Requires(_mem.Length >= ${i.size});")
                }
                is Instr.DeserializePrim -> {
                    val fieldName = i.fieldName
                    val typeName = typeToString(i.type)
                    writeln("$fieldName = *($typeName*)(_p + $pos);")
                    pos += i.type.getSize()
                }
                is Instr.SerializePrim -> {
                    val fieldName = i.fieldName
                    val typeName = typeToString(i.type)
                    writeln("*($typeName*)(_p + $pos) = $fieldName;")
                    pos += i.type.getSize()
                }
                is Instr.SkipPadding -> {
                    pos += i.size
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
            is Type.Int8 -> "sbyte"
            is Type.Int16 -> "short"
            is Type.Int32 -> "int"
            is Type.Int64 -> "long"
            is Type.UInt8 -> "byte"
            is Type.UInt16 -> "ushort"
            is Type.UInt32 -> "uint"
            is Type.UInt64 -> "ulong"
        }
}