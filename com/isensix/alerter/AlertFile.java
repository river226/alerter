package com.isensix.alerter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AlertFile {

	private String filename;
	private ArrayList<String> lines;

	public AlertFile() {
		filename = "alert.alt";
	}

	public boolean readFile() {
		if(!doesFileExist()) return false;

		Path path = Paths.get(filename);

		try {
			lines = (ArrayList<String>)Files.readAllLines(path);
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public boolean doesFileExist() {
		File f = new File(filename);
		return f.exists() && !f.isDirectory();
	}

	public ArrayList<Alerts> generateAlerts() {
		if (!readFile()) return null;
		
		ArrayList<Alerts> a = new ArrayList<Alerts>();
		
		for(int i = 0; i < lines.size(); i++)
			a.add(buildAlert(lines.get(i)));
		
		return a;
	}

	private Alerts buildAlert(String alert) {
		// TODO Auto-generated method stub
		return null;
	}
}
