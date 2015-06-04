package com.baublebar.pages;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.baublebar.testcases.TestBase;
import com.baublebar.util.Constants;
import com.baublebar.util.TestUtil;

/**
 * This is the page for top navigation bar of Baublebar
 * The class contains methods to load top navigation options
 * 
 * @author Maitri Acharya
 */
public class TopMenuBarPage{
	
	@FindBy(xpath=Constants.discountLink)
	public WebElement discountLink;
	
	@FindBy(xpath=Constants.discountEmail)
	public WebElement discountEmail;
	
	@FindBy(xpath=Constants.discountSubmit)
	public WebElement discountSubmit;	
	
	//@FindBy(xpath="//*[@id='email-drawer']/div/div[2]/p[1]")
	//public WebElement confirmationText;	
	
	@FindBy(xpath="//*[@id='email-signup']/div/div[2]/p[1]")
	public WebElement confirmationText;	
	
	@FindBy(name="email")
	public WebElement email;
	
	@FindBy(css="#bouncex_el_2 > input[type=\"submit\"]")
	public WebElement getStartedNow;
	
	@FindBy(xpath="//*[@id='bouncex_el_3']/input[@type='button' and @name='3_name']")
	public WebElement enterSiteWithoutCoupon;

	@FindBy(css="#bouncex_el_20 > input[type=\"button\"]")
	public WebElement noThanksIDontWantToSave;
	
	@FindBy(xpath="//*[@id='nav-top-link-login']/a")
	public WebElement loginLink;
	
	@FindBy(xpath="//*[@id='nav-top-link-logout']")
	public WebElement logOutLink;
	
	@FindBy(xpath="html/body/div[1]/div/section/div[2]/div[2]/h1")
	public WebElement logOutMsg;
		
	@FindBy(xpath=Constants.shoppingBag)
	public WebElement shoppingBag;
	
	@FindBy(xpath="//*[@id='global']/div[1]/div[2]/ul/li[3]/a")
	public WebElement contactUs;
	
	@FindBy(xpath="html/body/div[1]/div/section/div[2]/div[2]/section[1]/div[1]/ul/li[1]/p/a")
	public WebElement contactUsEmail;
	
	@FindBy(xpath="//*[@id='global']/div[1]/div[2]/ul/li[4]/a")
	public WebElement visitUs;
	
	@FindBy(xpath="//*[@id='storelocator']/div[1]/h1")
	public WebElement locationsTitle;	

	@FindBy(css="#login-form-container > div.shell > p > a")
	public WebElement haveAnAcc;
	
	@FindBy(xpath="//*[@id=\"acc-email\"]")
	public WebElement accountEmail;
	
	@FindBy(xpath="//*[@id=\"acc-password\"]")
	public WebElement accountPassword;
	
	@FindBy(xpath="//*[@id=\"acc-password2\"]")
	public WebElement confirmPassword;
	
	@FindBy(css="#drawer-create-account-form > button")
	public WebElement submit;	

	@FindBy(xpath="//*[@id='login-create-account-content']/a")
	public WebElement closeLogin;
	
	@FindBy(xpath="//*[@id='product_addtocart_form']/div[3]/a[1]")
	public WebElement addToWish;
	
	@FindBy(css="#mini-login")
	public WebElement loginEmail;
	
	@FindBy(css="#mini-password")
	public WebElement loginPassword;
	
	@FindBy(xpath="//*[@id='drawer-login-form']/div/div/div[1]/button")
	public WebElement loginButton;
	
	@FindBy(xpath="//*[@id='login-form-container']/div[2]/h1")
	public WebElement loginUserMessage;
	
	@FindBy(xpath="//*[@id='create-form-container']/div[2]/h1")
	public WebElement createNewAccount;	
	
	@FindBy(css=".loginLink.btn.btnSm.btnDefault>span")
	public WebElement LoginToYourAccount;	
	
	//	@FindBy(xpath="//*[@id='customer_name']")	
	//public WebElement customerName;	

	@FindBy(xpath="//*[@id='nav-top-link-my-account']/a")	
	public WebElement myAccount;
	
	@FindBy(css="#my-account > ul > li:nth-child(1) > a")	
	public WebElement myDashboard;
	
	@FindBy(xpath="//*[@id='my-account']/ul/li[2]/a")	
	public WebElement myHearts;
	
	@FindBy(xpath="//*[@id='my-account']/ul/li[3]/a")	
	public WebElement myWaitList;
	
	@FindBy(xpath="//*[@id='my-account']/ul/li[4]/a")	
	public WebElement myValPoints;
	
	@FindBy(xpath="//*[@id='my-account']/ul/li[5]/a")	
	public WebElement inviteFriends;
		
	@FindBy(xpath="//*[@id='nav-top-link-my-account']/ul/li[6]/a")
	public WebElement myWishList;
	
	@FindBy(css="#wishlist-view-form > fieldset > ul > li.item.first > h2 > a")
	public WebElement myItem;
	
	@FindBy(css=".sub-title.account_dashboard_subHeading")
	public WebElement myDashboardTitle;
	
