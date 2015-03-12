package com.baublebar.testcases;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ShutterStockTest{
	
	public static WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setup(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait (driver,30);
		driver.get("https://www.shutterstock.com");
	}
	
	public static void main (String[] agrs){}
	//@Test
	public void loadShutterStockTest() {
		driver.get("https://www.shutterstock.com");
	}
	

	@Test
	public void getImagesTest(){
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div/section[1]/form/div/div/div[1]/div/input[2]"))));
		driver.findElement(By.xpath("//*[@id='search-input']")).clear();
		driver.findElement(By.xpath("//*[@id='search-input']")).sendKeys("Mountains");
		
		driver.findElement(By.xpath("//*[@id='site-search']/div/div/div[2]/div/span")).click();
		driver.findElement(By.id("photos")).click();
		
		String currentwindow = driver.getWindowHandle();
		Set windows = driver.getWindowHandles();
		Iterator itr = windows.iterator();
		while (itr.hasNext()){
			String popup = (String)itr.next();
			if(!popup.equals(currentwindow)){
				driver.switchTo().window(popup);
				if (driver.getTitle().equals("loginMiodel"))
					break;
			}
		}
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(" "))).perform();
		action.contextClick().perform();
		action.moveToElement(driver.findElement(By.xpath(" "))).click().build().perform();
		
		
		/*String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";


((JavascriptExecutor)driver).executeScript(javaScript, webElement);*/
		
		
		//driver.findElement(By.xpath("//*[@id='site-search']/div/div/span/button")).click();
		
		driver.findElement(By.xpath("//*[@id='site-search']/div/div/span/button")).click();
		
		
		List<WebElement>lstImages = driver.findElements(By.tagName("img"));
		System.out.println("images are " + lstImages.size());
		for (WebElement webImage : lstImages){
			System.out.println(webImage.getText());
		}
		
		
		
		
		/*
		System.out.println("images are" + lstImages.size());
		ListIterator itr = lstImages.listIterator();
		while (itr.hasNext()){
			WebElement webImage = (WebElement)itr.next();
			System.out.println(webImage.getText());
			
			
		}*/
	}
	
	@Test
	public void loginTest(){
		driver.get("https://www.shutterstock.com");
		driver.findElement(By.xpath("//*[@id='inline-login']/a")).click();
		driver.findElement(By.id("user")).clear();
		driver.findElement(By.id("user")).sendKeys("test");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("test123");
		driver.findElement(By.name("submit")).click();;
	}
	
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
		
	}
	
	
	
}