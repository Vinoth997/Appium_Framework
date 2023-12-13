package wayMore;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class wayMore {
	
	WebDriver driver;
	
	@Test
	public void open() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev.waymore.io/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("wmaqa@dc02.amdtelecom.net");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Support@123");
		driver.findElement(By.xpath("//button[@class='login-form__btn']")).click();
		
		
		driver.findElement(By.xpath("//span[text()='Events']/ancestor::a")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor js =(JavascriptExecutor)driver;
		
		WebElement loading = driver.findElement(By.xpath("//div[@class='wm-table__loader-container ng-scope']"));
		
		WebElement event = driver.findElement(By.xpath("//md-select[@id='event']/md-select-value"));
		
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.wm-table__loader-container.ng-scope")));
		
//		wait.until(ExpectedConditions.invisibilityOf(loading));
		wait.until(ExpectedConditions.visibilityOf(event));
		
		Thread.sleep(5000);
//		js.executeScript("arguments[0].click()",event);
		event.click();
		
		Thread.sleep(6000);
		driver.close();
		
	}

}
