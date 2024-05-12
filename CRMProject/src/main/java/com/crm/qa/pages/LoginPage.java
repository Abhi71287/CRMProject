package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

//	Page Factory or Object repo
	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpButton;

	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;

//	initializing the page objects or object repo
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

//	Actions
	public String valiadteLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMLogo() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pw) {
		username.sendKeys(un);
		password.sendKeys(pw);
		loginButton.click();
		return  new HomePage();
		
		
	}
	
	

}
