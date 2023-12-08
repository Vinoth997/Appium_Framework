package org.appium.ios.test;

import org.appium.baseTest.IOSBaseTest;
import org.appium.pageObjects.ios.AlertViews;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UiKitApp_test extends IOSBaseTest {

	@Test
	public void HomePageTest() {
		AlertViews alertViews = new AlertViews(driver);
		alertViews.selectAlertViews();
		alertViews.fillTextLabel("ios test");
		String actualMessage = alertViews.getConfirmMessage();
		Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");
	}
}
