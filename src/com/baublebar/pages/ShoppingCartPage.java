package com.baublebar.pages;

import org.openqa.selenium.By;
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
	
	//@FindBy(xpath = "//*[@id='product_addtocart_form']/div[3]/div[1]/button")
	@FindBy(xpath = "//button[@class='btn btnLg btnDefault' and @title='Add to Bag']")
    public WebElement addToBagBtn;
	
	
	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tbody/tr[1]/td[2]/span")
	public WebElement ordSummaryTotal;
	
	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tfoot/tr[2]/td[2]/span")
	public WebElement valultPointsTotal;

	@FindBy(id = Constants.cartQty)
	public WebElement cartQty;
	
	// rumana start

	@FindBy(xpath = "//*[@id='use-points-form']/label[2]")
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

	@FindBy(xpath = "//*[@id='shopping-cart-totals-table']/tbody/tr[6]/td[2]/span")
	public WebElement taxAmount;
	
	@FindBy(className = "cart_checkoutReview_item_Delete")
	public WebElement removeFromCart;

	@FindBy(xpath = "html/body/div[1]/div/section/div[2]/div[2]/h1")
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
	 * Apply vault points with discount
	 * 
	 * @param productName
	 * @param true
	 *            if new user
	 * @param vaultPointAmount
	 * @param discountCode
	 * 
	 */
	public void applyVaultWithDiscount(String productName, boolean isNewUser, String vaultPointAmount,String discountCode) {
		String productURl = applicationURL + productName + ".html";
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
		while (trial < 3) {
			try {
				clickPromoCode.click();
				if (promoCodeRemove.isDisplayed()) {
					promoCodeRemove.click();
					clickPromoCode.click();
				}
				trial++;
				System.out.println("trial no " + trial);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		wait.until(ExpectedConditions.visibilityOf(inputPromocode));
		inputPromocode.sendKeys(discountCode);
		try {
			wait.until(ExpectedConditions.visibilityOf(clickApply));
			clickApply.click();
		} catch (Exception e) {
		}
		if (discountCode.contains("BBENG")) {
			try {
				wait.until(ExpectedConditions.visibilityOf(orderSubtotal));
			} catch (Exception e) {
				// e.printStackTrace();
			}
			double price = convertStringToNumber(orderSubtotal.getText());
			double discountAmount = ((price - Double.parseDouble(vaultPointAmount)) * 0.25);
			double priceAfterUsingVaultAndDiscount = (price - Double.parseDouble(vaultPointAmount)) - discountAmount;
			wait.until(ExpectedConditions.visibilityOf(priceAfterVaultAndDiscount));
			double priceAfterVaultAndDiscountNumber = convertStringToNumber(priceAfterVaultAndDiscount.getText());
			Assert.assertEquals(priceAfterUsingVaultAndDiscount, priceAfterVaultAndDiscountNumber);
		}
	}

	/**
	 * Replace $ sign and return double number
	 */
	public double convertStringToNumber(String num) {
		num = num.replace("$", "");
		double doubleNum = Double.parseDouble(num);
		return doubleNum;
	}

	/**
	 * Calculate vault with discount price
	 */
	public double calculateVaultPriceWithDiscount(String num) {
		num = num.replace("$", "");
		double doubleNum = Double.parseDouble(num);
		return doubleNum;
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
        
        driver.get("https://baublebar.com/derby-drops-earrings.html");
		waitForLoad();
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		
		driver.get("https://baublebar.com/lookinglass-drops-earrings.html");
		waitForLoad();
        addToBagBtn.click();
        wait.until(ExpectedConditions.visibilityOf(cartQty));
        
        
		wait.until(ExpectedConditions.visibilityOf(cartQty));
	    String cartQuantity=(cartQty.getText());
     
       
       /*
        
        for (int second = 0; second < 30 ; second++){
        	try {
        		cartQuantity=(cartQty.getText());
            	if (!cartQuantity.equals("3")) 
            		Thread.sleep(1000);
               	else
               		return;
        	}  catch (Exception e)   {
                	Thread.sleep(1000L);	
             }
                
         }
        */
        System.out.println("Cart Contains "+cartQuantity);
        removeItems();
    	
		}
      //  Assert.assertEquals(cartQuantity, "1");
		

	
	
	/**
	 * Remove Item from shopping cart
	 * @throws Exception 
	 * 
	 */
	public void removeItems(){
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		wait.until(ExpectedConditions.visibilityOf(removeFromCart));
		int itemsInCart=driver.findElements(By.className("cart_checkoutReview_item_Delete")).size();
		if (itemsInCart !=3 ) {
			driver.navigate().refresh();
			waitForLoad();
			itemsInCart=driver.findElements(By.className("cart_checkoutReview_item_Delete")).size();
			
		}
		System.out.println("Items in Cart" +itemsInCart);
		for(int i=1; i<itemsInCart+1; i++){
            //wait.until(ExpectedConditions.visibilityOf(cartQty));
			//wait.until(ExpectedConditions.visibilityOf(removeFromCart));
			removeFromCart.click();
			waitForLoad();
			System.out.println("clicked");
			//driver.navigate().refresh();
            
		}
		Assert.assertEquals("Your Shopping Bag is Empty", emptyText.getText());
	}

	/**
	 * Remove Item from shopping cart
	 * 
	 
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
	}*/
}
