package com.isensix.alerter;

import com.isensix.alerter.exceptions.NoTrayAccessException;

public class Main {

	public static void main(String[] args) {
		
		try {
			NotificationTray prog = new NotificationTray();
		} catch (NoTrayAccessException e) {
			e.printStackTrace();
		} finally {
			// Close the application
		}
	}

}
