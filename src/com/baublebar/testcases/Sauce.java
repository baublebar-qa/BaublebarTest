package com.baublebar.testcases;
import java.io.File;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baublebar.testcases.TestBase;
import com.baublebar.util.Constants;
import com.baublebar.util.TestUtil;

public class Sauce {
		
		public WebDriver driver;
		WebDriverWait wait;
		public static String username = "maitri";
		public static String password = "test123";
		public static String saucelabs = "http://maitri:c43770ad-be97-44dd-8b72-470d8d708340@ondemand.saucelabs.com:80/wd/hub";
		public static String key = "c43770ad-be97-44dd-8b72-470d8d708340";

		
		
		
		
		@BeforeClass	
		public void setupDesireCapabilities() throws Exception {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName("GOOGLECHROME");
			caps.setPlatform(Platform.WIN8);
			caps.setVersion("40");
			driver = new RemoteWebDriver(new URL(saucelabs),caps);
			wait = new WebDriverWait(driver, 30);
			
		
		}
		
		
		//@BeforeClass
		public void setup(){
			String path = System.getProperty("user.dir")+"/chromedriver";
			File file = new File(path);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			driver=new ChromeDriver(capabilities);
			wait = new WebDriverWait(driver, 30);
		}
		
	@Test
	public void signupForDiscount() throws Throwable{
			driver.navigate().to("http://www.google.com/");			
			WebElement serchbutton = driver.findElement(By.xpath("//*[@id='gbqfba']"));			
			wait.until(ExpectedConditions.elementToBeClickable(serchbutton));
			serchbutton.click();		
			
		}
	
	

	}

