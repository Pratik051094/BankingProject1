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
import Resoureces.ExtentReportManager;
import Resoureces.Listeners;

public class AssetDetails extends DropDown {
	private WebDriver driver;
	private ExtentTest test9;

	public AssetDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.test9 = Listeners.getTest();
	}

	@FindBy(xpath = "//button[@name='Two Wheeler Manufacturer']")
	public WebElement TwoWheelerManufacturerDropdwon;

	@FindBy(xpath = "//button[@name='Two Wheeler Type']")
	public WebElement TwoWheelerTypeDropdwon;

	@FindBy(xpath = "//button[@name='Model']")
	public WebElement ModelDropdwon;

	@FindBy(xpath = "//button[@name='Variant']")
	public WebElement VariantDropdwon;

	@FindBy(xpath = "(//input[@class='slds-input'])[4]")
	public WebElement ExShowroomPriceField;

	@FindBy(xpath = "(//input[@class='slds-input'])[5]")
	public WebElement RTOChargesField;

	@FindBy(xpath = "(//input[@class='slds-input'])[6]")
	public WebElement InsuranceAmountField;

	@FindBy(xpath = "(//input[@class='slds-input'])[7]")
	public WebElement MandatoryAccessoriesAmountField;

	@FindBy(xpath = "(//input[@class='slds-input'])[8]")
	public WebElement OtherRegistrationChargesField;

	public void Manufacturer(String bike) {
		DrpDnAccess("Two Wheeler Manufacturer", bike);
	}

	public void Type(String typ) {
		DrpDnAccess("Two Wheeler Type", typ);
	}

	public void Model(String model) {
		DrpDnAccess("Model", model);
	}

	public void Variant(String var) {
		DrpDnAccess("Variant", var);
	}

	public void EngineCC(String var) {
		DrpDnAccess("EngineCC", var);
	}

	public void Electric(String var) {
		DrpDnAccess("Electric", var);
	}

	public void VehicleCategory(String var) {
		DrpDnAccess("VehicleCategory", var);
	}

	@FindBy(xpath = "//button[text()='Save & Next']")
	private WebElement Saveandnextbutton;

	public void ModelOne(String ModelOne) {
		driver.findElement(By.xpath("//*[@name='Model']")).click();
		WebElement we = driver.findElement(By.xpath("//*[@name='Model']"));
		we.findElement(By.xpath("(//*[@data-value='" + ModelOne + "'])[1]")).click();
	}

	public void AssetDetails(String type, String variant, String ShowroomPrice, String RTOcharge,
			String InsuranceAmount, String MandatoryAmount, String OtherCharges, String ModelOne) throws Exception {
		try {
			test9.info("Navigate to Asset page");
			test9.pass("Navigated to Asset page successfully");
			test9.log(Status.INFO, "Fetching Asset details.");
			waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
			waitForElementToAppear(driver, By.xpath("//button[@aria-label='Two Wheeler Type']"),
					Duration.ofSeconds(15));

			Type(type);
			test9.log(Status.INFO, "Type :" + type);
			waitForElementToClickeable(driver, By.xpath("//*[@name='Model']"), Duration.ofSeconds(15));
			ModelOne(ModelOne);
			waitForElementToClickeable(driver, By.xpath("//button[@name='Variant']"), Duration.ofSeconds(15));
			Variant(variant);

			waitForRequired(driver, By.xpath("(//input[@class='slds-input'])[4]"), Duration.ofSeconds(20));
			
			ExShowroomPriceField.sendKeys(ShowroomPrice);
			RTOChargesField.sendKeys(RTOcharge);
			ScrollContainer("(//input[@class='slds-input'])[6]");
			waitForElementToAppear(driver, By.xpath("(//input[@class='slds-input'])[6]"), Duration.ofSeconds(15));
			InsuranceAmountField.sendKeys(InsuranceAmount);
			MandatoryAccessoriesAmountField.sendKeys(MandatoryAmount);
			OtherRegistrationChargesField.sendKeys(OtherCharges);
			test9.log(Status.INFO, "ExShowroomPriceField :" + ShowroomPrice);
			test9.log(Status.INFO, "RTOChargesField :" + RTOcharge);
			test9.log(Status.INFO, "InsuranceAmountField :" + InsuranceAmount);
			test9.log(Status.INFO, "MandatoryAccessoriesAmountField:" + MandatoryAmount);
			test9.log(Status.INFO, "OtherRegistrationChargesField :" + OtherCharges);
			test9.pass("Asset details fetched successfully.");
			Utiles.getScreenshot(driver, "asset_pass"); // Capture screenshot with "asset_pass" suffix
			Saveandnextbutton.click();

		} catch (Exception e) {
			test9.fail(e.getMessage());
			Utiles.getScreenshot(driver, "asset_fail"); // Capture screenshot with "asset_fail" suffix
		}
	}
}
