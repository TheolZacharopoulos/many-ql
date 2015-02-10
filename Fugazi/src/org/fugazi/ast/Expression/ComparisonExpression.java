package org.fugazi.ast.Expression;

/**
 * The ComparisonExpression class. An abstract class the express a ComparisonExpression.
 * It is a Node of the AST, and an expression.
 */
public abstract class ComparisonExpression extends Expression {

    // The left expression
    protected Expression leftExpr;

    // The right expression
    protected Expression rightExpr;

    /**
     * Constructor.
     * @param _leftExpr The left expression
     * @param _rightExpr The right expression
     */
    public ComparisonExpression(Expression _leftExpr, Expression _rightExpr) {
        leftExpr = _leftExpr;
        rightExpr = _rightExpr;
    }

    public Expression getLeftExpr() {
        return leftExpr;
    }

    public Expression getRightExpr() {
        return rightExpr;
    }
}