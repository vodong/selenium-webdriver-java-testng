package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Handle_Page_Ready_Ajax_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsexecutor;
	Actions actions;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		//driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_Orange_UI_Page_Ready() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		
		//Wait cho page ready
		Assert.assertTrue(isPageLoadedSuccess());
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
	}

	public void TC_02_() {
		driver.get("https://blog.testproject.io/");
		
		if(driver.findElement(By.xpath("//div[@id='mailch-bg']")).isDisplayed()){
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();	
		}
		

		actions.moveToElement(driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		// Kiểm tra các title có chứa 'Selenium' Keyword
		List<WebElement> Content = driver.findElements(By.xpath("//h3[@class='post-title']//a"));
		for (WebElement ContainText : Content) {
			Assert.assertTrue(ContainText.getText().contains("Selenium"));
		}
		Assert.assertTrue(isPageLoadedSuccess());
		
	}

 
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 
	// JAVA + JQUERY
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsexecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return (Boolean) jsexecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
		}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return jsexecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}	

	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
