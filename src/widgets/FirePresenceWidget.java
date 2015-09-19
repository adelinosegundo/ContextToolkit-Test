package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

public class FirePresenceWidget extends Widget {
	
	public static final String CLASSNAME = FirePresenceWidget.class.getName();
	public static final String FIRE_PRESENCE = "firePresence";
	public static final String AMBIENT = "ambient";
	
	public static final int TEMPERATURE_MAX = 40;

	private String ambient;
	
	public FirePresenceWidget(String ambient) {
		super(CLASSNAME, CLASSNAME);
		this.ambient = ambient;
		super.start(true);
	}
	

	@Override
	protected void init() {
		addAttribute(Attribute.instance(FIRE_PRESENCE, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(AMBIENT, ambient), true);
	}
}
