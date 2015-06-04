package com.baublebar.testcases;

import java.net.URL;
import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.*;


public class TestSauce {

//	public static String username = "maitri";
	//public static String password = "test123";
	//public static String saucelabs = "http://maitri:c43770ad-be97-44dd-8b72-470d8d708340@ondemand.saucelabs.com:80/wd/hub";
	public static final String USERNAME = "maitri2";
	public static final String AUTOMATE_KEY = "Mq64x7AmqfCajUL1m8xi";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

	private WebDriver driver;
	
	
	@Parameters({"browser","platform","version"})
	@BeforeClass
	public void setup(String browser, Platform platform, String version) throws Exception {
		System.out.println("This is Browser String" +' '+ browser);
		System.out.println("This is Platform String" +' '+ platform);
		System.out.println("This is Version String" +' '+ version);
		//DesiredCapabilities caps = DesiredCapabilities.firefox();
		//caps.setCapability("browser","FIREFOX");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(browser);
		caps.setPlatform(platform);
		caps.setVersion(version);
		//caps.setCapability("os_version","Win7");
		driver = new RemoteWebDriver(new URL(URL),caps);
		//driver = new RemoteWebDriver(new URL(saucelabs),caps);
	}
	
	@Test
	public void testsimple() throws Exception{
		driver.get("http://www.google.com");
		System.out.println("Page Title is" + driver.getTitle());
		WebElement element = driver.findElement(By.name("q"));
	    element.sendKeys("seleniumworks");
	    element.submit();    	
	}
			
	@AfterClass  
	public void tearDown() throws Exception {  
			driver.quit();  
	}  
	
					
}
