package com.baublebar.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.baublebar.testcases.TestBase;

/**
 * Author - Maitri Acharya
 */

public class MainNavigationBarPage extends TestBase{
	
	public WebDriver driver;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[1]/a")
	public WebElement whatsNew;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[2]/a")
	public WebElement necklaces;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[3]/a")
	public WebElement bracelets;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[4]/a")
	public WebElement earRings;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[5]/a")
	public WebElement rings;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[6]/a")
	public WebElement featuredShop;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[7]/a")
	public WebElement personalized;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[8]/a")
	public WebElement guestBartender;
	
	@FindBy(xpath="//div[@id='wrapper-nav']/nav/ul[1]/li[9]/a")
	public WebElement holiday;
	

	public MainNavigationBarPage(WebDriver dr){
		driver = dr;
		WebDriverWait wait = new WebDriverWait(driver, 30);
	}
	
	
	public void selectCatagoryByName(String categoryName) throws InterruptedException{
		
		//List<WebElement> category = driver.findElements(By.xpath("//*[@id='main-nav']/ul[1]"));
		String begin = "//*[@id='main-nav']/ul[1]/li[";
		String end = "]/a/span";

		List<WebElement> list = driver.findElements(By.xpath("//nav[@id='main-nav']/ul[1]/li"));
		int listSize = list.size();
		//System.out.println(listSize);
		//System.out.println(driver.findElement(By.xpath("//*[@id='wrapper-nav']/nav/ul[1]")).getText() +"\n"+ "Total size is " + listSize);
		
		for (int i=1; i<=listSize; i++){
			
			WebElement name = driver.findElement(By.xpath(begin+i+end));
			String nameStr = name.getText();
			 if (nameStr.equalsIgnoreCase(categoryName)){
				 System.out.println("Clicking on category " + nameStr);
				 name.click();
				 Thread.sleep(2000L);
			//	 wait.until(ExpectedConditions.titleIs(driver.getTitle()));
				 System.out.println(driver.getTitle()  +" -------- "+ driver.getCurrentUrl());
				// logs.debug(driver.getTitle()  +" -------- "+ driver.getCurrentUrl());
				 break;
			  }
			 else if (!nameStr.contains(categoryName)){
				 continue;
			 }
			 else{
				 System.out.println("Category not found!, please enter a valid category");
				 //logs.debug("Category not found!, please enter a valid category");
			}
		  } 
		}
	
	public void mainNavFlyOut(String category, String subCategory){
		
	String begin = "//*[@id='main-nav']/ul[1]/li[";
	String end = "]/a";
	
	Actions a = new Actions(driver);
	 
	int listSize = driver.findElements(By.xpath("//nav[@id='main-nav']/ul[1]/li")).size();
	for (int i=1; i<=listSize; i++){
					
		String name = driver.findElement(By.xpath(begin+i+end)).getText();
		
		 if (name.equalsIgnoreCase(category)){
			 System.out.println("Option " +"\""+ name +"\""+ " selected");	 
			 a.moveToElement(driver.findElement(By.xpath(begin+i+end))).perform();
			 
			 String start = "//*[@id='main-nav']/ul[1]/li[" +i+ "]/ul/li/div[1]/ul/li[";
			 String finsih ="]/a";
			 
			 for (int j=1; j<10; j++){
				 String subName = driver.findElement(By.xpath(start+j+finsih)).getText();
				 System.out.println("Option " +"\""+subName+"\""+ " selected");
				 if (subName.equalsIgnoreCase(subCategory)){ 
				 a.click(driver.findElement(By.xpath(start+j+finsih))).perform();	
				 //initShopMenu(category, subCategory);
				 System.out.println("Clicked");
				 break;
				 }
			 }
			 break;
		   } 
		}
	}

}
