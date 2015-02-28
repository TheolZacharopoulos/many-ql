package org.uva.ql.ast.expression.literal;

import org.uva.ql.ast.builder.CodePosition;
import org.uva.ql.ast.visitor.Visitor;

public class IntLiteral extends Literal{

	private final Integer value;
	
	public IntLiteral(Integer value,CodePosition pos) {
		super(pos);
		this.value = value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Integer getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
