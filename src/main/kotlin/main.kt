import algorithm.validate
import backend.BackendSettings
import backend.CSharpBackend
import compiler.Compiler

fun main() {
    //val code = "message Tick { x: int8; y: int16; z: uint32; w: int64; ww: uint64; xxz: uint16; ya: int64; bb: uint32; }"
    val code = "message Tick { player: int32; action: uint16; }"
    val file = parseString(code)
    val settings = BackendSettings(outputDirectory = "out/")
    validate(file).getOrThrow()

    val compiler = Compiler(file, CSharpBackend(settings))
    compiler.compileAll()

}