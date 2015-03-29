package uva.sc.ql.patronElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uva.sc.ql.ast.IQLExpressionNodeVisitor;
import uva.sc.ql.ast.IQLFormNodeVisitor;
import uva.sc.ql.ast.IQLStatementNodeVisitor;
import uva.sc.ql.atom.*;
import uva.sc.ql.expression.binaryExpressions.*;
import uva.sc.ql.expression.unaryExpressions.*;
import uva.sc.ql.form.Form;
import uva.sc.ql.statements.*;

/**
 *  Generates a map of questions (A) and their patron questions (B), that is questions (B) that their
 *  value will affect the value and the visibility of the first (A).
 * @author Pantelis & Santiago
 */
@SuppressWarnings({ "unchecked" })
public class PatronQuestionsVisitor implements
	IQLExpressionNodeVisitor<Object>, IQLFormNodeVisitor<Object>,
	IQLStatementNodeVisitor<Object> {

    Map<ID, List<ID>> patronElements = new HashMap<ID, List<ID>>();
    ID currentElement;

    public Map<ID, List<ID>> getPatronElements() {
	return patronElements;
    }

    public Object visit(Form questionnaire) {
	List<Statement> statements = questionnaire.getStatements();
	for (Statement statement : statements) {
	    statement.accept(this);
	}
	return null;
    }

    public Object visit(Question question) {
	currentElement = question.getId();
	if (question.getExpr() != null) {
	    question.getExpr().accept(this);
	}
	return null;
    }

    public Object visit(IfStatement ifStatement) {
	List<Question> questions = ifStatement.getQuestions();
	for (Question question : questions) {
	    question.accept(this);
	    ifStatement.getExpr().accept(this);
	}
	return null;
    }

    public Object visit(ID id) {
	List<ID> elements = patronElements.get(id);
	if (elements == null) {
	    elements = new ArrayList<ID>();
	}
	elements.add(currentElement);
	patronElements.put(id, elements);
	return null;
    }

    public Object visit(Addition addition) {
	addition.getFirstOperand().accept(this);
	addition.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(And and) {
	and.getFirstOperand().accept(this);
	and.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Division division) {
	division.getFirstOperand().accept(this);
	division.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Equals equals) {
	equals.getFirstOperand().accept(this);
	equals.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(GreaterThan greaterThan) {
	greaterThan.getFirstOperand().accept(this);
	greaterThan.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(GreaterThanEquals greaterThanEquals) {
	greaterThanEquals.getFirstOperand().accept(this);
	greaterThanEquals.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(LesserThan lesserThan) {
	lesserThan.getFirstOperand().accept(this);
	lesserThan.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(LesserThanEquals lesserThanEquals) {
	lesserThanEquals.getFirstOperand().accept(this);
	lesserThanEquals.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Modulus mod) {
	mod.getFirstOperand().accept(this);
	mod.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Multiplication mult) {
	mult.getFirstOperand().accept(this);
	mult.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(NotEquals notEquals) {
	notEquals.getFirstOperand().accept(this);
	notEquals.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Or or) {
	or.getFirstOperand().accept(this);
	or.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Substraction sub) {
	sub.getFirstOperand().accept(this);
	sub.getSecondOperand().accept(this);
	return null;
    }

    public Object visit(Minus minus) {
	minus.getOperand().accept(this);
	return null;
    }

    public Object visit(Not not) {
	not.getOperand().accept(this);
	return null;
    }

    public Object visit(BooleanAtom bool) {
	return null;
    }

    public Object visit(NumberAtom doub) {
	return null;
    }

    public Object visit(StringAtom str) {
	return null;
    }

}
