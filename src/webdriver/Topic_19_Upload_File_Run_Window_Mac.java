package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_File_Run_Window_Mac {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName =  System.getProperty("os.name");

	String filename1 = "Screenshot1.png";
	String filename2 = "Screenshot2.png";
	String filename3 = "Screenshot3.png";
	
	//Support cho tất cả các OS
	String UploadFileFolderPath = projectPath + File.separator + "uploadfile" + File.separator;
	String Screenshot1FilePath = UploadFileFolderPath + filename1;
	String Screenshot2FilePath = UploadFileFolderPath + filename2;
	String Screenshot3FilePath = UploadFileFolderPath + filename3;


	@BeforeClass
	public void beforeClass() {
		
		// Both: windows + MAC
		if (osName.toUpperCase().startsWith("MAC")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
		}
		
		
		//chạy headless không cần mở trình duyệt
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
//		options.addArguments("window-size=1366x768");
//		driver = new ChromeDriver(options);
		
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		System.out.println(UploadFileFolderPath);
		System.out.println(Screenshot1FilePath);
		System.out.println(Screenshot2FilePath);
		System.out.println(Screenshot3FilePath);
	}
 
	public void TC_01_One_File_One_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInsecond(2);
		//Selenium sendkeys method
		// Không có click vào button Add file để bật Open File Dialog len
		
		// Cách 1 không nên dùng vì khi upload 1 file lên thì element sẽ bị thay đổi
//		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(Screenshot1FilePath);
//		uploadFile.sendKeys(Screenshot2FilePath);
//		uploadFile.sendKeys(Screenshot3FilePath);
		
		//Cách 2
		By uploadFile = By.xpath("//input[@type='file']");
		driver.findElement(uploadFile).sendKeys(Screenshot1FilePath);		
		driver.findElement(uploadFile).sendKeys( Screenshot2FilePath);
		driver.findElement(uploadFile).sendKeys(Screenshot3FilePath);
		sleepInsecond(3);
		
		//Verify loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ filename1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ filename2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ filename3 + "']")).isDisplayed());

		//Click to Upload button at each file
		List<WebElement> uploadbuttons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadbuttons) {
			button.click();
			sleepInsecond(2);			
		}
		
		//Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename3 + "']")).isDisplayed());
	}

	public void TC_02_Multiple_File_One_Time() {	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInsecond(2);

		By uploadFile = By.xpath("//input[@type='file']");
		
		//Load 3 files at that time
		driver.findElement(uploadFile).sendKeys(Screenshot1FilePath + "\n" + Screenshot2FilePath + "\n" + Screenshot3FilePath);		
		sleepInsecond(3);
		
		//Verify loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ filename1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ filename2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ filename3 + "']")).isDisplayed());

		//Click to Upload button at each file
		List<WebElement> uploadbuttons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement button : uploadbuttons) {
			button.click();
			sleepInsecond(2);			
		}
		
		//Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + filename3 + "']")).isDisplayed());

	}
	
	@Test
	public void TC_03_Go_File() {	
		driver.get("https://gofile.io/uploadFiles");
		sleepInsecond(2);

		By uploadFile = By.xpath("//input[@type='file']");
		
		//Load 3 files at that time
		driver.findElement(uploadFile).sendKeys(Screenshot1FilePath + "\n" + Screenshot2FilePath + "\n" + Screenshot3FilePath);		
		sleepInsecond(10);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		driver.get(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href"));
		
		
		//Verify download button
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ filename1 + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ filename2 + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ filename3 + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']")).isDisplayed());
		
		//Verify play button
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ filename1 + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ filename2 + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+ filename3 + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]")).isDisplayed());
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
