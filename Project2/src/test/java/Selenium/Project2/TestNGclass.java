package Selenium.Project2;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNGclass {
	public static WebDriver driver;
	String url ="https://saucedemo.com/v1/index.html";
	String driverpath=Paths.get("C:\\Users\\Administrator\\Desktop\\driver(1)\\chromedriver.exe").toString();
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", driverpath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@BeforeMethod @Parameters({"user","password"})
	public void beforeMethod(String username, String password) {
		driver.findElement(By.xpath("//input[starts-with(@id,'user')]")).sendKeys(username);
		driver.findElement(By.xpath("//input[starts-with(@id,'pass')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[starts-with(@id,'login')]")).click();
	}
	@Parameters({"firstname", "lastname"}) @Test 
	public void firsttest(String firstname,String lastname) {
		driver.findElement(By.xpath("//*[contains(text(),'Sauce Labs Backpack')]//ancestor::div[@class='inventory_item_label']/following-sibling::div//button")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Sauce Labs Bike Light')]//ancestor::div[@class='inventory_item_label']/following-sibling::div//button")).click();
		driver.findElement(By.cssSelector("#shopping_cart_container>a>svg>path")).click();
		driver.findElement(By.xpath("//a[contains(text(),'CHECKOUT')]")).click();
		driver.findElement(By.id("first-name")).sendKeys(firstname);
		driver.findElement(By.id("last-name")).sendKeys(lastname);
		driver.findElement(By.id("postal-code")).sendKeys("698811");
		driver.findElement(By.xpath("//input[@class='btn_primary cart_button']")).click();
		scrolldown(1000);
		driver.findElement(By.cssSelector("a.btn_action.cart_button")).click();
		System.out.println(driver.findElement(By.cssSelector("#checkout_complete_container>h2")).getText());
	}
	@AfterMethod
	public void logoutbutton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement( By.cssSelector(".bm-burger-button>button")).click();
		WebElement column = driver.findElement(By.id("menu_button_container"));
		column.findElement(By.id("logout_sidebar_link")).click();
	}
	@AfterTest
	public void closebrowser() {
		driver.quit();
	}
	public void scrolldown(int scrollvalue) {
		JavascriptExecutor javascripts = (JavascriptExecutor) driver;
		javascripts.executeScript("window.scrollBy(0," + scrollvalue + ")", "");
	}

}
