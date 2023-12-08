package org.appium.android.test;

import java.util.HashMap;
import java.util.Map;

import org.appium.baseTest.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testSetup extends AndroidBaseTest {

	@BeforeMethod
	public void preSetup() {
		// set the screen to homepage
//		Activity activity = new Activity("io.appium.android.apis",
//				"io.appium.android.apis.preference.PreferenceDependencies");
//		((StartsActivity) activity).currentActivity();
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
//				"com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));

		Map<String, Object> args = new HashMap<>();
		args.put("appPackage", "com.androidsample.generalstore");
		args.put("appActivity", "com.androidsample.generalstore.MainActivity");
		driver.executeScript("mobile: startActivity", args);

	}

	@Test
	public void Fillform_ErrorValidation() {
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id//text1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");
	}

	@Test
	public void Fillform_PositiveFlow() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("testing");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id//text1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
	}

	@DataProvider
	public Object[][] setData() {
		return new Object[][] { { "Testing", "Male", "Argentina" } };
	}

}
