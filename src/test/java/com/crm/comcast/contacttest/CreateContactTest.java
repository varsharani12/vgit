package com.crm.comcast.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.vtiger.comcast.pomrepositoryLib.Contact;
import com.vtiger.comcast.pomrepositoryLib.ContactInformation;
import com.vtiger.comcast.pomrepositoryLib.CreateNewContacts;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		//create objects
		JavaUtility jlib=new JavaUtility();	
	    WebDriverUtility wlib=new WebDriverUtility();
	    FileUtility flib=new FileUtility();
	    ExcelUtility elib=new ExcelUtility();
	    WebDriver driver=null;
	 
	    // read data from property
	    String USERNAME = flib.getPropertyKeyValue("username");
	    String PASSWORD = flib.getPropertyKeyValue("password");
	    String URL = flib.getPropertyKeyValue("url");
	    String BROWSER = flib.getPropertyKeyValue("browser");
	    
	    //read data from excel
	    String lastName = elib.getDataFromExcel("contact", 1, 2)+"_"+jlib.getRanDomNumber();
	    
	    // open browser
	    if(BROWSER.equals("chrome"))
	    {
	    	driver=new ChromeDriver();
	    }
	    else if ((BROWSER.equals("firefox")))
	    		{
	    	driver=new FirefoxDriver();
	    	 }
	   // login to app
	   driver.get(URL);
	   Login lp=new Login(driver);
	   lp.getLogintoApp(USERNAME, PASSWORD);
	   
	   //navigate to contact page
	   Home hp=new Home(driver);
	   hp.getContactlink().click();
	   
	   // navigate create new contact page
	   Contact cp=new Contact(driver);
	   cp.getCreateContactImg().click();
	   
	   
	   //create contact
	   CreateNewContacts cnp=new CreateNewContacts(driver);
	   cnp.createContact(lastName);
	   
	   //verify the contact details
	   ContactInformation ci=new ContactInformation(driver);
	   String actLstName = ci.getOrgHeaderSuchMsg().getText();
	   if(actLstName.contains(lastName)) {
		   System.out.println(lastName+" contact last name is verified & PASS");
	   }
	   else {
		   System.out.println(lastName+" contact last name is not verified & FAIL");
	   }
	   //logout
	   hp.logout();
	   driver.quit();

	}

}
