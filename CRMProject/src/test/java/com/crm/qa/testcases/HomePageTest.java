package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class HomePageTest extends TestBase {

	LoginPage login;
	HomePage Home;
	ContactsPage contacts;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initilization();
		login = new LoginPage();
		contacts = new ContactsPage();
		Home = login.login(pr.getProperty("username"), pr.getProperty("password"));
	}

	@Test(priority = 1)
	public void verify_HomePage_Title() {
		String title = Home.verifyPageTitle();
		Assert.assertEquals(title, "CRMPRO", "Home Page title is not matched");
	}

	@Test(priority = 2)
	public void verify_Correct_UserDetails_On_HomePage() {
		Assert.assertTrue(Home.verifyLoggedInUserDetails());

	}

	@Test(priority = 3)
	public void verify_Contact_Link() {
		contacts = Home.clickOnContactsLink();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
