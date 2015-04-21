package com.isensix.alerter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

@SuppressWarnings("unused")
class AlertTimer extends TimerTask {

	private Calendar next;
	private String message;

	private AlertTimer(Calendar n, String m) {
		next = n;
		message = m;
	}

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


	private Calendar setAlertDate(int day, int time) {
		GregorianCalendar date = new GregorianCalendar();
		date.set(Calendar.DAY_OF_WEEK, day);
		return setTime(date, time);
	}

	private Calendar setTime(Calendar c, int t) {
		c.set(Calendar.HOUR_OF_DAY, t/100);
		c.set(Calendar.MINUTE, (t - ((((int) (t/100)))*100)));
		return c;
	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, message, "alert", JOptionPane.INFORMATION_MESSAGE);
	}
}