﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QL.Exceptions.Errors;
using QL.Hollywood;
using QL.Hollywood.DataHandlers;
using QL.UI.Controls;

namespace QL.UI.Builder
{
    public class Renderer : IExecutable
    {
        public IList<WidgetBase> ElementsToDisplay { get; private set; }

        public Renderer()
        {
            ElementsToDisplay = new List<WidgetBase>();
        }

        public bool Execute(DataContext context)
        {
            try
            {
                UserInterfaceVisitor visitor = new UserInterfaceVisitor(context.ValueReferenceTable, context.ASTHandlerExceptions, ElementsToDisplay);
                context.RootNode.Accept(visitor);
            }
            catch (QLError ex)
            {
                context.ASTHandlerExceptions.Add(ex);
                return false;
            }

            return !context.ASTHandlerExceptions.Any();
        }
    }
}
