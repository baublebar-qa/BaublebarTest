package com.baublebar.pages;

import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.baublebar.util.Constants;
import com.baublebar.testcases.TestBase;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.thoughtworks.selenium.Wait;

/**
 * This is the page for main or landing page of Baublebar
 * The class contains core functionality of site 
 * 
 * @author Maitri Acharya
 */
public class BaublebarPage{
	
	@FindBy(xpath=Constants.discountLink)
	public WebElement discountLink;
	
	@FindBy(xpath=Constants.discountEmail)
	public WebElement discountEmail;
	
	@FindBy(xpath=Constants.discountSubmit)
	public WebElement discountSubmit;	
	
	@FindBy(name=Constants.email)
	public WebElement email;
	
	@FindBy(css=Constants.getStarted)
	public WebElement getStarted;
	
	@FindBy(css=Constants.loginLink)
	public WebElement loginLink;
	
	@FindBy(css=Constants.haveAnAcc)
	public WebElement haveAnAcc;
	
	@FindBy(xpath=Constants.accountEmail)
	public WebElement accountEmail;
	
	@FindBy(xpath=Constants.accountPassword)
	public WebElement accountPassword;
	
	@FindBy(xpath=Constants.confirmPassword)
	public WebElement confirmPassword;
	
	@FindBy(css=Constants.submit)
	public WebElement submit;
		
	@FindBy(xpath=Constants.addToWish)
	public WebElement addToWish;
	
	@FindBy(css=Constants.loginEmail)
	public WebElement loginEmail;
	
	@FindBy(css=Constants.loginPassword)
	public WebElement loginPassword;
	
	@FindBy(css=Constants.loginButton)
	public WebElement loginButton;
	
	@FindBy(xpath=Constants.myAccount)
	public WebElement myAccount;
	
	@FindBy(xpath=Constants.myWishList)
	public WebElement myWishList;
	
	@FindBy(css=Constants.myItem)
	public WebElement myItem;
	
	@FindBy(xpath = ".//*[@id='product_addtocart_form']/div[3]/div[1]/button")
	public WebElement addToBagBtn;
	
	@FindBy(xpath = ".//*[@id='cart-container']/a/span")
	public WebElement checkOutBtn;
	
	@FindBy(xpath = "//*[@id='nav-top-link-cart']/button")
	public WebElement shoppingCart;
	
	@FindBy(xpath = "//*[@id='order-summary-container']/ul/li[1]/button")
	public WebElement checkOut;
	
	@FindBy(xpath = "//*[@id='onepage-guest-register-button']")
	public WebElement newToBaublebarContinue;
	
	@FindBy(xpath = "//*[@id='email_address']")
	public WebElement newUserEmailAddress;
	
	@FindBy(xpath = "//*[@id='password']")
	public WebElement  newUserPassword;
	
	@FindBy(xpath = "//*[@id='confirmation']")
	public WebElement  newUserConfirmPassword;
	
	@FindBy(xpath = "//*[@id='form-validate']/div[2]/div[3]/button")
	public WebElement newUserSubmit;
	
	@FindBy(xpath = "//*[@id='billing:firstname']")
	public WebElement billFirstName;
	
	@FindBy(xpath = "//*[@id='billing:lastname']")
	public WebElement billLastName;
	
	@FindBy(xpath = "//*[@id='billing:email']")
	public WebElement billEmail;
	
	@FindBy(xpath = "//*[@id='billing:street1']")
	public WebElement billStreet1;
	
	@FindBy(xpath = "//*[@id='billing:city']")
	public WebElement billCity;
	
	@FindBy(xpath = "//*[@id='billing:region_id']")
	public WebElement billState;
	
	@FindBy(xpath = "//*[@id='billing:postcode']")
	public WebElement billPostalCode;
	
	@FindBy(xpath = "//*[@id='billing:telephone']")
	public WebElement billPhone;
	
	@FindBy(xpath = "//*[@id='billing:customer_password']")
	public WebElement billPassword;
	
	@FindBy(xpath = "//*[@id='billing:confirm_password']")
	public WebElement billConfirmPassword;
	
	@FindBy(xpath = "//*[@id='billing-buttons-container']/div[2]/button")
	public WebElement billContinue;
	
	@FindBy(xpath = "//*[@id='shipping-method-buttons-container']/div[3]/button")
	public WebElement shippingMethodContinue;
	
	@FindBy(xpath = "//*[@id='checkout-payment-method-load']/dl[1]/dt/label")
	public WebElement paymentType;
		
	@FindBy(xpath = "//*[@id='payment-buttons-container']/div[3]/button")
	public WebElement paymentContinue;
	
