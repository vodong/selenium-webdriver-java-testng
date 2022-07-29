package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.jetty9.io.ssl.ALPNProcessor.Client;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_JavascriptExecutor_HTML5 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailaddress;
	String loginPageUrl, userID, userPassword, customerID;
	String customerName, gender, dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password;
	JavascriptExecutor jsExecutor;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		emailaddress = "account_" + getRandomNumber() + "@yopmail.com";
		
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
		
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
 

	public void TC_01_Guru() {
		navigateToUrlByJS("http://live.techpanda.org");
		sleepInsecond(2);
		
		// Verify domain
		String Homepagedomain = (String) executeForBrowser("return document.domain");
		System.out.println("Domain name: " + Homepagedomain);
		Assert.assertEquals(Homepagedomain, "live.techpanda.org");
		
		//Verify URL
		String HomepageURL = (String) executeForBrowser("return document.URL");
		System.out.println("Domain name: " + HomepageURL);
		Assert.assertEquals(HomepageURL, "http://live.techpanda.org/");
		
		clickToElementByJS("//a[text()='Mobile']");
		sleepInsecond(3);
		
		clickToElementByJS("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInsecond(3);
		
		String shoppingcarttext = getInnerText();
		Assert.assertTrue(shoppingcarttext.contains("Sony Xperia was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInsecond(5);
		
		String Titlepage = (String) executeForBrowser("return document.title");
		System.out.println("Title: " + Titlepage);
		Assert.assertEquals(Titlepage, "Customer Service");
		
		scrollToElementOnDown("//input[@id='newsletter']");
		sleepInsecond(3);
		
		sendkeyToElementByJS("//input[@id='newsletter']", emailaddress);
		sleepInsecond(3);
		
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInsecond(3);
		
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInsecond(5);
		
		Homepagedomain = (String) executeForBrowser("return document.domain");
		System.out.println("Domain name: " + Homepagedomain);
		Assert.assertEquals(Homepagedomain, "demo.guru99.com");

	}

	public void TC_02_Validate_HTML5_Message() {
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		By firtname = By.id("user_first_name");
		By email = By.id("user_email");
		By password = By.id("user_password");
		By createbutton = By.xpath("//button[contains(text(),'Tạo tài khoản mới')]");
		
		driver.findElement(createbutton).click();
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage(firtname), "Please fill out this field.");
		
		driver.findElement(firtname).sendKeys("Automation");
		driver.findElement(createbutton).click();
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage(email), "Please fill out this field.");

		driver.findElement(email).sendKeys("12345@23451@123");
		driver.findElement(createbutton).click();
		sleepInsecond(3);
		Assert.assertEquals(getElementValidationMessage(email), "Please enter an email address.");
		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(emailaddress);
		driver.findElement(createbutton).click();
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage(password), "Please fill out this field.");
	}
	
	public void TC_03_Remove_Attribute() {
		driver.get(loginPageUrl);
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);

		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		//Remove attribute dateOfBirth remove datepicker
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInsecond(4);

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
	}
	
	@Test
	public void TC_04_Image_Loaded() {
		driver.get("https://automationfc.github.io/basic-form/");
		Assert.assertTrue(isImageLoaded("//img[@alt='User Avatar 05']"));
		Assert.assertFalse(isImageLoaded("//img[@alt='broken_04']"));
	}

 
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(By locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (Boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
 
	public void sleepInsecond (long second) {
		try {
			Thread.sleep(second *1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random randomgenerator = new Random();
		return randomgenerator.nextInt(9999);	
	}
}
