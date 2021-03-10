package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.TestAllureListener;

@Listeners(TestAllureListener.class)
public class ProductInfoPageTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void AccSetUP() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productInfoDataTest() {
		searchResultPage = accPage.doSearch("Macbook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertTrue(actProductInfoMap.get("name").equals("MacBook Pro"));
		softAssert.assertTrue(actProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(actProductInfoMap.get("price").equals("$2,000.00"));
		softAssert.assertAll();

	}

}
