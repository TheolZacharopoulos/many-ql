package com.klq.ast.impl.expr.bool;

import com.klq.ast.IVisitor;
import com.klq.ast.impl.expr.AExpression;
import com.klq.ast.impl.expr.value.BooleanValue;
import com.klq.ast.impl.expr.value.UndefinedValue;
import com.klq.ast.impl.expr.value.Value;

import java.util.Map;

/**
 * Created by Juriaan on 22-2-2015.
 */
public class NotEqualsNode extends ABooleanNode {

    public NotEqualsNode(AExpression leftChild, AExpression rightChild, String location) {
        super(leftChild, rightChild, location);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value evaluate(Map<String, Value> variables) {
        Value left = getLeftChild().evaluate(variables);
        Value right =getRightChild().evaluate(variables);

        if(left.isUndefined() || right.isUndefined())
        {
            return new UndefinedValue();
        }
        else {
            return new BooleanValue(!left.equals(right));
        }
    }
}
