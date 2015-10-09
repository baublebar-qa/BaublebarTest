package com.baublebar.testcases.shoppingcart;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Test to place an order for new user
 * 
 * @author Maitri Acharya
 */
@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class PlaceOrderNewUserTest extends TestBase{
	
	@Test(dataProvider = "getPlaceOrderNewUserTestData")
		public void placeOrderTest(Hashtable<String, String> data) throws Throwable {
			APPLICATION_LOGS.debug("Executing the PlaceOrderNewUserTest");
			if (!TestUtil.isExecutable("PlaceOrderNewUserTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
			landingPage = getLandingPage();
			landingPage.logout();
			landingPage.placeOrder(data.get("ProductName"), true);
			landingPage.createAccount();
			Map<String, String> billInfo = new HashMap<String, String>();
			billInfo.put("firstName", data.get("FirstName"));
			billInfo.put("lastName", data.get("LastName"));
			billInfo.put("street", data.get("Street"));
			billInfo.put("city", data.get("City"));
			billInfo.put("state", data.get("State"));
			billInfo.put("zip", data.get("Zip"));
			billInfo.put("phone", data.get("Phone"));
			billInfo.put("creditCard", data.get("CreditCard"));
			billInfo.put("ccExpirMonth",data.get("CCExpirMonth"));
			billInfo.put("ccExpirYear",data.get("CCExpirYear"));
			billInfo.put("cvvNumber", data.get("CVVNumber"));
			landingPage.filloutBillingInfoAndCheckOut(billInfo);
			landingPage.logout();
			APPLICATION_LOGS.debug("Place Order New User Test Completed");
			APPLICATION_LOGS.debug("************************************************");
		}
		
		@DataProvider
		public Object[][] getPlaceOrderNewUserTestData() {
			return TestUtil.getData("PlaceOrderNewUserTest", xls);
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
