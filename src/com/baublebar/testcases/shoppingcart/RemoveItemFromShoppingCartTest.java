
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
 * Test Remove Item from shopping cart
 * 
 * @author Rumana Afroz
 */
public class RemoveItemFromShoppingCartTest extends TestBase {
	@Test(dataProvider = "getRemoveItemFromCartData")
	public void removeItemFromCartTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the Remove Item From Cart	Test");
		if (!TestUtil.isExecutable("RemoveItemFromShoppingCartTest", xls) || data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");
		landingPage = getLandingPage();
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		shoppingCartPage.removeItemFromShoppingCart(data.get("ProductName"));
		APPLICATION_LOGS.debug("Remove Item From Cart Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}

	@DataProvider
	public Object[][] getRemoveItemFromCartData() {
		return TestUtil.getData("RemoveItemFromShoppingCartTest", xls);
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
