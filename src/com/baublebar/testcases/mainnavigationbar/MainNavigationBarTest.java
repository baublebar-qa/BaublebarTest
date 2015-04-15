package com.baublebar.testcases.mainnavigationbar;

import java.util.HashMap;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

public class MainNavigationBarTest extends TestBase{
	
	@Test(dataProvider = "getMainNavigationBarData")
	public void MainNavigationBarTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the MainNavigationBarTest");
		if (!TestUtil.isExecutable("MainNavigationBarTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		mainNavBar = getMainNavBar();		
		//mainNavBar.loadProductCategory(data.get("CategoryName"));
		mainNavBar.selectCatagoryByName(data.get("CategoryName"));
		isLoggedIn = true;
		APPLICATION_LOGS.debug("Main Navigation Bar Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getMainNavigationBarData() {
		return TestUtil.getData("MainNavigationBarTest", xls);
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