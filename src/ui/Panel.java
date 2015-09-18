package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import context.arch.widget.Widget;
import widgets.AmbientWidget;
import widgets.WarningWidget;

public class Panel extends JPanel {
	private JSlider temperatureSlider;
	private JSlider airPollutionSlider;
	
	private float fontSize = 20f;

	public Panel(JLabel warningLabel, final Widget ambientWidget) {			
		setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns
		
		add(new JLabel(AmbientWidget.TEMPERATURE) {{ setFont(getFont().deriveFont(fontSize)); }});
		add(temperatureSlider = new JSlider(new DefaultBoundedRangeModel(0, 0, 0, 40)) {{
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					int temperature = (int)temperatureSlider.getValue();
					
					ambientWidget.updateData(AmbientWidget.TEMPERATURE, temperature);
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
		
		add(new JLabel(AmbientWidget.AIR_POLLUTION) {{ setFont(getFont().deriveFont(fontSize)); }});
		add(airPollutionSlider = new JSlider(new DefaultBoundedRangeModel(0, 0, 0, 40)) {{
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					int airPollution = (int)airPollutionSlider.getValue();
					
					ambientWidget.updateData(AmbientWidget.AIR_POLLUTION, airPollution);
					
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
		
		add(new JLabel(WarningWidget.WARNING) {{ setFont(getFont().deriveFont(fontSize)); }});
		add(warningLabel);
		/*
		 * Init state of widgets
		 */
		int temperature = (int)temperatureSlider.getValue();
		ambientWidget.updateData(AmbientWidget.TEMPERATURE, temperature);
		
		int airPollution = (int)airPollutionSlider.getValue();
		ambientWidget.updateData(AmbientWidget.AIR_POLLUTION, airPollution);
	}
}