	@FindBy(xpath = "//*[@id='braintree_cc_number']")
	public WebElement creditCard;
	
	@FindBy(xpath = "//*[@id='braintree_cc_cid']")
	public WebElement cvvNumber;
	
	@FindBy(xpath = "//*[@id='braintree_store_in_vault_div']/label")
	public WebElement saveCardCheckbox;
	
	@FindBy(xpath = "//*[@id='review-buttons-container']/div/button")
	public WebElement reviewOrder;
	
	@FindBy(xpath = "//*[@id='login-email']")
	public WebElement checkOutLogin;
	
	@FindBy(xpath = "//*[@id='login-password']")
	public WebElement checkOutPassword;
	
	@FindBy(xpath = "//*[@id='login-form']/fieldset/ul/li[3]/button")
	public WebElement checkOutLoginEnter;

	@FindBy(xpath = "html/body/div[1]/div/section/div[2]/div[2]/h1")
	public WebElement ordConfirmMsg;
	
	@FindBy(xpath="//*[@id='nav-top-link-logout']")
	public WebElement logOutLink;
	
	WebDriver driver;
	WebDriverWait wait;
	String applicationURL = (TestBase.CONFIG.getProperty("applicationURL"));
	
	
	public BaublebarPage(WebDriver dr){
		driver = dr;
		wait = new WebDriverWait(driver, 30);
	}
	
	/**
	 * Load the Baublebar site
	 * Adding up the first visit cookie to avoid loading on 15% off coupon
	 */
	public void loadBaublebar(){
		driver.manage().deleteAllCookies();
			try {
			driver.get(applicationURL);
			Thread.sleep(2000);
			Cookie ck = new Cookie("firstVisit", "1",applicationURL, "/", null,true);
			driver.manage().addCookie(ck);
			driver.get(applicationURL);
			Assert.assertEquals("The Final Say in Fashion Jewelry | BaubleBar", driver.getTitle());
		} catch (Exception e ){
			//e.printStackTrace();
		}
	}
	
