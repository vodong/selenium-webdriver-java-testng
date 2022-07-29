package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Handle_Iframe_Frame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Kyna() {
		driver.get("https://kyna.vn/");
		// Switch vào Iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		
		//Verify số lượng like của Facebook
		Assert.assertEquals(driver.findElement(By.cssSelector("div._1drq")).getText(), "166K likes");
		System.out.println(driver.findElement(By.cssSelector("div._1drq")).getText());
		
		//switch ra khỏi Iframe Facebook
		driver.switchTo().defaultContent();
		
		//switch vào CS chat
		driver.switchTo().frame(driver.findElement(By.cssSelector("div#cs-live-chat>iframe")));
		//click vào chat popup
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("pham vo dong");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("pham vo dong");
		
		select = new Select (driver.findElement(By.cssSelector("select#serviceSelect")));
		select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		
		
		driver.findElement(By.cssSelector("textarea.meshim_widget_widgets_TextArea")).sendKeys("pham vo dong \ntest \nautomation");
		
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		sleepInsecond(2);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List <WebElement> text = driver.findElements(By.cssSelector("div.content h4"));
		for (WebElement contain : text) {
			System.out.println(contain.getText());
			Assert.assertTrue(contain.getText().contains("Excel"));		
		}
	}
	
	
	public void TC_02_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame(driver.findElement(By.name("facebook")));
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		
		
	}
 
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
 

	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
