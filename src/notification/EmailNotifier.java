package notification;

import java.util.ArrayList;

public class EmailNotifier implements Notifier {

	@Override
	public void sendNotification() {
		System.out.println("Sended email");
	}

}
