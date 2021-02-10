package compiler

import Type

/**
 * The instructions passed to the backend.
 *
 * They are atomic steps that should be performed
 * by the serialization/deserialization algorithm.
 */
sealed class Instr {
    /**
     * Serialize a primitive type from a message field
     */
    class SerializePrim(val fieldName: String, val type: Type) : Instr()

    /**
     * Deserialize a primitive type into a message field
     */
    class DeserializePrim(val fieldName: String, val type: Type) : Instr()

    /**
     * Assert the input/output buffer size is at least N bytes
     */
    class CheckBufSize(val size: Int) : Instr()

    /**
     * Skips N bytes in the input/output buffer because of padding
     */
    class SkipPadding(val size: Int) : Instr()
}
