package TestComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import PageObjects.LoginPage;
import Resoureces.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public LoginPage loginPage;

	// Method to initialize the WebDriver based on browser type specified in the properties file
	public WebDriver initilizationOfDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\pmeher\\eclipse-workspace\\RBL_LOA\\src\\main\\java\\Resoureces\\Data.properties");
		prop.load(file);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		return driver;
	}

	@BeforeSuite(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initilizationOfDriver();
		driver.get("https://rvfrblbank--closuat.sandbox.my.site.com/login?ec=302&startURL=%2Fs%2Fclos-lead%2FCLOS_Lead__c%2FDefault");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // Use quit() to close all browser windows and end the WebDriver session
		}
		ExtentReportManager.flushReports(); // Ensure reports are flushed after all tests
	}
}
