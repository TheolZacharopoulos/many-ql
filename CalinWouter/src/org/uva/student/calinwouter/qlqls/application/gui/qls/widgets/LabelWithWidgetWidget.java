package org.uva.student.calinwouter.qlqls.application.gui.qls.widgets;

import org.uva.student.calinwouter.qlqls.ql.exceptions.LabelNotAvailableException;
import org.uva.student.calinwouter.qlqls.ql.interpreter.impl.headless.ChangedStateEventListener;
import org.uva.student.calinwouter.qlqls.ql.interpreter.impl.headless.HeadlessFormInterpreter;
import org.uva.student.calinwouter.qlqls.qls.model.abstractions.AbstractFormField;

import javax.swing.*;
import java.awt.*;

/**
 * Name may be confusing. This widget is basically a (Label + Widget) Widget.
 */
public class LabelWithWidgetWidget implements IWidget {
    private JPanel labelWithWidgetWidget;
    private HeadlessFormInterpreter headlessFormInterpreter;

    @Override
    public Component getWidget() {
        return labelWithWidgetWidget;
    }

    public LabelWithWidgetWidget(final AbstractFormField model, IWidget widget,
                                 final HeadlessFormInterpreter headlessFormInterpreter) {
        this.headlessFormInterpreter = headlessFormInterpreter;
        final Label fieldLabel = new Label();
        labelWithWidgetWidget = new JPanel();
        labelWithWidgetWidget.add(fieldLabel);
        labelWithWidgetWidget.add(widget.getWidget());
        headlessFormInterpreter.subscribeChangedStateEventListener(new ChangedStateEventListener() {
            @Override
            public void onStateChanged() {
                try {
                    fieldLabel.setText(headlessFormInterpreter.getLabelForField(model.getFieldName()));
                    labelWithWidgetWidget.setVisible(true);
                } catch (LabelNotAvailableException e) {
                    fieldLabel.setText("");
                    labelWithWidgetWidget.setVisible(false);
                }
            }
        });
    }

}
