package com.isensix.alerter.backend;

import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.isensix.alerter.*;

@SuppressWarnings("unused")
public class Backend {
	// TODO set up timer funtions
	// Reference: http://stackoverflow.com/questions/6965296/running-a-java-method-at-a-set-time-each-day
	ArrayList<AlertTimer> timers;

	public Backend() {
		timers = new ArrayList<AlertTimer>();
	}

	public void buildTimer(Alerts al) {
		timers.add(new AlertTimer(al));
	}

	private class AlertFile {

		private String filename;
		private ArrayList<String> lines;
		private Charset charset;

		public AlertFile() {
			filename = "alert.alt";
		}

		/**
		 * Read in the file and separate into several lines
		 */
		private boolean readFile() {
			if(!doesFileExist()) return false;

			Path path = Paths.get(filename);

			try {
				lines = (ArrayList<String>) Files.readAllLines(path, charset);
			} catch (IOException e) {
				return false;
			}

			return true;
		}

		/**
		 * Write alerts to the alert file
		 */
		public boolean writeFile(Alerts a) {
			if(!doesFileExist()) {
				File f = new File(filename);
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String alertString = a.toString();
			lines.add(alertString);

			PrintWriter writer;

			try {
				writer = new PrintWriter(filename, "UTF-8");

				for(int i = 0; i < lines.size(); i++)
					writer.println(lines.get(i));

				writer.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}

		/**
		 * Test if file exists
		 */
		public boolean doesFileExist() {
			File f = new File(filename); // TODO Make s
			return f.exists() && !f.isDirectory();
		}

		/**
		 * Build a list of alerts from Alert File
		 */
		public ArrayList<Alerts> generateAlerts() {
			if (!readFile()) return null; // returns null if no file exits

			ArrayList<Alerts> a = new ArrayList<Alerts>();

			for(int i = 0; i < lines.size(); i++) {
				Alerts b = buildAlert(lines.get(i));
				if(b != null) a.add(b); // only add valid alerts
			}

			return a;
		}

		/**
		 * This handles parsing a String and building an alert
		 */
		private Alerts buildAlert(String alert) {
			// verify that the string has a value
			if(alert == null || alert.isEmpty()) return null;

			String[] a = alert.split("::"); // Split up at designated seperator
			int t;

			if(a.length != 9 || a[0].length() != 4  || a[1].length() != 1
					|| a[2].length() != 1 || a[3].length() != 1 || a[4].length() != 1
					|| a[5].length() != 1 || a[6].length() != 1 || a[7].length() != 1) 
				return null; // confirm that there are no invalid string lengths

			try { // generate time value
				t = Integer.parseInt(a[0]);
			} catch(Exception e) {
				return null;
			}

			// gather day values
			boolean[] d = new boolean[7];
			for(int i = 1; i < 8; i++) {
				if(a[i].equals("1")) d[i-1] = true;
				else if(a[i].equals("0")) d[i-1] = false;
				else return null;
			}

			return new Alerts(t, d, a[8]);
		}
	}

	private class AlertTimer extends TimerTask {

		private Calendar next;
		private String message;

		/**
		 * 
		 * @param n
		 * @param m
		 */
		private AlertTimer(Calendar n, String m) {
			next = n;
			message = m;
		}

		/**
		 * 
		 * @param al
		 */
		public AlertTimer(Alerts al) {
			ArrayList<Timer> timers = new ArrayList<Timer>();
			ArrayList<Calendar> dates = new ArrayList<Calendar>();

			/*MONDAY*/ if(al.isThisDay(0)) dates.add(this.setAlertDate(Calendar.MONDAY, al.getTime()));
			/*TUESDAY*/ if(al.isThisDay(1)) dates.add(this.setAlertDate(Calendar.TUESDAY, al.getTime()));
			/*WEDNESDAY*/ if(al.isThisDay(2)) dates.add(this.setAlertDate(Calendar.WEDNESDAY, al.getTime()));
			/*THURSDAY*/ if(al.isThisDay(3)) dates.add(this.setAlertDate(Calendar.THURSDAY, al.getTime()));
			/*FRIDAY*/ if(al.isThisDay(4)) dates.add(this.setAlertDate(Calendar.FRIDAY, al.getTime()));
			/*SATURDAY*/ if(al.isThisDay(5)) dates.add(this.setAlertDate(Calendar.SATURDAY, al.getTime()));
			/*SUNDAY*/ if(al.isThisDay(6)) dates.add(this.setAlertDate(Calendar.SUNDAY, al.getTime()));

			for(int i = 0; i < dates.size(); i++) {
				Timer temp = new Timer();
				temp.schedule(new AlertTimer(dates.get(i), al.getMessage()), dates.get(i).getTime());
				timers.add(temp);
			}
		}

		/**
		 * 
		 * @param day
		 * @param time
		 * @return
		 */
		private Calendar setAlertDate(int day, int time) {
			GregorianCalendar date = new GregorianCalendar();
			date.set(Calendar.DAY_OF_WEEK, day);
			return setTime(date, time);
		}

		/**
		 * 
		 * @param c
		 * @param t
		 * @return
		 */
		private Calendar setTime(Calendar c, int t) {
			c.set(Calendar.HOUR_OF_DAY, t/100);
			c.set(Calendar.MINUTE, t%100);
			return c;
		}

		/**
		 * 
		 */
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, message, "alert", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

