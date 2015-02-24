package org.uva.ql.ast.expression.binary;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.visitor.Visitor;

public class Minus extends Binary {

	public Minus(Expression left, Expression right) {
			super(left, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.left.toString() + " - " + this.right.toString();
	}

}
