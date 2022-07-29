package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitwait;
	Action action;
	Select select;
	String emailrandom;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void beforemethod(){
		emailrandom = "account_" + getRandomNumber() + "@yopmail.com";
	}
 
	@Test
	public void TC_01_Rode_Dropdown_list() {
		driver.get("https://www.rode.com/wheretobuy");
		// Khởi tạo khi sử dụng (element xuất hiện)
		// Khởi tạo select để thao tác vs element (country dropdown)
		select = new Select(driver.findElement(By.xpath("//select[@class='where_country required_map']")));
		
		select.selectByVisibleText("Vietnam");
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		
		driver.findElement(By.xpath("//input[@id='search_loc_submit']")).click();
		sleepInsecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(), "33");
		
		// Java generic
		List<WebElement> storename = driver.findElements(By.xpath("//div[@class='result_item']//div[@class='store_name']"));
		
		// verify tong so luong storename = 33
		Assert.assertEquals(storename.size(), 33);
		
		// in ra tat ca cac element
		for (WebElement store : storename) {
			System.out.println(store.getText());
			
		}

	}

	@Test
	public void TC_02_nopcommerce_dropdown_list() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.xpath("//span[@class='male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Dong");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Vo");
		
		select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("1");
		
		List<WebElement> DOB = driver.findElements(By.xpath("//select[@name='DateOfBirthDay']/option"));
		Assert.assertEquals(DOB.size(), 32);
		
		for (WebElement dob : DOB) {
			System.out.println(dob.getText());
		}
		
		select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		
		List<WebElement> DOM = driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']/option"));
		Assert.assertEquals(DOM.size(), 13);
		
		for (WebElement dom : DOM) {
			System.out.println(dom.getText());		
		}
		
		select = new Select (driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1980");
		List<WebElement> DOBY = driver.findElements(By.xpath("//select[@name='DateOfBirthYear']/option"));
		Assert.assertEquals(DOBY.size(), 112);
		for (WebElement doby : DOBY) {
			System.out.println(doby.getText());			
		}
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailrandom);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		sleepInsecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), "Dong");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), "Vo");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		
	}
	
	public int getRandomNumber() {
		Random randomgenerator = new Random();
		return randomgenerator.nextInt(9999);	
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
