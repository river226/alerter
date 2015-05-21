package ain.tolva.alerter.backend;

import javax.swing.JOptionPane;

public class NotificationPane implements Runnable {
	
	// JOptionPane Types
	public static final int ALERT = 0;
	public static final int INPUT = 1;
	// Add other in the future
	
	private final int type;
	private String message;
	private String title;
	
	public NotificationPane(int t, String m, String ti) {
		type = t;
		message = m;
		title = ti;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch(type) {
		case ALERT:
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
			break;
		case INPUT:
			break;
		default:
			break;
		}
		
	}
	
	public void closePane() {
		System.exit(0);
	}
	
}
