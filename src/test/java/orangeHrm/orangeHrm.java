package orangeHrm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class orangeHrm {

	WebDriver driver;

	@Test
	public void open() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebElement profileDropDown = driver.findElement(
				By.cssSelector(".oxd-userdropdown-tab > .oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOf(profileDropDown));
		profileDropDown.click();
		driver.findElement(By.xpath("//a[text()='About']")).click();

		List<WebElement> title = driver
				.findElements(By.cssSelector(".orangehrm-about .oxd-text--p.orangehrm-about-title"));
		List<WebElement> text = driver
				.findElements(By.cssSelector(".orangehrm-about .oxd-text--p.orangehrm-about-text"));

		for (int i = 0; i < title.size(); i++) {
			String titleText = title.get(i).getText();
			String textValue = text.get(i).getText();
			System.out.println(titleText + ": " + textValue);
		}

		driver.quit();
	}

}
