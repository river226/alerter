package com.isensix.alerter.backend;

/**
 * This class handles reading and writing files in the correct format
 * It also produces require alerts
 */

//import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.List;

import com.isensix.alerter.Alerts;

@SuppressWarnings("unused")
public class AlertFile {

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
