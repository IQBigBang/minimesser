// Generated from C:/Users/42060/IdeaProjects/minimesser-kotlin/src/main/kotlin\Minimesser.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MinimesserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MinimesserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MinimesserParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MinimesserParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinimesserParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(MinimesserParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinimesserParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(MinimesserParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MinimesserParser#typ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyp(MinimesserParser.TypContext ctx);
}