package com.isensix.alerter;

/**
 * This class builds a basic GUI with a List area listing all current alerts
 * and a config area that is used to edit and add alerts.
 */

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Checkbox;

import java.util.ArrayList;

import javax.swing.event;
import javax.swing.event.ListSelectionEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout;

@SuppressWarnings("unused")
public class AlertSettings {

	// Define classes that can change
	private JFrame frame;
	private JList<String> list;
	private ArrayList<Alerts> alerts;
	private GroupLayout layout;
	private Checkbox[] dayCheck;
	private JButton add;
	private JTextField messageBox;
	private JTextField timeBox;

	// Define final values
	private final JLabel aLabel = new JLabel("Alerts");
	private final JLabel mLabel = new JLabel("Messages");
	private final JLabel[] dayLabel = [new JLabel("M"),
		new JLabel("Tu"), new JLabel("W"), new JLabel("Tr"),
		new JLabel("F"), new JLabel("Sa"), new JLabel("Su")];
	private final JLabel[] timeLabel = [new JLabel("Time"),
		new JLabel("am"), new JLabel("pm"), new JLabel("24hr")];

	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlertSettings window = new AlertSettings();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the application.
	 */
	public AlertSettings(ArrayList<Alerts> al) {
		alerts = al;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monWidth = gd.getDisplayMode().getWidth();
		int monHeight = gd.getDisplayMode().getHeight();

		int winWidth = 450;
		int winHeight = 300;

		frame = new JFrame();
		frame.setBounds(((monWidth/2)-(winWidth/2)), ((monHeight/2)-(winHeight/2)), // center window
		 	winWidth, winHeight); // size windowIa
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.buildList();

	}

	private void buildList() {
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListiner(){
			public valueChanged(ListSelectionEvent e) {
				changeConfig();
			}
		});
	}

	private GridLayout buildGridLayout() {
		// TODO Build the layout
		return null;
	}

	private void changeConfig() {
		// TODO Change config area to match selected alert
	}

	private void builtConfig() {
		// TODO Layout the config area
	}

	private void addAlert() {
		// TODO import data from config area to create a new alert, and add to list
	}

}
