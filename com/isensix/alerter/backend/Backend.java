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
	// Reference: http://stackoverflow.com/questions/6965296/running-a-java-method-at-a-set-time-each-day
	ArrayList<AlertTimer> timers;

	public Backend() {
		timers = new ArrayList<AlertTimer>();
	}

	public void buildTimer(Alerts al) {
		timers.add(new AlertTimer(al));
	}
}

