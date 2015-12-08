package com.baublebar.pages;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
public class BaublebarPage extends Page{
	
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
	
	@FindBy(xpath = "//*[@id='product_addtocart_form']/div[3]/div[1]/button")
	public WebElement addToBagBtn;
	
	@FindBy(xpath = ".//*[@id='cart-container']/a/span")
	public WebElement checkOutBtn;
	
	@FindBy(xpath = Constants.shoppingBag)
	public WebElement shoppingCart;
		
	@FindBy(xpath = "//*[@id='order-summary-container']/div[1]/ul/li[1]/button") 
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
	
	@FindBy(xpath = "//*[@id='gene_braintree_creditcard_cc_number']")
	public WebElement creditCard;
	
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
	
	@FindBy(xpath="//*[@id='bouncex_el_3']/input[@type='button' and @name='3_name']")
	public WebElement enterSiteWithoutCoupon;

	//@FindBy(css="#bouncex_el_20 > input[type=\"button\"]")
	
	
	@FindBy(xpath="//*[@id='bouncex_el_3']/input")
	public WebElement noThanksIDontWantToSave;
	
	@FindBy(xpath=Constants.searchProductInput)
	public WebElement searchProductInput;
	
	@FindBy(xpath=Constants.searchProduct)
	public WebElement searchProduct;
	
	@FindBy(xpath="//*[@id='bundle-product-wrapper']/section/em/em/div/div/article[1]/a[3]/div/label")
	public WebElement bundleProduct1;
	
	@FindBy(xpath="//*[@id='bundle-product-wrapper']/section/em/em/div/div/article[2]/a[3]/div/label")
	public WebElement bundleProduct2;
	
	@FindBy(xpath="//*[@id='bundle-product-wrapper']/section/em/em/div/div/article[3]/a[3]/div/label")
	public WebElement bundleProduct3;
	
	//@FindBy(xpath="//*[@id='bundle-product-wrapper']/section/em/em/div/div/article[4]/a[3]/div/label")
	@FindBy(xpath="//*[@id='bundle-product-wrapper']/section/em/em/div/div/article[4]/a/div/img")
	public WebElement bundleProduct4;
	
	//@FindBy(xpath = "//*[@id='product_addtocart_form']/em/em/div[3]/div[1]/div[2]/div[1]/button")
	//@FindBy(xpath = "//button[@type='submit'])[5]")
	//public WebElement addBundleToBagBtn;
	
	@FindBy(xpath="//*[@id='bundle-step-762']")
	public WebElement bundleBraclet1;
	
	@FindBy(xpath="//*[@id='bundle-step-744']")
	public WebElement bundle1;
	
	@FindBy(xpath="//*[@id='bundle-step-745']")
	public WebElement bundle2;
	
	@FindBy(xpath="//*[@id='bundle-step-746']")
	public WebElement bundle3;
	
	@FindBy(xpath = "//*[@id='updateCartForm']/article/div[2]/div/p[3]") 
	public WebElement returnPolicy;
	

	@FindBy(xpath = "//*[@id='product_addtocart_form']/div[1]/h1")
	public WebElement searchResultTitle;
	
	@FindBy(id = "add-to-cart-msg")
	public WebElement errorMsg;
	
	@FindBy(id = Constants.cartQty)
	public WebElement cartQty;
	
	
	boolean got20Off = false;
	///	WebDriver driver;
	//WebDriverWait wait;
	String applicationURL = (TestBase.CONFIG.getProperty("applicationURL"));
	
	
	public BaublebarPage(WebDriver driver){
	//	driver = dr;
		super(driver);
		//wait = new WebDriverWait(driver, 30);
	}
	
	/**
	 * Load the Baublebar site
	 * Adding up the first visit cookie to avoid loading on 15% off coupon
	 */
	public void loadBaublebar(){
		driver.manage().deleteAllCookies();
		try {
			driver.get(applicationURL);
			waitForLoad();
		//	Cookie ck = new Cookie("firstVisit", "1","baublebar.com", "/", null,true);
		//	driver.manage().addCookie(ck);
		//	Thread.sleep(20000);
		//	driver.get(applicationURL);
			closeTheCoupon();
			Assert.assertEquals("The Final Say in Fashion Jewelry | BaubleBar", driver.getTitle());
		} catch (Exception e ){
			e.printStackTrace();
		}
	}
	
