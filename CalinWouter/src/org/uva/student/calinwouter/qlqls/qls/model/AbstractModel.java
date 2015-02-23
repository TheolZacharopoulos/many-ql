package org.uva.student.calinwouter.qlqls.qls.model;

import org.uva.student.calinwouter.qlqls.ql.interpreter.TypeDescriptor;
import org.uva.student.calinwouter.qlqls.ql.interpreter.impl.headless.HeadlessFormInterpreter;
import org.uva.student.calinwouter.qlqls.qls.types.AbstractPushable;

import java.util.HashMap;
import java.util.List;

// TODO check if invoking this model fails the interpreter.
public abstract class AbstractModel<T> implements IModel {
    protected boolean visible;

    @Override
    public void caseHashMap(HashMap<Object, Object> hashMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseString(String string) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseInteger(Integer styleSheet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseStyleSheet(StyleSheet styleSheet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseDefault(Default defaultSetting) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void casePage(Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseSection(Section section) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseTypeDescriptor(TypeDescriptor<?> typeDescriptor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseQuestion(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseComputedValue(ComputedValue computedValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseRadio(Radio radio) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void caseSpinbox(Spinbox radio) {
        throw new UnsupportedOperationException();
    }

    public abstract void apply(IModel iModel);

    public abstract void updateStates(HeadlessFormInterpreter headlessFormInterpreter, List<Default> defaultList);

}
