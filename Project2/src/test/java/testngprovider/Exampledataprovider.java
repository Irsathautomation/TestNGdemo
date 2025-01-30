package testngprovider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resuablity.Reusablitylibrary;

public class Exampledataprovider {
	public static WebDriver driver;
	String Url = "https://www.calculator.net/bmi-calculator.html";
	public By clearbutton= By.xpath("//input[@value='Clear']");
	public By calculatebutton= By.xpath("//input[@value='Calculate']");
	public By ageid= By.id("cage");
	public By heightid=By.id("cheightfeet");
	By weightid=By.id("cpound");
	By resultBMI= By.cssSelector("div.bigtext>font>b");
	Reusablitylibrary reuse;
	
	@Test(dataProvider = "datas")
	public void BMICalculator(Integer age, Integer height,Integer weight) {
		firstrun();
		reuse.clickelement(clearbutton);
		reuse.sendtextwithxpath(ageid,age.toString());
		reuse.sendtextwithxpath(heightid, height.toString());
		reuse.sendtextwithxpath(weightid, weight.toString());
		reuse.clickelement(calculatebutton);
		reuse.print_the_text(resultBMI);
	}
	public void firstrun() {
		reuse=new Reusablitylibrary(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\driver(1)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider(name = "datas")
	public Object[][] datas() {
		return new Object[][] { { 30, 170, 60 },
			{ 10, 90, 60}, 
			{ 69, 170, 70 } };
	}
}
