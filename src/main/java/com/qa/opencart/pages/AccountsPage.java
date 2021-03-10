package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	ElementUtil elementUtil;

	private By logo = By.xpath("//a[text()='Your Store']");
	private By accHeader = By.cssSelector("#content h2");
	private By searchField = By.name("search");
	private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public boolean isLogoExist() {
		return elementUtil.doIsDisplayed(logo);
	}

	public String getAccPageTitle() {
		return elementUtil.waitForTitleIs(5, Constants.ACC_PAGE_TITLE);
	}

	public int getAccountPageHeadersCount() {
		return elementUtil.getElements(accHeader).size();
	}

	public List<String> getAccountPageHeadersList() {
		List<WebElement> accList = elementUtil.getElements(accHeader);
		List<String> accSectionList = new ArrayList<String>();
		for (WebElement e : accList) {
			accSectionList.add(e.getText());
		}
		return accSectionList;
	}

	public SearchResultPage doSearch(String productName) {
		System.out.println("Searching fo the product: " + productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}

}
