package ain.tolva.alerter;

/**
 * This is a Simple Object class defining alerts for the Alerter program
 * 
 * @author river226
 */

public class Alerts {

	private int time; // 24hour time, xxxx format
	private boolean[] day; // size 6, days mon-sun
	private String message; // Alert Message
	
	/**
	 * Alert Constructor
	 * @param t - time value in 24 hour format
	 * @param d - Boolean array size 7, that runs Mon-Sun. Specifies if a day has an alert
	 * @param m - Message displayed when alert goes off
	 */
	public Alerts(int t, boolean[] d, String m) {
		time = t;
		day = d;
		message = m;
	}
	
	/**
	 * This Method manages confirming if a day has an alert
	 * @param d - int value defining day:
	 * mon = 0, tues = 1, wed = 2, thurs = 3,
	 * Fri = 4, sat = 5, and sun =6
	 * @return boolean value confirming if specified day has an alert
	 */
	public boolean isThisDay(int d) {
		return day[d];
	}


	/**
	 * @return the Time value for the this alert
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return returns the alert message of this object
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * @return A String representation of this object
	 */
	@Override
	public String toString() {
		String alertString = "";
		
		alertString = String.valueOf(this.getTime());

		for(int i = 0; i < 7; i++) {
			if(this.isThisDay(i)) alertString = alertString + "::" + 1;
			else alertString = alertString + "::" + 0;
		}

		alertString = alertString + "::" + this.getMessage();
		
		return alertString;
	}

}
