package com.baublebar.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Pagination test
 * 
 * @author Rumana Afroz
 */

public class PaginationTest extends TestBase {
	@Test(dataProvider = "getPaginationTestData")
	public void paginationTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the Pagination Test");
		if (!TestUtil.isExecutable("PaginationTest", xls) || data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		landingPage = getLandingPage();
		landingPage.paginationTest(data.get("CategoryName"));
		APPLICATION_LOGS.debug("Pagination Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getPaginationTestData() {
		return TestUtil.getData("PaginationTest", xls);
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
		} else {
			client.jobFailed(jobID);
		}
		client.updateJobInfo(jobID, saucejob);
	}

}