package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.*;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Reports.Reports;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import com.aventstack.extentreports.ExtentReports;
import org.testng.IReporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchPatient_AddInsuranceDetermination extends HCPBaseTestClass {

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
     Add 'Insurance Determination And Coverage Review' service
    */

    @Test
    public void createInuranceDeterminationRequest() throws Exception {

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient5 sheet");
        excelReader.readExcel("Add_Patient5");

        logger.info("Clicking 'START A NEW REQUEST' button");
        newRequestPage.clickStartNewRequestFromRequestPage();

        logger.info("Searching n Select a Patient");
        newRequestPage.searchPatient(excelReader.testData.get("FirstnLastName"));

        logger.info("Selecting 'Insurance Determination & Coverage Review' service request");
        newRequestPage.selectInsuranceDeterminationAndCoverageReview();
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

        logger.info("Selecting 'YES' on 'DOES THE PATIENT HAVE INSURANCE?'");
        insurancePage.selectYesInsurance();

        logger.info("Reading data from Add_Insurance sheet");
        excelReader.readExcel("Add_Insurance");

        logger.info("Selecting 'Medicare'");
        insurancePage.select1stOption();

        logger.info("Populate Primary insurance information");
        insurancePage.populatePrimaryInsuranceInfo(
                excelReader.testData.get("Primary Plan name"),
                excelReader.testData.get("Primary Plan ID"),
                excelReader.testData.get("Primary Plan Group #"),
                excelReader.testData.get("Primary Plan Phone number"));

        logger.info("Populate Secondary insurance information");
        insurancePage.populateSecondaryInsuranceInfo(
                excelReader.testData.get("Secondary Plan name"),
                excelReader.testData.get("Secondary Plan ID"),
                excelReader.testData.get("Secondary Plan Group #"),
                excelReader.testData.get("Secondary Plan Phone number"));

        logger.info("Populate Pharmacy information");
        insurancePage.populatePharmacyInfo(
                excelReader.testData.get("Pharmacy Plan name"),
                excelReader.testData.get("Pharmacy Card ID"),
                excelReader.testData.get("Pharmacy Rx Group #"),
                excelReader.testData.get("RxBIN"),
                excelReader.testData.get("RxPCN"),
                excelReader.testData.get("Pharmacy Plan Phone number"));

        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Reading data from Add_Patient5 sheet");
        excelReader.readExcel("Add_Patient5");

        logger.info("Searching for Prescriber");
        prescriberPage.selectPrescriber(excelReader.testData.get("PrescriberName"));

        logger.info("Searching for Prescriber Location");
        prescriberPage.searchPrescriberLocation(excelReader.testData.get("PrescriberLocation"));
;
        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Entering treatment start date");
        excelReader.readExcel("Add_Clinical");
        clinicalPage.enterTreatmentStartDate(excelReader.testData.get("TreatmentStartDate"));
        clinicalPage.clickPatientEnrolledInTrial();

        logger.info("Selecting Diagnosis Code");
        clinicalPage.enterICD10primaryDiagnCode(excelReader.testData.get("ICD10DiagnosisCode"));

        logger.info("Selecting 'Has patient been diagnosed with ASCVD and/or HeFH?'");
        clinicalPage.clickYesDiagnosedASCVD();
        patientPage.clickNext();

        logger.info("Selecting 'Specialty Pharmacy'");
        clinicalPage.clickSpecialtyPharmacy();
        patientPage.clickNext();

        logger.info("Reading data from 'Add_Patient5' sheet");
        excelReader.readExcel("Add_Patient5");

        logger.info("Selecting 'Electronic Attestation' and Verifying patient's email");
        patientAttestationPage.clickElectronicAttestation();
        patientPage.clickNext();

        logger.info("Verify 'PATIENT INFORMATION'");
        //prescriberAttestationNSubmitPage.verifyPatientInfo(
        //        excelReader.testData.get("FirstName"),
        //        excelReader.testData.get("LastName"),
        //        excelReader.testData.get("DOB"));

        logger.info("Verify 'INSURANCE INFORMATION'");
        logger.info("Verify 'PRESCRIBER INFORMATION'");
        logger.info("Verify 'TREATMENT  INFORMATION'");

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
        //newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Clicking 'SUBMIT' button and Verify 'SUBMISSION SUCCESSFUL'");
        registerPage.clickSubmitRequest();
        prescriberAttestationNSubmitPage.verifySubmissionSuccessful();

        //logger.info("Testing Completed");

/*
        //Connect Verification ---------------------------------------------------------------
        logger.info("Reading data from Add_Patient2 sheet");
        excelReader.readExcel("Add_Patient2");

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

