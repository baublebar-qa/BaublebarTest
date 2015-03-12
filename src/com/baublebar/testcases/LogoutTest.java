package com.baublebar.testcases;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

public class LogoutTest extends TestBase {
	
	@Test(dataProvider = "getLogoutData")
	public void LogoutTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the LogoutTest");
		if (!TestUtil.isExecutable("LogoutTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		topMenuBar = getTopMenuBar();		
		topMenuBar.logout();
		APPLICATION_LOGS.debug("Logout Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}
	
	@DataProvider
	public Object[][] getLogoutData() {
		return TestUtil.getData("LogoutTest", xls);
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