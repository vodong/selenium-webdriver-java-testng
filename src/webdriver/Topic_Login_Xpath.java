package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Login_Xpath {
	// Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By MyAccountlink = By.xpath("//div[@class='footer-container']//a[@title='My Account']");
	By Email = By.xpath("//input[@id='email']");
	By Password = By.xpath("//input[@id='pass']");
	By Loginbutton = By.xpath("//button[@id='send2']");
	By Erroremail1 = By.xpath("//div[@id='advice-required-entry-email']");
	By Erroremail = By.xpath("//div[@id='advice-validate-email-email']");
	By Errorpassword = By.xpath("//div[@id='advice-validate-password-pass']");
	String emailrandom, firstname, lastname, middlename, fullname, pass;
	
	


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		
		// Run browser
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		
		// Set time to find element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void	beforeMethod() {
		driver.get("http://live.techpanda.org/");
		emailrandom = "account_" + getRandomNumber() + "@yopmail.com";
		pass = "1234567891";
		firstname = "Pham";
		middlename = "Vo";
		lastname = "Dong";
		fullname = firstname + " " + middlename + " " + lastname;
	}
 
	
	@Test
	public void TC_01_Login_with_empty_Email_and_Password() {		
		driver.findElement(MyAccountlink).click();
		driver.findElement(Loginbutton).click();
		Assert.assertEquals(driver.findElement(Erroremail1).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(Erroremail1).getText(), "This is a required field.");
//		driver.findElement(By.xpath("//div[@id='advice-required-entry-email' and text()='This is a required field.']"));
//		driver.findElement(By.xpath("//div[@id='advice-required-entry-pass' and text()='This is a required field.']"));
	}

	@Test
	public void TC_02_Login_with_invalid_Email() {
		driver.findElement(MyAccountlink).click();
		driver.findElement(Email).sendKeys("123434234@12312.123123");
		driver.findElement(Password).sendKeys("123456");
		driver.findElement(Loginbutton).click();
		Assert.assertEquals(driver.findElement(Erroremail).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
//		System.out.println(Erroremail);
//		driver.findElement(By.xpath("//div[@id='advice-validate-email-email' and text()='Please enter a valid email address. For example johndoe@domain.com.']"));
		
	}
	
	@Test
	public void TC_03_Login_with_Password_less_than_6_characters() {
		driver.findElement(MyAccountlink).click();
		driver.findElement(Email).sendKeys("automation@gmail.com");
		driver.findElement(Password).sendKeys("123");
		driver.findElement(Loginbutton).click();
		Assert.assertEquals(driver.findElement(Errorpassword).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
//		driver.findElement(By.xpath("//div[@id='advice-validate-password-pass' and text()='Please enter 6 or more characters without leading or trailing spaces.']"));
		
	}
	
	@Test
	public void TC_04_Login_with_incorrect_Email_Password() {
		driver.findElement(MyAccountlink).click();
		driver.findElement(Email).sendKeys("automation@gmail.com");
		driver.findElement(Password).sendKeys("123123123");
		driver.findElement(Loginbutton).click();
		driver.findElement(By.xpath("//li[@class='error-msg']//span[text()='Invalid login or password.']"));
	}
	
	public int getRandomNumber() {
		Random randomgenerator = new Random();
		return randomgenerator.nextInt(9999);	
	}
	
	@Test
	public void TC_05_Create_a_new_account() {
		driver.findElement(MyAccountlink).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middlename);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailrandom);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']"));
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//li[@class=' last']//a[text()='Log Out']")).click();
//		driver.findElement(By.xpath("//h2[contains(text(),'This is demo')]"));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());
	}
	
	@Test
	public void TC_06_Login_with_valid_email_and_passwrod() {
		driver.findElement(MyAccountlink).click();
		driver.findElement(Email).sendKeys(emailrandom);
		driver.findElement(Password).sendKeys(pass);
		driver.findElement(Loginbutton).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.pagetitle>h1")).getText(), "MY DASHBOARD");
//		driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]"));
//		driver.findElement(By.xpath("//p[@class='hello']//strong[text()=('Hello, Pham Vo Dong!')]"));
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello" +  fullname + "!");
//		String contactinformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
//		Assert.assertTrue(contactinformation.contains(fullname));
//		Assert.assertTrue(contactinformation.contains(emailrandom));
//		driver.findElement(By.xpath("//div[@class='box-content']//p[contains(string(),'Pham Vo Dong')]"));
//		driver.findElement(By.xpath("//div[@class='box-content']//p[contains(string(),emailrandom)]"));
	}

 
	/*@AfterClass
	public void afterClass() {
		driver.quit();
	}*/
 

	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
