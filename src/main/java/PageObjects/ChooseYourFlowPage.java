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

public class ChooseYourFlowPage extends DropDown {

    private WebDriver driver;
    private ExtentTest test6;

    public ChooseYourFlowPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test6=Listeners.getTest();
    }

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[2]")
    private WebElement NoButton;

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[1]")
    private WebElement YesButton;

    @FindBy(xpath = "//button[text()='Proceed']")
    private WebElement ProceedButton;

    public void FlowPage() throws InterruptedException, IOException {
        try {

            // Wait for any loading spinner to disappear
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
            
            // Click on the 'No' button
            NoButton.click();
            test6.info("Clicked on 'No' button.");
            
            // Wait for the 'Proceed' button to appear
            waitForElementToAppear(driver, By.xpath("//button[text()='Proceed']"), Duration.ofSeconds(15));

            // Log the successful execution
            test6.pass("Successfully executed FlowPage method");
            // Capture a screenshot of the successful operation
            Utiles.getScreenshot(driver, "flow_pass");
            
            // Click on the 'Proceed' button
            ProceedButton.click();
            
        } catch (Exception e) {
            // Log the exception and capture a screenshot of the failure
            test6.fail("Test failed due to: " + e.getMessage());
            Utiles.getScreenshot(driver, "flow_fail");
        }
    }
}
