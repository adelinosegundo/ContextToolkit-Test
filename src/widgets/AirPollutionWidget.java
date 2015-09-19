package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

public class AirPollutionWidget extends Widget{
	public static final String CLASSNAME = AirPollutionWidget.class.getName();
	public static final String AIR_POLLUTION = "airPollution";
	public static final String AMBIENT = "ambient";
	
	private String ambient;
	
	public AirPollutionWidget(String ambient) {
		super(CLASSNAME, CLASSNAME);
		this.ambient = ambient;
		super.start(true);
	}
	
	@Override
	protected void init() {
		addAttribute(Attribute.instance(AIR_POLLUTION, Integer.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(AMBIENT, ambient), true);
	}
}
