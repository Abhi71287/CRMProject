package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtils;

public class ContactsPageTest extends TestBase {

	LoginPage login;
	HomePage Home;
	ContactsPage contacts;
	String sheetName = "contact";

	public ContactsPageTest() {
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
	public void verify_Contacts_Label_In_Contacts_Page() {
		Home.clickOnContactsLink();
		Assert.assertTrue(contacts.verifyContactLabel(), "Contact label is not displayed");

	}

	@Test(priority = 2)
	public void verify_Select_Contact_Checkbox_By_Name() {
		Home.clickOnContactsLink();
		contacts.selectContactByName("ui ui");
	}

	@DataProvider
	public Object[][] getCRMTestData() {
		Object[][] data = TestUtils.getTestData(sheetName);
		return data;
	}

	@Test(priority = 3, dataProvider = "getCRMTestData")
	public void verify_Add_New_Contact_Hunctionality(String title, String firstName, String lastName,
			String companyName) {
		Home.clickOnNewContactLink();
		contacts.createNewContact(title, firstName, lastName, companyName);;

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
