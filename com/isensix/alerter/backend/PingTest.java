package com.isensix.alerter.backend;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Extend from thread instead
public class PingTest implements Runnable {
	
	static String[][] id;
	static Calendar cal;
	static SimpleDateFormat sdf;
	
	public PingTest(String[][] addr) {
		id = addr;
		sdf = new SimpleDateFormat("MM/dd HH:mm");
	}


	public static String testPing(String name, String addr) {
		cal = Calendar.getInstance();
		String results = sdf.format(cal.getTime()) + ": Sending Ping Request to " + name;

		try {
			InetAddress.getByName(addr);
			results += "\t GOOD \n";
		} catch (Exception e) {
			results += "\t --> BAD <-- \n" + e.toString();
		}

		return results;
	}

	@Override
	public void run() {
		while(true) {
			String result = "";

			for(int i = 0; i < id[0].length; i++) {
				result += testPing(id[i][0], id[i][1]);
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
	
}
