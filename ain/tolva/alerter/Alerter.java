package ain.tolva.alerter;

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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

import ain.tolva.alerter.GUI.AlertSettings;
import ain.tolva.alerter.backend.AlertFile;
import ain.tolva.alerter.backend.NotificationPane;
import ain.tolva.alerter.backend.PingTest;
// local import
import ain.tolva.alerter.exceptions.*;

import com.glavsoft.viewer.Viewer;
import com.glavsoft.viewer.cli.*;

@SuppressWarnings("unused")
public class Alerter extends Thread {

	// code adapted from: https://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
	// TODO add Tray icon

	// Flexible Variables
	private Menu displayMenu = new Menu("Display");
	private AlertFile file; // Alert file
	private AlertSettings set; // GUI to add and edit alerts
	private ArrayList<Alerts> alerts = new ArrayList<Alerts>();
	private PingTest autoPing;

	// Testing Variables
	String[][] testarray = {
			{"google", "www.google.com"}
	};

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

		// Create a popup menu components
		//MenuItem ccItem = new MenuItem("Control Center");
		Menu alertMenu = new Menu("Alerts");
		MenuItem amItem1 = new MenuItem("Alert Settings");
		// TODO: add Checkbox items for alerts
		Menu netMenu = new Menu("Network");
		final MenuItem ipPingItem = new MenuItem("Ping");
		final MenuItem autoPingItem = new MenuItem("Auto Ping");
		final MenuItem openVNC = new MenuItem("Open VNC");
		MenuItem exitItem = new MenuItem("Exit");

		//Add components to popup menu
		//popup.add(ccItem);
		popup.add(alertMenu);
		alertMenu.add(amItem1);
		popup.addSeparator();
		popup.add(openVNC);
		popup.addSeparator();
		popup.add(netMenu);
		netMenu.add(ipPingItem);
		netMenu.add(autoPingItem);
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);
		tray.add(trayIcon);

		// TODO implement action listeners
		
		openVNC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Viewer vn = new Viewer();
			}
		});

		// Ping Menu
		ipPingItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				NotificationPane n = new NotificationPane(NotificationPane.ALERT, "This is a Test", "Test");
				n.run();
			}
		});
		autoPingItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoPing.run();
			}
		});


		// Alerter Menu
		amItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				NotificationPane n = new NotificationPane(NotificationPane.ALERT, "This is a Test", "Test");
				n.run();
			}
		});

		// Exit
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
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