	/**
	 * Load the Baublebar site
	 * Adding up the first visit cookie to avoid loading on 15% off coupon
	 */
	public void closeTheCoupon() throws InterruptedException{ // Wait function to wait for element    
		int trial = 0;
		while (!got20Off && trial <11 ){
            try {
            	quit15PercentAdd();
                trial++;
                System.out.println("No of Trials " +  trial);
            } catch (Exception e)   {
                	Thread.sleep(1000L);	
                	 trial++;
             }
                
          }
	}
	
	
	/** 
	 * Close 15% off ad when pops up
	 * @throws InterruptedException 
	 */
	public void quit15PercentAdd() throws InterruptedException {
		System.out.println(driver.getTitle());
		List<WebElement> frame = driver.findElements(By.tagName("iframe"));
		for(int i= 0; i< frame.size(); i++){
			String name = frame.get(i).getAttribute("id");
			System.out.println(name + " -- " + i);
			//logs.debug(name + " -- " + i);
			
			if (name.contains("iframe_overlay")){
				/*driver.switchTo().frame(name);
				try {
					//if(enterSiteWithoutCoupon.isEnabled()){
						//System.out.println(enterSiteWithoutCoupon.isDisplayed());
						//wait.until(ExpectedConditions.elementToBeClickable(enterSiteWithoutCoupon));
					////	enterSiteWithoutCoupon.click();
					//}	
					//else
				//if (noThanksIDontWantToSave.isDisplayed()){
						//System.out.println(noThanksIDontWantToSave.isDisplayed());
						WebElement noThanksIDontWantToSave =  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/form/div[2]/input"));
						wait.until(ExpectedConditions.elementToBeClickable(noThanksIDontWantToSave));
						noThanksIDontWantToSave.click();
					//}
				driver.switchTo().defaultContent();
				got20Off = true;
				Thread.sleep(1000);
				} catch(Exception e){
					e.printStackTrace();
				}*/
				try{
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("document.querySelector('div.bcx_container.bcx_rfadescale.bcx_overlay.bcx_after').parentNode.removeChild(document.querySelector('div.bcx_container.bcx_rfadescale.bcx_overlay.bcx_after'))");
					got20Off = true;
					Thread.sleep(1000);
					break;
				} catch(Exception e){
					//e.printStackTrace();
					
				}
			  }
			else {
				Thread.sleep(3000);
				
				
			}
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
		wait.until(ExpectedConditions.elementToBeClickable(addToBagBtn));
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
		wait.until(ExpectedConditions.elementToBeClickable(checkOut));
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
		//if(!isLoggedIn()){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//*[@id='login-email']")));
			checkOutLogin.sendKeys("qa@baublebar.com");
			checkOutPassword.sendKeys("test123");
			checkOutLoginEnter.click();
		
		//}
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
	 * Search product to match the entered text and verifies the results
	 */
	public void searchProduct(String searchString) {
		wait.until(ExpectedConditions.elementToBeClickable(searchProduct));
		searchProduct.click();
		searchProductInput.clear();
		searchProductInput.sendKeys(searchString);

		try {
			WebElement serachLink = waitForElement("//*[@id="+ "\"search_mini_form1\"" + "]/div/span/span/div[1]");
			String linkText = waitForText(serachLink);
			WebElement eleDevInfo = driver.findElement(By.cssSelector("div.info"));
			eleDevInfo.click();
		//	System.out.println("linkText is " + linkText );
			TestBase.APPLICATION_LOGS.debug("linkText is " + linkText );	
			
			wait.until(ExpectedConditions.visibilityOf(searchResultTitle)); 
			String winURL = driver.getCurrentUrl().toLowerCase();
			TestBase.APPLICATION_LOGS.debug("win uril is" + winURL);
			TestBase.APPLICATION_LOGS.debug("search String is" + searchString);	
			waitForLoad();
			if (winURL.contains(searchString.toLowerCase())) {
				TestBase.APPLICATION_LOGS.debug("title contains the search");
			//} else {
			///	driver.implicitly_wait(3);
			//	String winURL1 = driver.getCurrentUrl().toLowerCase();
			//	if (winURL1.contains(searchString.toLowerCase())) {
				//	System.out.println("trying second time");
				//	TestBase.APPLICATION_LOGS.debug("title contains the search");
				//}
			}else{
				Assert.fail("title did not contain the search");
			}
			if (linkText.contains(searchString.toUpperCase())) {
				TestBase.APPLICATION_LOGS.debug("Found the serch resulsts");
			} else {
				Assert.fail("Did not find search result containing text");
			}

		} catch (Exception e) {
			Assert.fail("Did not find seach result containing text");
			e.printStackTrace();
		}
		// searchProductInput.sendKeys(Keys.ENTER);
	}
	
	/**0
	 *  Entering billing information for new customer while checking out 
	 *  @param map of billing data 
	 */
	public void filloutBillingInfoAndCheckOut(Map<String, String> billInfo){
		//driver.executeScript("document.getElementById('elementID').setAttribute('value', 'new value for element')");// May be need to use java script instead of send keys
		wait.until(ExpectedConditions.visibilityOf(billFirstName));
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
		ccExpirMonth.sendKeys(billInfo.get("ccExpirMonth"));
		ccExpirYear.sendKeys(billInfo.get("ccExpirYear"));
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
	
	/**
	 * Verifies that all the footer links are loading okay
	 * 
	 */
	public void testFooter() {
		WebElement footer = driver.findElement(By.xpath("html/body/div[1]/div/section/footer")); // Get Footer element which contains all footer links
		List<WebElement> footlinks = footer.findElements(By.tagName("a"));
		int i = footer.findElements(By.tagName("a")).size(); // Get number of links
		for (int j = 0; j < i; j++) { // create loop based upon number of links // to traverse all links
			footer = driver.findElement(By.xpath("html/body/div[1]/div/section/footer")); 
			// We are re-creating "footer" webelement as DOM changes after navigate.back()
			String mainWindow = driver.getWindowHandle();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
			TestBase.APPLICATION_LOGS.debug("Name of Link "+ footer.findElements(By.tagName("a")).get(j).getText());
			WebElement ele = footer.findElements(By.tagName("a")).get(j);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			try {
				ele.click();
			} catch (Exception e) {
				ele.click();
				// e.printStackTrace();
			}
			//ele.click();
			try {
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//footer"))));
			} catch (Exception e) {
				
				// e.printStackTrace();
			}
			Set windows = driver.getWindowHandles();
			int numWins = windows.size();
			if (numWins > 1) {
				Iterator iter = windows.iterator();
				while (iter.hasNext()) {
					String popupHandle = iter.next().toString();
					if (!popupHandle.contains(mainWindow)) {
						driver.switchTo().window(popupHandle);
						if (driver.getTitle().contains("404")) {
							System.out.println("404 Found");
							TestBase.APPLICATION_LOGS.debug("Pop up window title"+ driver.getTitle());
							Assert.fail();
						}
						driver.close();
						driver.switchTo().window(mainWindow);
					}
				}

			} else {
				if (driver.getTitle().contains("404")) {
					System.out.println("404 Found");
					TestBase.APPLICATION_LOGS.debug("Failed"+ driver.getTitle());
					Assert.fail();
				}
				driver.navigate().back();
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
			}

		}
	}

	/**
	 * Add bundle Products. Verifies the error pops up when required selections are not made
	 * @throws InterruptedException 
	 * 
	 */
	public void addBundleProduct(String productName) {
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		waitForLoad();
		WebElement eleTitle = driver.findElement(By.xpath("//*[@id='product_addtocart_form']/em/em/div[1]/h1"));
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id='product_addtocart_form']/em/em/div[1]/h1"))));
		System.out.println("Title " + eleTitle.getText());
		
		wait.until(ExpectedConditions.visibilityOf(bundleProduct1));
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct1));
		
		int trial = 0;
		while ( trial < 5) {
			try {
				bundleProduct1.click();
				String bundleBraclet1_product_id = bundleBraclet1.getAttribute("data-set-item");
				System.out.println("bundleBraclet1_product_id in addBundleProduct is " +bundleBraclet1_product_id);
				if (bundleBraclet1_product_id != null)
					break;
				trial++;
				System.out.println("trial no " +trial);
			} catch (Exception e){
				e.printStackTrace();
				trial++;
			}
				
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct2));
		bundleProduct2.click();
		String errorMessage = "Required options are not selected.";
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//button[@type='submit'])[5]"))));
		driver.findElement(By.xpath("(//button[@type='submit'])[5]")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("add-to-cart-msg"))));
		String errorMsgText = driver.findElement(By.id("add-to-cart-msg")).getText();
		Assert.assertEquals(errorMessage, errorMsgText);
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct3));
		bundleProduct3.click();
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct4));
		bundleProduct4.click();
		
		driver.findElement(By.xpath("(//button[@type='submit'])[5]")).click();
		wait.until(ExpectedConditions.visibilityOf(cartQty));
		
		wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
		shoppingCart.click();
		wait.until(ExpectedConditions.visibilityOf(returnPolicy));
		String returnPolicytext = returnPolicy.getText();
		Assert.assertEquals(returnPolicytext, "If you need to return this purchase, you must return the whole set. Bracelets are not available individually at the promotional price.");
	}
	
	/**
	 * Refreshing page should preserve the selection
	 * 
	 */
	public void refreshBundle(String productName) {
		String productURl =  applicationURL + productName +".html";
		driver.get(productURl);
		waitForLoad();
		WebElement eleTitle = driver.findElement(By.xpath("//*[@id='product_addtocart_form']/em/em/div[1]/h1"));
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id='product_addtocart_form']/em/em/div[1]/h1"))));
		
		wait.until(ExpectedConditions.visibilityOf(bundleProduct1));
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct1));
		
		int trial = 0;
		while ( trial < 5) {
			try {
				bundleProduct1.click();
				String bundle1_product_id = bundle1.getAttribute("data-set-item");
				System.out.println("bundle1_product_id in refreshBundle is" +bundle1_product_id);
				if (bundle1_product_id !=null)
					break;
				trial++;
				System.out.println("trial no " +trial);
			} catch (Exception e){
				e.printStackTrace();
				trial++;
			}
			}
		
		
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct2));
		bundleProduct2.click();
		wait.until(ExpectedConditions.elementToBeClickable(bundleProduct3));
		bundleProduct3.click();
		
		String bundle1_product_id = bundle1.getAttribute("data-set-item");
		String bundle2_product_id = bundle2.getAttribute("data-set-item");
		String bundle3_product_id = bundle3.getAttribute("data-set-item");
		
		driver.navigate().refresh();
		waitForLoad();
		
		String newBundle2_product_id = null;
		int trialAfterRefresh = 0;
		try {
			while ( trialAfterRefresh < 5) {
				//bundleProduct1.click();
				WebElement new_bundle2 = driver.findElement(By.xpath("//*[@id='bundle-step-745']"));
				newBundle2_product_id = new_bundle2.getAttribute("data-set-item");
				System.out.println("newBundle2_product_id in refreshBundle is " + newBundle2_product_id); //will remove print after few runs
				if (newBundle2_product_id !=null){
					System.out.println("newBundle2_product_id  in block " + newBundle2_product_id);
					break;
				}
				trialAfterRefresh++;
				System.out.println("trialAfterRefresh no " + trialAfterRefresh);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
			
		WebElement new_bundle3 = driver.findElement(By.xpath("//*[@id='bundle-step-746']"));
		String newBundle3_product_id = new_bundle3.getAttribute("data-set-item");
		System.out.println("newBundle3_product_id in refreshBundle is "+ newBundle3_product_id);
		
		WebElement new_bundle1 = driver.findElement(By.xpath("//*[@id='bundle-step-744']"));
		String newBundle1_product_id = new_bundle1.getAttribute("data-set-item");
		System.out.println("newBundle1_product_id in refreshBundle is "+newBundle1_product_id);
			
		Assert.assertEquals(bundle1_product_id,newBundle1_product_id);
		Assert.assertEquals(bundle2_product_id,newBundle2_product_id);
		Assert.assertEquals(bundle3_product_id,newBundle3_product_id);
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