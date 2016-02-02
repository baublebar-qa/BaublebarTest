
package com.baublebar.testcases.shoppingcart;

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

import com.baublebar.pages.ShoppingCartPage;
import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Verify Color And Size in shopping cart
 * 
 * @author Rumana Afroz
 */
public class VerifyColorAndSizeTest extends TestBase {
	@Test(dataProvider = "getVerifyColorAndSizeTestData")
	public void verifyColorAndSizeTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the Verify Color And Size Test");
		if (!TestUtil.isExecutable("VerifyColorAndSizeTest", xls) || data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");
		landingPage = getLandingPage();
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		shoppingCartPage.verifyColorAndSize(data.get("ProductName"), data.get("Color"), data.get("Size"));
		APPLICATION_LOGS.debug("Verify Color And Size Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getVerifyColorAndSizeTestData() {
		return TestUtil.getData("VerifyColorAndSizeTest", xls);
	}

	@AfterMethod(enabled = ifSauce)
	public void updateSauceTestName(ITestResult result) throws Exception {
		String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
		SauceREST client = new SauceREST(username, key);
		Map<String, Object> saucejob = new HashMap<String, Object>();
		saucejob.put("name", result.getMethod().getMethodName());
		Object a = saucejob.get("name");
		System.out.println(a);
		if (result.isSuccess()) {
			client.jobPassed(jobID);
		} else {
			client.jobFailed(jobID);
		}
		client.updateJobInfo(jobID, saucejob);
	}

}