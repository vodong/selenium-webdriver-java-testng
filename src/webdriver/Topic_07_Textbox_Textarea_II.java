package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea_II {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl, userID, userPassword, customerID;
	String customerName, gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserdriver\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		loginPageUrl = "https://demo.guru99.com/v4/";
		customerName = "Brian Tracy";
		gender = "male";
		dateOfBirth = "1950-01-31";
		addressInput = "123 Los Angeles\nUnited States";
		addressOutput = "123 Los Angeles United States";
		city = "New York";
		state = "California";
		pinNumber = "632565";
		phoneNumber = "0987569584";
		email = "briantracy" + getRandomNumber() + "@mail.net";
		password = "123456";

		editAddressInput = "456 Tracy\nColombia";
		editAddressOutput = "456 Tracy Colombia";
		editCity = "Hawaii";
		editState = "New Yersey";
		editPin = "659853";
		editPhone = "0986333222";
		editEmail = "briantracy" + getRandomNumber() + "@mail.vn";

		driver.get(loginPageUrl);
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);
	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);

		driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// Verify New Customer displayed at Edit Customer correctly
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(), addressInput);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"), pinNumber);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"), phoneNumber);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"), email);

		// Edit
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(editAddressInput);

		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(editCity);

		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(editState);

		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(editPin);

		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(editPhone);

		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}