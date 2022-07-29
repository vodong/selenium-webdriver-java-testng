package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Handle_Alert_Authentication_Alert {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String authenfirefox = projectPath + "\\autoitdriver\\authen_firefox.exe";
	String authenchrome = projectPath + "\\autoitdriver\\authen_chrome.exe";


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");
//		driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
//	@Test
//	public void TC_01_Accept_Alert() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		By jsalert = (By.cssSelector("button[onclick='jsAlert()']"));
//		driver.findElement(jsalert).click();
//		sleepInsecond(3);
//		
//		// Switch qua alert
//		alert = driver.switchTo().alert();
//		String alerttext = alert.getText();
//		Assert.assertEquals(alerttext, "I am a JS Alert");
//		
//		alert.accept();
//		sleepInsecond(3);
//		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
//
//	}

//	@Test
//	public void TC_02_Confirm_Alert() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		By jsconfirm = By.cssSelector("button[onclick='jsConfirm()']");
//		driver.findElement(jsconfirm).click();
//		sleepInsecond(3);
//		
//		alert = driver.switchTo().alert();
//		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
//		sleepInsecond(3);
//		
//		alert.dismiss();
//		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
//		
//	}
	
//	@Test
//	public void TC_03_Prompt_Alert() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		By jsprompt = By.cssSelector("button[onclick='jsPrompt()']");
//		String sendtext = "phamvodong";
//		driver.findElement(jsprompt).click();
//
//		alert = driver.switchTo().alert();
//		sleepInsecond(3);
//		
//		Assert.assertEquals(alert.getText(), "I am a JS prompt");
//		alert.sendKeys(sendtext);
//		sleepInsecond(3);
//		alert.accept();
//		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: " + sendtext);
//		
//	}
	
//	@Test
//	public void TC_04_Authentication_Alert_I() {
//		String username = "admin";
//		String password = "admin";
//		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
//		
////		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
//		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

//	}
	
	@Test
 //Recommend to use it
	public void TC_04_Authentication_Alert_II() {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com");
		
		String basicauthenlink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicauthenlink);
		
		//https://the-internet.herokuapp.com/basic_auth
		// slipt (tách chuỗi)
		String[] basiclink = basicauthenlink.split("//");
		// http:
		// the-internet.herokuapp.com/basic_auth
		
		basicauthenlink = basiclink[0] + "//" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		System.out.println(basicauthenlink);
		driver.get(basicauthenlink);
		sleepInsecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
//	@Test
//	public void TC_04_Authentication_Alert_Use_AutoIT_III() throws IOException {
//		String username = "admin";
//		String password = "admin";
//		driver.get("http://the-internet.herokuapp.com");
//		
//		//Script sẽ chạy trước để chờ Authen alert bật lên sau
//		if(driver.toString().contains("Firefox")) {
//			Runtime.getRuntime().exec(new String[] {authenfirefox, username, password});
//		}else {
//			Runtime.getRuntime().exec(new String[] {authenchrome, username, password});
//		}
//		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
//		sleepInsecond(8);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
//
//	}

 
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
