package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Register_Xpath {
	// Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By txtFirstname = By.xpath("//input[@id='txtFirstname']");
	By txtEmail = By.xpath("//input[@id='txtEmail']");
	By txtCEmail = By.xpath("//input[@id='txtCEmail']");
	By txtPassword = By.xpath("//input[@id='txtPassword']");
	By txtCPassword = By.xpath("//input[@id='txtCPassword']");
	By txtPhone = By.xpath("//input[@id='txtPhone']");
	By Registerbutton = By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']");
	By txtFirstnameerror = By.xpath("//label[@id='txtFirstname-error']");
	By txtEmailerror = By.xpath("//label[@id='txtEmail-error']");
	By txtCEmailerror = By.xpath("//label[@id='txtCEmail-error']");
	By txtPassworderror = By.xpath("//label[@id='txtPassword-error']");
	By txtCPassworderror = By.xpath("//label[@id='txtCPassword-error']");
	By txtPhoneerror = By.xpath("//label[@id='txtPhone-error']");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserdriver\\geckodriver.exe");
		
		// Run browser
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		
		// Set time to find element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforemethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
 
	@Test
	public void TC_01_Register_with_emty_data() {	
		sleepInsecond(2);
		driver.findElement(Registerbutton).click();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(txtFirstnameerror).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(txtEmailerror).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(txtCEmailerror).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(txtPassworderror).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(txtCPassworderror).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(txtPhoneerror).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Register_with_invalid_Email() {
		driver.findElement(txtFirstname).sendKeys("Pham Vo Dong");
		driver.findElement(txtEmail).sendKeys("abc@");
		driver.findElement(txtCEmail).sendKeys("test@");
		driver.findElement(txtPassword).sendKeys("123456");
		driver.findElement(txtCPassword).sendKeys("123456");
		driver.findElement(txtPhone).sendKeys("0123456789");
		driver.findElement(Registerbutton).click();
		Assert.assertEquals(driver.findElement(txtEmailerror).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(txtCEmailerror).getText(), "Email nhập lại không đúng");
//		driver.findElement(By.xpath("//label[@id='txtEmail-error' and text()='Vui lòng nhập email hợp lệ']"));
//		driver.findElement(By.xpath("//label[@id='txtCEmail-error' and text()='Email nhập lại không đúng']"));
		sleepInsecond(2);
		
	}
	
	@Test
	public void TC_03_Register_with_incorrect_Confrim_Email() {
		driver.findElement(txtFirstname).sendKeys("Pham Vo Dong");
		driver.findElement(txtEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtCEmail).sendKeys("test@");
		driver.findElement(txtPassword).sendKeys("123456");
		driver.findElement(txtCPassword).sendKeys("123456");
		driver.findElement(txtPhone).sendKeys("0123456789");
		driver.findElement(Registerbutton).click();
		Assert.assertEquals(driver.findElement(txtCEmailerror).getText(), "Email nhập lại không đúng");
//		driver.findElement(By.xpath("//label[@id='txtCEmail-error' and text()='Email nhập lại không đúng']"));
		sleepInsecond(2);
		
	}
	
	@Test
	public void TC_04_Register_with_password_less_than_6_chracters() {
		driver.findElement(txtFirstname).sendKeys("Pham Vo Dong");
		driver.findElement(txtEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtCEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtPassword).sendKeys("12345");
		driver.findElement(txtCPassword).sendKeys("12345");
		driver.findElement(txtPhone).sendKeys("0123456789");
		driver.findElement(Registerbutton).click();
		Assert.assertEquals(driver.findElement(txtPassworderror).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(txtCPassworderror).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
//		driver.findElement(By.xpath("//label[@id='txtPassword-error' and text()='Mật khẩu phải có ít nhất 6 ký tự']"));
//		driver.findElement(By.xpath("//label[@id='txtCPassword-error' and text()='Mật khẩu phải có ít nhất 6 ký tự']"));
		sleepInsecond(2);
	}
	
	@Test
	public void TC_05_Register_with_incorrect_confirm_password() {
		driver.findElement(txtFirstname).sendKeys("Pham Vo Dong");
		driver.findElement(txtEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtCEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtPassword).sendKeys("1234567");
		driver.findElement(txtCPassword).sendKeys("123456");
		driver.findElement(txtPhone).sendKeys("0123456789");
		driver.findElement(Registerbutton).click();
//		Assert.assertEquals(driver.findElement(txtCPassworderror), "Mật khẩu bạn nhập không khớp");
		driver.findElement(By.xpath("//label[@id='txtCPassword-error' and text()='Mật khẩu bạn nhập không khớp']"));
		sleepInsecond(2);
	}
	
	@Test
	public void TC_06_Register_with_invalid_Phone_number() {
		driver.findElement(txtFirstname).sendKeys("Pham Vo Dong");
		driver.findElement(txtEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtCEmail).sendKeys("phamvodong@gmail.com");
		driver.findElement(txtPassword).sendKeys("1234567");
		driver.findElement(txtCPassword).sendKeys("123456");
		driver.findElement(txtPhone).sendKeys("012345678911");
		driver.findElement(Registerbutton).click();
//		Assert.assertEquals(driver.findElement(txtPhoneerror), "Số điện thoại phải từ 10-11 số.");
		driver.findElement(By.xpath("//label[@id='txtPhone-error' and text()='Số điện thoại phải từ 10-11 số. ']"));
		driver.findElement(txtPhone).clear();
		sleepInsecond(2);
		driver.findElement(txtPhone).sendKeys("123456");
//		Assert.assertEquals(driver.findElement(txtPhoneerror), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		driver.findElement(By.xpath("//label[@id='txtPhone-error' and text()='Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019']"));
		sleepInsecond(2);
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
