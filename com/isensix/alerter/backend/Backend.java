package com.isensix.alerter.backend;

import java.util.*;

import javax.swing.*;

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
}