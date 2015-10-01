package services;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;
import widgets.WarningWidget;

public class FireWarningService extends Service {
	public JTextArea warningTextArea;

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
		warningTextArea = new JTextArea("") {{
			setBorder(BorderFactory.createEtchedBorder());
			setEditable(false);
			setAutoscrolls(true);
			setOpaque(true); // to allow background color to show
			// set color to represent light level
			setBackground(Color.white); // initially dark
		}};
	}
	
	@Override
	public DataObject execute(ServiceInput serviceInput) {
		String warning_value = String.valueOf(serviceInput.getInput().getAttributeValue(WarningWidget.WARNING_FIRE));
		
		int last  = warningTextArea.getLineCount() - 1;
		int start;
		int end;
		String lastLine = "";
		
		try {
			if (last > 0){
				start = warningTextArea.getLineStartOffset(last-1);
				end = warningTextArea.getLineEndOffset(last);
				lastLine = warningTextArea.getText().substring(start, end-1);
			}	
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!warning_value.equals(lastLine))
			warningTextArea.append(warning_value + "\n");	
		
		return new DataObject(); // no particular info to return
	}
}
