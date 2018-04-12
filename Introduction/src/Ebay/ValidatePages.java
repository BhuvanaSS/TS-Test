package Ebay;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatePages {
	RemoteWebDriver driver ;

	@Test
	public void  userCredential(String browser) throws InterruptedException {

		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get("https://www.ebay.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Sign in")).click();

		verifyExcetTitle("Sign in or Register | eBay");
		driver.findElement(By.id("userid")).sendKeys("bhuvana.sara@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("4ureyes");
		/*Sign in*/ 
		driver.findElement(By.id("sgnBt")).click();
		/*Searching for Laptops*/
		verifyExcetTitle("Electronics, Cars, Fashion, Collectibles, Coupons and More | eBay");
		driver.findElement(By.id("gh-ac")).sendKeys("Laptops");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//Clicking first available brand - Acer//
		verifyExcetTitle("Laptops | eBay");
		driver.findElement(By.xpath("//a[@data-text='new laptops']")).click();
		driver.findElement(By.xpath("//span[text()='Acer']/preceding::input[1]")).click();
		/*Displayed checked box - Accer */
		verifyContainsTitle("Acer new laptops | eBay");
		if (driver.findElement(By.xpath("//span[text()='Acer']/preceding-sibling::input[1]")).isSelected()) {
			System.out.println("The checkbox is Selected ");
		}
		//Clicking first available product link//
		verifyContainsTitle("Acer new laptops | eBay");
		driver.findElement(By.xpath("(//*[@id='ListViewInner']//h3/a)[1]")).click();
		Thread.sleep(2000);
		//Add to cart //
		verifyContainsTitle("Acer");
		driver.findElement(By.xpath("//a[contains(text(),'cart')]")).click();
		//Checked to proceed //
		verifyContainsTitle("Your eBay Shopping Cart");
		driver.findElement(By.linkText("Proceed to checkout")).click();
		Thread.sleep(2000);
		verifyExcetTitle("Checkout");
		driver.findElement(By.xpath("//td[contains(text(),'Item (1)')]")).click();
		System.out.println("Successfully loaded the payment page");

	}	

	public void verifyExcetTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle,expectedTitle);
		System.out.println("actualTitle "+actualTitle+" matching with expectedTitle : "+expectedTitle);
	}

	public void verifyContainsTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if (actualTitle.contains(expectedTitle)) {			
			System.out.println("actualTitle "+actualTitle+" Contains with expectedTitle : "+expectedTitle);
		}
	}

	
	
}
