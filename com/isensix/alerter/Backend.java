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
			Timer timer = new Timer();
		    Calendar date = Calendar.getInstance();
		    date.set(
		      Calendar.DAY_OF_WEEK,
		      Calendar.SUNDAY
		    );
		    date.set(Calendar.HOUR, 0);
		    date.set(Calendar.MINUTE, 0);
		    date.set(Calendar.SECOND, 0);
		    date.set(Calendar.MILLISECOND, 0);
		    // Schedule to run every Sunday in midnight
		    // timer.schedule(new Timer(), date.getTime(),(1000 * 60 * 60 * 24 * 7));
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
