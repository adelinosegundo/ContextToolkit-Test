package widgets;

import context.arch.storage.Attribute;
import context.arch.storage.AttributeNameValue;
import context.arch.widget.Widget;
import notification.NotificationSubject;

public class WarningWidget extends Widget {
	
	public static final String CLASSNAME = WarningWidget.class.getName();
	
	public static final String WARNING_FIRE = "warningFire";
	public static final String WARNING_AIR_POLLUTION = "warningAirPollution";
	public static final String WARNING_TEMPERATURE = "warningTemperature";
	
	public static final Boolean WARNING_ON = true;
	public static final Boolean WARNING_OFF = false;
	
	public static final String AMBIENT = "ambient";
	
	private String ambient;
	
	private NotificationSubject notificationSubject;
	
	public WarningWidget(String ambient) {
		super(CLASSNAME, CLASSNAME);
		this.ambient = ambient;
		super.start(true);
		notificationSubject = new NotificationSubject();
	}
	

	@Override
	protected void init() {
		addAttribute(Attribute.instance(WARNING_FIRE, Boolean.class));
		addAttribute(Attribute.instance(WARNING_AIR_POLLUTION, Boolean.class));
		addAttribute(Attribute.instance(WARNING_TEMPERATURE, Boolean.class));
		
		addAttribute(AttributeNameValue.instance(AMBIENT, ambient), true);	
	}


	public NotificationSubject getNotificationSubject() {
		return notificationSubject;
	}


	public void setNotificationSubject(NotificationSubject notificationSubject) {
		this.notificationSubject = notificationSubject;
	}
	
}
