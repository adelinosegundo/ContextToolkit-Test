package widgets;



import java.util.ArrayList;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import notification.Notifier;

public class TemperatureWidget extends Widget {
	public static final String CLASSNAME = TemperatureWidget.class.getName();
	public static final String TEMPERATURE = "temperature";
	public static final String AMBIENT = "ambient";
	
	public static final int TEMPERATURE_MAX = 40;

	private String ambient;
	
	public TemperatureWidget(String ambient) {
		super(CLASSNAME, CLASSNAME);
		this.ambient = ambient;
		super.start(true);
	}
	

	@Override
	protected void init() {
		addAttribute(Attribute.instance(TEMPERATURE, Integer.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(AMBIENT, ambient), true);
	}
	
}
