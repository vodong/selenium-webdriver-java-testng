package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_Dropdown_CustomDropdown_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
//	@BeforeMethod
//	public void beforemethod() {
//		
//	}
 
	@Test
	public void TC_01_textbox_textarea() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		
		sleepInsecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).isDisplayed());
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		Assert.assertTrue(driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).isDisplayed());

	}

	@Test
	public void TC_02() {

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
