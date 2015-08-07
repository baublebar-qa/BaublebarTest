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

import com.baublebar.pages.BaublebarPage;
import com.baublebar.pages.ShoppingCartPage;
import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;
import com.saucelabs.saucerest.SauceREST;

/**
 * Test of earning of Vault points
 * 
 * @author Maitri Acharya
 */
public class EarnVaultPointsTest extends TestBase{
	
	@Test(dataProvider = "getEarnVaultPointData")
	public void earnVaultPointTest(Hashtable<String, String> data) throws Throwable {
		APPLICATION_LOGS.debug("Executing the EarnVaultPointTest");
		if (!TestUtil.isExecutable("EarnVaultPointTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");
		ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
		shoppingCartPage.earnVaultPoints(data.get("ProductName"));
		APPLICATION_LOGS.debug("Earn VaultPoint Test Completed");
		APPLICATION_LOGS.debug("************************************************");
	}
	
	@DataProvider
	public Object[][] getEarnVaultPointData() {
		return TestUtil.getData("EarnVaultPointTest", xls);
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
