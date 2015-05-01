package com.isensix.alerter;

/**
 * This is the main class, builds out the app, and launces the GUI as well
 * as all other components and classes that are necessary.
 */

// Java Imports
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.ArrayList;
import javax.swing.JFrame;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

// local import
import com.isensix.exceptions.NoTrayAccessException;

@SuppressWarnings("unused")
public class NotificationTray {

	// code adapted from: https://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
	// TODO add Tray icon

	Menu displayMenu = new Menu("Display");
	AlertFile file; // Alert file
	AlertSettings set; // GUI to add and edit alerts
	ArrayList<Alerts> alerts = new ArrayList<Alerts>();

	// Construct the System Tray
	public NotificationTray () throws NoTrayAccessException {
		if(test()) try { run();  // Builds the app
		} catch (AWTException e)
		{ throw new NoTrayAccessException("Exception Thrown\n"  + e.getMessage()); }
		else throw new NoTrayAccessException("No System Support");
	}


	private boolean test() { // Tests if System allows access to the System Tray
		return SystemTray.isSupported();
	}


	public void run() throws AWTException {

		file = new AlertFile();

		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon(createImage("media/becon.gif", "tray icon"));
		final SystemTray tray = SystemTray.getSystemTray();

		// Create a pop-up menu components
		CheckboxMenuItem enabled = new CheckboxMenuItem("Alerts enabled");
		MenuItem alerts = new MenuItem("Programmed Alerts");


		//Add components to pop-up menu
		popup.add(enabled);
		popup.addSeparator();
		displayMenu.add(alerts);

		trayIcon.setPopupMenu(popup);

		tray.add(trayIcon); //Throw AWTException
	}

	private Image createImage(String path, String description) {
		java.net.URL imageURL = NotificationTray.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

	private boolean alertCheck() {
		return file.doesFileExist();
	}

	private void enableGUI() {
		set = new AlertSettings(file, alerts);
		set.setVisible(true);
	}

	private ArrayList<Alerts> createAlerts() {
		if(!alertCheck()) enableGUI();
		return file.generateAlerts();
	}

}
