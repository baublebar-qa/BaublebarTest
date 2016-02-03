package com.baublebar.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

public class AddToWishListTest extends TestBase {

	@Test(dataProvider = "getAddToWishListData")
	public void addToWishList(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the add to wish list test");
		if (!TestUtil.isExecutable("AddToWishListTest", xls)|| data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");
		BaublebarPage landingPage = getLandingPage();
		landingPage.verifyAnItemToWishList(data.get("ProductName"),data.get("AccountLogIn"),data.get("AccountPwd"));
		isLoggedIn = true;
		APPLICATION_LOGS.debug("Add to wish list Test Completed");
		APPLICATION_LOGS.debug("************************************************");

	}

	@DataProvider
	public Object[][] getAddToWishListData() {
		return TestUtil.getData("AddToWishListTest", xls);
	}

	@AfterMethod(enabled = ifSauce)
	public void updateSauceTestName(ITestResult result) throws Exception {
		String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
		System.out.println(jobID);
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
