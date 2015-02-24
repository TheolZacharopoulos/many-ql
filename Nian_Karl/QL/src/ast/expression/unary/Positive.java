package ast.expression.unary;

import ast.expression.Expression;
import ast.visitor.Visitor;

public class Positive extends Unary {

	public Positive(Expression expr) {
		super(expr);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "+" + this.expr.toString();
	}
	
}
