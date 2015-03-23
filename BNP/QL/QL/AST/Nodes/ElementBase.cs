﻿namespace QL.AST.Nodes
{
    public abstract class ElementBase
    {
        public SourceLocation SourceLocation { get; set; }

        /// <summary>
        /// Gets an ElementType indicating if this element is a leaf or a node.
        /// </summary>
        
        protected ElementBase()
        {
        }


        public virtual void Accept(IVisitor visitor)
        {
            visitor.Visit((dynamic)this);
        }
       
       
    }
}
