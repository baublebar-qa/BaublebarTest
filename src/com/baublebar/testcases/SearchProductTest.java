package com.baublebar.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Test to search on site
 * 
 * @author Maitri Acharya
 */
@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class SearchProductTest extends TestBase {

	@Test(dataProvider = "getSearchProductData")
	public void  searchProduct(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the SearchProductTest");
		if (!TestUtil.isExecutable("SearchProductTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		landingPage = getLandingPage();
		String searchString = data.get("SearchString");
		landingPage.searchProduct(searchString);
		APPLICATION_LOGS.debug("Search Product Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getSearchProductData() {
		return TestUtil.getData("SearchProductTest", xls);
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
