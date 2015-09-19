package notification;

import java.util.ArrayList;

public class SMSNotifier implements Notifier{

	@Override
	public void sendNotification() {
		System.out.println("Sended sms");
	}
}
