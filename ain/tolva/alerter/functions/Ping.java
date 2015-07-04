package ain.tolva.alerter.functions;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public abstract class Ping extends Thread {
	
	public void intialize(boolean repeat) {
		
	}

	static boolean test;
	static String name;
	static String id;
	static Calendar cal;
	static SimpleDateFormat sdf;
	
	public Ping(String n, String a) {
		// Assign name, and Address
		name = n;
		id = a;
		
		// Magic Values
		sdf = new SimpleDateFormat("MM/dd HH:mm");
		test = true;
	}


	public String testPing() {
		cal = Calendar.getInstance();
		String results = sdf.format(cal.getTime()) + ": Sending Ping Request to " + name;

		try {
			InetAddress.getByName(id);
			results += "\t GOOD \n";
			test = true;
		} catch (Exception e) {
			results += "\t --> BAD <-- \n" + e.toString();
			if(test)
				JOptionPane.showMessageDialog(null, "Cannot connect to " + name, "No Connectivity", JOptionPane.INFORMATION_MESSAGE);
		}

		return results;
	}

	@Override
	public abstract void run();
	
	public boolean writeLog(String result) {
		// TODO: Write Result to file. 
		System.out.print(result);
		return true;
	}
	
	public void alert() {
		// TODO: Alert user
	}
}
