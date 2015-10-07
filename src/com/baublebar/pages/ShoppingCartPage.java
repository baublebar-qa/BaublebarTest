package com.baublebar.pages;

import org.openqa.selenium.By;
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
	
	//rumana start
	
	@FindBy(xpath="//*[@id='use-points-form']/label[2]")
	public WebElement clickAddSomePoints;
	
	@FindBy(xpath = "//*[@id='use-points-form']/div/input")
	public WebElement dollarSignButton;
	
	@FindBy(xpath = "//*[@id='use-points-form']/div/button")
	public WebElement clickApplyPoints;
	
	@FindBy(xpath = "//*[@id='order-summary-container']/div[3]/dl/dt/a")
	public WebElement clickPromoCode;
	
	@FindBy(xpath = "//*[@id='coupon_code']")
	public WebElement inputPromocode;
	
	@FindBy(xpath = "//*[@id='discount-coupon-form']/fieldset/button")
	public WebElement clickApply;
	
	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td[2]/span")
	public WebElement orderSubtotal;
	
	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tfoot/tr[1]/td[2]/strong/span")
	public WebElement priceAfterVaultAndDiscount;
	
	@FindBy(xpath = "//*[@id='discount-coupon-form']/fieldset/button")
	public WebElement promoCodeRemove;
	
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
	//Rumana start from here
	/**
	 * Apply vault points with discount
	 * @param product name
	 * @param true if new user
	 * @throws InterruptedException 
	 */
	public void applyVaultWithDiscount(String productName, boolean isNewUser, String vaultPointAmount, String discountCode) throws InterruptedException{
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		try {
			Thread.sleep(2000);
		} 	catch (Exception e) {
				e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		System.out.println("Shoping cart clicked");
		wait.until(ExpectedConditions.elementToBeClickable(clickAddSomePoints));
		clickAddSomePoints.click();
		System.out.println("AddSomePoints clicked");
		wait.until(ExpectedConditions.visibilityOf(dollarSignButton));
		dollarSignButton.sendKeys(vaultPointAmount);
		wait.until(ExpectedConditions.elementToBeClickable(clickApplyPoints));
		clickApplyPoints.click();
		System.out.println("ApplyPoints clicked");
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOf(clickPromoCode));
		//wait.until(ExpectedConditions.elementToBeClickable(clickPromoCode));
		clickPromoCode.click();
		System.out.println("PromoCode clicked");
		
		if(promoCodeRemove.isDisplayed())
		{
			System.out.println("remove");
			promoCodeRemove.click();
			System.out.println("Remove button clicked");
			wait.until(ExpectedConditions.visibilityOf(clickPromoCode));
			clickPromoCode.click();
			System.out.println("Promo code clicked");
			
		}
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(inputPromocode));
		inputPromocode.sendKeys(discountCode);
		System.out.println("Promocode typed-----");
		wait.until(ExpectedConditions.visibilityOf(clickPromoCode));
		clickApply.click();
		System.out.println("Apply clicked");
		Thread.sleep(3000);
	  
		if(discountCode.contains("BBENG"))
		{   
			wait.until(ExpectedConditions.visibilityOf(orderSubtotal));
			double price = convertStringToNumber(orderSubtotal.getText());
			System.out.println("Price = " +price);
			double discountAmount = (price - Double.parseDouble(vaultPointAmount)) * 0.05d;
			System.out.println("discountAmount = " + discountAmount);
			double priceAfterUsingVaultAndDiscount = (price-Double.parseDouble(vaultPointAmount))-discountAmount;
			wait.until(ExpectedConditions.visibilityOf(priceAfterVaultAndDiscount));
			double priceAfterVaultAndDiscountNumber = convertStringToNumber(priceAfterVaultAndDiscount.getText());
			Assert.assertNotEquals(priceAfterUsingVaultAndDiscount, priceAfterVaultAndDiscountNumber);
			System.out.println("Expected result = " + priceAfterUsingVaultAndDiscount +" and Actual result = " + priceAfterVaultAndDiscountNumber + " are not matched.");
			
			
		}
		
		
	}
	
	/**
	 *  Replace $ sign and return float number
	 */
	public double convertStringToNumber(String num)
	{
		num = num.replace("$", "");
		double doubleNum = Double.parseDouble(num);
		return doubleNum;
		
	}
	
	/**
	 *  Calculate vault with discount price
	 */
	public double calculateVaultPriceWithDiscount(String num)
	{
		num = num.replace("$", "");
		double doubleNum = Double.parseDouble(num);
		return doubleNum;
		
	}
	
	/**
	 *  Login from shopping cart page
	 */
	
	public void login(){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//*[@id='login-email']")));
		checkOutLogin.sendKeys("qa@baublebar.com");
		checkOutPassword.sendKeys("test123");
		checkOutLoginEnter.click();
	}
	
	
}
