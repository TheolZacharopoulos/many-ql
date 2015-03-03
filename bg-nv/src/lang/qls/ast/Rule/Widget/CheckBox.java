package lang.qls.ast.Rule.Widget;

import lang.qls.ast.Rule.RuleVisitor;

/**
 * Created by bore on 02/03/15.
 */
public class CheckBox extends WidgetRule
{
    public CheckBox(int lineNumber)
    {
        super(lineNumber);
    }

    @Override
    public <T> T accept(RuleVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
