package com.baublebar.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
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
 * Base class for will define method of run
 * 
 * @author Maitri Acharya
 */
public class TestBase {

	public static Logger APPLICATION_LOGS = null;
	public static Properties CONFIG=null;
	public static WebDriver driver=null;
	public static WebDriverWait wait;

	public static final boolean ifSauce= false;
	public static final boolean ifLocal=true;
	public static final boolean ifCrossBrowser = false;
	public static final boolean ifSauceMobile= false;
	public static final boolean ifGrid = false;
	
	public static boolean isLoggedIn=false;
	public static TopMenuBarPage topMenuBar = null; 
	public static MainNavigationBarPage mainNavBar = null;
	
	public Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"/src/com/baublebar/data/TestCases.xlsx");
	//public Xls_Reader result_xls = new Xls_Reader(System.getProperty("user.dir")+"/results/results.xlsx");

	public static BaublebarPage landingPage =null;
	
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
	
	public static String crossBrowser = "http://maitri%40baublebar.com:u16e02dde7ea3210@hub.crossbrowsertesting.com:80/wd/hub";
	
	/**
	 * Initialization the configuration file
	 * 
	 */
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
	
	/**
	 * Initializes/loads the driver based on type of browser defined in config file
	 * To run locally
	 */
	public void initDriver(){
		if(driver==null){
			if(CONFIG.getProperty("browser").equals("Mozilla")){
				driver=new FirefoxDriver();
				driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			}
			
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
				driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
				
			}
			else if(CONFIG.getProperty("browser").equals("Phantom")){
				String phantomBinaryPath = "/Users/maitriacharya/Automation/phantomjs-2.0.0-macosx/bin/phantomjs";
				DesiredCapabilities caps = new DesiredCapabilities();
			    caps.setJavascriptEnabled(true);                       
			    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomBinaryPath);
			    driver = new PhantomJSDriver(caps);
			    driver.manage().window().setSize(new Dimension(1280, 1024));
			}
			else if(CONFIG.getProperty("browser").equals("Safari")){
				SafariOptions options = new SafariOptions();
				options.setUseCleanSession(true); 
				Platform current = Platform.getCurrent();
				if (Platform.MAC.is(current))
				  driver = new SafariDriver(options);
			}		
		}
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(480, 725));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}
	
	/**
	 * Initializes/loads the driver based on type of browser defined in config file
	 * To run locally
	 */
	
	public void initGridDriver(String browser) {//throws MalformedURLException, InterruptedException
		try{
		DesiredCapabilities capability=null;
		if(browser.equalsIgnoreCase("firefox")){
			System.out.println("firefox");
			capability= DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			//capability.setVersion("3.6");
		}

		if(browser.equalsIgnoreCase("internet explorer")){
			System.out.println("internet explorer");
			capability= DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			//capability.setVersion("");
		}
		if(browser.equalsIgnoreCase("GOOGLECHROME")){
			System.out.println("chrome");
			String path = System.getProperty("user.dir")+"/chromedriver";
			System.out.println(path);
			File file = new File(path);
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		//	 browser.add(setupDriver(new ChromeDriver()));
			capability= DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			//capability.setVersion("");
			
			
			
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			// capability = new DesiredCapabilities();
			//driver=new ChromeDriver(capability);
			//driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			
		}

		System.out.println("A");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		//driver = new RemoteWebDriver(new URL("http://jenkins.baublebar.com:4444/wd/hub"), capability);
		
		

		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(480, 725));
		driver.manage().deleteAllCookies();
		//	driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Initializes/loads remote driver for Sauce labs
	 * @param DesiredCapabilities of driver
	 */
	public void initRemoteDriver(DesiredCapabilities capabilities){
		try {
			//driver = new RemoteWebDriver(new URL(URL),capabilities);
			driver = new RemoteWebDriver(new URL(saucelabs),capabilities);
		    // driver = new RemoteWebDriver(new URL(crossBrowser), capabilities);
		    wait = new WebDriverWait(driver, 30);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes/loads  main page of site
	 * 
	 */
	public BaublebarPage getLandingPage(){
		if(landingPage==null){
			landingPage = PageFactory.initElements(driver, BaublebarPage.class);
		}
		return landingPage;
	}
	
	/**
	 * Handles multiple windows 
	 * @param	windowTitle
	 */
	private void handleMultipleWindows(String windowTitle) {
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(windowTitle)) {
                return;
            }
        }
    }
	
	/**
	 * Initializes/loads top menu bar  page of site
	 */
	public static TopMenuBarPage getTopMenuBar(){
		if(topMenuBar == null){
			topMenuBar = PageFactory.initElements(driver, TopMenuBarPage.class);
		}
		return topMenuBar;
	}
	
	/**
	 * Initializes/loads main menu bar/product category page
	 */
	public static MainNavigationBarPage getMainNavBar(){
		if(mainNavBar == null){
			mainNavBar = PageFactory.initElements(driver, MainNavigationBarPage.class);
		}
		return mainNavBar;
	}
	
	public static void takeScreenShot(String fileName) {
		File srcFile = ((TakesScreenshot)(TestBase.driver)).getScreenshotAs(OutputType.FILE);
	    try {
	    	FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Close the browser after execution of all test cases
	 */
	@AfterSuite
	public void quitDriver(){
		driver.quit();
		driver=null;
		System.out.println("driver null now");
	}	
}