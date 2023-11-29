package org.appium.ios.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {

	public IOSDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppiumService() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("0.0.0.0").usingPort(4723).build();
		service.start();

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 13 Pro");
		options.setApp("C:\\Users\\Admin\\eclipse-workspace\\AppiumNew\\src\\test\\java\\resources\\Testapp 3.app");
		options.setPlatformVersion("15.5");

		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		/**
		 * Appium install Webdriver Agent in your apple device , so this webdriver agent
		 * will further go and help you to automate in IOS apps
		 */

		driver = new IOSDriver(new URL("http://0.0.0.0:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
