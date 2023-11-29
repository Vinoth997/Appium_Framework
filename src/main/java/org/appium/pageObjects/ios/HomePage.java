package org.appium.pageObjects.ios;

import org.appium.utils.ios.IOSActions;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends IOSActions {

	IOSDriver driver;

	public HomePage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
