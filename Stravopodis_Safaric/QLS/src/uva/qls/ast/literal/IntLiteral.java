package uva.qls.ast.literal;
import uva.qls.ast.value.*;
import uva.qls.ast.CodeLines;

public class IntLiteral extends Literal {
	
	private Integer value;
	
	public IntLiteral(Integer _value, CodeLines _codeLines){
		super(_codeLines);
		this.value=_value;
		}
	public IntLiteral(CodeLines _codeLines){
		super(_codeLines);
	}
	@Override
	public NumberValue evaluate() {
		return new NumberValue(this.value);
	}
	
	@Override
	public String toString(){
		if (this.value == null) return "IntegerLiteral()";
		else return "IntegerLiteral(" + String.valueOf(this.value) + ")";
	}
}
