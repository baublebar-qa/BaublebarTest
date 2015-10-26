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
	
	//@FindBy(xpath = "//*[@id='order-summary-container']/ul/li[1]/button")
	//public WebElement checkOut;
	
	@FindBy(xpath = "//*[@id='order-summary-container']/div[1]/ul/li[1]/button")
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
	
	@FindBy(xpath = "//*[@id='order-summary-container']/div[3]")
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
	
	@FindBy(xpath="//*[@id='shopping-cart-totals-table']/tbody/tr[6]/td[2]/span")
	public WebElement taxAmount;
	
	@FindBy(xpath="//*[@id='updateCartForm']/article/a")
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
		//String valultPointsTotalTxt1 = valultPointsTotalTxt.replace("Points", "");
		String valultPointsTotalTxt1 = valultPointsTotalTxt.replace(" POINTS", ""); 
		//Assert.assertEquals(ordSummaryTotalText2.trim(), valultPointsTotalTxt1.trim());
		Assert.assertEquals(ordSummaryTotalText2, valultPointsTotalTxt1);
		
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
	//Rumana Start From Here
	/**
	 * Add Items in shopping cart 
	 * 
	 * @param product name
	 * @throws Exception 
	 *  
	 */
	public void addItems(String productName1, String productName2) throws Exception{
		String productURl =  applicationURL + productName1 +".html";
		driver.get(productURl);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
        String add1stProduct=(cartQty.getText());
        int add1= Integer.parseInt(add1stProduct);
        String productURl2 =  applicationURL + productName2 +".html";
		driver.get(productURl2);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		waitForWebElement("cart_quantity");
		String add2ndProduct=(cartQty.getText());
		int add2= Integer.parseInt(add2ndProduct);
		System.out.println(add2ndProduct);
		Assert.assertTrue(add2>add1, "incresed");
		}
    /**
	 * Apply vault points with discount
	 * @param product name
	 * @param true if new user
	 * @param vaultPointAmount
	 * @param discountCode
	 * @throws Exception 
	 */
	public void applyVaultWithDiscount(String productName, boolean isNewUser, String vaultPointAmount, String discountCode) throws Exception{
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		wait.until(ExpectedConditions.elementToBeClickable(clickAddSomePoints));
		clickAddSomePoints.click();
		wait.until(ExpectedConditions.visibilityOf(dollarSignButton));
		dollarSignButton.sendKeys(vaultPointAmount);
		wait.until(ExpectedConditions.elementToBeClickable(clickApplyPoints));
		clickApplyPoints.click();
		int trial = 0;
			while ( trial < 3) {
				try {
					 clickPromoCode.click();
					 if(promoCodeRemove.isDisplayed()){
						 promoCodeRemove.click();
						 clickPromoCode.click();
						 }
					    trial++;
					  System.out.println("trial no " +trial);
					}catch (Exception e){
					//e.printStackTrace();
					}		
				}
		wait.until(ExpectedConditions.visibilityOf(inputPromocode));
		inputPromocode.sendKeys(discountCode);
			try{
				wait.until(ExpectedConditions.visibilityOf(clickApply));
				clickApply.click();
			}catch(Exception e){
				
			}
		     
		if(discountCode.contains("BBENG")){ 
			try{
			    wait.until(ExpectedConditions.visibilityOf(orderSubtotal));
			}catch(Exception e){
				//e.printStackTrace();
			}
			double price = convertStringToNumber(orderSubtotal.getText());
			//double discountAmount = ((price - Double.parseDouble(vaultPointAmount)) * 0.05);// BBENG doesn't apply after vaultAmount deduction.
			double discountAmount = (price * 0.05); //This line specially for BBENG promocode.
			double priceAfterUsingVaultAndDiscount = (price-Double.parseDouble(vaultPointAmount))-discountAmount;
			wait.until(ExpectedConditions.visibilityOf(priceAfterVaultAndDiscount));
			double priceAfterVaultAndDiscountNumber = convertStringToNumber(priceAfterVaultAndDiscount.getText());
			Assert.assertEquals(priceAfterUsingVaultAndDiscount, priceAfterVaultAndDiscountNumber);
			}
		}
    /**
	 *  Replace $ sign and return double number
	 */
	public double convertStringToNumber(String num){
		num = num.replace("$", "");
		double doubleNum = Double.parseDouble(num);
		return doubleNum;
	}
	/**
	 *  Calculate vault with discount price
	 */
	public double calculateVaultPriceWithDiscount(String num){
		num = num.replace("$", "");
		double doubleNum = Double.parseDouble(num);
		return doubleNum;
	}
	/**
	 * Remove Item from shopping cart page
	 * 
	 * @param product name
	 * @throws Exception 
	 *  
	 */
	public void removeItemFromShoppingCartPage(String productName) throws Exception{
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		removeItems();
		Assert.assertEquals("Your Shopping Bag is Empty", emptyText.getText());
		}
	/**
	 * Remove Item from shopping cart page
	 * 
	 * @throws Exception 
	 *  
	 */
	public void removeItems() throws Exception{
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		try{
			driver.findElement(By.xpath("//form[@id='updateCartForm']//article/a")).click();
			//waitForElement(".//*[@id='nav-top-link-cart']/button");
		 }catch(Exception e){
			   int a = driver.findElements(By.xpath("//*[@id='updateCartForm']/article/a")).size();
				for(int i=1;i<a;i++)
				{
					if(driver.findElement(By.xpath("//*[@id='updateCartForm']/article["+i+"]/a")).isDisplayed()){
					driver.findElement(By.xpath("//*[@id='updateCartForm']/article["+i+"]/a")).click();
					}
				}
				driver.findElement(By.xpath("//form[@id='updateCartForm']//article/a")).click();
		      }
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
