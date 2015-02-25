﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.AST.Model.Statements;

namespace UvA.SoftCon.Questionnaire.AST.Model.Expressions.Binary
{
    public class Divide : BinaryExpression
    {
        public override NodeType Type
        {
            get
            {
                return NodeType.Divide;
            }
        }

        public Divide(Operation operation, IExpression left, IExpression right, TextPosition position)
            : base(operation, left, right, position) {}

        public override T Accept<T>(IASTVisitor<T> visitor)
        {
            return visitor.Visit(this);
        }

        public override DataType? GetType(IDictionary<string, DataType> symbolTable)
        {
            return DataType.Integer;
        }
    }
}
