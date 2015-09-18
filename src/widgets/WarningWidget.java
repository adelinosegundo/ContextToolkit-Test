package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;

public class WarningWidget extends Widget {
	
	public static final String CLASSNAME = WarningWidget.class.getName();
	
	public static final String WARNING = "warning";
	public static final Boolean WARNING_ON = true;
	public static final Boolean WARNING_OFF = false;

	public static final String AMBIENT = "ambient";
	
	private String ambient;
	
	public WarningWidget(String ambient) {
		super(CLASSNAME, CLASSNAME);
		this.ambient = ambient;
		super.start(true);
	}
	

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		addAttribute(Attribute.instance(WARNING, Boolean.class));
		
		// constant attributes
		addAttribute(AttributeNameValue.instance(AMBIENT, ambient), true);
		
	}

}
