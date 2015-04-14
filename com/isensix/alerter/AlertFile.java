package com.isensix.alerter;

public class AlertFile {

  private String filename;
  private String[] lines;

  public alertFile() {
    filename = alert.alt;
  }

  public void readFile() {

  }

  public boolean doesFileExist {
    File f = new File(filename);
    return f.exists() && !f.isDirectory();
  }

}
