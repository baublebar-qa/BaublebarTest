package com.baublebar.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	
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
	
	public void waitForLoad() throws TimeoutException {
	    ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, 45);
	    wait.until(pageLoadCondition);
	}
	
}
