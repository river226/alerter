package ain.tolva.alerter.functions;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public abstract class Ping extends Thread {
	
	public void intialize(boolean repeat) {
		
	}

	static boolean[] test;
	static String[][] id;
	static Calendar cal;
	static SimpleDateFormat sdf;
	
	public Ping(String[][] addr) {
		id = addr;
		sdf = new SimpleDateFormat("MM/dd HH:mm");
		test = new boolean[addr[0].length];
		
		for(int i = 0; i < test.length; i++)
			test[i] = true;
	}


	public static String testPing(String name, String addr, int i) {
		cal = Calendar.getInstance();
		String results = sdf.format(cal.getTime()) + ": Sending Ping Request to " + name;

		try {
			InetAddress.getByName(addr);
			results += "\t GOOD \n";
			test[i] = true;
		} catch (Exception e) {
			results += "\t --> BAD <-- \n" + e.toString();
			if(test[i])
				JOptionPane.showMessageDialog(null, "Cannot connect to " + name, "No Connectivity", JOptionPane.INFORMATION_MESSAGE);
		}

		return results;
	}

	@Override
	public abstract void run();
		/*while(true) {
			String result = "";

			for(int i = 0; i < id[0].length; i++) {
				result += testPing(id[i][0], id[i][1], i);
			}
			
			writeLog(result);
			
			try { // Put program to sleep for 15 minutes
				Thread.sleep(900000);
			} catch (InterruptedException e) {
				writeLog(sdf.format(cal.getTime()) + e.toString());
				break;
			}
		}*/
	
	public static boolean writeLog(String result) {
		// TODO: Write Result to file. 
		System.out.print(result);
		return true;
	}
}
