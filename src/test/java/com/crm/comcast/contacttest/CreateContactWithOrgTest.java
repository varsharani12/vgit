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
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;
import com.vtiger.comcast.pomrepositoryLib.Organization;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInformation;

public class CreateContactWithOrgTest {

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
			  //read data from excel
			    String orgName = elib.getDataFromExcel("org", 1, 2)+"_"+jlib.getRanDomNumber();
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
				wlib.waitForElementVisibility(driver,oi.getOrgHeaderSucMsg());
				
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
				
				//logout
				hp.logout();
				driver.close();

	}

}
 