	@FindBy(css=".account_dashboard_genHeading.text-center")
	public WebElement myHeartsTitle;
	
	@FindBy(xpath="//*[@id='waitlist-view-form']/div/h2")
	public WebElement myWaitListTitle;
	
	//@FindBy(xpath="html/body/div[1]/div/section/div[3]/div[2]/div[2]/div[1]/h1")
	@FindBy(xpath="html/body/div[1]/div/section/div[2]/div[2]/div[2]/div[1]/h1")
	public WebElement myValPointTitle;
	
	//@FindBy(xpath="html/body/div[1]/div/section/div[3]/div[2]/div[2]/div/div[1]/p")
	@FindBy(xpath="html/body/div[1]/div/section/div[2]/div[2]/div[2]/div/div[1]/h2")
	public WebElement inviteFriendsTitle;
	
	public WebDriver driver;
	WebDriverWait wait;
	String newCustName;

	
	public TopMenuBarPage(WebDriver dr){
		driver = dr;
		wait = new WebDriverWait(dr, 30);
	}
	
	/**
	 * Sign up for 15% discount 
	 * @param user email
	 */
	public void signupForDiscount(String email) throws Throwable{
		//driver.navigate().to(CONFIG.getProperty("applicationURL"));
		//	quit15PercentAdd();
		wait.until(ExpectedConditions.elementToBeClickable(discountLink));
		discountLink.click();	
		wait.until(ExpectedConditions.elementToBeClickable(discountEmail));
		discountEmail.clear();
		discountEmail.sendKeys(email);
		discountEmail.sendKeys(Keys.RETURN);
		try {
			wait.until(ExpectedConditions.visibilityOf(confirmationText));
			System.out.println(confirmationText.getText());
		} catch (Exception e){ 
			//e.printStackTrace();
			discountLink.click();
			wait.until(ExpectedConditions.visibilityOf(confirmationText));
			///System.out.println(confirmationText.getText());
		}
		Assert.assertEquals(confirmationText.getText(), "You're all signed up!" );
	}
	
	/**
	 * Create New Bauble bar user Account 
	 * @param new user name
	 * @param password 
	 */
	public void createAccount(String accUser, String accPassword) throws Throwable{
		//driver.get(CONFIG.getProperty("url"));
		//quit15PercentAdd();
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		loginLink.click();
		wait.until(ExpectedConditions.visibilityOf(accountEmail));
		accountEmail.clear();
		String strCustomerName = accUser +Long.toHexString(Double.doubleToLongBits(Math.random()));
		String userAccEmail = strCustomerName+"@baublebar.com"; 
		TestBase.APPLICATION_LOGS.debug("generated email is" + userAccEmail);
		///System.out.println("generated email is" + userAccEmail);
		accountEmail.sendKeys(userAccEmail);
		accountPassword.clear();
		accountPassword.sendKeys(accPassword);
		confirmPassword.clear();
		confirmPassword.sendKeys(accPassword);
		submit.click();
		WebElement newCustomerName = waitForElement("//*[@id='customer_name']");
		try {
			 newCustName = newCustomerName.getText();		
		} catch (Exception e) {
			 newCustomerName = waitForElement("//*[@id='customer_name']");
			 newCustName = newCustomerName.getText();
		}	
		 Assert.assertTrue(newCustName.contains("AUTOMATION"));
	}
	
	/** 
	 * Log in for existing account 
	 * @param new user name
	 * @param password 
	 */
	public void doLogin(String username, String password) throws InterruptedException{
		//driver.navigate().to(CONFIG.getProperty("url"));
		//quit15PercentAdd(); //adding the first visit cookie so will not need it here 
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		loginLink.click();
		//driver.switchTo().activeElement();// requires when quitting the 15% add frame
		//wait.until(ExpectedConditions.visibilityOf(loginEmail));
		//System.out.println("Entering user credentials");
		loginEmail.sendKeys(username);
		loginPassword.sendKeys(password);
		loginButton.click();
		WebElement customerName = waitForElement("//*[@id='customer_name']");
		//System.out.println("Customer Name " + customerName.getText());
		Assert.assertEquals(customerName.getText(), ("QA"));
	}
	
	/** 
	 * Log out from user account
	 */
	public void logout(){
		wait.until(ExpectedConditions.elementToBeClickable(logOutLink));
		logOutLink.click();
		wait.until(ExpectedConditions.visibilityOf(logOutMsg));
		//System.out.println("Logout Msg" + logOutMsg.getText());
		Assert.assertEquals(logOutMsg.getText(), "You are now logged out");
	}
	
