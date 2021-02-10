sealed class Type {
    object Int8 : Type()
    object Int16 : Type()
    object Int32 : Type()
    object Int64 : Type()
    object UInt8 : Type()
    object UInt16 : Type()
    object UInt32 : Type()
    object UInt64 : Type()

    fun getSize(): Int =
        when (this) {
            Int8, UInt8 -> 1
            Int16, UInt16 -> 2
            Int32, UInt32 -> 4
            Int64, UInt64 -> 8
        }
}

data class MessageField(val name: String, val type: Type)

data class Message(val name: String, val fields: List<MessageField>)

data class File(val messages: List<Message>)
