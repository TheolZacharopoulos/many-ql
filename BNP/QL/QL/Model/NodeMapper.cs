﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Antlr4.Runtime.Tree;
using QL.Grammars;
using QL.Model.Terminals;
using System.Diagnostics;

namespace QL.Model
{
    public sealed class NodeMapper
    {
        private readonly Stack<Stack<TreeElementBase>> _childrenStack;
        private TreeElementBase _astRootNode;
        
        public NodeMapper()
        {
            _childrenStack = new Stack<Stack<TreeElementBase>>();
        }

        public void InitializeNewLevel()
        {
            _childrenStack.Push(new Stack<TreeElementBase>());
        }

        private IList<TreeElementBase> GetChildren()
        {
            Debug.Assert(_childrenStack.Count() > 0, "Level with children should be always initialized before appending one.");//TODO maybe throw it out
            return _childrenStack.Pop().ToList();
        }

        private void PutIntoTree(TreeElementBase newChild)
        {

            if (_childrenStack.Count() > 0)
            {
                Stack<TreeElementBase> siblings = _childrenStack.Peek();
                siblings.Push(newChild);
            }
            else
            {
                //this is the last one
                _astRootNode = newChild;
            }
        }

        public void HandleNode<T>() where T : TreeElementBase, new()
        {
            IList<TreeElementBase> children = GetChildren();
            T parent = new T();
            parent.HandleChildren(children);
            PutIntoTree(parent);
        }

        public UnitBase Create(QLParser.QuestionUnitContext context)
        {
            Identifier identifier = new Identifier(context.IDENTIFIER().GetText());
            string typeName = context.typeName().GetText();
            string unitText = context.TEXT().GetText();

            TerminalTypeFactory typeFactory = new TerminalTypeFactory(typeName);
            ITerminalType answerType = typeFactory.Create();

            QuestionUnit question = new QuestionUnit(identifier, answerType, unitText, true); //true == todo

            return question;
        }

        public UnitBase Create(QLParser.StatementUnitContext context)
        {
            Identifier identifier = new Identifier(context.IDENTIFIER().GetText());
            string typeName = context.typeName().ToString();
            string unitText = context.TEXT().GetText();

            TerminalTypeFactory typeFactory = new TerminalTypeFactory(typeName);
            ITerminalType answerType = typeFactory.Create();

            StatementUnit statement = new StatementUnit(identifier, answerType, unitText);

            return statement;
        }

        public TreeElementBase Create(QLParser.ControlBlockUnitContext context)
        {
            return Create(context.controlBlock());
        }

        public TreeElementBase Create(QLParser.UnitContext context)
        {
            // todo: not use 'is' operator, but not sure how to do that now
            if (context is QLParser.QuestionUnitContext) return Create(context as QLParser.QuestionUnitContext);

            if (context is QLParser.StatementUnitContext) return Create(context as QLParser.StatementUnitContext);

            if (context is QLParser.ControlBlockUnitContext) return Create(context as QLParser.ControlBlockUnitContext);

            throw new ArgumentOutOfRangeException("context");
        }

        public ControlBlock Create(QLParser.ControlBlockContext context)
        {
            ControlBlock controlBlock = new ControlBlock();
            context.expression();
            context.block();

            return controlBlock;
        }

        public Block Create(QLParser.BlockContext context)
        {
            TreeElementBase[] childUnits = context.unit().Select(Create) as TreeElementBase[];
            return new Block(childUnits);
        }

        // todo: move to own mapper
        public Form Create(QLParser.FormBlockContext context)
        {
            Identifier formBlockId = new Identifier(context.IDENTIFIER().GetText());
            Block formBody = Create(context.block());
            Form retVal = new Form(formBlockId, formBody);
            retVal.SourceLocation = SourceLocation.Create(context);

            return retVal;
        }
        
        internal void Create(Antlr4.Runtime.ParserRuleContext context)
        {
            throw new NotImplementedException();
        }
    }
}
