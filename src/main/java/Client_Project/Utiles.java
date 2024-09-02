package Client_Project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Resoureces.Listeners;

public class Utiles {
	WebDriver driver;

	public Utiles(WebDriver driver) {
		this.driver = driver;
	}

	public static WebElement waitForElementToAppear(WebDriver driver, By locator, Duration i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void AutoItFileUpload() throws Exception {
		Thread.sleep(3000);
		String File = "C:\\Users\\pmeher\\eclipse-workspace\\RBL_LOA\\src\\main\\java\\Resoureces\\Demo.png";
		File DynamicFile = new File(File);
		// FileInputStream file1 = new FileInputStream(DynamicFile);
		Runtime.getRuntime().exec(

				// "C:\\Users\\pmeher\\OneDrive - Planit Test Management Solutions Pty
				// Ltd\\Documents\\FileUpload.exe"
				// "C:\\Users\\pmeher\\eclipse-workspace\\RBL_Bank\\src\\main\\java\\Resoureces\\FileUpload.exe"
				"C:\\Users\\pmeher\\eclipse-workspace\\RBL_LOA\\src\\main\\java\\Files\\ImgFileUpload.exe " + DynamicFile);
		Thread.sleep(3000);
	}

	public static void getScreenshot(WebDriver driver, String str) throws IOException {
	    // Get the name of the current test
	    String testName = Listeners.getTestName();
	    
	    // Capture the screenshot
	    TakesScreenshot screenshot = (TakesScreenshot) driver;
	    File src = screenshot.getScreenshotAs(OutputType.FILE);
	    
	    // Define the path for the screenshot file, creating a directory named after the test case
	    File testDirectory = new File("./screenshots/" + testName);
	    if (!testDirectory.exists()) {
	        testDirectory.mkdirs(); // Create the directory if it doesn't exist
	    }
	    
	    // Define the destination file path within the test-specific directory
	    File dest = new File(testDirectory, str + ".png");
	    
	    // Copy the screenshot to the destination file
	    FileUtils.copyFile(src, dest);
	}

	public void ScrollParent(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void ScrollContainer(String xpath) {
		WebElement element = driver.findElement(By.xpath("(//section)[2]"));
		WebElement elm = element.findElement(By.xpath(xpath));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elm);
	}

	public static void highlightElement(WebDriver driver, String ele) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement tickethi = driver.findElement(By.xpath(ele));
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", ele);
		try {
			Thread.sleep(1000); // Highlight for 1 second
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", ele);

		tickethi.click();
	}
}
