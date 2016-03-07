package com.baublebar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	
	@FindBy(xpath = Constants.viewShoppingBag)
	public WebElement viewShoppingBag;
	
	//@FindBy(xpath = "//*[@id='order-summary-container']/ul/li[1]/button")
	//public WebElement checkOut;
	
	@FindBy(xpath = ".//*[@id='order-summary-container']/div[1]/ul/li[1]/button")
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

	@FindBy(xpath = "html/body/section/div[2]/div[2]/h1")
	public WebElement emptyText;
	
	@FindBy(xpath = "//*[@id='billing-buttons-container']/div[2]/button") 
	public WebElement billContinue;
	
	@FindBy(xpath = "//*[@id='shipping-method-buttons-container']/div[3]/button")
	public WebElement shippingMethodContinue;
	
	@FindBy(xpath = "//*[@id='checkout-payment-method-load']/dl[1]/dt/label")
	public WebElement paymentType;
	
	@FindBy(xpath = "//*[@id='gene_braintree_creditcard_cc_number']")
	public WebElement creditCard;
		
	@FindBy(xpath = "//*[@id='payment-buttons-container']/div[3]/button")
	public WebElement paymentContinue;
	
	@FindBy(xpath = "//*[@id='gene_braintree_creditcard_cc_cid']")
	public WebElement cvvNumber;
	
	@FindBy(xpath = "//*[@id='gene_braintree_creditcard_store_in_vault']")
	public WebElement saveCardCheckbox;
	
	@FindBy(xpath = "//*[@id='gene_braintree_creditcard_expiration']")
	public WebElement ccExpirMonth;
	
	@FindBy(xpath = "//*[@id='gene_braintree_creditcard_expiration_yr']")
	public WebElement ccExpirYear;
	
	@FindBy(xpath = "//*[@id='review-buttons-container']/div/button")
	public WebElement reviewOrder;
	
	@FindBy(xpath = "html/body/div[1]/div/section/div[2]/div[2]/h1")
	public WebElement ordConfirmMsg;
	
	@FindBy(xpath = "//*[@id='header_points']")
	public WebElement vaultPoints;
	//*[@id='creditcard-saved-accounts']/tbody/tr[2]/td[1]
	@FindBy(xpath = "//*[@id='creditcard-saved-accounts']/tbody/tr[2]/td[1]/input[@type='radio']")
	public WebElement newCreditCard;
	
	@FindBy(xpath = "//*[@id='80']")
	public WebElement selectColor;
	
	@FindBy(xpath = "//*[@id='133']")
	public WebElement selectSize;
	
	@FindBy(xpath = "//*[@id='updateCartForm']/article/div[1]/div/p[1]")
	public WebElement verifyColortxt;
	
	@FindBy(xpath = "//*[@id='updateCartForm']/article/div[1]/div/p[2]")
	public WebElement verifySizetxt;
	
	@FindBy(xpath="//div[@class='cart_checkoutReview_continueShopping']/a")
    public WebElement clickContinueShopping;

	@FindBy(xpath="//div[@class='cart-empty']/p[@class='actions']/a[1]")
    public WebElement clickContinueShop;

	@FindBy(xpath=".//*[@id='optional']/ul/div")
    public WebElement bbCarousel;
	
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
	 * 
	 * @param product
	 *            name
	 */
	public void earnVaultPoints(String productName, String creditcardNumber, String ccexpirMonth, String ccexpirYear, String cvvnumber) {
		String productURl = applicationURL + productName + ".html";
		driver.get(productURl);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
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
		wait.until(ExpectedConditions.elementToBeClickable(checkOut));
		checkOut.click();
		login();
		wait.until(ExpectedConditions.elementToBeClickable(billContinue));
		billContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinue));
		shippingMethodContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(paymentContinue));
		paymentType.click();
		newCreditCard.click();
		creditCard.sendKeys(creditcardNumber);
		ccExpirMonth.sendKeys(ccexpirMonth);
		ccExpirYear.sendKeys(ccexpirYear);
		cvvNumber.sendKeys(cvvnumber);
		paymentContinue.click();
		String vaultPointsBeforeOrder = vaultPoints.getText();
		String[] vaultPointsBeforeOrdertxt = vaultPointsBeforeOrder.split(" ");
		int vaultpointsBeforeOrder = Integer.parseInt(vaultPointsBeforeOrdertxt[2]);
		wait.until(ExpectedConditions.elementToBeClickable(reviewOrder));
		reviewOrder.click();
		String confirmMsg = "Your order has been received.";
		wait.until(ExpectedConditions.elementToBeClickable(ordConfirmMsg));
		Assert.assertEquals(confirmMsg, ordConfirmMsg.getText());
		driver.navigate().refresh();
		String vaultPointsAfterOrder = vaultPoints.getText();
		String[] vaultPointsAfterOrdertxt = vaultPointsAfterOrder.split(" ");
		int vaultpointsAfterOrder = Integer.parseInt(vaultPointsAfterOrdertxt[2]);
		Assert.assertTrue(vaultpointsAfterOrder > vaultpointsBeforeOrder);
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
	 */
	public void removeItemFromShoppingCart(String productName1, String productName2, String productName3) {
		String productURl1 = applicationURL + productName1 + ".html";
		driver.get(productURl1);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		String productURl2 = applicationURL + productName2 + ".html";
		driver.get(productURl2);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		String productURl3 = applicationURL + productName3 + ".html";
		driver.get(productURl3);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		removeItems();
	}

	/**
	 * Remove Item from shopping cart
	 * 
	 */
	public void removeItems() {
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		wait.until(ExpectedConditions.visibilityOf(viewShoppingBag));
		wait.until(ExpectedConditions.elementToBeClickable(viewShoppingBag));
		viewShoppingBag.click();
		//shoppingCart.click();
		waitForLoad();
		try{
			wait.until(ExpectedConditions.visibilityOf(removeFromCart));
		} catch (Exception e){
			
		}
		int itemsInCart = driver.findElements(By.className("cart_checkoutReview_item_Delete")).size();
		if (itemsInCart != 3) {
			driver.navigate().refresh();
			waitForLoad();
			itemsInCart = driver.findElements(By.className("cart_checkoutReview_item_Delete")).size();
		}
		for (int i = 1; i < itemsInCart + 1; i++) {
			removeFromCart.click();
			waitForLoad();
		}
		Assert.assertEquals("Your Shopping Bag is Empty", emptyText.getText());
	}
	
	/**
	 * Add configurable product
	 * select color and size 
	 * verify that in shopping cart
	 * @param productName
	 */
	
	public void verifyColorAndSize(String productName, String color, String size) {
		driver.get(applicationURL);
		waitForLoad();
		removeItems();
		String productURl = applicationURL + productName + ".html";
		driver.get(productURl);
		waitForLoad();
		selectcolor(color);
		selectsize(size);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		waitForLoad();
		String colortxt = verifyColortxt.getText();
		String sizetxt = verifySizetxt.getText();
		Assert.assertEquals(colortxt, "Color: Clear/Gold");
		Assert.assertEquals(sizetxt, "Size: 6");

	}

	public void selectcolor(String color) {
		wait.until(ExpectedConditions.visibilityOf(selectColor));
		wait.until(ExpectedConditions.elementToBeClickable(selectColor));
		selectColor.click();
		Select dropDownColor = new Select(selectColor);
		dropDownColor.selectByVisibleText(color);
	}

	public void selectsize(String size) {
		wait.until(ExpectedConditions.visibilityOf(selectSize));
		wait.until(ExpectedConditions.elementToBeClickable(selectSize));
		selectSize.click();
		Select dropDownSize = new Select(selectSize);
		dropDownSize.selectByVisibleText(size);
	}
	

	/**
	 * Click Continue Shopping on shopping cart page
	 * 
	 */
	public void continueShopping(String productName) throws Exception {
		String productURl = applicationURL + productName + ".html";
		driver.get(productURl);
		waitForLoad();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		clickContinueProductInCart();
		clickContinueEmptyCart();
	}

	/**
	 * Click Continue Shopping when product in shopping cart
	 */
	public void clickContinueProductInCart() {
		try {
			wait.until(ExpectedConditions.visibilityOf(clickContinueShopping));
			wait.until(ExpectedConditions.elementToBeClickable(clickContinueShopping));
			clickContinueShopping.click();
		} catch (Exception e) {
		}
		String pages = driver.getPageSource();
		if (pages.contains("PRODUCT DESCRIPTION")) {
			Assert.assertTrue(true);
		}
	}

	/**
	 * Click Continue Shopping when shopping cart empty
	 */
	public void clickContinueEmptyCart() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		waitForLoad();
		wait.until(ExpectedConditions.visibilityOf(removeFromCart));
		try {
			removeFromCart.click();
		} catch (Exception e) {
		}
		wait.until(ExpectedConditions.visibilityOf(clickContinueShop));
		wait.until(ExpectedConditions.elementToBeClickable(clickContinueShop));
		clickContinueShop.click();
		waitForLoad();
		String pages = driver.getPageSource();
		if (pages.contains("bbCarousel")) {
			Assert.assertTrue(true);
		}
	}
	
	/**
	 * Add Items in shopping cart 
	 * @param product name
	 */
	public void addItems(String productName1, String productName2) {
		String productURl = applicationURL + productName1 + ".html";
		driver.get(productURl);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		String add1stProduct = (cartQty.getText());
		int cartQtyAfterAdd1stItem = Integer.parseInt(add1stProduct);
		String productURl2 = applicationURL + productName2 + ".html";
		driver.get(productURl2);
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		addToBagBtn.click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		String add2ndProduct = (cartQty.getText());
		int cartQtyAfterAdd2ndItem = Integer.parseInt(add2ndProduct);
		Assert.assertTrue(cartQtyAfterAdd2ndItem > cartQtyAfterAdd1stItem, "Cart Quantity incresed");
	}

}
