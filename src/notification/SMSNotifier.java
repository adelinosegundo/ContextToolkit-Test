package notification;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SMSNotifier implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("sendSMS");
	}
}
