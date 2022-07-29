package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Handle_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsexecutor;
	Alert alert;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 

	public void TC_01_auto_switch_to_google() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String homepage = driver.getWindowHandle();
		System.out.println("Home Page: " + driver.getCurrentUrl());
		
		driver.findElement(By.cssSelector("a[href='https://google.com.vn']")).click();
		sleepInsecond(2);
		
		switchtowindowbytitle("Google");
//		switchtowindowbyid(homepage);
		
		// sau Khi switch qua Google
		System.out.println("Google: " + driver.getCurrentUrl());
		
		Assert.assertEquals(driver.getTitle(), "Google");
		
		// Switch to home page
		switchtowindowbytitle("SELENIUM WEBDRIVER FORM DEMO");
//		driver.switchTo().window(homepage);
		System.out.println("Home page: " + driver.getCurrentUrl());
		
		driver.findElement(By.cssSelector("a[href='https://facebook.com']")).click();
		sleepInsecond(2);
		
//		switchtowindowbyid(homepage);
		switchtowindowbytitle("Facebook – log in or sign up");
		// Sau khi switch qua Facebook
		System.out.println("Facebook: " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		switchtowindowbytitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.cssSelector("a[href='https://tiki.vn']")).click();
		switchtowindowbytitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("Tiki: " + driver.getCurrentUrl());
		CloseAllWindownsWithoutParent(homepage);
		
		switchtowindowbytitle("SELENIUM WEBDRIVER FORM DEMO");
		System.out.println("Home Page: " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		
	}

	public void TC_02_kyna() {
		driver.get("https://kyna.vn/");
		sleepInsecond(2);
		//scroll xuống cuối page
//		jsexecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
//		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
//		jsexecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='hotline']")));
//		sleepInsecond(5);
		
		//Không quan tâm Element có hiển thị hay không dùng javascript để click
//		driver.findElement(By.xpath("//div[@class='hotline']//a[@href='https://www.facebook.com/kyna.vn']")).click();
		clickToElementByJS("//div[@class='hotline']//a[@href='https://www.facebook.com/kyna.vn']");
		sleepInsecond(3);
		
		String Homepage = driver.getWindowHandle();
		switchtowindowbytitle("Kyna.vn");
		sleepInsecond(3);
		System.out.println("Facebook: " + driver.getCurrentUrl());
		CloseAllWindownsWithoutParent(Homepage);
		switchtowindowbytitle("Kyna.vn - Học online cùng chuyên gia");
		System.out.println("Homepage: " + driver.getCurrentUrl());
		System.out.println("Title Homepage: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
	}
	
	public void TC_03_Live_Chat_Guru() {
		driver.get("http://live.techpanda.org/");
		String Homepage = driver.getWindowHandle();
		sleepInsecond(2);
		
		driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']")).click();
		sleepInsecond(2);
		
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button")).click();
		sleepInsecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Sony Xperia was added to your shopping cart.");
		
		driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']")).click();
		sleepInsecond(2);
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button")).click();
		sleepInsecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Samsung Galaxy was added to your shopping cart.");
		
		driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/mobile.html']")).click();
		sleepInsecond(2);
		
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		driver.findElement(By.xpath("//div[@class='actions']/button[@title='Compare']")).click();
		sleepInsecond(2);
		
		switchtowindowbytitle("Products Comparison List - Magento Commerce");
		System.out.println("Compare page: " + driver.getCurrentUrl());
		System.out.println("Title: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		CloseAllWindownsWithoutParent(Homepage);
		switchtowindowbytitle("Mobile");
		System.out.println("Title: " + driver.getTitle());
		
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
		alert = driver.switchTo().alert();
		alert.accept();
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The comparison list was cleared.");
		
	}
	

	public void TC_04_Cambridge_Dictionary() {
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInsecond(2);
		String Homepagedictionary = driver.getTitle();
		driver.findElement(By.cssSelector("span.cdo-login-button span")).click();
		
		
		Set<String> windowtabid = driver.getWindowHandles();
		for (String currentwindowtabid : windowtabid) {
			if(!currentwindowtabid.equals(Homepagedictionary)) {
				driver.switchTo().window(currentwindowtabid);
				String currenttitle = driver.getTitle();
				if(currenttitle.equals("Login"))
					break;
			}
		}
		System.out.println("Page title: " + driver.getTitle());
		
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']/following-sibling::span")).getText(), "This field is required");
		
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']")).sendKeys("phamvodong@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']")).sendKeys("Admin@123");
		sleepInsecond(2);
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		sleepInsecond(5);
		
		windowtabid = driver.getWindowHandles();
		for (String currentwindowtabid : windowtabid) {
			if(!currentwindowtabid.equals(Homepagedictionary)) {
				driver.switchTo().window(currentwindowtabid);
				String currenttitle = driver.getTitle();
				if(currenttitle.equals("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa"))
					break;
			}
		}
		System.out.println("Page title: " + driver.getTitle());
		Assert.assertEquals(driver.findElement(By.cssSelector("header[id='header'] span.cdo-username")).getText(), "Dong Vo");
		
	}
	
	@Test
	public void TC_05_Revu() {
		driver.get("https://vn.test.porsche.revu.net/login");
		sleepInsecond(2);
		String Homepagedictionary = driver.getTitle();
		driver.findElement(By.cssSelector("facebook-id-login-button  button.social-login")).click();
		
		
		Set<String> windowtabid = driver.getWindowHandles();
		for (String currentwindowtabid : windowtabid) {
			if(!currentwindowtabid.equals(Homepagedictionary)) {
				driver.switchTo().window(currentwindowtabid);
				String currenttitle = driver.getTitle();
				if(currenttitle.equals("Facebook"))
					break;
			}
		}
		System.out.println("Page title: " + driver.getTitle());
		
		driver.findElement(By.cssSelector("input#email")).sendKeys("phamvodong@gmail.com");

		sleepInsecond(2);

		
	}
	
	// Switch to child windows (only 2 Windows)
	public void switchtowindowbyid (String currentid) {
		// Lấy hết tất cả các ID đang có ra
		Set<String> Allwindows = driver.getWindowHandles();
		
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String id : Allwindows) {
			// Nếu như id nào khác với currentid của page hiện tại thì switch qua
			if(!id.equals(currentid)) {
				driver.switchTo().window(id);	
			}
		}
	}
	
	// Dùng được cho cả 2 tab/ window hoặc nhiều hơn 2 đều được
	public void switchtowindowbytitle (String expectedtitle) {
		// Lấy hết tất cả các ID đang có ra
		Set<String> Allwindows = driver.getWindowHandles();
		
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String id : Allwindows) {
			// Nếu như Title nào khác với Current title của page hiện tại thì switch qua
			if(!id.equals(expectedtitle)) {
				driver.switchTo().window(id);			
				// Lấy title của page đó ra
				String actualtitle = driver.getTitle();
				if(actualtitle.equals(expectedtitle))
					break;
			}
		}
	}
	
	// Dùng được cho cả 2 tab/ window hoặc nhiều hơn 2 đều được
	public void switchtowindowbylink (String expectedrelativelink) {
		// Lấy hết tất cả các ID đang có ra
		Set<String> Allwindows = driver.getWindowHandles();
		
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String id : Allwindows) {
			// Nếu như link nào khác với current link của page hiện tại thì switch qua
			if(!id.equals(expectedrelativelink)) {
				driver.switchTo().window(id);			
				// Lấy title của page đó ra
				String actuallink = driver.getCurrentUrl();
				if(actuallink.equals(expectedrelativelink))
					break;
			}
		}
	}
	
	// Đóng tất cả các link chỉ chừa lại HomePage
		public boolean CloseAllWindownsWithoutParent (String parentid) {
			// Lấy hết tất cả các ID đang có ra
			Set<String> Allwindows = driver.getWindowHandles();
			
			// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
			for (String id : Allwindows) {
				// Nếu như id nào khác với currentid của page hiện tại thì switch qua
				if(!id.equals(parentid)) {
					driver.switchTo().window(id);			
					driver.close();
				}
			}
			driver.switchTo().window(parentid);
			
			if (driver.getWindowHandles().size() == 1) {
				return true;
			}else {
				return false;
			}
		}
		
		//Không quan tâm Element có hiển thị hay không dùng javascript để click
		public void clickToElementByJS (String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			jsexecutor = (JavascriptExecutor) driver;
			jsexecutor.executeScript("arguments[0].click();", element);
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
