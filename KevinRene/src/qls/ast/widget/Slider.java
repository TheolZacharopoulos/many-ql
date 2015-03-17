package qls.ast.widget;

import qls.ast.Widget;
import qls.ast.visitor.StatementVisitor;

public class Slider extends Widget {
	ValueSet values;
	
	public Slider(ValueSet values) {
		this.values = values;
	}
	
	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Slider";
	}
}