	/**
	 * Place an order for existing customer
	 * @param product name
	 * @param true if new user
	 */
	public void placeOrder(String productName, boolean isNewUser){
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		addToBagBtn.click();
		try {
			Thread.sleep(2000);
		} 	catch (Exception e) {
				e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
	//	if (!checkOut.isDisplayed()){
		//	shoppingCart.click();
			//System.out.println("In Shopping cart Click ");
		//}
		checkOut.click();
		if (!isNewUser){
			login();
			checkOut();
		}		 
	}
	
	/**
	 *  Log out from user account 
	 */
	public void logout(){
		try{
		//if (isLoggedIn()){
			wait.until(ExpectedConditions.elementToBeClickable(logOutLink));
			logOutLink.click();
		//}
		}catch(Exception e){}
	}
	
	/**
	 *  Log in to user account
	 */
	public void login(){
		if(!isLoggedIn()){
		wait.until(ExpectedConditions.visibilityOf(checkOutLogin));
		checkOutLogin.sendKeys("qa@baublebar.com");
		checkOutPassword.sendKeys("test123");
		checkOutLoginEnter.click();
		}
	}
	
	/**
	 *  Checkout process for existing customer with saved account preferences 
	 */
	public void checkOut(){
		wait.until(ExpectedConditions.elementToBeClickable(billContinue));
		billContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinue));
		shippingMethodContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(paymentContinue));
		paymentType.click();
		paymentContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(reviewOrder));
		reviewOrder.click();
		String confirmMsg = "Your order has been received.";
		wait.until(ExpectedConditions.elementToBeClickable(ordConfirmMsg));
		Assert.assertEquals(confirmMsg, ordConfirmMsg.getText());		
	}
	
	/**
	 *  Create an account while checking out  
	 */
	public void createAccount(){
		wait.until(ExpectedConditions.elementToBeClickable(newToBaublebarContinue));
		newToBaublebarContinue.click();
		String strCustomerName = "NewUserCheckout" +Long.toHexString(Double.doubleToLongBits(Math.random()))+"@baublebar.com";
		wait.until(ExpectedConditions.visibilityOf(newUserEmailAddress));
		newUserEmailAddress.clear();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('email_address').setAttribute('value','NewUserCheckout" +Long.toHexString(Double.doubleToLongBits(Math.random()))+"@baublebar.com')");
		newUserPassword.clear();
		newUserPassword.sendKeys("test123");
		newUserConfirmPassword.clear();
		newUserConfirmPassword.sendKeys("test123");
		newUserSubmit.click();
	}
	
	/**
	 *  Entering billing information for new customer while checking out 
	 *  @param map of billing data 
	 */
	public void filloutBillingInfoAndCheckOut(Map<String, String> billInfo){
		//driver.executeScript("document.getElementById('elementID').setAttribute('value', 'new value for element')");// May be need to use java script instead of send keys
		billFirstName.sendKeys	(billInfo.get("firstName"));
		billLastName.sendKeys(billInfo.get("lastName"));
		billStreet1.sendKeys(billInfo.get("street"));
		billCity.sendKeys(billInfo.get("city"));
		Select dropdown = new Select(billState);
		dropdown.selectByVisibleText(billInfo.get("state"));
		billPostalCode.sendKeys(billInfo.get("zip"));
		billPhone.sendKeys(billInfo.get("phone"));
		wait.until(ExpectedConditions.elementToBeClickable(billContinue));
		billContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinue));
		shippingMethodContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(paymentContinue));
		paymentType.click();
		creditCard.sendKeys(billInfo.get("creditCard"));
		cvvNumber.sendKeys(billInfo.get("cvvNumber"));
		saveCardCheckbox.click();
		paymentContinue.click();
		wait.until(ExpectedConditions.elementToBeClickable(reviewOrder));
		reviewOrder.click();
		String confirmMsg = "Your order has been received.";
		wait.until(ExpectedConditions.elementToBeClickable(ordConfirmMsg));
		Assert.assertEquals(confirmMsg, ordConfirmMsg.getText());
		//wait.until(ExpectedConditions.elementToBeClickable(logOutLink));
		//logOutLink.click();
	}
	
	/**
	 * Checks if user is logged in to system
	 * @return true or false
	 */
	public boolean isLoggedIn(){
		if(myAccount.isDisplayed())
			return true;
		else
			return false;		
	}
	
	
	/*moved code to top Menubar
	public void signupForDiscount(String discounEmail) throws Throwable{
		//driver.manage().deleteAllCookies();
	//	driver.get(CONFIG.getProperty("applicationURL"));
		discountLink.click();
		wait.until(ExpectedConditions.elementToBeClickable(discountEmail));
		//this.switchToFrameByIndex(6);
		//email.clear();
		discountEmail.clear();
		discountEmail.sendKeys(discounEmail);
		//email.sendKeys();
		discountSubmit.click();
		//getStarted.click();
		//System.out.println("Code is " + driver.findElement(By.id("bouncex_el_18")).getText());
		//System.out.println("Code is " + driver.findElement(By.xpath("//*[@id=\"bouncex_el_18\"]/div/div")).getText());
	}
	
	public void createAccount(String accEmail, String accPassword) throws Throwable{
		driver.get(TestBase.CONFIG.getProperty("applicationURL"));
		loginLink.click();
		haveAnAcc.click();
		accountEmail.clear();
		accountEmail.sendKeys(accEmail);
		accountPassword.clear();
		accountPassword.sendKeys(accPassword);
		confirmPassword.clear();
		confirmPassword.sendKeys(accPassword);
		submit.click();
	}
	*/
	
	/**
	 *  This method is not called yet because this is work in progress
	 *  @param user email
	 *  @param user password
	 */
	public void verifyAnItemToWishList(String accEmail, String accPassword) throws InterruptedException{
		driver.get("http://www.baublebar.com/wishbone-bracelet.html");
		addToWish.click();
		String mainWindow =driver.getWindowHandle();
		Set windows=driver.getWindowHandles();
		//this method will you handle of all opened windows
		Iterator iter = windows.iterator();
		while(iter.hasNext()){
			String popupHandle = iter.next().toString();
		    if(!popupHandle.contains(mainWindow)) {
		    	driver.switchTo().window(popupHandle);
		    }
			loginEmail.clear();
	    	loginEmail.sendKeys(accEmail);
	    	loginPassword.clear();
		    loginPassword.sendKeys(accPassword);
		    loginButton.click();
			myAccount.click();
			myWishList.click();
			System.out.println("test completed");
			String actual = myItem.getText();
			String expected = "WISHBONE BRACELET";
			Assert.assertEquals(expected, actual);	
		}
	}
	
	/**
	 *  Trying figure out the number of frames present on site
	 *  @param frame index
	 */
	public void switchToFrameByIndex(int index){
       WebElement frame;
       List<WebElement> frameset=driver.findElements(By.tagName("iframe"));
       if (frameset.size() > index) {
           frame=frameset.get(index);
           driver.switchTo().frame(frame);
       }else{
           System.out.println("Number of Frames present are:"+frameset.size());
           System.out.println("Index is greater than the number of frames present.");
       }
   }
}