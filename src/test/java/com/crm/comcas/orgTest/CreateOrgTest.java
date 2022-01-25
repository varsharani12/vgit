package com.crm.comcas.orgTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;
import com.vtiger.comcast.pomrepositoryLib.Organization;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInformation;
import com.crm.GenericLibrary.JavaUtility;


public class CreateOrgTest {
	public static void main(String[] args) throws Throwable 
	{
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
    String organizationName = elib.getDataFromExcel("org", 1, 2)+"_"+jlib.getRanDomNumber();
    
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
   
   //navigate to organization
   Home hp=new Home(driver);
   hp.getOrglink().click();
   
   // create organization
	Organization org=new Organization(driver);
	org.getCreateNewOrgImg().click();
	
	CreateNewOrganization cnop=new CreateNewOrganization(driver);
	cnop.createOrg(organizationName);
	
	wlib.waitUntilPageLoad(driver);
	OrganizationInformation orgin=new OrganizationInformation(driver);
	String actorgMsg = orgin.getOrgHeaderSucMsg().getText();
	
	if(actorgMsg.contains(organizationName))
	 {
		 System.out.println("organization created successfully==PASS");
	 }
	 else
	 {
		 System.out.println("organization not created successfully==FAIL");
	 }
	hp.logout();
    driver.close();
    
	}
	}
   
	