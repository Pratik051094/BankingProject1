package PageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Client_Project.Utiles;
import Resoureces.*;

public class LoginPage extends DropDown {
	private Utiles utiles;
	public WebDriver driver;
	private ExtentTest test;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.test = Listeners.getTest();
	}

	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameInput;

	@FindBy(xpath = "//input[@name='pw']")
	private WebElement passwordInput;

	@FindBy(xpath = "//input[@name='Login']")
	private WebElement loginButton;

	public void LoginIntoApp(String username, String password) throws Exception {
		try {
			utiles = new Utiles(driver);
			test.info("Navigating to website");

			if (username == null || password == null) {
				test.fail("No input data in excel.");
			} else if (isElementClickable(driver, By.xpath("//input[@name='username']"), Duration.ofSeconds(20))) {
				usernameInput.sendKeys(username);
				passwordInput.sendKeys(password);

				Utiles.getScreenshot(driver, "login pass"); // Update call to use the test name

				loginButton.click();
				
			} else {
				test.fail("Unexpected error.");
			}

			test.info("Entered username: " + username);
			test.info("Entered password: " + password);

			test.pass("Login data entered successfully.");
		} catch (Exception e) {
			test.fail("Login failed: " + e.getLocalizedMessage());
			Utiles.getScreenshot(driver, "login fail"); // Update call to use the test name
		}
		if(driver.findElement(By.xpath("//a[@id='1']")).isDisplayed()) {
			test.pass("Login Success");
		}
		else {
			test.fail("Login fail");
		}
	}
}
