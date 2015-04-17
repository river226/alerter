package com.isensix.alerter;

import java.util.*;

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

		public AlertTimer(){

		}

		public AlertTimer(Alerts al) {
			ArrayList<Timer> timers = new ArrayList<Timer>();
			ArrayList<Calendar> dates = new ArrayList<Calendar>();

			if(al.isThisDay(0)) dates.add(setAlertMon(al.getTime()));
			if(al.isThisDay(1)) dates.add(setAlertTues(al.getTime()));
			if(al.isThisDay(2)) dates.add(setAlertWed(al.getTime()));
			if(al.isThisDay(3)) dates.add(setAlertThurs(al.getTime()));
			if(al.isThisDay(4)) dates.add(setAlertFri(al.getTime()));
			if(al.isThisDay(5)) dates.add(setAlertSat(al.getTime()));
			if(al.isThisDay(6)) dates.add(setAlertSun(al.getTime()));

			for(int i = 0; i < dates.length(); i++)
		  	timers.add(Timer.schedule(new AlertTimer(), dates.get(i).getTime()));
		}

		private Calendar setAlertMon(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
			return setTime(date, t);
		}

		private Calendar setAlertTues(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
			return setTime(date, t);
		}

		private Calendar setAlertWed(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
			return setTime(date, t);
		}

		private Calendar setAlertThurs(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
			return setTime(date, t);
		}

		private Calendar setAlertFri(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
			return setTime(date, t);
		}

		private Calendar setAlertSat(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
			return setTime(date, t);
		}

		private Calendar setAlertSun(int t) {
			Calendar date = date.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			return setTime(date, t);
		}

		private Calendar setTime(Calendar c, int t) {
			c.set(Calendar.HOUR_OF_DAY, t/100);
			c.set(Calendar.MINUTE, (t - ((t/100(int))*100)));
			return c;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}
	}

	private class AlertPopUp {
		// TODO build up Alert pop up

		public AlertPopUp() {

		}

		public void launchPopUp(String m) {

		}
	}
}
