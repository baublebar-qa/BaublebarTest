package com.baublebar.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.pages.ShoppingCartPage;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Test to check the pagination
 * 
 * @author Rumana Afroz
 */

public class SortTest extends TestBase{
	@Test(dataProvider = "getSortTestData")
	public void sortTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the Sort Test");
		if (!TestUtil.isExecutable("SortTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		landingPage = getLandingPage();
		landingPage.sortByTest(data.get("CategoryName")); 
		APPLICATION_LOGS.debug("Sort Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}
	
	@DataProvider
	public Object[][] getSortTestData() {
		return TestUtil.getData("SortTest", xls);
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
