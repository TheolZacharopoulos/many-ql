﻿using AST;
using AST.Nodes;
using AST.Nodes.Expressions;
using Evaluation;
using Evaluation.Values;
using QuestionnaireLanguage.Contracts;
using QuestionnaireLanguage.GUI.CustomUIElements.CustomPanels;
using QuestionnaireLanguage.Visitors;
using System.Collections.Generic;
using System.Windows;
using ASTFormObject = AST.Nodes.FormObjects;
using Types = AST.Types;

namespace QuestionnaireLanguage.Presenter
{
    public class MainPresenter
    {
        private static ASTResult astTree;
        private static IMainWindow window;
        private static SymbolTable symbolTable;


        public MainPresenter(IMainWindow mainWindow, ASTResult ast)
        {
            window = mainWindow;
            astTree = ast;

            symbolTable = new SymbolTable();
        }

        public static UIElement ProcessBody(IList<ASTFormObject.FormObject> body, UIElement form)
        {
            foreach (ASTFormObject.FormObject node in body)
            {
                form = new FormObject().VisitFormObject(node).ProcessFormObject(form);
            }

            return form;
        }

        public static Value GetObjectValue(Id id)
        {
            return symbolTable.GetValue(id);
        }

        public static void UpdateValue(string id, Value value)
        {
            symbolTable.SetUpdateValue(new Id(id, new PositionInText()), value);

            window.DeleteElements();

            MainPresenter.ProcessBody(astTree.Ast.GetBody(), window.GetRootElement());
        }
        
        public static Value Evaluate(BaseExpression expression)
        {
            return new Evaluator(symbolTable).Evaluate(expression);
        }

        public static void AddValue(Id key, Types.Type type)
        {
            TypeToValue visitor = new TypeToValue();
            symbolTable.AddValue(key, visitor.VisitValue(type));
        }

        public static UIElement AddChildren(UIElement element, UIElement form)
        {
            ((CustomStackPanel)form).Children.Add(element);
            return form;
        }
    }
}
