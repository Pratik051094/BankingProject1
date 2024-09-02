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
import org.testng.annotations.Listeners;

@Listeners(Resoureces.Listeners.class)
public class LoanAppCreation extends BaseClass {

	@Test(priority = 1, dataProvider = "getLoginData", dataProviderClass = ReadExcelData.class)
	public void LoginPage(String username, String password) throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.LoginIntoApp(username, password);

		// WebElement con =
		// driver.findElement(By.xpath("//div[@data-aura-class=\"forceInlineEditGrid\"]"));

	}

	@Test(priority = 2, dependsOnMethods = "LoginPage")
	public void LeadPageAccess() throws Exception {
		WaitFramework pageLoadWait = new WaitFramework(driver, Duration.ofSeconds(20));
		pageLoadWait.waitForPageToLoad();

		CreateLeadPage clp = new CreateLeadPage(driver);
		clp.createLead();

	}

	@Test(priority = 3, dataProvider = "getLeadData", dataProviderClass = ReadExcelData.class, dependsOnMethods = "LeadPageAccess")
	public void LeadFillDetails(String ProductName, String FristName, String Mobile, String Pincode, String LeadSource,
			String LeadDisp) throws Exception {
		WaitFramework pageLoadWait = new WaitFramework(driver, Duration.ofSeconds(15));
		pageLoadWait.waitForPageToLoad();

		LeadDetailsPage ldp = new LeadDetailsPage(driver);
		ldp.LeadDetails(ProductName, FristName, Mobile, Pincode, LeadSource, LeadDisp);
	}

	@Test(priority = 4, dependsOnMethods = "LeadFillDetails")
	public void ConvertToApplication() throws Exception {
		WaitFramework pageLoadWait = new WaitFramework(driver, Duration.ofSeconds(20));
		pageLoadWait.waitForPageToLoad();
		LeadActions la = new LeadActions(driver);
		la.ConvApp();
	}

	@Test(priority = 5, dataProvider = "ApplicationData1", dataProviderClass = ReadExcelData.class, dependsOnMethods = "ConvertToApplication")
	public void CreateApp1(String delearCode, String requestedLoanAmount, String tenure, String declaredEMI)
			throws Exception {
		CreateApplicationPage1 cap1 = new CreateApplicationPage1(driver);
		
		cap1.CreateApp1(delearCode, requestedLoanAmount, tenure, declaredEMI);
	}

	@Test(priority = 6, dataProvider = "ApplicationData2", dataProviderClass = ReadExcelData.class, dependsOnMethods = "CreateApp1")
	public void CreateApp2(String custoCategory, String profession, String title, String dateofBirth) throws Exception {
		CreateApplicationPage2 cap2 = new CreateApplicationPage2(driver);
		
		// String title = null;
		cap2.CreateApp2(custoCategory, profession, title, dateofBirth);
	}

	@Test(priority = 7, dependsOnMethods = "CreateApp2")
	public void CreateApp3() throws Exception {
		CreateApplicationPage3 cap3 = new CreateApplicationPage3(driver);
		
		cap3.createApp3();
	}

	@Test(priority = 8, dataProvider = "ApplicationData4", dataProviderClass = ReadExcelData.class, dependsOnMethods = "CreateApp3")
	public void CreateApp4(String document, String voterNo, String marital, String Mother, String Spouse)
			throws Exception {
		CreateApplicationPage4 cap4 = new CreateApplicationPage4(driver);
		
		cap4.createApp4(document, voterNo, marital, Mother, Spouse);
	}

	@Test(priority = 9, dataProvider = "KYCaddress", dataProviderClass = ReadExcelData.class, dependsOnMethods = "CreateApp4")
	public void ParmanentAdd(String kycAdd, String addOne, String addTwo, String pincode, String currentAdd,
			String baddone, String baddtwo, String landMark, String Pincode2, String CommunicationAdd) throws Exception {
		PermanentAddress_KYCAddressPage PA = new PermanentAddress_KYCAddressPage(driver);
		
		PA.fillPermanentAndKYCAddress(kycAdd, addOne, addTwo, pincode, currentAdd, baddone, baddtwo, landMark, Pincode2, CommunicationAdd);
	}

	@Test(priority =10, dataProvider="ecoDetails", dataProviderClass=ReadExcelData.class, dependsOnMethods = "ParmanentAdd")
	public void SocioEconomicDetails(String religion, String ss, String education ,String OCC, String companyType, String typeofOccupation, String brs, String house, String family, String residingSince, String totalworkExp, String retirement, String monthlyIn, String grossIncome, String EmpName, String JOD, String salaryPerAnn) throws Exception {
		PageObjects.SocioEconomicDetails SE = new PageObjects.SocioEconomicDetails(driver);
	
		SE.SocioEco(religion, ss, education, OCC,  companyType,  typeofOccupation,  brs,  house,  family,  residingSince,  totalworkExp,  retirement,  monthlyIn,  grossIncome,  EmpName,  JOD,  salaryPerAnn );
	}
	
	@Test(priority =11, dataProvider="AssetDetails", dataProviderClass=ReadExcelData.class, dependsOnMethods = "SocioEconomicDetails")
	public void AssetDetails(String type, String variant, String ShowroomPrice, String RTOcharge, String InsuranceAmount, String MandatoryAmount,  String OtherCharges, String ModelOne) throws Exception {
		PageObjects.AssetDetails AD = new PageObjects.AssetDetails(driver);
	
		AD.AssetDetails(type, variant, ShowroomPrice,  RTOcharge,  InsuranceAmount,  MandatoryAmount, OtherCharges, ModelOne);
	}
	
 
	@Test(priority = 12, dependsOnMethods = "AssetDetails")
	public void Flowpage() throws Exception {
		ChooseYourFlowPage cyf = new ChooseYourFlowPage(driver);
		
		cyf.FlowPage();
	}
	
	@Test(priority = 13, dependsOnMethods = "Flowpage")
	public void LoanEligibility() throws Exception {
		LoanEligibilityAndOffer LA = new LoanEligibilityAndOffer(driver);
		
		LA.processLoanEligibilityAndOffer();
	}
	
	@Test(priority = 14, dependsOnMethods = "LoanEligibility")
	public void BankVerification() throws Exception {
		PageObjects.BankVerification BV = new PageObjects.BankVerification(driver);
	
		BV.BankVerification();
	}

}
