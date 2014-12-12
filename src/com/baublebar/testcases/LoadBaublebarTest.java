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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.saucelabs.saucerest.SauceREST;

public class LoadBaublebarTest extends TestBase{
	
	public static boolean local= false;
	
	/*
	@BeforeSuite	
	public void init() throws Exception{	
		initConfigurations();
		initDriver();
	}*/

	@Parameters({"browser","platform","version"})
	@BeforeSuite	
	public void setupDesireCapabilities(String browser, Platform platform, String version) throws Exception {
		System.out.println("This is Browser String" +' '+ browser);
		System.out.println("This is Platform String" +' '+ platform);
		System.out.println("This is Version String" +' '+ version);
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(browser);
		caps.setPlatform(platform);
		caps.setVersion(version);
		initRemoteDriver(caps);
		initConfigurations();
	
	}
	
	@Test
	public void loadBaublebar() throws Exception{
		BaublebarPage landingPage = PageFactory.initElements(driver, BaublebarPage.class);
		landingPage.loadBaublebar();
	
		//driver.get("http://www.google.com");
		//System.out.println("Page Title is" + driver.getTitle());
		//WebElement element = driver.findElement(By.name("q"));
		//element.sendKeys("seleniumworks");
	   // element.submit();    	
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws Exception {  
	String jobID = ((RemoteWebDriver)driver).getSessionId().toString();
	System.out.println(jobID);
	SauceREST client = new SauceREST(username,key);
	
	Map<String, Object>saucejob = new HashMap<String,Object>();
	
	
	//System.out.println(result.getMethod().getMethodName());
	//saucejob.put("name", result.getMethod().getMethodName());
	saucejob.put("name",result.getMethod().getMethodName());
	
	Object a = saucejob.get("name");
	System.out.println(a);
	
	
	if (result.isSuccess()){
	client.jobPassed(jobID);
	}
	
	else{
	client.jobFailed(jobID);
	}
	client.updateJobInfo(jobID, saucejob);
	}
}
