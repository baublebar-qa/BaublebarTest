package com.baublebar.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * This is the page for main navigation bar of Baublebar
 * The main purpose is to navigate to different product categories 
 * 
 * @author Maitri Acharya
 */
public class MainNavigationBarPage{
	
	@FindBy(xpath="//*[@id='main-nav']/div/ul/li[8]/a/span")
	public WebElement whatsNew;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[9]/a/span")
	public WebElement necklaces;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[10]/a/span")
	public WebElement bracelets;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[11]/a/span")
	public WebElement earRings;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[12]/a/span")
	public WebElement rings;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[13]/a/span")
	public WebElement featuredShop;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[14]/a/span")
	public WebElement personalized;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[15]/a/span")
	public WebElement guestBartender;
	
	@FindBy(xpath=".//*[@id='main-nav']/div/ul/li[16]/a/span")
	public WebElement miniBar;
	
	@FindBy(xpath="//*[@id='main-nav']/div/ul/li[8]/a/span")
	public WebElement categoryNameTitle;
	
	@FindBy(xpath="html/body/div[1]/div/section/div[2]/div/div/div[2]/div/div[2]/img")
	public WebElement featuredShopTitle;	
	
	String beginMenuBar = "//*[@id='main-nav']/div/ul/li[";
	String endMenuBar = "]/a/span";
	
	public WebDriver driver;
	WebDriverWait wait;
	
	List<WebElement> lstTotalMenu;
	List<WebElement> lstDeskMenu;

	public MainNavigationBarPage(WebDriver dr){
		driver = dr;
		wait = new WebDriverWait(driver, 30);
	}
	
	//Not Calling this method for assert fails frequently because of page design changes
	public void loadProductCategory(String categoryName) throws InterruptedException{
		if(categoryName.contains("What")){
			wait.until(ExpectedConditions.elementToBeClickable(whatsNew));
			whatsNew.click();
			Assert.assertEquals(categoryNameTitle.getText(), "JUST ADDED");
		} else if(categoryName.equalsIgnoreCase("Necklaces")){
			wait.until(ExpectedConditions.elementToBeClickable(necklaces));
			necklaces.click();
			Assert.assertEquals(categoryNameTitle.getText(), "NECKLACES");
		} else if(categoryName.equalsIgnoreCase("Bracelets")){
			wait.until(ExpectedConditions.elementToBeClickable(bracelets));
			bracelets.click();
			Assert.assertEquals(categoryNameTitle.getText(), "BRACELETS");	
		} else if(categoryName.equalsIgnoreCase("Earrings")){
			wait.until(ExpectedConditions.elementToBeClickable(earRings));
			earRings.click();
			Assert.assertEquals(categoryNameTitle.getText(), "EARRINGS");	
		} else if(categoryName.equalsIgnoreCase("Rings")){
			wait.until(ExpectedConditions.elementToBeClickable(rings));
			rings.click();
			Assert.assertEquals(categoryNameTitle.getText(), "RINGS");	
		} else if(categoryName.equalsIgnoreCase("Featured Shops")){
			wait.until(ExpectedConditions.elementToBeClickable(featuredShop));
			featuredShop.click();
			Assert.assertEquals(featuredShopTitle.getAttribute("title"), "Build A Look");	
		} else if(categoryName.equalsIgnoreCase("Personalized")){
			wait.until(ExpectedConditions.elementToBeClickable(personalized));
			personalized.click();
			Assert.assertEquals(categoryNameTitle.getText(), "PERSONALIZED PIECES");	
		} else if(categoryName.equalsIgnoreCase("Guest Bartender")){
			wait.until(ExpectedConditions.elementToBeClickable(guestBartender));
			guestBartender.click();
			//no assert because changes every month and more image base page	
		//} else if (categoryName.equalsIgnoreCase("Minibar")){
			//wait.until(ExpectedConditions.elementToBeClickable(miniBar));
			//miniBar.click();
			//no assert because changes every month and more image base page
		}		
	}
	
	/**
	 * Navigate to Sub Categories
	 * @param categoryName
	 * @param subCategoryName
	 */
	public void loadProductSubCategory(String categoryName, String subCategoryName){
		
	}
	
	public void selectCatagoryByName(String categoryName) {
		if (lstTotalMenu == null)
			lstTotalMenu = driver.findElements(By.xpath("//*[@id='main-nav']/div/ul/li"));
		if (lstDeskMenu == null)
			lstDeskMenu = driver.findElements(By.cssSelector(".level-top.regular-menu._JS_llmenu.__containsSubNav>span"));
		int counter = lstTotalMenu.size() -lstDeskMenu.size();
		int size =  lstTotalMenu.size();
		WebElement name = null;
		for (int i= counter; i <= size; i++){
			 try {
				 String path = beginMenuBar+i+endMenuBar;
				 name = driver.findElement(By.xpath(path));
				 String replacedCatStr = categoryName.replaceAll("’","");	
				 String nameStr = name.getText();
				 String replacedNameStr = nameStr.replaceAll("'","");
				 if (replacedNameStr.equals(replacedCatStr.toUpperCase())){
					 //System.out.println("Clicking on category " + nameStr);
					 name.click();
					 Thread.sleep(3000); //need to add for Safari Driver wait for page load is not available for Safari
					 String winURL = driver.getCurrentUrl();
					 String urlCatName = replacedCatStr.toLowerCase().replaceAll(" ", "-");
					// System.out.println("url category " + urlCatName);
					 Assert.assertTrue(winURL.contains(urlCatName));
					 break;
				  }
			 } catch (Exception e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
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