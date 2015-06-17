package com.baublebar.testcases.footer;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.baublebar.testcases.TestBase;
import com.baublebar.util.TestUtil;

/**
 * This class tests the all the links from footer
 * 
 * @author Maitri Acharya
 */
@Listeners({com.baublebar.util.ScreenShotOnFailure.class })
public class FooterTest extends TestBase {

	@Test(dataProvider = "getFooterTestData")
	public void testFooter(Hashtable<String, String> data){
		APPLICATION_LOGS.debug("Executing the FooterTest");
		if (!TestUtil.isExecutable("FooterTest", xls)|| data.get("Runmode").equals("N")) throw new SkipException("Skipping the test");		
		landingPage = getLandingPage();
		landingPage.testFooter();
	}
	
	@DataProvider
	public Object[][] getFooterTestData() {
		return TestUtil.getData("FooterTest", xls);
	}
	
}
