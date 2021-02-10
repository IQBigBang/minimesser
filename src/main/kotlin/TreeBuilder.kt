import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import parser.MinimesserBaseVisitor
import parser.MinimesserLexer
import parser.MinimesserParser

class TreeBuilder : MinimesserBaseVisitor<Any>() {
    override fun visitProgram(ctx: MinimesserParser.ProgramContext?): File =
        File(ctx!!.message()!!.map(this::visitMessage))

    override fun visitMessage(ctx: MinimesserParser.MessageContext?): Message =
        Message(ctx!!.ID().text, ctx.field().map(this::visitField))

    override fun visitField(ctx: MinimesserParser.FieldContext?): MessageField =
        MessageField(ctx!!.ID().text, visitTyp(ctx.typ()))

    override fun visitTyp(ctx: MinimesserParser.TypContext?): Type =
        when(ctx!!.text) {
            "int8" -> Type.Int8
            "int16" -> Type.Int16
            "int32" -> Type.Int32
            "int64" -> Type.Int64
            "uint8" -> Type.UInt8
            "uint16" -> Type.UInt16
            "uint32" -> Type.UInt32
            "uint64" -> Type.UInt64
            else -> throw Exception("Internal error")
        }
}

fun parseString(s: String): File {
    val lex = MinimesserLexer(CharStreams.fromString(s))
    val stream = CommonTokenStream(lex)
    val parser = MinimesserParser(stream)
    return TreeBuilder().visitProgram(parser.program())
}