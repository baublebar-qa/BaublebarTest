package com.baublebar.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Defines locators shared among pages
 * 
 * Author - Maitri Acharya
 */
public class Constants {	

	//Baulble Bar
	public static final String discountLink = "html/body/header/div/nav/ul/li[5]/a";
	
	public static final String discountEmail = "//*[@id='drawer-email']";
	public static final String discountSubmit = "//*[@id='email-drawer']/div/div[3]/form/div[3]/button[2]";
	
	public static final String footer = "html/body/section/footer";
	
	public static final String enter = "//*[@id='email-drawer']/div/div[3]/form/div[3]/button[2]";
	public static final String email = "email";
	public static final String getStarted = "#bouncex_el_2 > input[type=\"submit\"]";
	public static final String addToWish = "//*[@id='product_addtocart_form']/div[3]/a[1]";
	//public static final String loginEmail = "#login-email";
	//public static final String loginPassword = "#login-password";
	public static final String loginEmail = "#mini-login";
    public static final String loginPassword = "#mini-password";
	public static final String loginLink = "#nav-top-link-login > a";
	public static final String haveAnAcc = "#login-form-container > div.shell > p > a";
	public static final String accountEmail = "//*[@id=\"acc-email\"]";
	public static final String accountPassword = "//*[@id=\"acc-password\"]";
	public static final String confirmPassword = "//*[@id=\"acc-password2\"]";
	public static final String submit = "#drawer-create-account-form > button";
	//public static final String loginButton = "#wrapper-modal-login > div:nth-child(3) > form > div.form-buttons > button";
	public static final String loginButton = "//*[@id='drawer-login-form']/div/div/div[1]/button";
	public static final String myAccount = "//*[@id='nav-top-link-my-account']/a";
	//public static final String myWishList = "body > main > div > section.col-left > div > div.block-content > ul > li:nth-child(6) > a";
	//public static final String myWishList = "html/body/main/div/section[2]/div/div[2]/ul/li[6]/a";
	public static final String myWishList = "//*[@id='my-account']/ul/li[2]/a";
	//public static final String myItem = "#wishlist-view-form > fieldset > ul > li.item.first > h2 > a";
	public static final String myItem = "//*[@id='wishlist-view-form']/fieldset/div/ul/li/div[2]/h3/a[1]";
	//public static final String shoppingBag = "//*[@id='nav-top-link-cart']/a/span";
	public static final String shoppingBag = "span.header_subNav_link--isHiddenMobile";
	
	public static final String addToBag = "html/body/div[1]/main/section[1]/section[3]/section[3]/div/a[1]";
	
	public static final String 	viewShoppingBag = "//*[@id='cart-container']/a/span";
	
	public static final String searchProductInput = "//*[@id='search1']";
	
	public static final String searchProduct = "//*[@id='main-nav']/div/div/button";
	
	public static final String cartQty =  "html/body/header/div/nav/ul/li[6]/span";
	public static final String checkOutURl = "/checkout/cart/";
	
	public static final String checkOutBtn = "//*[@id='order-summary-container']/div[1]/ul/li[1]/button";
	
	
	
}