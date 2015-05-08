package com.isensix.alerter;

/**
 * This is the main class, builds out the app, and launces the GUI as well
 * as all other components and classes that are necessary.
 * 
 * @author river226
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

import com.isensix.alerter.GUI.AlertSettings;
import com.isensix.alerter.backend.AlertFile;
// local import
import com.isensix.exceptions.NoTrayAccessException;

@SuppressWarnings("unused")
public class Alerter implements Runnable {

	// code adapted from: https://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
	// TODO add Tray icon

	// Flexible Variables
	private Menu displayMenu = new Menu("Display");
	private AlertFile file; // Alert file
	private AlertSettings set; // GUI to add and edit alerts
	private ArrayList<Alerts> alerts = new ArrayList<Alerts>();
	
	// Final Global Variables
	private final PopupMenu popup = new PopupMenu();
	private final TrayIcon trayIcon = new TrayIcon(createImage("media/alert.gif", "tray icon"));
	private final SystemTray tray = SystemTray.getSystemTray();

	/**
	 * This Constructor manages exceptions for the program, and launches the program
	 * @throws NoTrayAccessException
	 */
	public Alerter () throws NoTrayAccessException {
		if(!test()) { // The System Tray is not supported
			throw new NoTrayAccessException("No System Support");
		}
	}

	/**
	 * Test if the system allows you to access to the System Tray
	 * @return boolean telling you if system allows access to System Tray
	 */
	private boolean test() { // Tests if System allows access to the System Tray
		return SystemTray.isSupported();
	}

	/**
	 * This launches and builds the Application
	 * @throws AWTException
	 * @throws InterruptedException 
	 */
	public void launch() throws AWTException, InterruptedException {
		System.out.println("launch");
		
		file = new AlertFile();

		// Create a pop-up menu components
		CheckboxMenuItem enabled = new CheckboxMenuItem("Alerts enabled");
		MenuItem alerts = new MenuItem("Programmed Alerts");

		//Add components to pop-up menu
		popup.add(enabled);
		popup.addSeparator();
		displayMenu.add(alerts);
		trayIcon.setPopupMenu(popup);
		tray.add(trayIcon);
	}

	/**
	 * Manages creating the Tray Icon
	 * @param path - File path for the Tray Icon
	 * @param description - Describes the icon being built
	 * @return Image file of the Tray Icon
	 */
	private Image createImage(String path, String description) {
		java.net.URL imageURL = Alerter.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

	/**
	 * Checks if there is any alert file saved
	 * @return true if there is an alert file
	 */
	private boolean alertCheck() {
		return file.doesFileExist();
	}

	/**
	 * Build and launch the GUI to have change settings;
	 * edit alerts
	 */
	private void enableGUI() {
		set = new AlertSettings(file, alerts);
		set.setVisible(true);
	}

	/**
	 * Launches GUI if there are no Alerts, and generate all Alerts
	 * @return ArrayList containing built alerts
	 */
	private ArrayList<Alerts> createAlerts() {
		if(!alertCheck()) enableGUI();
		return file.generateAlerts();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("run");
			launch();
		} catch (AWTException | InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
}
