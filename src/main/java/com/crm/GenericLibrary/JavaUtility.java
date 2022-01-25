package com.crm.GenericLibrary;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

/**
 * This class contains generic methods wrt java
 * @varsha r
 *
 */

public class JavaUtility {

	/**
	 * this method is used to generate random number with range of 0 to 1000
	 * @return
	 */
	public int getRanDomNumber() {
		Random ran = new Random();
		int ranNum = ran.nextInt(1000);
		return ranNum;
	}
	
	/**
	 * this method returns system date and time
	 * @return
	 */
	public String getDateAndTime() {
	Date date = new Date();
	String dateAndTime = date.toString();
	return dateAndTime;
	}
	
	/**
	 * this method returns the date in YYYY-MM-DD format
	 * @return
	 */
	public String getDate_YYYY_MM_DD() {
		Date date = new Date();
		String sysDateAndTime = date.toString();
		System.out.println(sysDateAndTime);
		String[]ar = sysDateAndTime.split(" ");
		String DD = ar[2];
		String YYYY = ar[5];
		String MM = ar[1];
		String finalFormat = YYYY+"-"+MM+"-"+DD;
		return finalFormat;
	}
    public void pressVurtualEnterKey() throws Throwable {
    	
    	Robot rc=new Robot();
    	rc.keyPress(KeyEvent.VK_ENTER);
    	rc.keyRelease(KeyEvent.VK_ENTER);
    }
}






















