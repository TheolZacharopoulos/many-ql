package qls.ast.expression.literal;

import qls.value.StringValue;
import qls.ast.expression.Literal;
import qls.ast.visitor.ExpressionVisitor;

public class StringLiteral extends Literal<StringValue> {
	public StringLiteral(StringValue value) {
		super(value);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
