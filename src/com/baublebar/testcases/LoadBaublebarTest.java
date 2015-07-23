package com.baublebar.testcases;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.saucelabs.saucerest.SauceREST;

/**
 * Load Baublebar
 * 
 * @author Maitri Acharya
 */
@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class LoadBaublebarTest extends TestBase{

	@BeforeSuite(enabled = ifLocal)	
	//@BeforeClass
	public void init() throws Exception{
		initConfigurations();
		initDriver();
	}
	
	@BeforeSuite(enabled = ifGrid)	
	@Parameters({"browser"})
	//@BeforeClass
	public void initGrid(String browser) throws Exception{
		initConfigurations();
		initGridDriver(browser);
	}

	@BeforeSuite(enabled = ifSauce)
	@Parameters({"browser","platform","version"})	
	//@BeforeClass
	public void setupDesireCapabilities(String browser,  Platform platform, String version) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(browser);
		caps.setPlatform(platform);
		caps.setVersion(version);
		initRemoteDriver(caps);
		initConfigurations();
	}
	
	@BeforeSuite(enabled = ifSauceMobile)
	//@BeforeClass
	public void setupDesireCapabilitiesMobile() throws Exception {
	//public void setupDesireCapabilities() throws Exception {
		//DesiredCapabilities caps = new DesiredCapabilities();
		//caps.setBrowserName(browser);
		//caps.setPlatform(platform);
		//caps.setVersion(version);
		DesiredCapabilities caps = DesiredCapabilities.iphone();
		caps.setCapability("platform", "OS X 10.10");
		caps.setCapability("version", "8.2");
		caps.setCapability("deviceName","iPhone Simulator");
		caps.setCapability("device-orientation", "portrait");
		initRemoteDriver(caps);
		initConfigurations();
	}
	
	
	
	@BeforeSuite(enabled = ifCrossBrowser)
	@Parameters({"browser","platform","version"})	
	//@BeforeClass
	public void setupDesireCapabilitiesCrossBrowser(String browser,  Platform platform, String version) throws Exception {
	//public void setupDesireCapabilities() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		// caps.setCapability("browser_api_name", "Chrome40");
	    // caps.setCapability("os_api_name", "Win8");
		// caps.setCapability("screen_resolution", "1024x768");
		// caps.setCapability("record_video", "false");
		 //caps.setCapability("record_network", "flase");
		 //caps.setCapability("record_snapshot", "false");
	    caps.setCapability("name", "Selenium Test Example");
	    caps.setCapability("build", "1.0");
	    caps.setCapability("browser_api_name", "MblSafari8.0");
	    caps.setCapability("os_api_name", "iPhone6Plus-iOS8sim");
	    caps.setCapability("screen_resolution", "1080x1920");
	    caps.setCapability("record_video", "true");
	    caps.setCapability("record_network", "true");
	    caps.setCapability("record_snapshot", "false");
	    initRemoteDriver(caps);
		initConfigurations();
	}
	
	
	@Test
	public void loadBaublebar() throws Exception{
		APPLICATION_LOGS.debug("Executing the LoadBaublebarTest");
		BaublebarPage landingPage = PageFactory.initElements(driver, BaublebarPage.class);
		landingPage.loadBaublebar();
		APPLICATION_LOGS.debug("Load Baublebar test completed");
		APPLICATION_LOGS.debug("************************************************");
	}
	
	@AfterMethod(enabled = ifSauce)
	public void updateSauceTestName(ITestResult result) throws Exception {  
		String jobID = ((RemoteWebDriver)driver).getSessionId().toString();
		SauceREST client = new SauceREST(username,key);
		Map<String, Object>saucejob = new HashMap<String,Object>();
		saucejob.put("name",result.getMethod().getMethodName());	
		Object a = saucejob.get("name");
		if (result.isSuccess()){
			client.jobPassed(jobID);
		} else{
			client.jobFailed(jobID);
		}
		client.updateJobInfo(jobID, saucejob);
	}
}