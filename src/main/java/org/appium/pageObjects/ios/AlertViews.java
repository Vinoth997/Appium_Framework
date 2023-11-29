package org.appium.pageObjects.ios;

import org.appium.utils.ios.IOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSActions {

	IOSDriver driver;

	public AlertViews(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViews;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
	private WebElement textEntryMenu;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textEntryTextField;

	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopup;

	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;

	@iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;

	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement submitBtn;

	public void selectAlertViews() {
		alertViews.click();
	}

	public void fillTextLabel(String text) {
		textEntryMenu.click();
		textEntryTextField.sendKeys(text);
		acceptPopup.click();
	}

	public String getConfirmMessage() {
		confirmMenuItem.click();
		return confirmMessage.getText();
	}
}
