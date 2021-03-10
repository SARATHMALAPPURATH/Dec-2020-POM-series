package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.xpath("//a[@class='thumbnail']/img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	// div#content ul.list-unstyled:nth-of-type(1) li
	//// div[@id='content']//ul[@class='list-unstyled'][1]/li
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetElementText(productHeader);
	}

	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}

	public Map<String, String> getProductInformation() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());

		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		System.out.println("total product metadata: " + productMetaDataList.size());
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		for (WebElement e : productMetaDataList) {
			String[] meta = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}

		// price
		List<WebElement> productPriceList = elementUtil.getElements(productPriceData);
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());

		return productInfoMap;
	}

	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);
	}

	public void addToCart() {
		elementUtil.doClick(addToCartBtn);
	}

	public String getSuccessMessage() {
		return elementUtil.doGetElementText(successMessage);
	}

}
