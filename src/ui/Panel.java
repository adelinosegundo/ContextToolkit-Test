package ui;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import context.arch.widget.Widget;
import widgets.AirPollutionWidget;
import widgets.FirePresenceWidget;
import widgets.TemperatureWidget;
import widgets.WarningWidget;

public class Panel extends JPanel {
	private JSlider temperatureSlider;
	private JSlider airPollutionSlider;
	private JSlider firePresenceSlider;
	
	private float fontSize = 20f;

	public Panel(JTextArea fireWarningTextArea, JTextArea airPollutionWarningTextArea, JTextArea temperatureWarningTextArea, final Widget temperatureWidget, final Widget airPollutionWidget, final Widget firePresenceWidget) {			
		setLayout(new GridLayout(3, 3)); // 3 rows, 2 columns
		
		add(new JLabel(TemperatureWidget.TEMPERATURE) {{ setFont(getFont().deriveFont(fontSize)); }});
		add(temperatureSlider = new JSlider(new DefaultBoundedRangeModel(0, 0, 0, 40)) {{
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					int temperature = (int)temperatureSlider.getValue();
					
					temperatureWidget.updateData(TemperatureWidget.TEMPERATURE, temperature);
					// set color to represent brightness level
					double double_temperature = temperature;
					Double red_value= (double_temperature/40)*255;
					Double other_values= (1.0-(double_temperature/40))*255;
					setBackground(new Color(red_value.intValue(), other_values.intValue(), other_values.intValue()));
				}
			});
			setOpaque(true); // to allow background color to show
			setMajorTickSpacing(10);
			setPaintTicks(true);
			setPaintLabels(true);
		}});
		add(temperatureWarningTextArea);		
		
		add(new JLabel(AirPollutionWidget.AIR_POLLUTION) {{ setFont(getFont().deriveFont(fontSize)); }});
		add(airPollutionSlider = new JSlider(new DefaultBoundedRangeModel(0, 0, 0, 40)) {{
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					int airPollution = (int)airPollutionSlider.getValue();
					
					airPollutionWidget.updateData(AirPollutionWidget.AIR_POLLUTION, airPollution);
					
					// set color to represent brightness level
					double double_temperature = airPollution;
					Double red_value= (double_temperature/40)*255;
					Double other_values= (1.0-(double_temperature/40))*255;
					setBackground(new Color(red_value.intValue(), other_values.intValue(), other_values.intValue()));
				}
			});
			setOpaque(true); // to allow background color to show
			setMajorTickSpacing(10);
			setPaintTicks(true);
			setPaintLabels(true);
		}});
		add(airPollutionWarningTextArea);
		
		add(new JLabel(FirePresenceWidget.FIRE_PRESENCE) {{ setFont(getFont().deriveFont(fontSize)); }});
		add(firePresenceSlider = new JSlider(new DefaultBoundedRangeModel(0, 0, 0, 1)) {{
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					int firePresence = (int)firePresenceSlider.getValue();
					
					firePresenceWidget.updateData(FirePresenceWidget.FIRE_PRESENCE, firePresence);				}
			});
			setOpaque(true); // to allow background color to show
			setMajorTickSpacing(1);
			setPaintTicks(true);
			setPaintLabels(true);
		}});
		add(fireWarningTextArea);
		/*
		 * Init state of widgets
		 */
		int temperature = (int)temperatureSlider.getValue();
		temperatureWidget.updateData(TemperatureWidget.TEMPERATURE, temperature);
		
		int airPollution = (int)airPollutionSlider.getValue();
		airPollutionWidget.updateData(AirPollutionWidget.AIR_POLLUTION, airPollution);
		
		int firePresence = (int)firePresenceSlider.getValue();
		firePresenceWidget.updateData(FirePresenceWidget.FIRE_PRESENCE, firePresence);
	}
}
