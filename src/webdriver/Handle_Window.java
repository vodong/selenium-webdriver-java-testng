package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Handle_Window {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsexecutor;
	Alert alert;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_Login_Windown() {
		driver.get("https://vn.test.porsche.revu.net/login");
		sleepInsecond(2);
		String Homepagedictionary = driver.getTitle();
		driver.findElement(By.cssSelector("facebook-id-login-button  button.social-login")).click();
		sleepInsecond(2);
		
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
			
		driver.findElement(By.cssSelector("input#email")).sendKeys("phamvodong2@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		sleepInsecond(2);
		driver.findElement(By.xpath("//div[@role='button']//div//span[contains(text(),'Tiếp tục dưới tên SaiGon Hobby')]")).click();
		sleepInsecond(2);

		windowtabid = driver.getWindowHandles();
		for (String currentwindowtabid : windowtabid) {
			if(!currentwindowtabid.equals(Homepagedictionary)) {
				driver.switchTo().window(currentwindowtabid);
				String currenttitle = driver.getTitle();
				if(currenttitle.equals("Global No.1 Influencer Marketing Platform, REVU"))
					break;
			}
		}
		System.out.println("Page title: " + driver.getTitle());
		sleepInsecond(5);
		
		
	}

	
	public void switchtowindowbyid (String currentid) {
		Set<String> Allwindows = driver.getWindowHandles();
		
		for (String id : Allwindows) {
			if(!id.equals(currentid)) {
				driver.switchTo().window(id);	
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