	/** 
	 * Close 15% off ad when pops up
	 */
	public void quit15PercentAdd(){
		System.out.println(driver.getTitle());
		List<WebElement> frame = driver.findElements(By.tagName("iframe"));
		for(int i= 0; i< frame.size(); i++){
		String name = frame.get(i).getAttribute("id");
		System.out.println(name + " -- " + i);
		//logs.debug(name + " -- " + i);
		
		if (name.contains("iframe_overlay")){
			driver.switchTo().activeElement();
			driver.switchTo().frame(i);
			try {
				if(enterSiteWithoutCoupon.isEnabled()){
					//System.out.println(enterSiteWithoutCoupon.isDisplayed());
					//wait.until(ExpectedConditions.elementToBeClickable(enterSiteWithoutCoupon));
					enterSiteWithoutCoupon.click();
				}	
				else if (noThanksIDontWantToSave.isDisplayed()){
					//System.out.println(noThanksIDontWantToSave.isDisplayed());
					wait.until(ExpectedConditions.elementToBeClickable(noThanksIDontWantToSave));
					noThanksIDontWantToSave.click();
				}
			driver.switchTo().defaultContent();
			} catch(Exception e){
				e.printStackTrace();
			}
		  }
	    }
	  }

	/** 
	 * Loads the shopping cart
	 */
	public void clickShoppingCart(){
		wait.until(ExpectedConditions.elementToBeClickable(shoppingBag));
		shoppingBag.click();
		WebElement element = wait.until(ExpectedConditions.visibilityOf(LoginToYourAccount));
		String text = element.getText();
		Assert.assertEquals(text, "LOGIN TO YOUR ACCOUNT");
	}
	
	/**
	 * Loads the Contact Us Page
	 */
	public void clickContactUs(){
		wait.until(ExpectedConditions.elementToBeClickable(contactUs));
		contactUs.click();
		Assert.assertEquals(contactUsEmail.getText(), "helpme@baublebar.com");
	}
	
	/**
	 * Loads the Visit Us Page
	 */
	public void clickVisitUs(){
		wait.until(ExpectedConditions.elementToBeClickable(visitUs));
		visitUs.click();
		Assert.assertEquals(locationsTitle.getText(), "LOOKING TO SHOP BAUBLEBAR IN PERSON? LET'S GET STARTED:");
	}
	
	/*
	 * Loads the Visit Us Page
	
	public void optionTopMenuBar(String option) throws InterruptedException{
		List <WebElement> topLinks = driver.findElements(By.xpath("//*[@id='nav_top_list']/li")); //black top menu bar
		System.out.println(topLinks.size());
		for(int i=1; i<=topLinks.size()-1; i++){
			if(topLinks.get(i).isDisplayed()){
				System.out.println(topLinks.get(i).getText());
				if(topLinks.get(i).getText().equalsIgnoreCase(option)){
					//System.out.println(i);
					driver.findElement(By.xpath("//*[@id='nav_top_list']/li["+(i+1)+"]")).click();
					Thread.sleep(4000L);
					//System.out.println(driver.getCurrentUrl());
					break;
				}
			}
		}
	} */

	/*public void getMyAccount() throws InterruptedException{
		Thread.sleep(3000L);
		driver.switchTo().defaultContent();
		//wait.until(ExpectedConditions.elementToBeClickable(myAccount));
		myAccount.click();
		//getMyAccountPage();
	}
	*/
	
	/** 
	 * Loads different options under my accounts menu
	 * @param name of the option
	 */
	public void myAccountOptions(String option) throws InterruptedException{
		Thread.sleep(3000L);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='nav-top-link-my-account']/a"))));
		element.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(option.equalsIgnoreCase("Dashboard")){
			wait.until(ExpectedConditions.elementToBeClickable(myDashboard));
			myDashboard.click();
			Assert.assertEquals(myDashboardTitle.getText(), "HELLO, QA BAUBLEBAR!");
		} else if(option.equalsIgnoreCase("My Hearts")){
			wait.until(ExpectedConditions.elementToBeClickable(myHearts));
			myHearts.click();
			Assert.assertEquals(myHeartsTitle.getText(), "LOVE 'EM, BUT DON'T LEAVE 'EM");
		} else if(option.equalsIgnoreCase("My Waitlist")){
			wait.until(ExpectedConditions.elementToBeClickable(myWaitList));
			myWaitList.click();
			Assert.assertEquals(myWaitListTitle.getText(), "GOOD THINGS COME TO THOSE WHO WAIT");	
		} else if(option.equalsIgnoreCase("vault points")){
			wait.until(ExpectedConditions.elementToBeClickable(myValPoints));
			myValPoints.click();
			Assert.assertEquals(myValPointTitle.getText(), "Vault Point Summary");	
		} else if(option.equalsIgnoreCase("invite friends")){
			wait.until(ExpectedConditions.elementToBeClickable(inviteFriends));
			inviteFriends.click();
			Assert.assertEquals(inviteFriendsTitle.getText(), "INVITE FRIENDS");	
		}
	}
	
	
	//Wait for element to appear/load on page
	public WebElement waitForElement(String xPath) throws InterruptedException{ // Wait function to wait for element    
        for (int second = 0; ; second++){
            if (second >= 60) Assert.fail("timeout");
	            try {
	            	WebElement webElement = driver.findElement(By.xpath(xPath));
	                if (webElement !=null) 
	                    return webElement;
	            }catch (Exception e)   {
	                	Thread.sleep(1000L);	
	            }
                Thread.sleep(1000);
        }
    }	
}