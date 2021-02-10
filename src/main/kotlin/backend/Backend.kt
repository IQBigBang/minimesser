package backend

import Message
import compiler.Instr
import java.nio.file.Path

abstract class Backend(protected val settings: BackendSettings) {
    /**
     * This function may be overridden by the backend to do tasks like
     * copy shared code and so on.
     */
    open fun prepare() {}
    abstract fun compileMessage(msg: Message, serialize: List<Instr>, deserialize: List<Instr>)
}

class BackendSettings(outputDirectory: String) {
    val outputDirectory = Path.of(outputDirectory).toAbsolutePath().normalize().toString()
}