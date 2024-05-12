package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtils;

public class HomePage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'User: Abhishek Shrivastava')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyLoggedInUserDetails() {
		TestUtils.switchToFrame("mainpanel");
		boolean username = userNameLabel.isDisplayed();
		return username;
	}

	public ContactsPage clickOnContactsLink() {
		TestUtils.switchToFrame("mainpanel");
		contactLink.click();
		return new ContactsPage();
	}

	public void clickOnNewContactLink() {
		TestUtils.switchToFrame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(contactLink).build().perform();
		newContactLink.click();
	}

	public DealsPage clickOnDealsLink() {
		TestUtils.switchToFrame("mainpanel");
		dealsLink.click();
		return new DealsPage();
	}

	public TasksPage clickOnTasksLink() {
		TestUtils.switchToFrame("mainpanel");
		tasksLink.click();
		return new TasksPage();
	}

}
