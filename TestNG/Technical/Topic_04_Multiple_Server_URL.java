package Technical;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_04_Multiple_Server_URL {
	 WebDriver driver;
	 By emailTextbox = By.xpath("//*[@id='email']");
	 By passwordTextbox = By.xpath("//*[@id='pass']");
	 By loginButton = By.xpath("//*[@id='send2']");
	 String projectPath = System.getProperty("user.dir");
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		 	switch (browserName) {		
			case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserdriver\\chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println(browserName);
				break;
				
			case "firefox":
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserdriver\\geckodriver.exe");
				driver = new FirefoxDriver();
				System.out.println(browserName);
				break;
				
			case "edge":
				System.setProperty("webdriver.edge.driver", projectPath + "\\browserdriver\\msedgedriver.exe");
				driver = new EdgeDriver();
				System.out.println(browserName);
				break;
	
			default:
				System.out.println("Browser is invalid");
				break;
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
 
	 @Parameters("server")
	 @Test
	 public void TC_01_Login(String serverName){
		String URL = getServerUrl(serverName);
		 driver.get("http://" + URL + "/index.php/customer/account/login/");
		 driver.findElement(emailTextbox).sendKeys("phamvodong2@gmail.com");
		 driver.findElement(passwordTextbox).sendKeys("Admin@123");
		 driver.findElement(loginButton).click();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("phamvodong2@gmail.com"));
		 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		 driver.findElement(By.xpath("//a[text()='Log Out']")).click();
}
	private String getServerUrl(String serverName) {
		switch (serverName) {
		case "DEV":
			serverName = "";
			break;
		case "Testing":
			serverName = "";
			break;
		case "Staging":
			serverName = "";
			break;
		case "Live":
			serverName = "live.techpanda.org";
			break;

		default:
			System.out.println("URL is invalid");
			break;
		}
		return serverName;
	}
	
 
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
