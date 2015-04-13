package com.isensix.alerter;

/**
 * This class builds and launches the App.
 */

import com.isensix.exceptions.*;

public class Main {

	/**
	 * Main class, uses methods to build, catches exceptions to 
	 * close program gracefully
	 */
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
		NotificationTray prog = new NotificationTray();
	}

}
