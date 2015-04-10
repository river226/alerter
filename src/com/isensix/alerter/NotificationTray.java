package com.isensix.alerter;

// Java Imports
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

// Local Imports
import com.isensix.alerter.exceptions.*;
import com.isensix.media.*;

public class NotificationTray {

	// code adapted from: https://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html

	 // Construct the System Tray
    public NotificationTray () throws NoTrayAccessException {
    	if(test()) try { run();  // Builds the app
			} catch (AWTException e) { throw new NoTrayAccessException("Exception Thrown\n"  + e); }
    	else throw new NoTrayAccessException("No System Support");
    }


    private boolean test() { // Tests if System allows access to the System Tray
		return SystemTray.isSupported();
	}


	public void run() throws AWTException {

        final PopupMenu popup = new PopupMenu();
        //final TrayIcon trayIcon = new TrayIcon(createImage("images/bulb.gif", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        CheckboxMenuItem enabled = new CheckboxMenuItem("Alerts enabled");
        MenuItem alerts = new MenuItem("Programmed Alerts");


        //Add components to pop-up menu
        popup.add(enabled);
        popup.addSeparator();
        displayMenu.add(Alerts);

        trayIcon.setPopupMenu(popup);

       	//tray.add(trayIcon); //Throw AWTException
    }

}
