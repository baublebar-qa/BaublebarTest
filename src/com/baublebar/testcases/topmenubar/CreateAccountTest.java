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
 * Test to create new account
 * 
 * @author Maitri Acharya
 */
@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class CreateAccountTest extends TestBase {

	@Test(dataProvider = "getCreateAccountData")
	public void createAccTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the CreateAccountTest");
		if (!TestUtil.isExecutable("CreateAccountTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");		
		topMenuBar = getTopMenuBar();		
		topMenuBar.createAccount(data.get("Username"), data.get("Password"));
		//isLoggedIn = true;
		landingPage = getLandingPage();
		landingPage.logout();
		APPLICATION_LOGS.debug("Create account test completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getCreateAccountData() {
		return TestUtil.getData("CreateAccountTest", xls);
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
