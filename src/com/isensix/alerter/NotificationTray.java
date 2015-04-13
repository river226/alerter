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
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

// Local Imports
import com.isensix.alerter.exceptions.*;
import com.isensix.media.*;

public class NotificationTray {

		// code adapted from: https://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
		// TODO add Tray icon

	Menu displayMenu = new Menu("Display");
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

	private ArrayList<Alerts>() alertCheck() {
		// if(/* alerts file doesn't exist */)
			enableGUI();

		return createAlerts();
	}

	private void enableGUI() {
		// Build GUI to add alerts to alert file
	}

	private ArrayList<Alerts>() createAlerts() {
		// Read in alert file, and build alerts
	}

}
