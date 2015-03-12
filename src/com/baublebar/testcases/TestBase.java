package com.baublebar.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Platform;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.pages.MainNavigationBarPage;
import com.baublebar.pages.TopMenuBarPage;
import com.baublebar.util.Constants;
import com.baublebar.util.CustomLogger;
import com.baublebar.util.Xls_Reader;

/**
 * Author - Maitri Acharya
 */
public class TestBase {

	public static Logger APPLICATION_LOGS = null;
	public static Properties CONFIG=null;
	public static WebDriver driver=null;
	public static WebDriverWait wait;

	public static final boolean ifSauce= false;
	public static final boolean ifLocal= true;
	
	public static boolean isLoggedIn=false;
	public static TopMenuBarPage topMenuBar = null; 
	public static MainNavigationBarPage mainNavBar = null;
	
	
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"/src/com/baublebar/data/TestCases.xlsx");
	//public Xls_Reader result_xls = new Xls_Reader(System.getProperty("user.dir")+"/results/results.xlsx");

	BaublebarPage landingPage =null;
	
	//public CustomLogger logger;
	//public static String username = "maitri";
	//public static String password = "test123";
	//public static String saucelabs = "http://maitri:c43770ad-be97-44dd-8b72-470d8d708340@ondemand.saucelabs.com:80/wd/hub";
//	public static String key = "c43770ad-be97-44dd-8b72-470d8d708340";

	public static String username = "baublebar";
	public static String password = "kFgVSUdo1ZLY";
	public static String saucelabs = "http://baublebar:054ad79b-2d36-4a95-98ca-4067bdc045b2@ondemand.saucelabs.com:80/wd/hub";
	public static String key = "054ad79b-2d36-4a95-98ca-4067bdc045b2";

	
	//public static final String USERNAME = "maitri2";
	//public static final String AUTOMATE_KEY = "Mq64x7AmqfCajUL1m8xi";
	//public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

	
	public void initConfigurations(){
		if(CONFIG==null){
		// Logging
		//logger = new CustomLogger();
		APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
		// config.prop
		 CONFIG = new Properties();
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/com/baublebar/config/config.properties");
			CONFIG.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	//Initializes/loads  the driver based on type of browser defined in config file
	public void initDriver(){
		if(driver==null){
			if(CONFIG.getProperty("browser").equals("Mozilla"))
				driver=new FirefoxDriver();
			else if(CONFIG.getProperty("browser").equals("IE")){
				String iePath = System.getProperty("user.dir")+"/IEDriverServer.exe";
				System.out.println(iePath);
				File file = new File(iePath);
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = new DesiredCapabilities();
				//capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(capabilities);
			} else if(CONFIG.getProperty("browser").equals("Chrome")){
				String path = System.getProperty("user.dir")+"/chromedriver";
				System.out.println(path);
				File file = new File(path);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = new DesiredCapabilities();
				driver=new ChromeDriver(capabilities);
			}
			else if(CONFIG.getProperty("browser").equals("Safari")){
				Platform current = Platform.getCurrent();
				if (Platform.MAC.is(current))
				  driver = new SafariDriver();
			}		
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}
	
	public void initRemoteDriver(DesiredCapabilities capabilities){
		try {
			//driver = new RemoteWebDriver(new URL(URL),capabilities);
			driver = new RemoteWebDriver(new URL(saucelabs),capabilities);
			wait = new WebDriverWait(driver, 30);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public BaublebarPage getLandingPage(){
		if(landingPage==null){
			landingPage = PageFactory.initElements(driver, BaublebarPage.class);
		}
		return landingPage;
	}
	
	//Set the focus on for given window
	//@param	windowTitle
	private void handleMultipleWindows(String windowTitle) {
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(windowTitle)) {
                return;
            }
        }
    }
	
	//Wait for element to appear/load on page
	public WebElement waitForElement(String xPath) throws InterruptedException{ // Wait function to wait for element    
        for (int second = 0; ; second++){
            if (second >= 60) Assert.fail("timeout");
	            try {
	            	WebElement webElement = driver.findElement(By.xpath(xPath));
	                if (webElement !=null) 
	                    return webElement;
	            }catch (Exception e)   {
	                	Thread.sleep(1000L);	
	            }
                Thread.sleep(1000);
             }  
    }
	
	public static TopMenuBarPage getTopMenuBar(){
		if(topMenuBar == null){
			topMenuBar = PageFactory.initElements(driver, TopMenuBarPage.class);
		}
		return topMenuBar;
	}
	
	
	public static MainNavigationBarPage getMainNavBar(){
		if(mainNavBar == null){
			mainNavBar = PageFactory.initElements(driver, MainNavigationBarPage.class);
		}
		return mainNavBar;
	}

	
	//Closes the browser
	@AfterSuite
	public void quitDriver(){
		driver.quit();
		driver=null;
	}	
}