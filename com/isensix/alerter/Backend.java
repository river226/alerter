package com.isensix.alerter;

import java.util.*;
import javax.swing.*;

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

	private class AlertTimer extends TimerTask {

		private Calendar next;
		private String message;

		private AlertTimer(Calendar n, String m) {
			next = n;
			message = m;
		}

		public AlertTimer(Alerts al) {
			ArrayList<Timer> timers = new ArrayList<Timer>();
			ArrayList<Calendar> dates = new ArrayList<Calendar>();

			if(al.isThisDay(0)) dates.add(this.setAlertDate(Calendar.MONDAY, al.getTime()));
			if(al.isThisDay(1)) dates.add(this.setAlertDate(Calendar.TUESDAY, al.getTime()));
			if(al.isThisDay(2)) dates.add(this.setAlertDate(Calendar.WEDNESDAY, al.getTime()));
			if(al.isThisDay(3)) dates.add(this.setAlertDate(Calendar.THURSDAY, al.getTime()));
			if(al.isThisDay(4)) dates.add(this.setAlertDate(Calendar.FRIDAY, al.getTime()));
			if(al.isThisDay(5)) dates.add(this.setAlertDate(Calendar.SATURDAY, al.getTime()));
			if(al.isThisDay(6)) dates.add(this.setAlertDate(Calendar.SUNDAY, al.getTime()));

			for(int i = 0; i < dates.length(); i++)
		  	timers.add(Timer.schedule(new AlertTimer(dates.get(i) al.getMessage()), dates.get(i).getTime()));
		}

		private Calendar setAlertDate(int day, int time) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,day);
			return setTime(date, t);
		}

		private Calendar setTime(Calendar c, int t) {
			c.set(Calendar.HOUR_OF_DAY, t/100);
			c.set(Calendar.MINUTE, (t - ((t/100(int))*100)));
			return c;
		}

		@Override
		public void run() {
			JOptionPane.showMessageDialog(message, JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
