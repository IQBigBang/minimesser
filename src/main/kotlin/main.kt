import algorithm.align
import algorithm.validate
import backend.BackendSettings
import backend.CSharpBackend
import compiler.Compiler
import java.io.InputStream

fun main() {
    //val code = "message Tick { x: int8; y: int16; z: int32; w: int64; ww: int64; xxz: int16; ya: int64; bb: int32; }"
    val code = "message Tick { player: int32; action: int16; }"
    val file = parseString(code)
    val settings = BackendSettings(outputDirectory = ".")
    validate(file).getOrThrow()

    val compiler = Compiler(file, CSharpBackend(settings))
    compiler.compileAll()

}