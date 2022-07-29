package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Mouse_Action_part_II {
	WebDriver driver;
	Actions actions;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name").toLowerCase();


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
//	@Test
//	public void TC_01_Hover_Tooltip() {
//		driver.get("https://automationfc.github.io/jquery-tooltip/");
//		actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
//		sleepInsecond(2);
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
//	}

//	@Test
//	public void TC_02_Hover_To_Element() {
//		driver.get("https://www.myntra.com");
//		actions.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
//		sleepInsecond(2);
//		actions.click(driver.findElement(By.xpath("//a[@href='/kids-home-bath']"))).perform();
//		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
//
//	}
	
//	@Test
//	public void TC_03_Hover_To_Element() {
//		driver.get("https://www.fahasa.com/");
//		driver.findElement(By.xpath("//a[@id='NC_CLOSE_ICON']")).click();
//		actions.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
//		sleepInsecond(2);
//		actions.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']"))).perform();
//
//
//	}
	
//	@Test
//	public void TC_03_Click_And_Hold_Element() {
//		driver.get("https://automationfc.github.io/jquery-selectable/");
//		List <WebElement> allnumbers = driver.findElements(By.cssSelector("li.ui-selectee"));
//		actions.clickAndHold(allnumbers.get(0)).moveToElement(allnumbers.get(3)).release().perform();
//		sleepInsecond(2);
//		
//		List <WebElement> allnumbersselected = driver.findElements(By.cssSelector("li.ui-selected"));
//		Assert.assertEquals(allnumbersselected.size(), 4);
//	}
	
	@Test
	public void TC_04_Click_And_Select_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> allnumbers = driver.findElements(By.cssSelector("li.ui-selectee"));
		
		Keys controlkeys;
		if(osName.contains("win") || osName.contains("nux")) {
			controlkeys = Keys.CONTROL;
		}else {
			controlkeys = Keys.COMMAND;
		}
		
		actions.keyDown(controlkeys).perform();
		actions.click(allnumbers.get(0)).click(allnumbers.get(4)).click(allnumbers.get(10)).perform();
		actions.keyUp(controlkeys).perform();
		sleepInsecond(3);
		
		List <WebElement> allnumbersselected = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(allnumbersselected.size(), 3);
	}

 
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
 

	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
