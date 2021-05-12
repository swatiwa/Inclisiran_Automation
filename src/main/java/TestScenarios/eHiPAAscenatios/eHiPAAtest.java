package TestScenarios.eHiPAAscenatios;

import TestFramework.HCPPages.HomePage;
import TestFramework.HCPPages.PatientPage;
import TestFramework.eHiPAA.eHiPAApages;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class eHiPAAtest extends HCPBaseTestClass {

@BeforeClass
        public void runEnv (){
            setEnv("eHiPAA");
        }

    HomePage homePage = new HomePage();
    PatientPage patientPage= new PatientPage();
    TestFramework.eHiPAA.eHiPAApages eHiPAApages = new eHiPAApages();


    /*
    eHiPAA test
    Authorization allows healthcare providers to maintain PHI about patient,
    disclose PHI to Novartis Pharmaceuticals Corporation
    so that the Service Center may provide services to patient
    */

    //will put this into separate package


    @Test
    public void createAuthorization() throws Exception {

        ExcelReader excelReader = new ExcelReader();

        logger.info("Verifying 'Welcome Page'");
        eHiPAApages.verifyWelcomePage();

        logger.info("Clicking 'here' link on 'Welcome Page'");
        eHiPAApages.clickHereLink();

        logger.info("Reading data from 'eHiPAA1' sheet");
        excelReader.readExcel("eHiPAA1");

        logger.info("Inputting 'Patient information'");
        eHiPAApages.populatePatientInfo(
                excelReader.testData.get("FirstName"),
                excelReader.testData.get("LastName"),
                excelReader.testData.get("DOB"),
                excelReader.testData.get("Gender"),
                excelReader.testData.get("Address1"),
                excelReader.testData.get("Address2"),
                excelReader.testData.get("City"),
                excelReader.testData.get("State"),
                excelReader.testData.get("Zip"),
                excelReader.testData.get("Email"),
                excelReader.testData.get("Phone"));

        if(excelReader.testData.get("Representative").equalsIgnoreCase("Yes")){
            logger.info("Signing as 'Personal Representative' of the patient, by Clicking CheckBox");
            eHiPAApages.selectRepresentative();
            logger.info("Inputting 'Patient Representative' information");
            eHiPAApages.populateRepresentativeInfo(
                excelReader.testData.get("RepFirstName"),
                excelReader.testData.get("RepLastName"),
                excelReader.testData.get("RepPhone"),
                excelReader.testData.get("RepRelationship"));
        }else if(excelReader.testData.get("Representative").equalsIgnoreCase("No")){
            logger.info("Signing as 'Patient'.");
        }

        logger.info("Clicking 'Next' after inputting 'Patient information'");
        eHiPAApages.clickNext();

        logger.info("Verifying we've landed on 'Email confirmation' page and Clicking 'Send Verification Code' button");
        eHiPAApages.clickSendVerificationCode();

        logger.info("Launching 'Gmail' inbox, getting the 'Verification Code' from email and input into 'eHiPAA' webpage");
        eHiPAApages.getVerificationCode(
                excelReader.testData.get("GmailAddress"),
                excelReader.testData.get("GmailPassword"),
                excelReader.testData.get("Email"),
                excelReader.testData.get("FirstName"));

        logger.info("Signing the 'Authorization'");
        eHiPAApages.inputSignature(excelReader.testData.get("FirstName")+" "+excelReader.testData.get("LastName"));

        logger.info("Verifying 'Consent' Page displayed");
        eHiPAApages.consentTxt();

        //Below 3 consents need to be checked according to scenario; adding all to check (for now)
        logger.info("Clicking 'Copay Consent' checkbox");
        eHiPAApages.clickCopayConsent();

        logger.info("Clicking 'Support Consent' checkbox");
        eHiPAApages.clickSupportConsent();

        logger.info("Clicking 'Financial Consent' checkbox");
        eHiPAApages.checkFinancialConsent();

        logger.info("Clicking 'Next' after Consent");
        eHiPAApages.clickNextAfterConsent();

        logger.info("'Review and Confirm' and clicking 'Complete'");
        eHiPAApages.reviewNconfirmTxt();

        logger.info("Verifying 'Thank you for filling out and signing this.' message");
        eHiPAApages.thankYouMsg();

        //in the below step:
        //need to verify text: Thank you for completing the Patient Authorization and Additional Consents Form.' - TO DO!
        logger.info("Verifying 'Patient Authorization Form' has been received");
        eHiPAApages.verifyFormReceived();

        //eHiPAApages.closeChrome();
    }
}
