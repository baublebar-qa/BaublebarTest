package com.baublebar.testcases.shoppingcart;

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

import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class AddBundleProductTest extends TestBase{
	
	@Test(dataProvider = "getAddBundleProductData")
		public void addBundleTest(Hashtable<String, String> data) throws Throwable {
			APPLICATION_LOGS.debug("Executing the AddBundleProductTest");
			if (!TestUtil.isExecutable("AddBundleProductTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
			landingPage = getLandingPage();
			landingPage.logout();
			landingPage.addBundleProduct(data.get("ProductName"));
			landingPage.logout();
			APPLICATION_LOGS.debug("Place Order Test Completed");
			APPLICATION_LOGS.debug("************************************************");
		}
		
		@DataProvider
		public Object[][] getAddBundleProductData() {
			return TestUtil.getData("AddBundleProductTest", xls);
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
