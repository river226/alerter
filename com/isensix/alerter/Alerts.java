package com.isensix.alerter;

/**
 * This class defines the characteristics of alerts that are
 * implemented by this program. 
 */

public class Alerts {

  private int time; // 24hour time, xxxx format
  private boolean[] day; // size 6, days mon-sun
  private String message; // Alert Message

  /**
   * Takes in values to build the alert
   *
   * slot1 time alert is set to go off
   * slot2 Days in boolean array (size 6, mon-sun) that alert is set to go off
   * slot3 message assigned to alert
   */
  public Alerts(int t, boolean[] d, String m) {
    time = t;
    day = d;
    message = m;
  }

  /**
   * This method takes an int value for the day of the week:
   * mon = 0, tues = 1, wed = 2, thurs = 3,
   * Fri = 4, sat = 5, and sun =6
   * And returns if this alert is set for that day
   *
   * @return Whether the specified day should have this alert
   */
  public boolean isThisDay(int d) {
    return day[d];
  }

  /**
   * This method returns the time alert is set for
 * @return 
   *
   * @return time this alert is set to go off
   */
  public int getTime() {
    return time;
  }

  /**
   * This method returns the message for this alert
   *
   * @return Alert Message
   */
  public String getMessage(){
    return message;
  }

}
