package com.crm.comcast.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.vtiger.comcast.pomrepositoryLib.Contact;
import com.vtiger.comcast.pomrepositoryLib.ContactInformation;
import com.vtiger.comcast.pomrepositoryLib.CreateNewContacts;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Organization;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInformation;

public class CreateOrganization extends BaseClass {
	@Test

	public  void CreateContactWithOrgTest() throws Throwable {
	  
  //read data from excel
    String orgName = eLib.getDataFromExcel("org", 1, 2)+"_"+jLib.getRanDomNumber();
    String lastName = eLib.getDataFromExcel("contact", 1, 2)+"_"+jLib.getRanDomNumber();
    
    // open browser
   WebDriver driver = null;
	Object BROWSER = null;
	if(BROWSER.equals("chrome"))
    {
    	driver=new ChromeDriver();
    }
    else if ((BROWSER.equals("firefox")))
    		{
    	driver=new FirefoxDriver();
    	 }
    //navigate to organization
	   Home hp=new Home(driver);
	   hp.getOrglink().click();
	   
	   // navigate to org page
		Organization org=new Organization(driver);
		org.getCreateNewOrgImg().click();
		
		// create organization
		CreateNewOrganization cnop=new CreateNewOrganization(driver);
		cnop.createOrg(orgName);
		
		//wait for header element
		OrganizationInformation oi=new OrganizationInformation(driver);
		wLib.waitForElementVisibility(driver,oi.getOrgHeaderSucMsg());
		
		// navigate to contact page
		hp.getContactlink().click();
		
		// navigate to create new contact
		Contact cp= new Contact(driver);
		cp.getCreateContactImg().click();
		
		//create a new contact with orgName page
		CreateNewContacts cnp=new CreateNewContacts(driver);
		cnp.createContact(lastName, orgName);
		
		
		//verify the details
		ContactInformation ci=new ContactInformation(driver);
		String actName = ci.getOrgHeaderSuchMsg().getText();
		if(actName.contains(lastName)) {
			   System.out.println(lastName+" contact last name is verified & PASS");
		   }
		   else {
			   System.out.println(lastName+" contact last name is not verified & FAIL");
		   }
		
		String actOrgName = ci.getOrgNameInfo().getText();
		if(actOrgName.trim().equals(orgName)) {
			System.out.println(orgName+" is verified in contact page and pass");
		}
		else {
			System.out.println(orgName+" is not verified in contact page and fail");
		}
	}
}
		
	


