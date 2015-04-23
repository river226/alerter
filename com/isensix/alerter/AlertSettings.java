package com.isensix.alerter;

/**
 * This class builds a basic GUI with a List area listing all current alerts
 * and a config area that is used to edit and add alerts. 
 */

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.util.ArrayList;

import javax.swing.event;
import javax.swing.event.ListSelectionEvent;

import javax.swing.JFrame;
import javax.swing.JList;

@SuppressWarnings("unused")
public class AlertSettings {

	private JFrame frame;
	private JList<String> list;
	private ArrayList<Alerts> alerts;

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
