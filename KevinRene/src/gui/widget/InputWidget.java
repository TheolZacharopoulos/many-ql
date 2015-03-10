package gui.widget;

import ql.Value;
import gui.UIComponent;

@SuppressWarnings("rawtypes")
public abstract class InputWidget<T extends Value> extends UIComponent {
	private UIComponent handler;
	
	public abstract void disable();
	
	public abstract void setValue(T value);
	public abstract T getValue();
	
	@Override
	public void setHandler(UIComponent handler) {
		this.handler = handler;
	}
	
	@Override
	public void handleChange(Value changedValue, UIComponent source) {
		handler.handleChange(changedValue, source);
	}
}
