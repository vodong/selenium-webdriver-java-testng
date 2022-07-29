package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_handle_button_default_radio_checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsexecutor; // ép kiểu driver qua Java


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsexecutor = (JavascriptExecutor) driver;
		
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
//	@Test
//	public void TC_01_Button() {
//		driver.get("https://www.fahasa.com/customer/account/create");
//		By tablogin = By.cssSelector("li.popup-login-tab-login");
//		By buttonlogin = By.cssSelector("button.fhs-btn-login");
//		
//		driver.findElement(tablogin).click();
//		
//		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-register")).isDisplayed());
//		driver.findElement(By.cssSelector("input[id=login_username]")).sendKeys("automationtester@yopmail.com");
//		driver.findElement(By.cssSelector("input[id=login_password]")).sendKeys("123456789");
//		
//		//Verify button is enabled
//		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-register")).isEnabled());
//		
//		// Verify background color = Red
//		String loginbuttonbackgroundcolor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
//		System.out.println("Background color:" + loginbuttonbackgroundcolor);
//		
//		// Verify background color = RGB		
////		Assert.assertEquals(loginbuttonbackgroundcolor, "rgba(201, 33, 39, 0.027)");
//		
//		// Convert qua Hexa
//		String logibuttonbackgroundcolorhexa = Color.fromString(loginbuttonbackgroundcolor).asHex();
//		System.out.println("Background color:" + logibuttonbackgroundcolorhexa);
//		
//		// Verify background color = Hexa	
//		Assert.assertEquals(logibuttonbackgroundcolorhexa.toUpperCase(), "#C92127");
//		
//		driver.navigate().refresh();
//		sleepInsecond(2);
//		driver.findElement(tablogin).click();
//		sleepInsecond(2);
//		
//		//Remove attribute của element
//		jsexecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(buttonlogin));
//		sleepInsecond(2);
//		driver.findElement(buttonlogin).click();
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
//		
//	}

//	@Test
//	public void TC_02_Default_Radio() {
//		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
//		sleepInsecond(2);
//		
//		By Dualzone = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
//		By Leathertrim = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
//		By Towbar = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
//		By Petrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
//		
//		// Select checkbox
////		if(!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
////			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
////		}
//		
//		checktocheckbox(Dualzone);
//		sleepInsecond(2);
//		
//		//Selected checkbox
//		Assert.assertTrue(isselected(Dualzone));
//			
//		//Deselected checkbox
//		unchecktocheckbox(Dualzone);
//		
//		Assert.assertFalse(isselected(Dualzone));
//		
//		//Check disabled checkbox
//		Assert.assertFalse(isdisabled(Leathertrim));
//		Assert.assertFalse(isdisabled(Towbar));
//		
//		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
//		sleepInsecond(2);
//		
//		// Select checkbox
////		if(!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()) {
////			driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
////		}
//		checktocheckbox(Petrol);
//		Assert.assertTrue(isselected(Petrol));
//	}
	
//	@Test
//	public void TC_03_Custom_Radio() {
//		driver.get("https://material.angular.io/components/radio/examples");
//		
//		By Summer = By.xpath("//input[@value='Summer']");
//		
//		if(!driver.findElement(Summer).isSelected()){
//			clickbyjavascript(Summer);
//		}
//		sleepInsecond(2);
//		Assert.assertTrue(driver.findElement(Summer).isSelected()); 	
//	}
	
//	@Test
//	public void TC_04_Custom_Checkbox() {
//		driver.get("https://material.angular.io/components/checkbox/examples");
//		By checked = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");		
//		By Indeterminate = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
//		
//		clickbyjavascript(checked);
//		sleepInsecond(2);
//		clickbyjavascript(Indeterminate);
//		sleepInsecond(2);
//		Assert.assertTrue(isselected(checked));
//		Assert.assertTrue(isselected(Indeterminate));
//		
//		clickbyjavascript(checked);
//		clickbyjavascript(Indeterminate);
//		sleepInsecond(2);
//		Assert.assertFalse(isselected(checked));
//		Assert.assertFalse(isselected(Indeterminate));
//		
//	}
	
//	@Test
//	public void TC_05_Custom_Radio() {
//		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//		By myself = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");		
//		By myfamily = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");	
//		
//		clickbyjavascript(myfamily);
//		sleepInsecond(2);
//		Assert.assertTrue(driver.findElement(By.cssSelector("input[formcontrolname='registerFullname']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.cssSelector("input[formcontrolname='registerPhoneNumber']")).isDisplayed());
//		
//		clickbyjavascript(myself);
//		sleepInsecond(2);
//		
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		Assert.assertEquals(driver.findElements(By.cssSelector("input[formcontrolname='registerFullname']")).size(), 0);
//		Assert.assertEquals(driver.findElements(By.cssSelector("input[formcontrolname='registerPhoneNumber']")).size(), 0);
//		
//		
//	}
	
	@Test
	public void TC_05_Custom_Radio_Google_doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canthoradio = By.xpath("//div[@aria-label='Cần Thơ']");
		By quangninhcheckbox = By.xpath("//div[@data-answer-value='Quảng Ninh']");
		By miquangcheckbox = By.xpath("//div[@data-answer-value='Mì Quảng']");
		
		Assert.assertEquals(driver.findElement(canthoradio).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangninhcheckbox).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(miquangcheckbox).getAttribute("aria-checked"), "false");
		driver.findElement(canthoradio).click();
		driver.findElement(quangninhcheckbox).click();
		driver.findElement(miquangcheckbox).click();
		
		Assert.assertEquals(driver.findElement(canthoradio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangninhcheckbox).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(miquangcheckbox).getAttribute("aria-checked"), "true");
		
		driver.findElement(quangninhcheckbox).click();
		driver.findElement(miquangcheckbox).click();
		
	}
	
	public void clickbyjavascript (By by) {
		jsexecutor.executeScript("arguments[0].click()", driver.findElement(by));
	}
	
	public void checktocheckbox (By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
		
	public void unchecktocheckbox (By by) {
			if (driver.findElement(by).isSelected()) {
				driver.findElement(by).click();
		}
	}

	public boolean isselected (By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isdisabled (By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		}else {
			return false;
		}
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
