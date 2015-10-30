package com.baublebar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	@FindBy(className="cart_checkoutReview_item_Delete")
	public WebElement removeFromCart;
	
	@FindBy(xpath="html/body/div[1]/div/section/div[2]/div[2]/h1")
	public WebElement emptyText;
	
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
		String valultPointsTotalTxt1 = valultPointsTotalTxt.replace("POINTS", "");
		Assert.assertEquals(ordSummaryTotalText2.trim(), valultPointsTotalTxt1.trim());
	}
	
	//Work in progress 
	public void applyVaultPoints(String productName){
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		wait.until(ExpectedConditions.visibilityOf(ordSummaryTotal));
	
	}

	/**
	 * Remove Item from shopping cart
	 * 
	 * @param productName
	 * @throws Exception
	 * 
	 */
	public void removeItemFromShoppingCart(String productName) throws Exception {
		String productURl = applicationURL + productName + ".html";
		driver.get(productURl);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
        String cartQuantity=(cartQty.getText());
        System.out.println("Cart Contains "+cartQuantity);
        Assert.assertEquals(cartQuantity, "1");
		removeItems();
	}

	/**
	 * Remove Item from shopping cart
	 * 
	 */
	public void removeItems() {
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		wait.until(ExpectedConditions.visibilityOf(removeFromCart));
		try {
			while (removeFromCart.isDisplayed()) {
				int trial = 0;
				while (trial < 5) {
					try {
						wait.until(ExpectedConditions.visibilityOf(removeFromCart));
						wait.until(ExpectedConditions.elementToBeClickable(removeFromCart));
						removeFromCart.click();
						if (removeFromCart == null) {
							break;
						}
					} catch (Exception e) {
						trial++;
						System.out.println("trial no " + trial);
					}
				}
			}
		} catch (Exception e) {
		}
		Assert.assertEquals("Your Shopping Bag is Empty", emptyText.getText());
	}
}
