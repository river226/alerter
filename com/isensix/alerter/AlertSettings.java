package com.isensix.alerter;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

@SuppressWarnings("unused")
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
		int winHeight = 300;

		frame = new JFrame();
		frame.setBounds(((monWidth/2)-(winWidth/2)), ((monHeight/2)-(winHeight/2)), // center window
		 	winWidth, winHeight); // size window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
