package com.isensix.alerter;

/**
 * This class builds and launches the App.
 * 
 * @author river226
 */

import com.isensix.exceptions.*;

@SuppressWarnings("unused")
public class Initialize {

	/**
	 * Main class, uses methods to build, catches exceptions to 
	 * close program gracefully
	 */
	
	private static Alerter prog;
	
	public static void main(String[] args) {
		try {
			buildNotificationTray();
			prog.run();
		} catch (NoTrayAccessException e) {
			System.out.println(e.toString());
		} finally {
			System.out.println("Ending program");
			System.exit(0);
		}
	}

	/**
	 * This class handles building the notification tray
	 */

	private static void buildNotificationTray() throws NoTrayAccessException {
		System.out.println("start");
		prog = new Alerter();
		System.out.println("done");
	}

}
