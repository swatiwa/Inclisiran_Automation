package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.*;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//this test uses 'search bar' on the landing page
//Clicks 'Start New Request' from 'Patient Profile' page

public class SearchPatient_AddAllServices extends HCPBaseTestClass {

    HomePage homePage = new HomePage();
    PatientPage patientPage= new PatientPage();
    PatientManagementPage managementPage = new PatientManagementPage();
    RegisterPage registerPage = new RegisterPage();
    ClinicalPage clinicalPage = new ClinicalPage();
    InsurancePage insurancePage = new InsurancePage();
    NewRequestPage newRequestPage = new NewRequestPage();
    PrescriberPage prescriberPage = new PrescriberPage();
    PatientAttestationPage patientAttestationPage = new PatientAttestationPage();
    PrescriberAttestationNSubmitPage prescriberAttestationNSubmitPage = new PrescriberAttestationNSubmitPage();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
     This test looks for an existing patient (Created by AddNewPatient_StartRequest_AllServices script)
     Uses 'Search Bar' to find created patient
     Adds a 'HUB enrollment' request
    */

    @Test
    public void searchPatientNAddRequest() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("input Patient name into search bar");
        homePage.enterPatientIntoSearchInput(excelReader.testData.get("FirstnLastName"));

        logger.info("Click Start New Request");
        patientPage.clickStartNewRequest();

        //-------Adding request:
        logger.info("Clicking 'All Services' radiobutton");
        newRequestPage.selectWouldYouLikeFullSupport();
        newRequestPage.scrollDown();

        logger.info("Clicking 'START REQUEST' button");
        newRequestPage.clickStartRequest();

        logger.info("Entering Patient contact and address info");
        patientPage.selectLanguage("English");
        patientPage.enterPhoneNumber(excelReader.testData.get("Phone"));
        patientPage.selectContactmethod();
        patientPage.enterStreetAddress(excelReader.testData.get("Address1"));
        patientPage.enterStreetAddress2(excelReader.testData.get("Address2"));
        patientPage.enterCity(excelReader.testData.get("City"));
        patientPage.enterZipCode(excelReader.testData.get("Zip"));
        patientPage.selectState(excelReader.testData.get("State"));
        patientPage.clickNext();

        logger.info("Selecting Patient has 'NO insurance'");
        insurancePage.selectNoInsurance();
        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Searching for Prescriber");
        prescriberPage.selectPrescriber(excelReader.testData.get("PrescriberName"));

        logger.info("Searching for Prescriber Location");
        prescriberPage.searchPrescriberLocation(excelReader.testData.get("PrescriberLocation"));

        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Reading from Add_Clinical sheet");
        excelReader.readExcel("Add_Clinical");

        logger.info("Entering treatment start date");
        clinicalPage.enterTreatmentStartDate(excelReader.testData.get("TreatmentStartDate"));
        clinicalPage.clickPatientEnrolledInTrial();

        logger.info("Selecting Diagnosis Code");
        clinicalPage.enterICD10primaryDiagnCode(excelReader.testData.get("ICD10DiagnosisCode"));

        logger.info("Selecting NOT diagnosed with 'ASCVD and/or HeFH'");
        clinicalPage.clickNOTdiagnosed();
        patientPage.clickNext();

        logger.info("Selecting 'Specialty Pharmacy'");
        clinicalPage.clickAcquiredAtSpecialtyPharmacy();
        patientPage.clickNext();

        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("Selecting 'Electronic Attestation' and Verifying patient's email");
        patientAttestationPage.clickElectronicAttestation();
        patientAttestationPage.verifyPatientEmail(excelReader.testData.get("Email"));
        patientPage.clickNext();

        newRequestPage.scrollDown();
        logger.info("Click checkBox on 'I certify that the above therapy is medically necessary...'");
        prescriberAttestationNSubmitPage.clickIcertify();

        newRequestPage.scrollDown();
        logger.info("Clicking 'Yes' on Can we contact the patient?");
        prescriberAttestationNSubmitPage.clickYEScontact();
        newRequestPage.scrollDown();

        logger.info("Reading data from Add_Clinical sheet");
        excelReader.readExcel("Add_Clinical");

        patientPage.clickNext();
        newRequestPage.scrollDown();

        logger.info("Clicking 'SUBMIT' button and Verify 'SUBMISSION SUCCESSFUL'");
        registerPage.clickSubmitRequest();
        prescriberAttestationNSubmitPage.verifySubmissionSuccessful();

    }

}