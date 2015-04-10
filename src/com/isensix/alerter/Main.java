package com.isensix.alerter;

import com.isensix.alerter.exceptions.NoTrayAccessException;

public class Main {

	public static void main(String[] args) {
			try {
				buildGUI();
				buildNotificationTray();
			} finally {
				System.exit(0);
			}
	}

	private void buildGUI() {
		// TODO check to see if settings have been made
		GUI settings = new GUI();
	}

	private void buildNotificationTray() throws NoTrayAccessException {
		NotificationTray prog = new NotificationTray();
	}

}
