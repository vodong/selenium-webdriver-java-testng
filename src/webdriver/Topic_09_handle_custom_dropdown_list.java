package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_handle_custom_dropdown_list {
	WebDriver driver;
	WebDriverWait expilictwait;
	JavascriptExecutor jsexecutor;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		// Wait cho các trạng thái của Element: visible/ presence/ invisible/ staleness
		expilictwait = new WebDriverWait(driver,15);
		
		jsexecutor = (JavascriptExecutor) driver; // ép kiểu tường minh
		
		// Waite cho việc tìm Element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
 
//	@Test
//	public void TC_01_JQeury() {
//		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
//		
//		selectitemindropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
//		
//		selectitemindropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");
//		
//		selectitemindropdownlist("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
//		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
//		
//		}
//	
//	@Test
//	public void TC_02_ReactJS() {
//		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
//		
//		selectitemindropdownlist("i.dropdown", "span.text", "Christian");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Christian");
//		
//		selectitemindropdownlist("i.dropdown", "span.text", "Matt");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Matt");
//		
//		selectitemindropdownlist("i.dropdown", "span.text", "Justen Kitsune");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Justen Kitsune");
//		
//		}
	
	@Test
	public void TC_03_VueJS() {
//		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
//		selectitemindropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
//		
//		selectitemindropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
//		
//		selectitemindropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
		}
	
//	@Test
//	public void TC_04_Angular_Select() {
//		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//		
//		selectitemindropdownlist("ng-select[bindvalue=provinceCode] span.ng-arrow-wrapper", "div[role=option]>span.ng-option-label", "Tỉnh Bắc Kạn");
//		String actualtext = (String) jsexecutor.executeScript("return document.querySelector(\"ng-select[bindvalue=provinceCode] span.ng-value-label\").innerHTML");
//		Assert.assertEquals(actualtext, "Tỉnh Bắc Kạn");
//		
//		selectitemindropdownlist("ng-select[bindvalue=provinceCode] span.ng-arrow-wrapper", "div[role=option]>span.ng-option-label", "Thành phố Hà Nội");
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue=provinceCode] span.ng-value-label")).getText(), "Thành phố Hà Nội");
//		
//		selectitemindropdownlist("ng-select[bindvalue=provinceCode] span.ng-arrow-wrapper", "div[role=option]>span.ng-option-label", "Thành phố Cần Thơ");
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue=provinceCode] span.ng-value-label")).getAttribute("innerText"), "Thành phố Cần Thơ");
//		
//		}
	
//	@Test
//	public void TC_05_Angular_Enter() {
//		driver.get("http://indrimuska.github.io/jquery-editable-select/");
//		
//		entertoitemindropdownlist("div[id=default-place] input.form-control", "div[id=default-place] li.es-visible", "Ford");
//		String actualtext = (String) jsexecutor.executeScript("return document.querySelector(\"div[id=default-place] li.es-visible\").innerHTML");
//		Assert.assertEquals(actualtext, "Ford");
//		
//		entertoitemindropdownlist("div[id=default-place] input.form-control", "div[id=default-place] li.es-visible", "Nissan");
//		String actualtext1 = (String) jsexecutor.executeScript("return document.querySelector(\"div[id=default-place] li.es-visible\").innerHTML");
//		Assert.assertEquals(actualtext1, "Nissan");
//		
//		entertoitemindropdownlist("div[id=default-place] input.form-control", "div[id=default-place] li.es-visible", "Volkswagen");
//		String actualtext2 = (String) jsexecutor.executeScript("return document.querySelector(\"div[id=default-place] li.es-visible\").innerHTML");
//		Assert.assertEquals(actualtext2, "Volkswagen");
//		
//		}
	
	public void selectitemindropdownlist (String parentlocator, String childlocator, String Expectecttextitem) {
		
		//Step 1: Click vào 1 Element cho nó xổ hết ra các item
		// write xpath
//		driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']")).click();
		
		// write CSS
		driver.findElement(By.cssSelector(parentlocator)).click();
		sleepInsecond(2);
		
		//Step 2: chờ cho các item load hết ra thành công (Locator chứa hết tất cả các item)
		// lưu ý 1: Các locator chứa hết tất cả các item
		// lưu ý 2: Locator phải đến node cuối cùng chứa text
		expilictwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childlocator)));
		
		//Step 3: Tìm item cần chọn
		
		// Lấy hết tất cả element ra sau khi duyệt qua từng item
		List <WebElement> allitems =  driver.findElements(By.cssSelector(childlocator));
		
		// Duyệt qua tất cả item
		for (WebElement item : allitems) {
			String Actualtext = item.getText();
			System.out.println("Actual text=" + Actualtext);
			
			// Check cái text mình muốn lấy
			if(Actualtext.equals(Expectecttextitem)) {
				// B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll xuống tìm tiếp
				// B2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến element đó
				
				jsexecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				// click vào item đó
				item.click();	
				
				// Thoát khỏi vòng lặp
				break;
				}
			}
		}
	
	public void entertoitemindropdownlist (String parentlocator, String childlocator, String Expectecttextitem) {
		
		//Step 1: Click vào 1 Element cho nó xổ hết ra các item
		// write xpath
//		driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-icon ui-icon ui-icon-triangle-1-s']")).sendKeys(Expectecttextitem);
		
		// write CSS
		driver.findElement(By.cssSelector(parentlocator)).sendKeys(Expectecttextitem);
		sleepInsecond(2);
		
		//Step 2: chờ cho các item load hết ra thành công (Locator chứa hết tất cả các item)
		// lưu ý 1: Các locator chứa hết tất cả các item
		// lưu ý 2: Locator phải đến node cuối cùng chứa text
		expilictwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childlocator)));
		
		//Step 3: Tìm item cần chọn
		
		// Lấy hết tất cả element ra sau khi duyệt qua từng item
		List <WebElement> allitems =  driver.findElements(By.cssSelector(childlocator));
		
		// Duyệt qua tất cả item
		for (WebElement item : allitems) {
			String Actualtext = item.getText();
			System.out.println("Actual text=" + Actualtext);
			
			// Check cái text mình muốn lấy
			if(Actualtext.equals(Expectecttextitem)) {
				// B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll xuống tìm tiếp
				// B2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến element đó
				
				jsexecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				// click vào item đó
				item.click();	
				
				// Thoát khỏi vòng lặp
				break;
				}
			}
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
