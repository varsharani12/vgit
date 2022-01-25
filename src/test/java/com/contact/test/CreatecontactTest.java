package com.contact.test;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
public class CreatecontactTest {
	public static void main(String[]args) throws Throwable {
//@Test
	//public void testCreateContact() throws IOException, AWTException {
		FileInputStream fis=new FileInputStream("./data/commondata2.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String Browser=pobj.getProperty("browser");
		String Url=pobj.getProperty("url");
		String Username=pobj.getProperty("username");
		String Pwd=pobj.getProperty("password");
	
		WebDriver driver=null;
		if(Browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(Browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(Browser.equals("ie")) {
			driver=new InternetExplorerDriver();
		}
		else {
		driver=new ChromeDriver();
		}
//		step1  login
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(Url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Pwd);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Searching organization
		driver.findElement(By.name("firstname")).sendKeys("raj");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("singh");
		driver.findElement(By.xpath("(//img[@alt='Select' ])[1]")).click();	//child window opened	
		Set<String> allwindow = driver.getWindowHandles();
		Iterator<String>itr=allwindow.iterator();
		String parentwindow = itr.next();
		String childwindow = itr.next();
		driver.switchTo().window(childwindow);		
		driver.findElement(By.xpath("//a[.='vtiger']")).click();
		driver.switchTo().window(parentwindow);
		driver.findElement(By.xpath("(//input[@alt='Clear'])[1]")).click();// erase button 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); // save button
		String acttext = driver.findElement(By.className("dvHeaderText")).getText();
		String expText = driver.findElement(By.name("firstname")).getText();
		if(acttext.contains(expText)) {
			System.out.println("contact information displayed");
		}
		else {
			System.out.println("contact information not displayed");
		}
		WebElement target = driver.findElement(By.xpath("//td[@class='small']/img"));
		Actions a= new Actions(driver);
		a.moveToElement(target).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		 

		}
	}

		