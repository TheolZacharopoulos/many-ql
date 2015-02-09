// Generated from /Users/lukaszharezlak/Projects/uva_software_construction/many-ql/Fugazi/src/org/fugazi/grammar/ql.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link qlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface qlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link qlParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(@NotNull qlParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(@NotNull qlParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#ifstat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstat(@NotNull qlParser.IfstatContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(@NotNull qlParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(@NotNull qlParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull qlParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull qlParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link qlParser#assignee}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignee(@NotNull qlParser.AssigneeContext ctx);
}