package com.isensix.alerter;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class AlertSettings {

	private JFrame frame;

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
	public AlertSettings() {
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
		int winWidth = 300;

		frame = new JFrame();
		frame.setBounds(((monWidth/2)-(winWidth/2)), ((monHeight/2)-(winHeight/2)), // center window
		 	winWidth, winHeight); // size windowIa
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
