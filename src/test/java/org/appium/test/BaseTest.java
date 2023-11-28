package org.appium.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public static AndroidDriver driver;
	public AppiumDriverLocalService service;
	public WebDriverWait wait;

	@BeforeClass
	public void configureAppiumService() throws MalformedURLException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("0.0.0.0").usingPort(4723).build();
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("vinothpixel");
		options.setChromedriverExecutable("D:\\Driver\\chromedriver\\chromedriver_83.exe");
		options.setApp("C:\\Users\\Admin\\eclipse-workspace\\AppiumNew\\src\\test\\java\\resources\\General-Store.apk");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
