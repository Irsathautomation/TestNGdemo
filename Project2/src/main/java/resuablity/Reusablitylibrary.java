package resuablity;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusablitylibrary {

	public WebDriver driver;

	public Reusablitylibrary(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void browsersetup() {
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
	}

	public void close_browser() {
		driver.close();
	}

	public void quitbrowser() {
		driver.quit();
	}

	public void wait(int waitingtime, By Locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitingtime));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}

	public void launchURL(String url) {
		driver.get(url);

	}

	public String getcolorwithxpath(By colorlocator) {
		String nocolor = "No color found";
		String color = generatexpath(colorlocator).getCssValue("color");
		if (color != null) {
			return color;
		}
		return nocolor;
	}

	private WebElement generatexpath(By xpath) {
		return driver.findElement(xpath);

	}

	private WebElement generatexpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	private WebElement generatexpath_id(String xpath) {
		return driver.findElement(By.id(xpath));
	}

	public void scrolldown(int scrollvalue) {
		JavascriptExecutor javascripts = (JavascriptExecutor) driver;
		javascripts.executeScript("window.scrollBy(0," + scrollvalue + ")", "");

	}

	public void mouse_hover_xpath(By locatorxpath) {
		Actions mouseactions = new Actions(driver);
		wait(2000, locatorxpath);
		mouseactions.moveToElement(generatexpath(locatorxpath));
		mouseactions.build().perform();
	}

	public void clickelement(String xpath) {
		generatexpath(xpath).click();
	}

	public void clickelement_byid(String xpath) {
		generatexpath_id(xpath).click();
	}

	public List<WebElement> list_of_elements(By xpath) {
		List<WebElement> Elements = driver.findElements(xpath);
		return Elements;

	}

	public void clickelement(By xpath) {
		generatexpath(xpath).click();
	}

	public void double_click_element(By xpath) {
		WebElement element = generatexpath(xpath);
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void Select_month_with_text(String month, By xpath) {
		Select objSelect = new Select(generatexpath(xpath));
		objSelect.selectByVisibleText(month.substring(0, 1).toUpperCase() + month.substring(1, 3));
	}

	public void double_click_element(String xpath) {
		WebElement element = generatexpath(By.xpath(xpath));
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void sendtextwithxpath(String xpath, String content) {
		generatexpath(By.xpath(xpath)).sendKeys(content);
	}

	public void sendtextwithxpath(By xpath, String content) {
		generatexpath(xpath).sendKeys(content);
	}

	public void sharefile_byxpath(By xpath, String filepath) {
		generatexpath(xpath).sendKeys(filepath);
	}

	public void copyandpaste_text_with_xpath(By takenxpath1, By targetxpath2) {
		WebElement copiedcontent = generatexpath(takenxpath1);
		copiedcontent.sendKeys(Keys.CONTROL, "c");
		WebElement pastecontent = generatexpath(targetxpath2);
		pastecontent.sendKeys(Keys.CONTROL, "v");
	}

	public void copyandpaste_text_with_string(String takenxpath1, String targetxpath2) {

		WebElement copiedcontent = generatexpath(By.xpath(takenxpath1));
		copiedcontent.sendKeys(Keys.CONTROL, "c");
		WebElement pastecontent = generatexpath(targetxpath2);
		pastecontent.sendKeys(Keys.CONTROL, "v");
	}

	public boolean check_empty(By xpath) {
		String check = generatexpath(xpath).getText();
		if (check.isEmpty()) {
			return true;
		}
		return false;
	}

	public String gettextfromcss(String selector) {
		String text = driver.findElement(By.cssSelector(selector)).getText();
		return text;
	}

	public String gettextfromxpath(By selector) {
		String text = driver.findElement(selector).getText();
		return text;
	}

	public List<String> gettextsfromcss(String selector) {
		List<WebElement> text = driver.findElements(By.cssSelector(selector));
		List<String> values = new ArrayList<>();
		for (WebElement valu : text) {
			values.add(valu.getText());
		}
		return values;
	}

	public List<String> gettextsfromxpath(String selector) {
		List<WebElement> text = driver.findElements(By.xpath(selector));
		List<String> values = new ArrayList<>();
		for (WebElement valu : text) {
			values.add(valu.getText());
		}
		return values;
	}

	public void mouse_hover_xpath(String locatorxpath) {
		Actions mouseactions = new Actions(driver);
		wait(2000, By.xpath(locatorxpath));
		mouseactions.moveToElement(generatexpath(locatorxpath));
	}

	public void navigate_back() {
		driver.navigate().back();
	}

	public Set<String> windowhandles() {
		Set<String> Windowsaddress = driver.getWindowHandles();
		return Windowsaddress;
	}

	public String windowhandle() {
		String windowaddress = driver.getWindowHandle();
		return windowaddress;
	}

	public WebDriver driver() {
		return this.driver;
	}

	public void setwindow(String windowaddress) {
		driver.switchTo().window(windowaddress);
	}

	public void print_the_text(String xpath) {
		System.out.println(generatexpath(xpath).getText());
	}

	public void drag_and_drop_xpath(By initialpath, By finalpath) {
		Actions mouseactions = new Actions(driver);
		wait(2000, initialpath);
		mouseactions.dragAndDrop(generatexpath(initialpath), generatexpath(finalpath));
		mouseactions.build().perform();

	}

	public void print_the_text(By xpath) {
		System.out.println(generatexpath(xpath).getText());
	}

	public void screenshot_file(String imagename) {
		TakesScreenshot screenshotfunction = ((TakesScreenshot)driver);
		File screenshotfile = screenshotfunction.getScreenshotAs(OutputType.FILE);
		String File_path=System.getProperty("user.dir")+"\\screenshot\\";
		try {
			FileUtils.copyFile(screenshotfile,new File(File_path+imagename+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
