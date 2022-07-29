package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_Part2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_URL() {
		driver.get("http://live.techpanda.org/");
		sleepInsecond(2);
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		sleepInsecond(2);
		String URLLoginpage = driver.getCurrentUrl();
		Assert.assertEquals(URLLoginpage, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(2);
		String URLRegisterpage = driver.getCurrentUrl();
		Assert.assertEquals(URLRegisterpage, "http://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		sleepInsecond(2);
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		sleepInsecond(2);
		String TitleLoginpage = driver.getTitle();
		Assert.assertEquals(TitleLoginpage, "Customer Login");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(2);
		String TitleRegisterpage = driver.getTitle();
		Assert.assertEquals(TitleRegisterpage, "Create New Customer Account");

	}
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		sleepInsecond(2);
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		sleepInsecond(2);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(2);
		String URLRegisterpage = driver.getCurrentUrl();
		Assert.assertEquals(URLRegisterpage, "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		sleepInsecond(2);
		String URLLoginpage = driver.getCurrentUrl();
		Assert.assertEquals(URLLoginpage, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		sleepInsecond(2);
		String TitleRegisterpage = driver.getTitle();
		Assert.assertEquals(TitleRegisterpage, "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Get_Page_Source() {
		driver.get("http://live.techpanda.org/");
		sleepInsecond(2);
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		sleepInsecond(2);
		String Loginpagesource = driver.getPageSource();
		Assert.assertTrue(Loginpagesource.contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInsecond(2);
		String Registerpagesource = driver.getPageSource();
		Assert.assertTrue(Registerpagesource.contains("Create an Account"));

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
