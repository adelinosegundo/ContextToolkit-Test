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

public class WarningService extends Service{
	
	// package protected to be accessible to UI of HelloRoom app
		public JLabel warningLabel;

		@SuppressWarnings("serial")
		public WarningService(final Widget widget) {
			super(widget, "WarningService", 
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
			boolean warning = serviceInput.getInput().getAttributeValue("warning");
			warningLabel.setText(String.valueOf(warning));
			
			return new DataObject(); // no particular info to return
		}

}
