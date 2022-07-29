package Technical;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_02_DataProvider {
 WebDriver driver;
 By emailTextbox = By.xpath("//*[@id='email']");
 By passwordTextbox = By.xpath("//*[@id='pass']");
 By loginButton = By.xpath("//*[@id='send2']");
 String projectPath = System.getProperty("user.dir");

 @BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
 
 @Test(dataProvider ="user_pass")
 public void TC_01_LoginToSystem(String username, String password) throws InterruptedException {
	 driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	 driver.findElement(emailTextbox).sendKeys(username);
	 driver.findElement(passwordTextbox).sendKeys(password);
	 driver.findElement(loginButton).click();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
	 driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	 driver.findElement(By.xpath("//a[text()='Log Out']")).click();
 }
 
 @DataProvider(name ="user_pass")
 public Object[][] UserAndPasswordData(){
	 return new Object[][] {{"phamvodong2@gmail.com", "Admin@123"},{"phamvodong4@gmail.com","Admin@123"},{"phamvodong6@gmail.com","Admin@123"}};
	 }
 
  @AfterSuite
  public void afterSuite() {
	  driver.quit();
  }

}
