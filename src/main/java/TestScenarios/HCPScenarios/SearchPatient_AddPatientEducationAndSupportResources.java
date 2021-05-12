package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.*;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchPatient_AddPatientEducationAndSupportResources extends HCPBaseTestClass {

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
     Select an existing patient
     Add 'Patient Education and Support Resources' service
     */

    @Test
    public void createPatientEducationAndSupportResourcesRequest() throws Exception {

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("Clicking 'START A NEW REQUEST' button");
        newRequestPage.clickStartNewRequestFromRequestPage();

        logger.info("Searching n Select a Patient");
        newRequestPage.searchPatient(excelReader.testData.get("FirstnLastName"));

        logger.info("Selecting 'Patient Education and Support Resources' radiobutton under individual services section");
        newRequestPage.selectPatientEducationAndSupportResources();
        newRequestPage.scrollDown();

        logger.info("Clicking 'START REQUEST' button");
        newRequestPage.clickStartRequest();
        patientPage.clickNext();

        logger.info("Searching for Prescriber");
        prescriberPage.selectPrescriber(excelReader.testData.get("PrescriberName"));
        patientPage.clickNext();

        logger.info("Clicking 'SUBMIT' button and Verify 'SUBMISSION SUCCESSFUL'");
        registerPage.clickSubmitRequest();
        prescriberAttestationNSubmitPage.verifySubmissionSuccessful();

        logger.info("Testing Completed");
    }
}

