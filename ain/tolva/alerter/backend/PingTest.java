package ain.tolva.alerter.backend;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

// Extend from thread instead
public class PingTest implements Runnable {
	
	static PopUp[] popup;
	static String[][] id;
	static Calendar cal;
	static SimpleDateFormat sdf;
	private static Thread[] notification; 

	public PingTest(String[][] addr) {
		id = addr;
		sdf = new SimpleDateFormat("MM/dd HH:mm");
		popup = new PopUp[addr.length];
		notification = new Thread[addr.length];

		for(int i = 0; i < popup.length; i++) {
			popup[i] = new PopUp(id[i][0]);
			notification[i] = new Thread(popup[i]);
		}

	}


	@SuppressWarnings("deprecation")
	public static String testPing(String name, String addr, int i) {
		cal = Calendar.getInstance();
		String results = sdf.format(cal.getTime()) + ": Sending Ping Request to " + name;

		try {
			if(InetAddress.getByName(addr).isReachable(300)) {
				results += "\t GOOD \n";
				if(notification[i].isAlive())
					notification[i].stop();
			} else {
				results += "\t --> BAD <-- \n";
				if(!notification[i].isAlive())
					notification[i].start();
			}
		} catch (Exception e) {
			// TODO do nothing for now
		}

		return results;
	}

	@Override
	public void run() {
		while(true) {
			String result = "";

			for(int i = 0; i < id.length; i++) {
				result += testPing(id[i][0], id[i][1], i);
			}

			writeLog(result);

			try { // Put program to sleep for 15 minutes
				Thread.sleep(900000);
			} catch (InterruptedException e) {
				writeLog(sdf.format(cal.getTime()) + e.toString());
				break;
			}
		}
	}

	public static boolean writeLog(String result) {
		// TODO: Write Result to file.
		System.out.print(result);
		return true;
	}

	private class PopUp implements Runnable {

		String name = "";

		public PopUp(String n) {
			name = n;
		}

		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, "Cannot connect to " + name, "No Connectivity", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
}
