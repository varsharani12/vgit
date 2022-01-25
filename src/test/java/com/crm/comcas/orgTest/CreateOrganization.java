package com.crm.comcas.orgTest;

import com.crm.GenericLibrary.BaseClass;
import com.vtiger.comcast.pomrepositoryLib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Organization;
import com.vtiger.comcast.pomrepositoryLib.OrganizationInformation;

import org.testng.annotations.Test;
public class CreateOrganization extends BaseClass {
	@Test(groups= {"smokeTest"})
	public void createOrgTest() throws Throwable {
		
		int randomInt = jLib.getRanDomNumber();
		/*test script Data*/
		String organizationName = eLib.getDataFromExcel("org", 1, 2) + randomInt;
		 //navigate to organization
		   Home hp=new Home(driver);
		   hp.getOrglink().click();
		   
		   // create organization
			Organization org=new Organization(driver);
			org.getCreateNewOrgImg().click();
			
			CreateNewOrganization cnop=new CreateNewOrganization(driver);
			cnop.createOrg(organizationName);
			
			wLib.waitUntilPageLoad(driver);
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
        }
	
	
	
	@Test(groups= {"regressionTest"})
	public void createOrgWithIndutriesTest() throws Throwable {
		/*test script Data*/
		int randomInt = jLib.getRanDomNumber();
		String orgName = eLib.getDataFromExcel("org", 1, 2) + randomInt;
		String industries = eLib.getDataFromExcel("org", 4, 3);
		
		  //navigate to organization
		   Home hp=new Home(driver);
		   hp.getOrglink().click();
		   
		   // create organization
			Organization org=new Organization(driver);
			org.getCreateNewOrgImg().click();
			
			//creating organization name with industry
			CreateNewOrganization ctorg=new CreateNewOrganization(driver);
			String industry;
			ctorg.createOrg(orgName, industries);
			
			// verify the org info
			wLib.waitUntilPageLoad(driver);
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
        }
	}



	


