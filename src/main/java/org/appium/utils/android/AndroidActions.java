package org.appium.utils.android;

import java.util.Collections;
import java.util.List;

import org.appium.utils.common.AppiumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {

	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void longPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public void scrollToText(String menuText) {
		// String elementText = element.getText();
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + menuText + "\"));"));
	}

	public void scrollToEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", "200", "height", 200, "direcction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	public void scrollToOption(String menu) {
		driver.findElement(AppiumBy.androidUIAutomator(
				("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ menu + "\").instance(0))")))
				.click();
	}

	public void scrollAndClick(String locator, String option) {
		List<WebElement> findElements = driver.findElements(By.xpath(locator));
		for (int i = 0; i < findElements.size(); i++) {
			String text = findElements.get(i).getText();
			if (text.equals(option)) {
				findElements.get(i).click();
			}
		}
	}

	public void swipeTo(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
	}

	public void dragAndDrop(WebElement element, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", x, "endY", y));
	}

	public void scrollAndClickLocator(By locator, String option) {
		boolean elementFound = false;

		while (!elementFound) {
			List<WebElement> findElements = driver.findElements(locator);
			for (int i = 0; i < findElements.size(); i++) {
				String text = findElements.get(i).getText();
				if (text.equals(option)) {
					findElements.get(i).click();
					elementFound = true;
					break;
				}
			}
			if (!elementFound) {
				boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
						ImmutableMap.of("left", 100, "top", 100, "width", "200", "height", 200, "direction", "down",
								"percent", 3.0));

				if (!canScrollMore) {
					break;
				}
			}
		}
	}

	public void scrollAndClickLocatorNew(By locator, String option) {
		List<WebElement> elements = driver.findElements(locator);

		for (WebElement element : elements) {
			if (element.getText().equals(option)) {
				element.click();
				return;
			}
		}
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 100,
				"width", "200", "height", 200, "direction", "down", "percent", 3.0));

		scrollAndClickLocator(locator, option);
	}

	public void scrollAndClick(By listLocator, String nameToFind, By clickLocator) {
		int productCount = driver.findElements(listLocator).size();
		for (int i = 0; i < productCount; i++) {
			String productName = driver.findElements(listLocator).get(i).getText();
			if (productName.equalsIgnoreCase(nameToFind)) {
				driver.findElements(clickLocator).get(i).click();
			}
		}
	}

	public void scrollToElement(By elementLocator) {
		WebElement element = driver.findElement(elementLocator);
		while (!element.isDisplayed()) {
			driver.executeScript("mobile:scroll", Collections.singletonMap("direction", "down"));
		}
		element.click();
	}
}
