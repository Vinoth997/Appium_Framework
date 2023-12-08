package org.appium.pageObjects.android;

import java.util.List;

import org.appium.utils.android.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;//25,240

public class ProductCatalogue extends AndroidActions {

	AndroidDriver driver;

	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;

	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();
	}

	public void goToCartPage() throws InterruptedException {
		cartButton.click();
		Thread.sleep(3000);
	}

}
