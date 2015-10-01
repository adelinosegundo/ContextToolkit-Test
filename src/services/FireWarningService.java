package services;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;
import widgets.WarningWidget;

public class FireWarningService extends Service {
	public JLabel warningLabel;

	@SuppressWarnings("serial")
	public FireWarningService(final Widget widget) {
		super(widget, "FireWarningService", 
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription(
								"WarningControl", 
								"Warns responsible for ambient", 
								widget.getNonConstantAttributes()));
					}
				});
		
		/*
		 * set up light label (for use in a UI)
		 */
		warningLabel = new JLabel("0") {{
			setHorizontalAlignment(JLabel.RIGHT);
			setBorder(BorderFactory.createEtchedBorder());
			
			setOpaque(true); // to allow background color to show
			// set color to represent light level
			setBackground(Color.white); // initially dark
		}};
	}
	
	@Override
	public DataObject execute(ServiceInput serviceInput) {
		boolean warning_value = serviceInput.getInput().getAttributeValue(WarningWidget.WARNING_FIRE);
		warningLabel.setText(String.valueOf(warning_value));	
		return new DataObject(); // no particular info to return
	}
}
