package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import com.crm.qa.util.TestUtils;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties pr;
	public  static WebEventListener eventListener;
	
	public TestBase() {
		File f= new File("../CRMProject/src/main/java/com/crm/qa/config/config.properties");
		try {
			FileReader fr =new FileReader(f);
			pr =new Properties();
			pr.load(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initilization() {
		String browserName = pr.getProperty("browser");
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if (browserName.equals("FF")) {
			driver = new FirefoxDriver();
		}else if (browserName.equals("IE")) {
			driver = new InternetExplorerDriver();
		}
		
		eventListener = new WebEventListener();
		EventFiringDecorator<WebDriver> e_driver = new EventFiringDecorator<WebDriver>(eventListener);
		WebDriver decoratedDriver = e_driver.decorate(driver);
		driver =decoratedDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(pr.getProperty("url"));
		
		
	}
	
	
	
	
}
