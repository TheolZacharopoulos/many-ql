package org.uva.ql.view;

import java.util.ArrayList;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.expression.literal.Identifier;
import org.uva.ql.ast.questionnaire.Form;
import org.uva.ql.ast.questionnaire.Questionnaire;
import org.uva.ql.ast.statement.Block;
import org.uva.ql.ast.statement.IfElseStatement;
import org.uva.ql.ast.statement.IfStatement;
import org.uva.ql.ast.statement.QuestionCompute;
import org.uva.ql.ast.statement.QuestionNormal;
import org.uva.ql.ast.statement.Statement;
import org.uva.ql.ast.type.BoolType;
import org.uva.ql.ast.type.IntType;
import org.uva.ql.ast.type.StrType;
import org.uva.ql.ast.value.Undefined;
import org.uva.ql.view.listener.WidgetListener;
import org.uva.ql.view.widgit.CheckBox;
import org.uva.ql.view.widgit.NumberTextField;
import org.uva.ql.view.widgit.TextField;
import org.uva.ql.view.widgit.Widget;
import org.uva.ql.visitor.QuestionnaireVisitor;
import org.uva.ql.visitor.StatementVisitor;
import org.uva.ql.visitor.TypeVisitor;

public class GUIVisitor implements StatementVisitor<Object>, TypeVisitor<Object>, QuestionnaireVisitor<Object> {

	private WidgetListener widgetListener;

	public GUIVisitor() {
		widgetListener = new WidgetListener();
	}

	@Override
	public DependentQuestionPanel visit(IfStatement ifStatement) {
		ArrayList<Panel> questionPanels = (ArrayList<Panel>) ifStatement.getIfBlock().accept(this);
		Expression expr = ifStatement.getExpr();
		DependentQuestionPanel questionPanel = new DependentQuestionPanel(questionPanels, expr);
		widgetListener.addDependentQuestionPanel(questionPanel);
		return questionPanel;
	}

	@Override
	public Panel visit(QuestionNormal questionStatement) {
		Widget widget = (Widget) questionStatement.getType().accept(this);
		widget.setDependent(false);
		Identifier identifier = questionStatement.getIdentifier();
		QuestionComponent questionComponent = new QuestionComponent(questionStatement, widget);
		widgetListener.initializeValue(identifier.toString(), new Undefined());
		return questionComponent;
	}

	@Override
	public Panel visit(QuestionCompute questionComputeStatement) {
		Widget widget = (Widget) questionComputeStatement.getType().accept(this);
		widget.setDependent(true);
		DependentQuestionComponent questionComponent = new DependentQuestionComponent(questionComputeStatement, widget);
		Identifier identifier = questionComputeStatement.getIdentifier();
		widgetListener.initializeValue(identifier.toString(), new Undefined());
		widgetListener.addDependentQuestionComponent(questionComponent);
		return questionComponent;
	}

	@Override
	public ArrayList<Panel> visit(Block blockStatement) {
		ArrayList<Panel> questionPannels = new ArrayList<Panel>();
		for (Statement statement : blockStatement.getStatements()) {
			questionPannels.add((Panel) statement.accept(this));
		}
		return questionPannels;
	}

	@Override
	public FormFrame visit(Form form) {
		FormFrame formView = new FormFrame(form.getIdentifier().toString());
		ArrayList<Panel> questionPannels = (ArrayList<Panel>) form.getBlock().accept(this);
		QuestionPanel questionPanel = new QuestionPanel(questionPannels);
		formView.add(questionPanel);
		formView.setVisible(true);
		return formView;
	}

	@Override
	public ArrayList<FormFrame> visit(Questionnaire questionnaire) {
		ArrayList<FormFrame> formViews = new ArrayList<FormFrame>();
		for (Form form : questionnaire.getForms()) {
			formViews.add((FormFrame) form.accept(this));
		}
		return formViews;
	}

	@Override
	public Widget visit(IntType node) {
		return new NumberTextField(widgetListener);
	}

	@Override
	public Widget visit(BoolType node) {
		return new CheckBox(widgetListener);
	}

	@Override
	public Widget visit(StrType node) {
		return new TextField(widgetListener);
	}

	@Override
	public Object visit(IfElseStatement ifElseStatement) {
		return null;
	}
}
