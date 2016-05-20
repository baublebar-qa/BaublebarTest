package com.baublebar.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
	@FindBy(xpath = "//*[@id='login-email']")
	public WebElement checkOutLogin;
	
	@FindBy(xpath = "//*[@id='login-password']")
	public WebElement checkOutPassword;
	
	@FindBy(xpath = "//*[@id='login-form']/fieldset/ul/li[3]/button")
	public WebElement checkOutLoginEnter;
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public  Page(WebDriver driver){
		Page.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
  
	public WebElement waitForElement(String xPath) throws InterruptedException{ // Wait function to wait for element    
        for (int second = 0; ; second++){
            if (second >= 60) Assert.fail("timeout");
	            try {
	            	WebElement webElement = driver.findElement(By.xpath(xPath));
	                if (webElement !=null) 
	                    return webElement;
	                }
	                catch (Exception e)   {
	                	Thread.sleep(1000L);	
	             }
                Thread.sleep(1000);
             }  
    }
	
	public String waitForText(WebElement webElement) throws InterruptedException{    
		String text = webElement.getText();
        for (int second = 0; ; second++){
            if (second >= 60) Assert.fail("timeout");
	            try {
	            	text = webElement.getText();
	                if (!text.equals("")) 
	                    return text;
	                }
	                catch (Exception e)   {
	                	Thread.sleep(1000L);	
	             }
                Thread.sleep(1000);
             }  
    }
	
	/**
	 *  Log in to user account
	 */
	public void login(){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//*[@id='login-email']")));
		checkOutLogin.sendKeys("qa@baublebar.com");
		checkOutPassword.sendKeys("test123");
		checkOutLoginEnter.click();
	}
	
	
	public void waitForLoad() throws TimeoutException {
	    ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, 45);
	    try {
	    	wait.until(pageLoadCondition);}
	    catch ( UnhandledAlertException uhe){
	    	System.out.println("Got the Alert");
	    	driver.switchTo().alert().accept();
	    }
	}
	
}
