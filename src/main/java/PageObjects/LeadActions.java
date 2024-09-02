package PageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Client_Project.Utiles;
import Resoureces.Listeners;

public class LeadActions extends DropDown {
    
    private WebDriver driver;
    private ExtentTest test2;

    @FindBy(xpath = "(//button[@class = 'slds-button slds-button_neutral slds-button_first'])[1]")
    private WebElement ChangeOwner;

    @FindBy(xpath = "(//button[@class = 'slds-button slds-button_neutral slds-button_middle'])[1]")
    private WebElement ConvertApp;

    @FindBy(xpath = "(//button[@class = 'slds-button slds-button_neutral slds-button_last'])[1]")
    private WebElement Edit;

    public LeadActions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test2 = Listeners.getTest(); // Initialize ExtentTest
    }

    /**
     * Clicks on the Convert Application button and handles exceptions.
     * 
     * @throws Exception if any errors occur during the process
     */
    public void ConvApp() throws Exception {
        try {
            // Wait for the 'ConvertApp' element to be visible
            waitForElementToAppear(driver, By.xpath("(//button[@class = 'slds-button slds-button_neutral slds-button_middle'])[1]"), Duration.ofSeconds(10));
            
            // Capture a screenshot before clicking
            Utiles.getScreenshot(driver, "LeadActions_beforeConvertApp");

            // Click on the 'ConvertApp' element
            ConvertApp.click();

            // Log success
            test2.pass("Successfully clicked on ConvertApp button.");

            // Capture a screenshot after clicking
            Utiles.getScreenshot(driver, "LeadActions_afterConvertApp");

        } catch (Exception e) {
            // Capture a screenshot on failure
            Utiles.getScreenshot(driver, "LeadActions_fail");

            // Log failure with exception message
            test2.fail("Failed to click on ConvertApp button due to: " + e.getMessage());

            // Optionally, rethrow the exception to let higher levels handle it
            throw e;
        }
    }
}
