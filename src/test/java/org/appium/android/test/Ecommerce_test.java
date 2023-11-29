package org.appium.android.test;

import org.appium.pageObjects.android.CartPage;
import org.appium.pageObjects.android.FormPage;
import org.appium.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Ecommerce_test extends AndroidBaseTest {

	@Test
	public void FillForm() {
		FormPage formPage = new FormPage(driver);
		formPage.setNameField("Testing");
		formPage.setGender("Male");
		formPage.setCountry("Argentina");
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
		double priceTotal = cartPage.getProductsSum();
		double totalAmountDisplayed = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(priceTotal, totalAmountDisplayed);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
	}
}
