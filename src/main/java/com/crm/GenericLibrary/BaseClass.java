package com.crm.GenericLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.vtiger.comcast.pomrepositoryLib.Home;
import com.vtiger.comcast.pomrepositoryLib.Login;




public class BaseClass {
	private static final String url = null;
	public WebDriver driver=null;
	  /*Object Creation for Lib*/
		public JavaUtility jLib = new JavaUtility();
		public WebDriverUtility wLib = new WebDriverUtility();
		public FileUtility fLib = new FileUtility();
		public ExcelUtility eLib = new ExcelUtility();
		public static WebDriver sdriver=null;
//	
	@BeforeSuite@Test(groups= {"smokeTest","regressionTest"})
	public void configBS() {
		System.out.println("========================connect to DB========================");
               
	}
//	@Parameters("BROWSER")
	
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("=============Launch the Browser=======");
		String URL = fLib.getPropertyKeyValue("url");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		
		
		if(BROWSER.equals("chrome")) {
		    driver = new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(BROWSER.equals("ie")) {
			driver = new InternetExplorerDriver();
		}else {
			 driver = new ChromeDriver();
		}

		driver = new ChromeDriver();
		wLib.waitUntilPageLoad(driver);
		driver.get(URL);
		sdriver=driver;
		
//		driver.manage().window().maximize();
	}
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		/*common Data*/
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String URL = fLib.getPropertyKeyValue("url");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		/* Navigate to app*/
		   driver.get(URL);
		// login to app
		   driver.get(URL);
		   Login lp=new Login(driver);
		   lp.getLogintoApp(USERNAME, PASSWORD);
		
	}
	
	
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAM() {
	      /*step 6 : logout*/
			Home homePage = new Home(driver);
	        homePage.logout();

	}
	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("=============Close the Browser=======");
		driver.quit();
	}
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAS() {
		System.out.println("========================close DB========================");
	}
}




