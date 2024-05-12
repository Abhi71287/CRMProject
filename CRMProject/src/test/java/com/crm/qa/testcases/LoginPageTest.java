package com.crm.qa.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test; 

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.WebEventListener;

public class LoginPageTest extends TestBase {

	LoginPage login;
	HomePage home;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void Setup() {
		initilization();
		login = new LoginPage();

	}

	@Test(priority = 1)
	public void verify_Page_Title() {
		String title = login.valiadteLoginPageTitle();
		Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support.");
	}

	@Test(priority = 2)
	public void verify_CRM_Logo() {
		boolean flag = login.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 3)
	public void verify_Login() {
		home =login.login(pr.getProperty("username"), pr.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
