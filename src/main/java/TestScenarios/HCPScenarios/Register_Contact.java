package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.RegisterPage;
import TestFramework.Pages.BasePage;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Register_Contact extends HCPBaseTestClass {

    RegisterPage registerPage = new RegisterPage();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
    Navigate to HCP portal login page
    Click Register
    Register a Contact
    */

    @Test
    public void register() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Clicking 'Register' button on the 'Login' page");
        registerPage.clickRegister();

        logger.info("Clicking 'Register' button on the 'Welcome' page");
        registerPage.clickRegister2();

        logger.info("Reading data from RegisterOrganization sheet");
        excelReader.readExcel("RegisterContact");

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

        logger.info("Inputting Administrator information");
        registerPage.populateAdministratorInfo(
                excelReader.testData.get("Administrator First Name"),
                excelReader.testData.get("Administrator Last Name"),
                excelReader.testData.get("Administrator Phone number"),
                excelReader.testData.get("Administrator Fax Name"),
                excelReader.testData.get("Administrator Email"),
                excelReader.testData.get("Administrator Username"));

        logger.info("Clicking 'Next' after we input 'Organization' & 'Administrator' information");
        registerPage.clickNext();

        logger.info("Inputting 'Prescriber' information");
        registerPage.populatePrescriberInfo(
                excelReader.testData.get("Prescriber NPI"),
                excelReader.testData.get("Prescriber First Name"),
                excelReader.testData.get("Prescriber Last Name"),
                excelReader.testData.get("Prescriber Tax ID"),
                excelReader.testData.get("Prescriber State License number"),
                excelReader.testData.get("Prescriber State"));

        logger.info("Clicking 'Next' after we input 'Prescriber' information");
        registerPage.clickNext();

        logger.info("Scrolling down and Clicking CheckBox on 'Terms and Conditions' document");
        registerPage.clickOnArticle1();
        registerPage.scrollDownAgreement1();

        logger.info("Clicking CheckBox on the 'Terms and Conditions Agreement' document");
        registerPage.clickCheckBox1();

        logger.info("Clicking 'Next' button");
        registerPage.clickNext();

        logger.info("Scrolling down on the 'Business Associate Agreement' document");
        registerPage.clickOnArticle2();
        registerPage.scrollDownAgreement2();

        logger.info("Clicking CheckBoxes on the 'Business Associate Agreement' document");
        registerPage.clickCheckBox2();
        registerPage.clickCheckBox3();

        logger.info("Inputting a Signature");
        registerPage.enterSignature(excelReader.testData.get("Signature"));

        logger.info("Verifying 'System date' vs 'Date' displayed on the page");
        registerPage.verifyDate();

        logger.info("Clicking 'SUBMIT' button");
        registerPage.clickSubmit();

        logger.info("Verifying 'Your registration has been successfully submitted.' message");
        registerPage.verifyRegistrationSubmitted();
        
        //registerPage.quitChrome();
    }

}
