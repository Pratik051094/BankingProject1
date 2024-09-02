package PageObjects;

import java.awt.Robot;
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

public class CreateApplicationPage2 extends DropDown {

    private WebDriver driver;
    private ExtentTest test3;

    public CreateApplicationPage2(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test3=Listeners.getTest();
    }

    @FindBy(xpath = "//button[@name='Customer Category']")
    private WebElement CustomerCategoryDropdown;

    @FindBy(xpath = "//button[@name='Type of Profession']")
    private WebElement TypeOfProfessionDropdown;

    @FindBy(xpath = "//button[@aria-label='Title']")
    private WebElement TitleField;

    @FindBy(xpath = "//input[@name='Dob']")
    private WebElement DateofBirthField;

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[2]")
    private WebElement NoRadioButton;

    @FindBy(xpath = "(//input[@name='PanAvailability'])[1]")
    private WebElement YesRadioButton;

    @FindBy(xpath = "//button[text()='Run POSIDEX']")
    private WebElement RunPOSIDEXButton;

    @FindBy(xpath = "//button[text()='Save & Next']")
    private WebElement Saveandnextbutton;

    @FindBy(xpath = "//c-c-l-o-s-generic-file-uploader")
    private WebElement PhotoUpload;

    public void CustoType(String ctype) {
        DrpDnAccess("Customer Category", ctype);
    }

    public void Profession(String prof) {
        DrpDnAccess("Type of Profession", prof);
    }

    public void Title(String title) {
        DrpDnAccess("Title", title);
    }

    public void CreateApp2(String custoCategory, String profession, String title, String dateOfBirth) throws Exception {
        try {

            // Wait for any loading spinner to disappear
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(20));

            waitForElementToClickeable(driver, By.xpath("//button[@name='Customer Category']"), Duration.ofSeconds(20));
            // Fill out the form
            CustoType(custoCategory);
            Profession(profession);
            Title(title);
            DateofBirthField.sendKeys(dateOfBirth);

            // Log details
            test3.log(Status.INFO, "Customer Category: " + custoCategory);
            test3.log(Status.INFO, "Profession: " + profession);
            test3.log(Status.INFO, "Title: " + title);
            test3.log(Status.INFO, "Date of Birth: " + dateOfBirth);

            // Interact with radio button and file upload
            ScrollContainer("(//span[@class='slds-radio_faux'])[2]");
            waitForElementToAppear(driver, By.xpath("(//span[@class='slds-radio_faux'])[2]"), Duration.ofSeconds(15));
            NoRadioButton.click();

            // Handle file upload
            PhotoUpload.click();
            Utiles.AutoItFileUpload(); // Ensure this method is implemented correctly in Utiles

            // Wait for the 'Run POSIDEX' button and interact with it
            ScrollContainer("//button[text()='Run POSIDEX']");
            waitForElementToAppear(driver, By.xpath("//button[text()='Run POSIDEX']"), Duration.ofSeconds(15));
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
            RunPOSIDEXButton.click();

            // Wait for completion and proceed
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
            waitForElementToAppear(driver, By.xpath("//button[text()='Save & Next']"), Duration.ofSeconds(15));

            test3.pass("Application 2 details fetched and created successfully.");
            // Capture a screenshot on success
            Utiles.getScreenshot(driver, "App2_pass");

            // Click on 'Save & Next'
            Saveandnextbutton.click();
        } catch (Exception e) {
            // Log the exception and capture a screenshot on failure
            test3.fail("Test failed due to: " + e.getMessage());
            Utiles.getScreenshot(driver, "App2_fail");
        }
    }
}
