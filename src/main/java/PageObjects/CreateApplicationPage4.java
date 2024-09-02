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

public class CreateApplicationPage4 extends DropDown {

	private WebDriver driver;
	private ExtentTest test5;

	public CreateApplicationPage4(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.test5 = Listeners.getTest();
	}

	@FindBy(xpath = "(//span[@class='slds-checkbox_faux'])[2]")
	private WebElement bioMetricKYCNoButton;

	@FindBy(xpath = "(//span[@class='slds-checkbox_faux'])[1]")
	private WebElement bioMetricKYCYesButton;

	@FindBy(xpath = "//button[@name='Document']")
	private WebElement documentDropdown;

	@FindBy(xpath = "(//div[@class='file-uploader'])[1]")
	private WebElement documentImageFront;

	@FindBy(xpath = "(//div[@class='file-uploader'])[2]")
	private WebElement documentImageBack;

	@FindBy(xpath = "(//input[@class='slds-input'])[1]")
	private WebElement enterAadhaarNumberField;

	@FindBy(xpath = "//button[text()='Verify']")
	private WebElement verifyButton;

	@FindBy(xpath = "//button[@name='MaritalStatus']")
	private WebElement maritalStatusDropdown;

	@FindBy(xpath = "(//input[@class='slds-input'])[6]")
	private WebElement motherField;

	@FindBy(xpath = "(//input[@class='slds-input'])[8]")
	private WebElement spouseNameField;

	@FindBy(xpath = "//button[text()='Run POSIDEX']")
	private WebElement runPOSIDEXButton;

	@FindBy(xpath = "//button[text()='Save & Next']")
	private WebElement saveAndNextButton;

	@FindBy(xpath = "//c-c-l-o-s-generic-file-uploader[1]")
	private WebElement photoUpload1;

	@FindBy(xpath = "(//*[@class='slds-icon slds-icon-text-default'])[3]")
	private WebElement photoUpload3;

	public void selectDocument(String doc) {
		DrpDnAccess("Document", doc);
	}

	public void selectMaritalStatus(String marital) {
		DrpDnAccess("Marital Status", marital);
	}

	public void createApp4(String document, String voterNo, String marital, String mother, String spouse)
			throws Exception {
		try {

			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(20));

			// Select BioMetric KYC option
			bioMetricKYCNoButton.click();
			waitForElementToAppear(driver, By.xpath("//button[@aria-label='Document']"), Duration.ofSeconds(15));

			// Select Document type and upload photo
			selectDocument(document);
			photoUpload1.click();
			Utiles.AutoItFileUpload();

			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
			enterAadhaarNumberField.sendKeys(voterNo);
			waitForElementToAppear(driver, By.xpath("//button[text()='Verify']"), Duration.ofSeconds(15));
			verifyButton.click();

			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));

			selectMaritalStatus(marital);

			waitForElementToAppear(driver, By.xpath("(//input[@class='slds-input'])[6]"), Duration.ofSeconds(15));
			motherField.sendKeys(mother);
			spouseNameField.sendKeys(spouse);

			ScrollContainer("//button[text()='Run POSIDEX']");
			photoUpload3.click();
			Utiles.AutoItFileUpload();
			
			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));

			runPOSIDEXButton.click();

			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
			waitForElementToAppear(driver, By.xpath("//button[text()='Save & Next']"), Duration.ofSeconds(15));

			if (isElementInvisible(driver, By.xpath("//button[text()='Run POSIDEX']"), Duration.ofSeconds(20))) {
				test5.pass("Application 4 details entered successfully.");
				Utiles.getScreenshot(driver, "App4_pass");
				saveAndNextButton.click();
			}
			else {
				test5.fail("");
			}

		} catch (Exception e) {
			test5.fail("Application 4 creation failed due to: " + e.getMessage());
			Utiles.getScreenshot(driver, "App4_fail");
			throw e; // Optionally rethrow the exception if needed
		}
	}
}
