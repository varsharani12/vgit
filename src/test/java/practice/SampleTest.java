package practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.vtiger.comcast.pomrepositoryLib.Home;
//@Listeners(com.crm.GenericLibrary.Listenerimpclass.class)
public class SampleTest extends BaseClass {
	@Test
	public void contactTest() {
		
		Home hp=new Home(driver);
		hp.getContactlink().click();
		
		Assert.assertEquals("A","B");
	}

}


