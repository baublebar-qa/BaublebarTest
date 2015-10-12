package com.baublebar.testcases.vaultpoints;

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
	 * Test of Vault points with Discount
	 * 
	 * @author Rumana Afroz 
	 */



public class VaultPointsWithDiscountTest extends TestBase {
	
	@Test(dataProvider = "getVaultWithDiscountData")
	public void applyVaultPointWithDicountTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the VaultPointWithDicountTest");
		if (!TestUtil.isExecutable("VaultPointWithDicountTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		topMenuBar = getTopMenuBar();		
		topMenuBar.doLogin(data.get("Username"), data.get("Password"));
		isLoggedIn = true;
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		shoppingCartPage.applyVaultWithDiscount(data.get("ProductName"),false,data.get("VaultPointAmount"),data.get("DiscountCode"));
		APPLICATION_LOGS.debug("Apply Vault Point with Discount Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}
	
	@DataProvider
	public Object[][] getVaultWithDiscountData() {
		return TestUtil.getData("VaultPointWithDicountTest", xls);
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
