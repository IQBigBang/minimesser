package algorithm

import Message
import Type

data class Padding(val PaddingSize: Int)

/**
 * Takes a message and returns where to insert padding to ensure alignment.
 *
 * @return Paddings. The String is the name of the field BEFORE which the padding is inserted
 */
fun align(msg: Message): HashMap<String, Padding> {
    var alignment = 0 // alignment = position mod 8
    val paddings = HashMap<String, Padding>()
    var i = 0

    while (i < msg.fields.size) {
        val field = msg.fields[i]
        val requiredAlign = getRequiredAlign(field.type)

        if (alignment % requiredAlign != 0) {
            val paddingSize = requiredAlign - (alignment % requiredAlign)
            paddings[field.name] = Padding(paddingSize)
            alignment += paddingSize
        }

        alignment = (alignment + field.type.getSize()) % 8
        i++
    }

    return paddings
}

/**
 * Gets the alignment required before a type
 * @param t The type
 * @return The required alignment
 */
private fun getRequiredAlign(t: Type): Int =
    when (t) {
        is Type.Int8, is Type.UInt8 -> 1
        is Type.Int16, is Type.UInt16 -> 2
        is Type.Int32, is Type.UInt32 -> 4
        is Type.Int64, is Type.UInt64 -> 8
    }