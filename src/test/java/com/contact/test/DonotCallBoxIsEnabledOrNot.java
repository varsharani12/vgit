package com.contact.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class DonotCallBoxIsEnabledOrNot {

	public static void main(String[] args) throws Throwable {
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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); // save button
		String acttext = driver.findElement(By.xpath("//span[@id='dtlview_Do Not Call']")).getText();
			if(acttext.contains("yes")) {
			System.out.println("Do not call checkbox is enabled");
		}
		else {
			System.out.println("Do not call checkbox is disabled");
		}
		WebElement target = driver.findElement(By.xpath("//td[@class='small']/img"));
		Actions a= new Actions(driver);
		a.moveToElement(target).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		

	}

}
