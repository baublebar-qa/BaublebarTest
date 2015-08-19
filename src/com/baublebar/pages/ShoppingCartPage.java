package com.baublebar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.baublebar.testcases.TestBase;
import com.baublebar.util.Constants;

/**
 * This is the shoping cart page of Baublebar
 * The class contains cart related functionality of site 
 * 
 * @author Maitri Acharya
 */
public class ShoppingCartPage extends Page{
	
	@FindBy(xpath = Constants.shoppingBag)
	public WebElement shoppingCart;
	
	@FindBy(xpath = "//*[@id='order-summary-container']/ul/li[1]/button")
	public WebElement checkOut;
	
	@FindBy(xpath = "//*[@id='product_addtocart_form']/div[3]/div[1]/button")
	public WebElement addToBagBtn;
	
	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td[2]/span")
	public WebElement ordSummaryTotal;
	
	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tfoot/tr[2]/td[2]/span")
	public WebElement valultPointsTotal;

	@FindBy(id = Constants.cartQty)
	public WebElement cartQty;
	
	//WebDriver driver;
	//WebDriverWait wait;
	String applicationURL = (TestBase.CONFIG.getProperty("applicationURL"));
	
	
	
	public ShoppingCartPage(WebDriver driver){
		super(driver);
		//driver = dr;
		//wait = new WebDriverWait(driver, 30);
	}

	/**
	 * Verifies the Vault Points for purchase of an item
	 * @param product name
	 */
	public void earnVaultPoints(String productName){
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
	//	try {
		//	Thread.sleep(2000);
	//	} 	catch (Exception e) {
				//e.printStackTrace();
		//}
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		wait.until(ExpectedConditions.visibilityOf(ordSummaryTotal));
		String ordSummaryTotalTxt = ordSummaryTotal.getText();
		String ordSummaryTotalText1 = ordSummaryTotalTxt.replace("$", "");
		String ordSummaryTotalText2 = ordSummaryTotalText1.replace(".00", "");
		wait.until(ExpectedConditions.visibilityOf(valultPointsTotal));
		String valultPointsTotalTxt = valultPointsTotal.getText();
		String valultPointsTotalTxt1 = valultPointsTotalTxt.replace("Points", "");
		Assert.assertEquals(ordSummaryTotalText2.trim(), valultPointsTotalTxt1.trim());
	}
	
}
