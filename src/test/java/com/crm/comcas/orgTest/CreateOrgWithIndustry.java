package com.crm.comcas.orgTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;
import com.vtiger.comcast.pomrepositoryLib.Organization;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInformation;

public class CreateOrgWithIndustry {
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
    String orgName = elib.getDataFromExcel("org", 1, 2)+"_"+jlib.getRanDomNumber();
    String industries = elib.getDataFromExcel("org", 4, 3);
    
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
	
	//creating organization name with industry
	CreateNewOrganization ctorg=new CreateNewOrganization(driver);
	ctorg.createOrg(orgName, industries);
	
	// verify the org info
	wlib.waitUntilPageLoad(driver);
	OrganizationInformation orgin=new OrganizationInformation(driver);
	String actorgMsg = orgin.getOrgHeaderSucMsg().getText();
	
	if(actorgMsg.contains(orgName))
	 {
		 System.out.println("organization created successfully==PASS");
	 }
	 else
	 {
		 System.out.println("organization not created successfully==FAIL");
	 }
	
    
	String actIndustryInfo = orgin.getIndustriesInfo().getText();
	if(actIndustryInfo.equals(industries))
	{
		System.out.println("org created with industries successfully==PASS");
	}
	else {
		System.out.println("org not created with industries  successfully==FAIL");
	}
	// logout
	hp.logout();
    driver.quit();
    
	}
	}
   
	

