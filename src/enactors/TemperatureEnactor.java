package enactors;

import context.arch.discoverer.ComponentDescription;
import context.arch.discoverer.component.NonConstantAttributeElement;
import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.discoverer.query.ElseQueryItem;
import context.arch.discoverer.query.ORQueryItem;
import context.arch.discoverer.query.RuleQueryItem;
import context.arch.discoverer.query.comparison.AttributeComparison;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorReference;
import context.arch.service.helper.ServiceInput;
import context.arch.storage.AttributeNameValue;
import context.arch.storage.Attributes;
import context.arch.widget.Widget;
import context.arch.widget.Widget.WidgetData;
import widgets.AirPollutionWidget;
import widgets.FirePresenceWidget;
import widgets.TemperatureWidget;
import widgets.WarningWidget;

public class TemperatureEnactor extends Enactor {

	public static final int TEMPERATURE_THRESHOLD = 35;
	public static final int AIR_POLLUTION_THRESHOLD = 15;
	public static final int FIRE_PRESENCE_THRESHOLD = 1;
	public TemperatureEnactor(AbstractQueryItem<?,?> inWidgetQuery, AbstractQueryItem<?,?> outWidgetQuery) {
		this(new AbstractQueryItem<?,?>[] {inWidgetQuery}, new AbstractQueryItem<?,?>[] {outWidgetQuery});
	}
	
	@SuppressWarnings("serial")
	public TemperatureEnactor(AbstractQueryItem<?,?>[] inWidgetQuery, AbstractQueryItem<?,?>[] outWidgetQuery) {
		super(inWidgetQuery, outWidgetQuery, WarningWidget.WARNING_TEMPERATURE, "");
		
		AbstractQueryItem<?, ?> warningQI = new ORQueryItem(
				RuleQueryItem.instance(
						new NonConstantAttributeElement(AttributeNameValue.instance(TemperatureWidget.TEMPERATURE, TEMPERATURE_THRESHOLD)), 
						new AttributeComparison(AttributeComparison.Comparison.GREATER)
				)
		);
		EnactorReference er = new AmbientEnactorReference( 
				warningQI, 
				WarningWidget.WARNING_ON
		);
		er.addServiceInput(new ServiceInput("TemperatureWarningService", "WarningControl",
				new Attributes() {{
					addAttribute(WarningWidget.WARNING_TEMPERATURE, Boolean.class);
				}}));
		addReference(er);
		
		er = new AmbientEnactorReference( 
				new ElseQueryItem(warningQI), 
				WarningWidget.WARNING_OFF
		);
		er.addServiceInput(new ServiceInput("TemperatureWarningService", "WarningControl",
				new Attributes() {{
					addAttribute(WarningWidget.WARNING_TEMPERATURE, Boolean.class);
				}}));
		
		addReference(er);
		
		start();
	}
	
	private class AmbientEnactorReference extends EnactorReference {

		public AmbientEnactorReference(AbstractQueryItem<?,?> conditionQuery, Boolean outcomeValue) {
			super(TemperatureEnactor.this, conditionQuery, outcomeValue.toString());
		}
		
		@Override
		protected Attributes conditionSatisfied(ComponentDescription inWidgetState, Attributes outAtts) {
			long timestamp = outAtts.getAttributeValue(Widget.TIMESTAMP);
			WidgetData data = new WidgetData("WarningWidget", timestamp);
			boolean warning;
			if (outcomeValue.equals(  String.valueOf(WarningWidget.WARNING_ON)) ) {
				warning = true;
			}
			else {
				warning = false;
			}
			
			data.setAttributeValue(WarningWidget.WARNING_TEMPERATURE, warning);
			outAtts.putAll(data.toAttributes());
			
	        return outAtts;
		}
		
	}

}
