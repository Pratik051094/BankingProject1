package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Client_Project.Utiles;
import Resoureces.ExtentReportManager;
import Resoureces.Listeners;

public class PermanentAddress_KYCAddressPage extends DropDown {

	private WebDriver driver;
	private ExtentTest test7;

	@FindBy(xpath = "(//input[@class='slds-input'])[1]")
	private WebElement add1;

	@FindBy(xpath = "(//input[@class='slds-input'])[2]")
	private WebElement add2;

	@FindBy(xpath = "(//input[@class='slds-input'])[4]")
	private WebElement pinCode;

	@FindBy(xpath = "(//input[@class='slds-input'])[15]")
	private WebElement bAdd1;

	@FindBy(xpath = "(//input[@class='slds-input'])[16]")
	private WebElement bAdd2;

	@FindBy(xpath = "(//input[@class='slds-input'])[17]")
	private WebElement landmark;

	@FindBy(xpath = "(//input[@class='slds-input'])[18]")
	private WebElement bPin;

	@FindBy(xpath = "//button[text()='Save & Next']")
	private WebElement saveAndNextButton;

	public PermanentAddress_KYCAddressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.test7 = Listeners.getTest();
	}

	public void KYCAddress(String addressType) {
		DrpDnAccess("Type of Address Proof", addressType);
	}

	public void CurrentAdd(String addressType) {
		ScrollContainer("(//button[@aria-label='Select Address'])[2]");
		waitForElementToClickeable(driver, By.xpath("(//button[@aria-label='Select Address'])[2]"),
				Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//button[@aria-label='Select Address'])[2]")).click();
		WebElement addressDropdown = driver.findElement(By.xpath("(//div[@aria-label='Select Address'])[2]"));
		addressDropdown.findElement(By.xpath("//*[@data-value='" + addressType + "']")).click();
	}

	public void CommunicationAdd(String communicationAddress) throws InterruptedException {
		ScrollContainer("(//button[@aria-label='Select Address'])[3]");
		waitForElementToClickeable(driver, By.xpath("(//button[@aria-label='Select Address'])[3]"),
				Duration.ofSeconds(20));
		driver.findElement(By.xpath("(//button[@aria-label='Select Address'])[3]")).click();
		WebElement addressDropdown = driver.findElement(By.xpath("(//div[@aria-label='Select Address'])[3]"));
		addressDropdown.findElement(By.xpath("//*[@data-value='" + communicationAddress + "']")).click();

	}

	public void fillPermanentAndKYCAddress(String kycAdd, String addOne, String addTwo, String pincode,
			String currentAdd, String baddone, String baddtwo, String landMark, String pincode2, String commAdd)
			throws Exception {
		try {
			test7.info("Starting to fill Permanent and KYC address details.");

			// Wait for spinner to disappear
			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));

			// Fill out KYC and permanent address details
			KYCAddress(kycAdd);
			test7.info("Selected KYC address: " + kycAdd);

			add1.sendKeys(addOne);
			test7.info("Entered address line 1: " + addOne);

			add2.sendKeys(addTwo);
			test7.info("Entered address line 2: " + addTwo);

			pinCode.sendKeys(pincode);
			test7.info("Entered pincode: " + pincode);
			waitForAriaInvalidAttribute(driver, By.xpath("(//input[@class='slds-input'])[7]"), Duration.ofSeconds(10));

			// Select current address
			ScrollContainer("(//button[@aria-label='Select Address'])[2]");
			waitForElementToAppear(driver, By.xpath("(//button[@aria-label='Select Address'])[2]"),
					Duration.ofSeconds(15));
			CurrentAdd(currentAdd);
			test7.info("Selected current address: " + currentAdd);
			waitForAriaInvalidAttribute(driver, By.xpath("(//input[@class='slds-input'])[14]"), Duration.ofSeconds(10));


			// Fill out billing address details
			ScrollContainer("(//button[@aria-label='Select Address'])[3]");
			waitForElementToAppear(driver, By.xpath("(//input[@class='slds-input'])[14]"), Duration.ofSeconds(15));

			bAdd1.sendKeys(baddone);
			test7.info("Entered billing address line 1: " + baddone);

			bAdd2.sendKeys(baddtwo);
			test7.info("Entered billing address line 2: " + baddtwo);

			landmark.sendKeys(landMark);
			test7.info("Entered landmark: " + landMark);

			bPin.sendKeys(pincode2);
			test7.info("Entered billing pincode: " + pincode2);
			
			waitForAriaInvalidAttribute(driver, By.xpath("(//input[@class='slds-input'])[21]"), Duration.ofSeconds(10));


			// Select communication address
			ScrollContainer("(//button[@aria-label='Select Address'])[3]");
			waitForElementToAppear(driver, By.xpath("//button[text()='Save & Next']"), Duration.ofSeconds(20));
			CommunicationAdd(commAdd);
			test7.info("Selected communication address: " + commAdd);
			
			waitForAriaInvalidAttribute(driver, By.xpath("(//input[@class='slds-input'])[28]"), Duration.ofSeconds(10));


			test7.pass("Details entered and form submitted successfully.");
			// Capture screenshot before saving
			Utiles.getScreenshot(driver, "permanent_address_success");

			saveAndNextButton.click();
		} catch (Exception e) {
			// Capture screenshot on failure
			Utiles.getScreenshot(driver, "permanent_address_failure");

			// Log the failure
			test7.fail("Failed to fill Permanent and KYC address details: " + e.getMessage());

			// Optionally, rethrow the exception
			throw e;
		}
	}
}
