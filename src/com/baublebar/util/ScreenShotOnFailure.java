package com.baublebar.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.baublebar.testcases.TestBase;

public class ScreenShotOnFailure extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		try {
			WebDriver driver = TestBase.driver;
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "screenshots";
			new File(destDir).mkdirs();
			String destFile = dateFormat.format(new Date()) + ".png";
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			Reporter.setEscapeHtml(false);
			Reporter.log("Saved <a href=../screenshots/" + destFile + ">Screenshot</a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
