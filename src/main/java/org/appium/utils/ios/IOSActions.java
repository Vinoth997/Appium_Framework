package org.appium.utils.ios;

import java.util.HashMap;
import java.util.Map;

import org.appium.utils.common.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils {

	IOSDriver driver;

	public IOSActions(IOSDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void longPress(WebElement element) {
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) element).getId());
		params.put("duration", 5);

		driver.executeScript("mobile:touchAndHold", params);
	}

	public void scrollToWebElement(WebElement element) {
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		params.put("element", ((RemoteWebElement) element).getId());

		driver.executeScript("mobile:scroll", params);
	}

	public void swipeToLeft() {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("direction", "left");

		driver.executeScript("mobile:swipe", paramsN);
	}

	public void swipeToRightt() {
		Map<String, Object> paramsN = new HashMap<String, Object>();
		paramsN.put("direction", "right");

		driver.executeScript("mobile:swipe", paramsN);
	}
}
