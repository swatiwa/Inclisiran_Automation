package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.*;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchPatient_AddCopayEnrollment extends HCPBaseTestClass {

    HomePage homePage = new HomePage();
    PatientPage patientPage= new PatientPage();
    RegisterPage registerPage = new RegisterPage();
    ClinicalPage clinicalPage = new ClinicalPage();
    InsurancePage insurancePage = new InsurancePage();
    NewRequestPage newRequestPage = new NewRequestPage();
    PrescriberPage prescriberPage = new PrescriberPage();
    PatientAttestationPage patientAttestationPage = new PatientAttestationPage();
    PrescriberAttestationNSubmitPage prescriberAttestationNSubmitPage = new PrescriberAttestationNSubmitPage();
    ConnectPages connectPages = new ConnectPages();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
     Add request to an existing patient
     Add Copay Enrollment service
     */

    @Test
    public void createCopayEnrollmentRequest() throws Exception {

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("Clicking 'START A NEW REQUEST' button");
        newRequestPage.clickStartNewRequestFromRequestPage();

        logger.info("Searching n Select a Patient");
        newRequestPage.searchPatient(excelReader.testData.get("FirstnLastName"));

        logger.info("Clicking 'Copay Enrollment' radiobutton under individual services section");
        newRequestPage.selectCopayEnrollment();
        newRequestPage.scrollDown();

        logger.info("Clicking 'START REQUEST' button");
        newRequestPage.clickStartRequest();
        patientPage.clickNext();

        logger.info("Searching for Prescriber");
        prescriberPage.selectPrescriber(excelReader.testData.get("PrescriberName"));

        logger.info("Searching for Prescriber Location");
        prescriberPage.searchPrescriberLocation(excelReader.testData.get("PrescriberLocation"));

        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("Selecting 'Electronic Attestation' and Verifying patient's email");
        patientAttestationPage.clickElectronicAttestation();
        patientPage.clickNext();

        logger.info("Verify 'PATIENT INFORMATION'");
        //prescriberAttestationNSubmitPage.verifyPatientInfo(
        //        excelReader.testData.get("FirstName"),
        //        excelReader.testData.get("LastName"),
        //        excelReader.testData.get("DOB"));

        logger.info("Verify 'PRESCRIBER INFORMATION'");

        newRequestPage.scrollDown();
        logger.info("Click checkBox on 'I certify that the above therapy is medically necessary...'");
        prescriberAttestationNSubmitPage.clickIcertify();
        newRequestPage.scrollDown();
        logger.info("Clicking 'Yes' on Can we contact the patient?");
        prescriberAttestationNSubmitPage.clickYEScontact();
        //prescriberAttestationNSubmitPage.enterSignature(excelReader.testData.get("FirstnLastName"));
        newRequestPage.scrollDown();

        //logger.info("Reading data from Add_Clinical sheet");
        //excelReader.readExcel("Add_Clinical");

        //logger.info("Verify 'DATE OF CERTIFICATION'");
        //prescriberAttestationNSubmitPage.verifySumbissionDate(excelReader.testData.get("SubmissionDate"));
        patientPage.clickNext();
        newRequestPage.scrollDown();

        logger.info("Clicking 'SUBMIT' button and Verify 'SUBMISSION SUCCESSFUL'");
        registerPage.clickSubmitRequest();

        prescriberAttestationNSubmitPage.verifySubmissionSuccessful();
        logger.info("Testing Completed");

/*
        //Connect Verification ---------------------------------------------------------------
        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("Launching Connect and Inputting credentials to log in");
        //InitSetup.initLogin("autotester@caremetx.com", "P@tients!", "autotester");
        connectPages.launchConnect(
                excelReader.testData.get("Env"),
                excelReader.testData.get("Username"),
                excelReader.testData.get("Password"));
        logger.info("Selecting username");
        connectPages.selectUsername();

        logger.info("Selecting 'Inclisiran Service Center'");
        connectPages.selectInclisiran();

        logger.info("Looking for the Patient");
        connectPages.clickEntityRecordsPatients();
        connectPages.inputPatientInfo(  excelReader.testData.get("FirstName"), excelReader.testData.get("LastName"), excelReader.testData.get("DOB"));
        connectPages.clickOnpatient();

        logger.info("Verify 'Patient information' in Connect");
        connectPages.verifyPatientInfo(
                excelReader.testData.get("FirstnLastName"),
                excelReader.testData.get("Address1"),
                excelReader.testData.get("Email"),
                excelReader.testData.get("Phone"),
                excelReader.testData.get("DOB"));

        logger.info("Closing the Browser");
        //connectPages.closeBrowserAndChrome();


*/

    }

}

