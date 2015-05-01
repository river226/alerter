package com.isensix.alerter;

/**
 * This class builds a basic GUI with a List area listing all current alerts
 * and a config area that is used to edit and add alerts.
 */

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Checkbox;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("unused")
public class AlertSettings extends JFrame{

	/**
	 * make eclipse happy
	 */
	private static final long serialVersionUID = 1L;
	// Define classes that can change
	private JFrame frame;
	private JList<String> list;
	private ArrayList<Alerts> alerts;
	private GridLayout layout;
	private ArrayList<JCheckBox> dayCheck;
	private JButton add;
	private JTextField messageBox;
	private JSpinner timeBox; // Box set to accept
	private SpinnerNumberModel timeSpinner; // Spinner Settings for JSpinner
	
	private ArrayList<JRadioButton> timeRadio;
	private ButtonGroup timeGroup;

	// Define final values
	private final JLabel aLabel = new JLabel("Alerts");
	private final JLabel mLabel = new JLabel("Messages");
	private final String[] dayLabel = {"M", "Tu", "W", "Tr", "F", "Sa", "Su"};
	private final String[] timeLabel = {"Time", "am", "pm", "24hr"};

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */
	
	/**
	 * Create the application.
	 * @param a 
	 */
	public AlertSettings(AlertFile f, ArrayList<Alerts> a) {
		AlertFile file = f;
		initialize();
	}

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
		int winHeight = 500;

		frame = new JFrame();
		frame.setBounds(((monWidth/2)-(winWidth/2)), ((monHeight/2)-(winHeight/2)), // center window
		 	winWidth, winHeight); // size windowIa
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(layout);
		buildGridLayout();

	}

	private Component buildList() {
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				changeConfig();
			}
		});
		//list.add(, comp)
		return aLabel;
	}

	private void buildGridLayout() {
		layout = new GridLayout(4, 1, 10, 10);
		layout.addLayoutComponent("Alert List", buildList()); // Alerts List
		layout.addLayoutComponent("Message", buildMessagebox()); // Alert Message
		layout.addLayoutComponent("Days", buildDays()); // Mon - Sun
		layout.addLayoutComponent("Time", buildTime()); // Time, Submit
	}

	private Component buildTime() {
		JPanel time = new JPanel(new FlowLayout());
		timeRadio = new ArrayList<JRadioButton>();
		timeSpinner = new SpinnerNumberModel(0, 0, 2400, 1);
		timeBox = new JSpinner(timeSpinner);
		
		timeRadio.add(new JRadioButton(timeLabel[1], false));
		timeRadio.add(new JRadioButton(timeLabel[2], false));
		timeRadio.add(new JRadioButton(timeLabel[3], true));
		
		time.add(new JLabel(timeLabel[0]));
		time.add(timeBox);
		time.add(timeRadio.get(0));
		time.add(timeRadio.get(1));
		time.add(timeRadio.get(2));
		
		return time;
	}

	private Component buildDays() {
		// TODO Auto-generated method stub
		JPanel days = new JPanel(new FlowLayout());
		dayCheck = new ArrayList<JCheckBox>();
		
		dayCheck.add(new JCheckBox(dayLabel[0]));
		dayCheck.add(new JCheckBox(dayLabel[1]));
		dayCheck.add(new JCheckBox(dayLabel[2]));
		dayCheck.add(new JCheckBox(dayLabel[3]));
		dayCheck.add(new JCheckBox(dayLabel[4]));
		dayCheck.add(new JCheckBox(dayLabel[5]));
		dayCheck.add(new JCheckBox(dayLabel[6]));
		
		days.add(dayCheck.get(0));
		days.add(dayCheck.get(1));
		days.add(dayCheck.get(2));
		days.add(dayCheck.get(3));
		days.add(dayCheck.get(4));
		days.add(dayCheck.get(5));
		days.add(dayCheck.get(6));
		
		return days;
	}

	private Component buildMessagebox() {
		JPanel mBox = new JPanel(new FlowLayout());
		messageBox = new JTextField();
		
		mBox.add(mLabel);
		mBox.add(messageBox);
		
		return mBox;
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
