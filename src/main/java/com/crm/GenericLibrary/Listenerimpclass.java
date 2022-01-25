package com.crm.GenericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listenerimpclass implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		String FailedTestName=result.getMethod().getMethodName();
		EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.sdriver);
		File srcFile=eDriver.getScreenshotAs(OutputType.FILE);
		String sytemDate=new Date().toString().replace(":","_").replace(" ","_");
		try {
			FileUtils.copyFile(srcFile,new File("./ScreenShot/"+FailedTestName +"_"+sytemDate+".png"));
		}
		catch(IOException e) {
			
		}
		
		
	}

	
	}
	


