package com.isensix.alerter;

/**
 * This class builds and launches the App.
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
		} catch (NoTrayAccessException e) {
			// Do nothing for now
		} finally {
			System.exit(0);
		}
	}

	/**
	 * This class handles building the notification tray
	 */

	private static void buildNotificationTray() throws NoTrayAccessException {
		prog = new Alerter();
	}

}
