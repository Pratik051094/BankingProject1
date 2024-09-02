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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

@Listeners(Resoureces.Listeners.class)
public class CreateLead extends BaseClass {

	@Test(priority = 1, dataProvider = "getLoginData", dataProviderClass = ReadExcelData.class)
	public void LoginPage(String username, String password) throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.LoginIntoApp(username, password);

		// WebElement con =
		// driver.findElement(By.xpath("//div[@data-aura-class=\"forceInlineEditGrid\"]"));

	}

	

	@Test(priority = 2, dataProvider = "getMultiLeadData", dataProviderClass = ReadExcelData.class, dependsOnMethods = "LoginPage")
	public void LeadFillDetails(String ProductName, String FristName, String Mobile, String Pincode, String LeadSource,
			String LeadDisp) throws Exception {
		WaitFramework pageLoadWait = new WaitFramework(driver, Duration.ofSeconds(15));
		pageLoadWait.waitForPageToLoad();
		CreateLeadPage clp = new CreateLeadPage(driver);
		clp.createLead();
		LeadDetailsPage ldp = new LeadDetailsPage(driver);
		ldp.LeadDetails(ProductName, FristName, Mobile, Pincode, LeadSource, LeadDisp);
		Utiles.waitForElementToAppear(driver, By.xpath("//h2[@class='slds-page-header__title slds-align-middle clip-text slds-line-clamp']"), Duration.ofSeconds(15));
		WebElement LeadId=driver.findElement(By.xpath("(//lightning-formatted-text[@slot='outputField'])[1]"));
		String LeadID=LeadId.getText();
		BankVerification BV = new BankVerification(driver);
		BV.WriteExcel1(LeadID);
	}

	////h2[@class='slds-page-header__title slds-align-middle clip-text slds-line-clamp']

}
