package typechecker;

import java.util.List;

import typechecker.errors.ErrorCollector;
import typechecker.errors.TaZQLError;
import ast.expression.BracketsExpression;
import ast.expression.arithmetic.AdditionExpression;
import ast.expression.arithmetic.DivisionExpression;
import ast.expression.arithmetic.MultiplicationExpression;
import ast.expression.arithmetic.SubstractionExpression;
import ast.expression.comparison.EqualExpression;
import ast.expression.comparison.GreaterEqualExpression;
import ast.expression.comparison.GreaterThanExpression;
import ast.expression.comparison.LessEqualExpression;
import ast.expression.comparison.LessThanExpression;
import ast.expression.comparison.NotEqualExpression;
import ast.expression.logical.AndExpression;
import ast.expression.logical.OrExpression;
import ast.expression.variables.BooleanVariable;
import ast.expression.variables.Id;
import ast.expression.variables.IntegerVariable;
import ast.expression.variables.StringVariable;
import ast.form.Form;
import ast.form.IFormVisitor;
import ast.question.ComputationQuestion;
import ast.question.IfElseStatement;
import ast.question.IfStatement;
import ast.question.Question;
import ast.question.SimpleQuestion;
import ast.type.ChoiceType;
import ast.type.DigitsType;
import ast.type.TextType;
import ast.type.UndefinedType;
import ast.unary.MinusExpression;
import ast.unary.NotExpression;
import ast.unary.PlusExpression;

public class TypeCheckerVisitor implements IFormVisitor<Void> {
	private final ErrorCollector errorCollector;
	
	public TypeCheckerVisitor() {
		this.errorCollector = new ErrorCollector();
	}
	
	public List<TaZQLError> getError() {
		return this.errorCollector.getErrorCollection();
	}

	public boolean isCorrect() {
		return !this.errorCollector.containsError();
	}
	
	
	@Override
	public Void visit(Form form) {
		for(Question q : form.getQuestionText())
			q.accept(this);
		
		//Test
		this.errorCollector.addError("Testing my awesome JDialog and arraylist");
		//this.errorCollector.addError("meow");
		
		return null;
	}

	@Override
	public Void visit(Question question) {
		// TODO Auto-generated method stub
		this.errorCollector.addError("Test");
		return null;
	}

	@Override
	public Void visit(SimpleQuestion simpleQuestion) {
		// TODO Auto-generated method stub
		//this.errorCollector.addError("Test");
		return null;
	}

	@Override
	public Void visit(ComputationQuestion calQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(BracketsExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(MultiplicationExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(DivisionExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(AdditionExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(SubstractionExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(EqualExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(NotEqualExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(LessThanExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(GreaterThanExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(LessEqualExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(GreaterEqualExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(NotExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(PlusExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(MinusExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(AndExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(OrExpression expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(StringVariable string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(IntegerVariable integer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(BooleanVariable bool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Id identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(TextType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(DigitsType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(ChoiceType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(UndefinedType type) {
		// TODO Auto-generated method stub
		return null;
	}
}
