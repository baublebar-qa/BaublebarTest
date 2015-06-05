package com.baublebar.testcases.topmenubar;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Test to check contact us navigation
 * 
 * @author Maitri Acharya
 */
@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class MyAccountOptionsTest  extends TestBase{
	
		@Test(dataProvider = "getMyAccountData")
		public void LoginTest(Hashtable<String, String> data) throws Throwable {
			APPLICATION_LOGS.debug("Executing the MyAccountOptionsTest");
			if (!TestUtil.isExecutable("MyAccountOptionsTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
			topMenuBar = getTopMenuBar();		
			topMenuBar.myAccountOptions(data.get("Options"));
			isLoggedIn = true;
			APPLICATION_LOGS.debug("MyAccount Test Completed");
			APPLICATION_LOGS.debug("************************************************");
		}

		@DataProvider
		public Object[][] getMyAccountData() {
			return TestUtil.getData("MyAccountOptionsTest", xls);
		}

		@AfterMethod(enabled = ifSauce)
		public void updateSauceTestName(ITestResult result) throws Exception {
			String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
			SauceREST client = new SauceREST(username, key);
			Map<String, Object> saucejob = new HashMap<String, Object>();
			saucejob.put("name", result.getMethod().getMethodName());
			Object a = saucejob.get("name");
			if (result.isSuccess()) {
				client.jobPassed(jobID);
			}
			else {
				client.jobFailed(jobID);
			}
			client.updateJobInfo(jobID, saucejob);
		}
	}