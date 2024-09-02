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

public class SocioEconomicDetails extends DropDown {
    private WebDriver driver;
    private ExtentTest test8;

    public SocioEconomicDetails(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test8 = Listeners.getTest();
    }

    @FindBy(xpath = "(//*[@data-value='No'])[2]")
    private WebElement NoBtn;

    @FindBy(xpath = "(//input[@class='slds-input'])[6]")
    private WebElement empName;

    @FindBy(xpath = "//div[@data-recid='Salaried']")
    private WebElement ele;

    @FindBy(xpath = "//div[@data-recid='31_311-Private Banks']")
    private WebElement ele1;

    @FindBy(xpath = "//button[@aria-label='Politically Exposed Person']")
    private WebElement Poli;

    @FindBy(xpath = "(//input[@class='slds-input'])[7]")
    private WebElement JoiningDate;

    @FindBy(xpath = "(//input[@class='slds-input'])[2]")
    private WebElement occ;

    @FindBy(xpath = "(//input[@class='slds-input'])[5]")
    private WebElement MonthlyIn;

    @FindBy(xpath = "(//input[@class='slds-input'])[4]")
    private WebElement typeBusiness;

    @FindBy(xpath = "(//input[@class='slds-input'])[3]")
    private WebElement TypeOfBRS;

    @FindBy(xpath = "(//input[@class='slds-input'])[6]")
    private WebElement gst;

    @FindBy(xpath = "(//button[@class='slds-button slds-button_brand'])")
    private WebElement snp;

    @FindBy(xpath = "//button[text()='Save & Next']")
    private WebElement Saveandnextbutton;

    public void Religion(String religion) {
        DrpDnAccess("Religion", religion);
    }

    public void SocialStatus(String socstat) {
        DrpDnAccess("Social Status", socstat);
    }

    public void Disability(String disab) {
        DrpDnAccess("Applicant Disablility", disab);
    }

    public void Education(String edu) {
        DrpDnAccess("Applicant Education", edu);
    }

    public void CompanyType(String type) {
        DrpDnAccess("Type Of Company", type);
    }

    public void TypeOfOccupation(String type) {
        DrpDnAccess("Type Of Occupation", type);
    }

    public void House(String house) {
        DrpDnAccess("House", house);
    }

    public void Family(String fam) {
        DrpDnAccess("Staying With Family", fam);
    }

    public void ResidingSince(String residence) {
        DrpDnAccess("Residing Since", residence);
    }

    public void DurationBusiness(String duration) {
        DrpDnAccess("Duration Of Business In Present Address(Years)", duration);
    }

    public void IncomeSource(String incsrc) {
        DrpDnAccess("Income Source", incsrc);
    }

    public void Retirement(String retire) {
        DrpDnAccess("Retirement Age", retire);
    }

    public void GrossIncome(String grsinc) {
        DrpDnAccess("Gross Annual Income", grsinc);
    }

    public void ResidentStatus(String resstat) {
        DrpDnAccess("Resident Status", resstat);
    }

    public void RelBusiness(String relbus) {
        DrpDnAccess("Relationship with the Business", relbus);
    }

    public void Politics(String politic) {
        DrpDnAccess("Politically Exposed Person", politic);
    }

    public void Industry(String indcls) {
        DrpDnAccess("Industry Class", indcls);
    }

    public void UdyogAadhar(String aadhar) {
        DrpDnAccess("Udhyog Aadhar Number", aadhar);
    }

    public void TotalWorkExp(String aadhar) {
        DrpDnAccess("Total Work Experience(Years)", aadhar);
    }

    public void SalaryPerAnn(String aadhar) {
        DrpDnAccess("Salary Per Annum", aadhar);
    }

    public void PoliticallyExp() {
        driver.findElement(By.xpath("//*[@name='Politically Exposed Person']")).click();
        WebElement we = driver.findElement(By.xpath("//*[@name='Politically Exposed Person']"));
        we.findElement(By.xpath("(//*[@data-value='No'])[3]")).click();
    }

    public void PoliticallyExpo() {
        driver.findElement(By.xpath("//*[@name='Politically Exposed Person']")).click();
        WebElement we = driver.findElement(By.xpath("//*[@name='Politically Exposed Person']"));
        we.findElement(By.xpath("(//*[@data-value='No'])[3]")).click();
    }

    public void SocioEco(String religion, String ss, String education, String OCC, String companyType,
                         String typeofOccupation, String brs, String house, String family, String residingSince, String totalworkExp,
                         String retirement, String monthlyIn, String grossIncome, String EmpName, String JOD, String salaryPerAnn)
            throws InterruptedException, IOException {
        try {
            test8.info("Navigate to SocioEconomic page");
            test8.log(Status.INFO, "Fetching SocioEconomic pagedetails.");
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(20));

            Religion(religion);
            SocialStatus(ss);
            Education(education);
            occ.sendKeys(OCC);
            test8.log(Status.INFO, "Religion : " + religion);
            test8.log(Status.INFO, "SocialStatus : " + ss);
            test8.log(Status.INFO, "Education : " + education);
            test8.log(Status.INFO, "occ : " + OCC);

            ele.click();
            CompanyType(companyType);
            TypeOfOccupation(typeofOccupation);
            TypeOfBRS.sendKeys(brs);
            test8.log(Status.INFO, "CompanyType : " + companyType);
            test8.log(Status.INFO, "TypeOfOccupation: " + typeofOccupation);
            test8.log(Status.INFO, "TypeOfBRS: " + brs);

            ele1.click();
            House(house);
            Family(family);
            ResidingSince(residingSince);
            
            ScrollParent("//button[@aria-label='Total Work Experience(Years)']");
            waitForElementToAppear(driver, By.xpath("//button[@aria-label='Retirement Age']"), Duration.ofSeconds(15));
            
            TotalWorkExp(totalworkExp);

            test8.log(Status.INFO, "House: " + house);
            test8.log(Status.INFO, "Family: " + family);
            test8.log(Status.INFO, "ResidingSince: " + residingSince);
            test8.log(Status.INFO, "TotalWorkExp : " + totalworkExp);

            Retirement(retirement);

            MonthlyIn.sendKeys(monthlyIn);

            GrossIncome(grossIncome);
            test8.log(Status.INFO, "Retirement : " + retirement);
            test8.log(Status.INFO, "MonthlyIn : " + monthlyIn);
            test8.log(Status.INFO, "GrossIncome : " + grossIncome);
            PoliticallyExpo();
            empName.sendKeys(EmpName);
            test8.log(Status.INFO, "empName : " + EmpName);
            JoiningDate.sendKeys(JOD);
            test8.log(Status.INFO, "JoiningDate : " + JOD);
            SalaryPerAnn(salaryPerAnn);
            test8.log(Status.INFO, "SalaryPerAnn : " + salaryPerAnn);
            ScrollContainer("(//span[@class='slds-radio_faux'])[6]");
            waitForElementToAppear(driver, By.xpath("//button[text()='Save & Next']"), Duration.ofSeconds(15));
            test8.pass("Details fetched Successfully.");
            Utiles.getScreenshot(driver, "socio_pass"); // Capture screenshot with "socio_pass" suffix
            Saveandnextbutton.click();
        } catch (Exception e) {
            test8.fail(e.getLocalizedMessage());
            Utiles.getScreenshot(driver, "socio_fail"); // Capture screenshot with "socio_fail" suffix
        }
    }
}
