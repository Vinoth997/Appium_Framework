package org.appium.baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.appium.utils.common.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends AppiumUtils {

	public IOSDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppiumService() throws IOException {
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//org//appium//utils//resources//data.properties");
		property.load(fis);
		String ipAddress = property.getProperty("ipAddress");
		String portNumber = property.getProperty("port");
		String deviceName = property.getProperty("iosDevice");
		String isoVersion = property.getProperty("iosVersion");

		service = startAppiumServer(ipAddress, Integer.parseInt(portNumber));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName(deviceName);
		options.setApp("C:\\Users\\Admin\\eclipse-workspace\\AppiumNew\\src\\test\\java\\resources\\Testapp 3.app");
		options.setPlatformVersion(isoVersion);

		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		/**
		 * Appium install Webdriver Agent in your apple device , so this webdriver agent
		 * will further go and help you to automate in IOS apps
		 */

//		driver = new IOSDriver(new URL("http://0.0.0.0:4723"), options);
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
