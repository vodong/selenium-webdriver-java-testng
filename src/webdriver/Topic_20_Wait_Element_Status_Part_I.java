package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Element_Status_Part_I {
	WebDriver driver;
	WebDriverWait eximplicitwait;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		eximplicitwait = new WebDriverWait(driver, 15);
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
 
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		
		//Visible: Có trên UI và có trong DOM/HTML
		eximplicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='Create New Account']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Create New Account']")).isDisplayed());

	}

	public void TC_02_Invisible_In_DOM() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		sleepInsecond(5);
		
		//Invisible: Không có trên UI nhưng có trong DOM (Không bắt buộc)
		eximplicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		//Không Hiển Thị -> Passed -> 1s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
		
	}
	

	public void TC_02_Invisible_Not_In_DOM() {
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		//Invisible: Không có trên UI nhưng có trong DOM (Không bắt buộc)
		eximplicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		//Không Hiển Thị -> Failed -> 20s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());

	}
	
	public void TC_03_Presence() {
		//Presence: Có trong DOM/HTML và có trên UI
		driver.get("https://www.facebook.com/");
		eximplicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Create New Account']")));
		
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		sleepInsecond(5);
		
		//Presence: Có trong DOM/HTML và không có trên UI
		eximplicitwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		sleepInsecond(5);
		
		//Tại thời điểm này element này đang có trong DOM
		WebElement confimationEmailAddressTexbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		//Đóng registration form lại
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInsecond(2);
		
		//Wait cho confirmation email address textbox không còn trong DOM nữa
		// vì 1 lý do nào đó mình cần wait cho nó không còn tồn tại trong DOM nữa
		eximplicitwait.until(ExpectedConditions.stalenessOf(confimationEmailAddressTexbox));

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
