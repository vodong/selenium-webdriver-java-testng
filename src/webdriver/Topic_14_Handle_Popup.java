package webdriver;

import java.awt.Window;
import java.util.List;
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

public class Topic_14_Handle_Popup {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	public void TC_01_Random_In_DOM_VNK() {
		// Step 1
		driver.get("https://vnk.edu.vn/");
		
//		sleepInsecond(15);
		
		//Step 2 luôn có elemtn trong DOM dù có hiển thị hoặc không
		// Nếu như popup hiển thị thì có thể thao vs popup rồi close nó đi -> qua step tiếp theo
		// Nếu như popup không hiển thị thì qua step tiếp theo luôn
		if(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
			//close popup
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInsecond(2);
			
			//expected popup không còn hiển thị nữa
			Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
			
		}
		
		//Step 3 - Click vào trang liên hệ
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		
		//Step 4 - Verify qua trang liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());

	}

	public void TC_02_Random_In_DOM_KMP() {
		// Step 1
				driver.get("https://www.kmplayer.com/home");
				
//				sleepInsecond(15);
				
				//Step 2 luôn có elemtn trong DOM dù có hiển thị hoặc không
				// Nếu như popup hiển thị thì có thể thao vs popup rồi close nó đi -> qua step tiếp theo
				// Nếu như popup không hiển thị thì qua step tiếp theo luôn
				if(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed()) {
					//close popup
					jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
					sleepInsecond(2);
					
					//expected popup không còn hiển thị nữa
					Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());					
				}
				
				//Step 3 - Click vào trang liên hệ
				driver.findElement(By.xpath("//a[contains(text(),'MOVIEBLOC')]")).click();
				
				//Step 4 - Verify qua trang liên hệ thành công
				Assert.assertTrue(driver.findElement(By.cssSelector("section.main_top_banner")).isDisplayed());

	}
	
	@Test
	public void TC_03_Random_Not_In_DOM_DeHieu() {
		// Step 1
				driver.get("https://dehieu.vn/");
				
				sleepInsecond(15);
				
				List<WebElement> popupContent = driver.findElements(By.cssSelector(("div.popup-content")));
				
				//Step 2 luôn có elemtn trong DOM dù có hiển thị hoặc không
				// Nếu như popup hiển thị thì có thể thao vs popup rồi close nó đi -> qua step tiếp theo
				// Nếu như popup không hiển thị thì qua step tiếp theo luôn
				if(popupContent.size() > 0) {
					//send thông tin
					driver.findElement(By.cssSelector("input#popup-name")).sendKeys("John Quick");
					driver.findElement(By.cssSelector("input#popup-email")).sendKeys("jd@yopmail.com");
					driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0985125555");
//					jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
					sleepInsecond(2);
					
					//close popup
					driver.findElement(By.cssSelector("button#close-popup")).click();
					
					//không dùng cách này để verify popup không hiển thị nữa
					// lý do: khi nó không hiển thị nữa thì element không còn trong DOM
					// Không còn trong DOM thì không findElement
//					-> Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());
					
					// Dùng cách này để verify popup không hiển thị nữa
					Assert.assertEquals(driver.findElements(By.cssSelector(("div.popup-content"))).size(), 0);
						
				}else {
					//Nếu như setting cho app vào ngày xxx nào đó sẽ hiện popup để chạy chương trình marketing - có popup
					// Sau thời gian này thì setting nó không hiển thị popup nữa: mở page ra nó không render cái popup này nữa
					// (Không có trong DOM ngay từ đầu)
					System.out.println("Case 2 - Nếu như popup không hiển thị thì qua step tiếp theo luôn");
					
				}
				
				//Step 3 - Click vào trang liên hệ
				driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
				
				//Step 4 - Verify qua trang liên hệ thành công
				Assert.assertTrue(driver.findElement(By.cssSelector("input#search-courses")).isDisplayed());

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
