package org.fugazi.ast.expression.numerical;

import org.fugazi.ast.expression.Expression;
import org.fugazi.ast.IASTVisitor;

/**
 * The Multiplication '*'.
 */
public class MulExpression extends NumericalExpression {

    public MulExpression(Expression _leftExpr, Expression _rightExpr) {
        super(_leftExpr, _rightExpr);
    }

    @Override
    public String toString() {
        return this.leftExpr.toString() + " * " + this.rightExpr.toString();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visitMulExpression(this);
    }
}