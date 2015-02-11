package com.form.language.node;

public class Modulus implements AST {
	private AST op1;
	private AST op2;
	public Modulus(AST op1, AST op2) {
		super();
		this.op1 = op1;
		this.op2 = op2;
	}
	@Override
	public int evaluate() {
		return op1.evaluate() % op2.evaluate();
	}
	
	
}
