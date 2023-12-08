package org.appium.baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.appium.utils.common.AppiumUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {

	public static AndroidDriver driver;
	public AppiumDriverLocalService service;
	public WebDriverWait wait;

	@BeforeClass
	public void configureAppiumService() throws IOException {
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//org//appium//utils//resources//data.properties");
		property.load(fis);
		String ipAddress = property.getProperty("ipAddress");
		String portNumber = property.getProperty("port");
		String deviceName = property.getProperty("androidDevice");

		service = startAppiumServer(ipAddress, Integer.parseInt(portNumber));

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		options.setChromedriverExecutable("D:\\Driver\\chromedriver\\chromedriver_83.exe");
		options.setApp(
				System.getProperty("user.dir") + "//src//test//java//org//appium//app//resources//General-Store.apk");
//		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
