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
    	if(test()) { 
    		try {
    			run();  // Builds the app
    		} catch (AWTException e) {
    			throw new NoTrayAccessException();
    		}
    	}
    	else {
    		throw new NoTrayAccessException();
    	}
    }
    
    
    private boolean test() { // Tests if System allows access to the System Tray
		return SystemTray.isSupported();
	}


	public void run() throws AWTException {
 
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("images/bulb.gif", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
       
        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(errorItem);
        displayMenu.add(warningItem);
        displayMenu.add(infoItem);
        displayMenu.add(noneItem);
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
       
        
       tray.add(trayIcon);
        
    }
	
}
