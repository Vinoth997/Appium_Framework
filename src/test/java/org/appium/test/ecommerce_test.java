package org.appium.test;

import org.appium.pageObjects.android.FormPage;
import org.appium.pageObjects.android.ProductCatalogue;
import org.testng.annotations.Test;

public class ecommerce_test extends BaseTest{
	
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

}
