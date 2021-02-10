package compiler

import File
import Message
import Type
import algorithm.align
import backend.Backend

class Compiler(
    private val file: File,
    private val backend: Backend) {

    fun compileAll() {
        backend.prepare()
        for (msg in file.messages) {
            val (serialize, deserialize) = compileMessage(msg)
            backend.compileMessage(msg, serialize, deserialize)
        }
    }

    private fun compileMessage(message: Message): Pair<List<Instr>, List<Instr>> {
        val serialize = mutableListOf<Instr>()
        val deserialize = mutableListOf<Instr>()
        // Get the padding of the message
        val padding = align(message)
        // Compute its size
        // We don't have any variable-sized fields yet, so we can just sum the sizes of all fields plus the padding
        val msgSize =
            message.fields.map { getSize(it.type) }.sum() +
            padding.map { it.value.PaddingSize }.sum()

        // Now emit the instructions:
        // First check the buffer size
        serialize += Instr.CheckBufSize(msgSize)
        deserialize += Instr.CheckBufSize(msgSize)
        // Now onto the fields:
        for (field in message.fields) {
            // Check whether there is padding before the field
            if (field.name in padding) {
                serialize += Instr.SkipPadding(padding[field.name]!!.PaddingSize)
                deserialize += Instr.SkipPadding(padding[field.name]!!.PaddingSize)
            }
            // Emit serialize/deserialize Primitive (because we don't have non-primitive types yet)
            serialize += Instr.SerializePrim(field.name, field.type)
            deserialize += Instr.DeserializePrim(field.name, field.type)
        }

        return Pair(serialize, deserialize)
    }

    private fun getSize(t: Type): Int =
        when (t) {
            is Type.Int8, is Type.UInt8 -> 1
            is Type.Int16, is Type.UInt16 -> 2
            is Type.Int32, is Type.UInt32 -> 4
            is Type.Int64, is Type.UInt64 -> 8
        }
}