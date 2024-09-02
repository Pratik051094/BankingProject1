package Client_Project;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;

import PageObjects.AssetDetails;
import PageObjects.BankVerification;
import PageObjects.ChooseYourFlowPage;
import PageObjects.CreateApplicationPage1;
import PageObjects.CreateApplicationPage2;
import PageObjects.CreateApplicationPage3;
import PageObjects.CreateApplicationPage4;
import PageObjects.CreateLeadPage;
import PageObjects.LeadActions;
import PageObjects.LeadDetailsPage;
import PageObjects.LoanEligibilityAndOffer;
import PageObjects.LoginPage;
import PageObjects.PermanentAddress_KYCAddressPage;
import Resoureces.ReadExcelData;
import Resoureces.WaitFramework;
import TestComponent.BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

@Listeners(Resoureces.Listeners.class)
public class ApplicationCreation extends BaseClass {

	@Test(priority = 1, dataProvider = "getLoginData", dataProviderClass = ReadExcelData.class)
	public void LoginPage(String username, String password) throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.LoginIntoApp(username, password);

		// WebElement con =
		// driver.findElement(By.xpath("//div[@data-aura-class=\"forceInlineEditGrid\"]"));

	}

	@Test(priority = 2, dataProvider = "gettestData", dataProviderClass = ReadExcelData.class, dependsOnMethods = "LoginPage")
	public void CreateApp1(String LeadNo, String delearCode, String requestedLoanAmount, String tenure,
			String declaredEMI, String custoCategory, String profession, String title, String dateofBirth,
			String document, String voterNo, String marital, String Mother, String Spouse, String kycAdd, String addOne,
			String addTwo, String pincode, String currentAdd, String baddone, String baddtwo, String landMark,
			String Pincode2, String CommunicationAdd, String religion, String ss, String education, String OCC,
			String companyType, String typeofOccupation, String brs, String house, String family, String residingSince,
			String totalworkExp, String retirement, String monthlyIn, String grossIncome, String EmpName, String JOD,
			String salaryPerAnn, String type, String variant, String ShowroomPrice, String RTOcharge,
			String InsuranceAmount, String MandatoryAmount, String OtherCharges, String ModelOne) throws Exception {
		
		WebElement SearchBox = driver.findElement(By.xpath("//input[@class='search-input search-input--left']"));
		SearchBox.clear();
		SearchBox.sendKeys(LeadNo);
		SearchBox.sendKeys(Keys.ENTER);
		synchronized (driver) {
			driver.wait(5000);
		}
		driver.findElement(By.xpath("//a[text()='" + LeadNo + "']")).click();
		WaitFramework pageLoadWait = new WaitFramework(driver, Duration.ofSeconds(20));
		pageLoadWait.waitForPageToLoad();
		LeadActions la = new LeadActions(driver);
		la.ConvApp();
		CreateApplicationPage1 cap1 = new CreateApplicationPage1(driver);
		cap1.CreateApp1(delearCode, requestedLoanAmount, tenure, declaredEMI);
		CreateApplicationPage2 cap2 = new CreateApplicationPage2(driver);
		cap2.CreateApp2(custoCategory, profession, title, dateofBirth);
		CreateApplicationPage3 cap3 = new CreateApplicationPage3(driver);

		cap3.createApp3();
		CreateApplicationPage4 cap4 = new CreateApplicationPage4(driver);

		cap4.createApp4(document, voterNo, marital, Mother, Spouse);
		PermanentAddress_KYCAddressPage PA = new PermanentAddress_KYCAddressPage(driver);

		PA.fillPermanentAndKYCAddress(kycAdd, addOne, addTwo, pincode, currentAdd, baddone, baddtwo, landMark, Pincode2,
				CommunicationAdd);
		PageObjects.SocioEconomicDetails SE = new PageObjects.SocioEconomicDetails(driver);

		SE.SocioEco(religion, ss, education, OCC, companyType, typeofOccupation, brs, house, family, residingSince,
				totalworkExp, retirement, monthlyIn, grossIncome, EmpName, JOD, salaryPerAnn);
		PageObjects.AssetDetails AD = new PageObjects.AssetDetails(driver);

		AD.AssetDetails(type, variant, ShowroomPrice, RTOcharge, InsuranceAmount, MandatoryAmount, OtherCharges,
				ModelOne);
		ChooseYourFlowPage cyf = new ChooseYourFlowPage(driver);

		cyf.FlowPage();
		LoanEligibilityAndOffer LA = new LoanEligibilityAndOffer(driver);

		LA.processLoanEligibilityAndOffer();
		PageObjects.BankVerification BV = new PageObjects.BankVerification(driver);

		BV.BankVerification();
	}

}
