package org.appium.android.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.appium.baseTest.AndroidBaseTest;
import org.appium.pageObjects.android.CartPage;
import org.appium.pageObjects.android.FormPage;
import org.appium.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Ecommerce_test extends AndroidBaseTest {

	@DataProvider
	public Object[][] singleDataSet() {
		return new Object[][] { { "Testing", "Male", "Argentina" } };
	}

	@DataProvider
	public Object[][] multipleDataSet() {
		return new Object[][] { { "Testing", "Male", "Argentina" }, { "Demo", "Female", "Argentina" } };
	}

	@DataProvider
	public Object[][] getJsonData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")
				+ "//src//test//java//org//appium//android//testData//eCommerce_testData.json");
		return new Object[][] { { data.get(0) } };
	}

	@Test(dataProvider = "getJsonData", enabled = true, groups = { "smoke" })
	public void FillForm(HashMap<String, String> input) {
		FormPage formPage = new FormPage(driver);
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountry(input.get("country"));
		formPage.submitForm();
	}

	@Test(dataProvider = "singleDataSet", priority = 0, enabled = false)
	public void FillForm(String name, String gender, String country) {
		FormPage formPage = new FormPage(driver);
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountry(country);
		formPage.submitForm();
	}

	@Test(dependsOnMethods = "FillForm")
	public void AddToCart() throws InterruptedException {
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(1);
		productCatalogue.goToCartPage();
	}

	@Test(dependsOnMethods = "AddToCart")
	public void CartPage() throws InterruptedException {
		CartPage cartPage = new CartPage(driver);
		double priceTotal = cartPage.getProductsSum() + 11;
		double totalAmountDisplayed = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(priceTotal, totalAmountDisplayed);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
	}
}
