package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login11 {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./data/commondata.property2");
		Properties pobj=new Properties();
		pobj.load(fis);
		String browser=pobj.getProperty("browser");
		String url=pobj.getProperty("url");
		String username=pobj.getProperty("username");
		String pwd=pobj.getProperty("password");
		WebDriver driver=new ChromeDriver();
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
	}

	}


