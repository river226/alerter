package com.isensix.alerter;

import com.isensix.alerter.exceptions.NoTrayAccessException;

public class Main {

	public static void main(String[] args) {
			try {
				buildGUI();
				buildNotificationTray();
			} catch (NoTrayAccessException e) {
				// Do nothing for now
			} finally {
				System.exit(0);
			}
	}

	private static void buildGUI() {
		// TODO check to see if settings have been made
		// GUI settings = new GUI();
	}

	private static void buildNotificationTray() throws NoTrayAccessException {
		NotificationTray prog = new NotificationTray();
	}

}
