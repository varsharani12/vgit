package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class createorg {
//	read common data from property file
public static  void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./data/commondata.property2");
		Properties pobj=new Properties();
		pobj.load(fis);
		String browser=pobj.getProperty("browser");
		String url=pobj.getProperty("url");
		String username=pobj.getProperty("username");
		String pwd=pobj.getProperty("password");
//		read test data from excel file
		FileInputStream fis1=new FileInputStream("./data/testscript.xlsx");
		Workbook wb=(Workbook) WorkbookFactory.create(fis1);
		String Orgname=((org.apache.poi.ss.usermodel.Workbook) wb).getSheet("org").getRow(1).getCell(0).getStringCellValue();
		WebDriver driver=null;
		if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browser.equals("ie")) {
			driver=new InternetExplorerDriver();
		}
		else {
		driver=new ChromeDriver();
//		step1  login
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
//		step 2 navigate to organization module
		driver.findElement(By.linkText("organisations")).click();
//		step 3 create organization button
		driver.findElement(By.xpath("//img[@title='create orgaisation']")).click();
//	 step 4	 enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(Orgname);
		driver.findElement(By.xpath("//img[@title='save[alt+s]']")).click();
//		verify organization name in the header of message
		String actSuc_msg=driver.findElement(By.className("dvHeaderText")).getText();
		if(actSuc_msg.contains(Orgname)) {
			System.out.println(" org is successfully created..pass");
		}
		else {
			System.out.println(" org is successfully not created..fail");
			}
//		step 6 logout
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.png']"))).perform();
		driver.findElement(By.linkText("sign out")).click();
		driver.close();
		}
	}
		
		
		}

