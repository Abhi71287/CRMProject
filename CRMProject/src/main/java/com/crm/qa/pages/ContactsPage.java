package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	@CacheLookup //it will store element in small memory and ll pick in from there and ll
//	not come every time to find the webElement from the pagefactory and ll improve performance by reducing execution time.
	WebElement contactLabel;

	@FindBy(xpath = "//select[@name='title']")
	WebElement titleDropdown;

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstNameTextBox;

	@FindBy(xpath = "//input[@name='surname']")
	WebElement lastNameTextBox;

	@FindBy(name = "client_lookup")
	WebElement companyTextBox;

	@FindBy(xpath = "//input[@type='submit' and @value ='Save']")
	WebElement saveButton;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactLabel() {
		return contactLabel.isDisplayed();
	}

	public void selectContactByName(String name) {
		driver.findElement(By.xpath(
				"//a[contains(text(),'" + name + "')]//parent::td//preceding-sibling::td//input[@type='checkbox']"))
				.click();
	}

	public void createNewContact(String title, String firstName, String lastName, String companyName) {
		Select select = new Select(titleDropdown);
		select.selectByVisibleText(title);
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(lastName);
		companyTextBox.sendKeys(companyName);
		saveButton.click();
	}

}
