package com.baublebar.testcases.topmenubar;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

public class SignupForDiscountTest extends TestBase {

	@Test(dataProvider = "getDiscountData")
	public void signupForDiscountTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the SignupForDiscountTest");
		if (!TestUtil.isExecutable("SignupForDiscountTest", xls) || data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");
		topMenuBar = getTopMenuBar();		
		topMenuBar.signupForDiscount(data.get("DiscountEmail"));
		APPLICATION_LOGS.debug("SIGNING UP FOR A NEW ACCOUNT");
		System.out.println("SIGNING UP FOR A NEW ACCOUNT");
		// isLoggedIn=true;
		APPLICATION_LOGS.debug("Sign up for discount test completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getDiscountData() {
		return TestUtil.getData("SignupForDiscountTest", xls);
	}

	@AfterMethod(enabled = ifSauce)
	public void updateSauceTestName(ITestResult result) throws Exception {
		String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
		//System.out.println(jobID);
		SauceREST client = new SauceREST(username, key);

		Map<String, Object> saucejob = new HashMap<String, Object>();

		// System.out.println(result.getMethod().getMethodName());
		// saucejob.put("name", result.getMethod().getMethodName());
		saucejob.put("name", result.getMethod().getMethodName());

		Object a = saucejob.get("name");
		//System.out.println(a);

		if (result.isSuccess()) {
			client.jobPassed(jobID);
		}
		else {
			client.jobFailed(jobID);
		}
		client.updateJobInfo(jobID, saucejob);
	}

}
