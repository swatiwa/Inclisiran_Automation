package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.RegisterPage;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Register_Prescriber_NonExistingNPI extends HCPBaseTestClass {

    RegisterPage registerPage = new RegisterPage();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
    Navigate to HCP portal login page
    Register Prescriber with NON-Existing NPI
    */

    @Test
    public void register() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Clicking 'Register' button on the 'Login' page");
        registerPage.clickRegister();

        logger.info("Clicking 'Register' button on the 'Welcome' page");
        registerPage.clickRegister2();

        logger.info("Reading data from RegisterOrganization sheet");
        excelReader.readExcel("RegisterPrescriberNonExistinNPI");

        logger.info("Inputting 'Organization' information");
        registerPage.populateOrganizationInfo(
                excelReader.testData.get("Organization NPI"),
                excelReader.testData.get("Organization Name"),
                excelReader.testData.get("Organization Address"),
                excelReader.testData.get("Organization City"),
                excelReader.testData.get("Organization State"),
                excelReader.testData.get("Organization ZipCode"),
                excelReader.testData.get("Organization Tax ID"),
                excelReader.testData.get("Organization Phone number"),
                excelReader.testData.get("Organization Fax number"));

        logger.info("Inputting Administrator-Prescriber information");
        registerPage.inputPrescriberNPI(excelReader.testData.get("Prescriber NPI"));
        registerPage.populateAdministratorInfo(
                excelReader.testData.get("Prescriber First Name"),
                excelReader.testData.get("Prescriber Last Name"),
                excelReader.testData.get("Prescriber Phone number"),
                excelReader.testData.get("Prescriber Fax number"),
                excelReader.testData.get("Prescriber Email"),
                excelReader.testData.get("Prescriber Username"));

        logger.info("Clicking 'Next' after we input 'Prescriber' information");
        registerPage.clickNext();

        logger.info("Inputting 'Prescriber Tax and License' information");
        registerPage.enterPrescriberTaxID(excelReader.testData.get("Prescriber Tax ID"));
        registerPage.enterPrescriberStateLicense(excelReader.testData.get("Prescriber State License number"));
        registerPage.enterPrescriberState(excelReader.testData.get("Prescriber State"));

        logger.info("Clicking 'Next' after we input 'Prescriber' information");
        registerPage.clickNext();

        logger.info("Scrolling down and Clicking CheckBox on 'Terms and Conditions' document");
        registerPage.clickOnArticle1();
        registerPage.scrollDownAgreement1();

        logger.info("Clicking CheckBox on the 'Terms and Conditions Agreement' document");
        registerPage.clickCheckBox1();

        logger.info("Clicking 'Next' button");
        registerPage.clickNext();

        logger.info("Scrolling down on 'Business Associate Agreement' document");
        registerPage.clickOnArticle2();
        registerPage.scrollDownAgreement2();

        logger.info("Clicking 'Business Associate Agreement' document CheckBoxes");
        registerPage.clickCheckBox2();
        registerPage.clickCheckBox3();

        logger.info("Inputting a Signature");
        registerPage.enterSignature(excelReader.testData.get("Signature"));

        logger.info("Verifying 'System date' vs 'Current date' displayed on the page");
        registerPage.verifyDate();

        logger.info("Clicking 'SUBMIT' button");
        registerPage.clickSubmit();

        logger.info("Verifying 'Your registration has been successfully submitted.' message");
        registerPage.verifyRegistrationSubmitted();
        
        //registerPage.quitChrome();
    }

}
