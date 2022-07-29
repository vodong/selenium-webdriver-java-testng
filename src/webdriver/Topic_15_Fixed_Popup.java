package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Fixed_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Ngooai_ngu_24h() {
		driver.get("https://ngoaingu24h.vn/");
		By Loginpopup = By.cssSelector("div#modal-login-v1");
		
		//Verify login popup is not displayed
		Assert.assertFalse(driver.findElement(Loginpopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInsecond(2);
		
		// Verify login popup is displayed
		Assert.assertTrue(driver.findElement(Loginpopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInsecond(2);
		
		//Verify login popup is displayed
		Assert.assertFalse(driver.findElement(Loginpopup).isDisplayed());

	}

	public void TC_02() {
		driver.get("https://ngoaingu24h.vn/");
		// Hạn chế sử dụng WebElement để gán Element vì nhiều khi sẽ có những trang trong 1 khoảng time sẽ tự động refesh page và element đã gán lúc đầu sẽ bị thay đổi thuộc tính
		// Ví dụ gán thêm dòng driver.navigate().refresh(); line 81 để page bị refesh
		// Nên sử dụng By Loginpopup = By.cssSelector("div#modal-login-v1");
		WebElement Loginpopup = driver.findElement(By.cssSelector("div#modal-login-v1"));
		
		//Verify login popup is not displayed
		Assert.assertFalse(Loginpopup.isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInsecond(2);
		
		// Verify login popup is displayed
		Assert.assertTrue(Loginpopup.isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInsecond(2);
		
		driver.navigate().refresh();
		
		//Verify login popup is displayed
		Assert.assertFalse(Loginpopup.isDisplayed());

		

	}

 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 

	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
