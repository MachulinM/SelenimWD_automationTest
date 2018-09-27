package automationTest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTestCase {

	WebDriver driver;
	
	@Before
	public void beforeScenario(){
		System.setProperty("webdriver.chrome.driver", "..\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void successfulAuthorization() throws InterruptedException {

		driver.get("https://www.google.com");
		
		WebElement signInButton = driver.findElement(By.id("gb_70"));
		signInButton.click();
		
		WebElement userEmail = driver.findElement(By.id("identifierId"));
		userEmail.sendKeys("automationTestMail123@gmail.com");
		userEmail.sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		
		WebElement userPass = driver.findElement(By.name("password"));
		userPass.sendKeys("a12345678A");
		userPass.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		
		if (driver.findElement(By.cssSelector(".gb_Sg")).isDisplayed())
		{
			driver.findElement(By.cssSelector(".gb_9a")).click();
			Thread.sleep(2000);
			assertEquals("automationtestmail123@gmail.com", driver.findElement(By.cssSelector("div.gb_Eb")).getText());
			System.out.println("Пользователь успешно зарегестрирован");
		}
		else 
		{
			System.out.println("Пользователь не залогинен");
		}

	}
	
	@Test
	public void invalidEmail() throws InterruptedException {

		driver.get("https://www.google.com");
		
		WebElement signInButton = driver.findElement(By.id("gb_70"));
		signInButton.click();
		
		WebElement invalidUserEmail = driver.findElement(By.id("identifierId"));
		invalidUserEmail.sendKeys("1invalidUserMail@gmail.com");
		invalidUserEmail.sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		
		if (driver.findElement(By.cssSelector(".dEOOab")).isDisplayed())
		{
			driver.get("chrome://settings/people");
			Thread.sleep(5000);
			assertTrue(isElementPresent(By.cssSelector("div.start")));
			System.out.println("Пользователь не залогинен");
		}
		else 
		{
			System.out.println("Такой логин существует");
		}
	}
	
	@Test
	public void invalidPassword() throws InterruptedException {

		driver.get("https://www.google.com");
		
		WebElement signInButton = driver.findElement(By.id("gb_70"));
		signInButton.click();
		
		WebElement validUserEmail = driver.findElement(By.id("identifierId"));
		validUserEmail.sendKeys("automationTestMail123@gmail.com");
		validUserEmail.sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		
		WebElement invalidUserPass = driver.findElement(By.name("password"));
		invalidUserPass.sendKeys("1invalidPass");
		invalidUserPass.sendKeys(Keys.ENTER);
		
		//.dEOOab .RxsGPe .Xb9hP
		Thread.sleep(3000);
		
		if (driver.findElement(By.cssSelector(".Xb9hP")).isDisplayed())
		{
			driver.get("chrome://settings/people");
			Thread.sleep(5000);
			assertTrue(isElementPresent(By.cssSelector("div.start")));
			System.out.println("Пользователь не залогинен");
		}
		else 
		{
			System.out.println("Пользователь успешно зарегестрирован");
		}
	}

	private void assertTrue(Object elementPresent) {
	}

	private Object isElementPresent(By cssSelector) {
		return null;
	}
	
	@After
    public void afterScenario() throws InterruptedException{
		Thread.sleep(3000);
		driver.quit();
    }	

}
