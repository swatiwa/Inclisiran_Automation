package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.*;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static TestFramework.Pages.BasePage.driver;

public class AddNewPatient_StartRequest_AllServices extends HCPBaseTestClass {

    //envName = "HCP_ENV";
    HomePage homePage = new HomePage();
    PatientPage patientPage = new PatientPage();
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
    This test adds a patient and starts request
    Verifies patient has been created successfully in Connect, verifies requests
    */

    @Test
    public void createNewPatientAddRequest() throws Exception {

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient1 sheet");
        excelReader.readExcel("Add_Patient1");

        logger.info("Clicking Add Patient");
        homePage.clickCreateNewPatient();

        logger.info("Populate all the Patient info");
        patientPage.populatePatientInfo(
                excelReader.testData.get("FirstName"),
                excelReader.testData.get("LastName"),
                excelReader.testData.get("DOB"),
                excelReader.testData.get("Gender"),
                excelReader.testData.get("Language"),
                excelReader.testData.get("Email"),
                excelReader.testData.get("Phone"),
                excelReader.testData.get("Address1"),
                excelReader.testData.get("Address2"),
                excelReader.testData.get("Zip"),
                excelReader.testData.get("City"),
                excelReader.testData.get("State"));

        logger.info("Clicking 'ADD PATIENT AND START A REQUEST' button");
        patientPage.clickAddPatientSubmit();

        logger.info("Verify 'PATIENT INFORMATION'");
        patientPage.verifyPatientInfo(
                excelReader.testData.get("FirstName"),
                excelReader.testData.get("LastName"),
                excelReader.testData.get("DOB"));

        //-------Adding request

        logger.info("Selecting 'All Services' radiobutton");
        newRequestPage.selectWouldYouLikeFullSupport();
        newRequestPage.scrollDown();

        logger.info("Clicking 'START REQUEST' button");
        newRequestPage.clickStartRequest();

        logger.info("Entering Patient mobile number");
        patientPage.enterPhoneNumber(excelReader.testData.get("Phone"));
        patientPage.selectContactmethod();
        patientPage.clickNext();

        //logger.info("Reading data from Add_Insurance sheet");
        //excelReader.readExcel("Add_Insurance");

        System.out.println(excelReader.testData.get("Have Insurance?"));
        insurancePage.selectYESorNOinsurance(excelReader.testData.get("Have Insurance?"));
/*        insurancePage.select1stOption();

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
                excelReader.testData.get("Pharmacy Plan Phone number"));*/
        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Adding a Prescriber");
        prescriberPage.clickAddPrescriber();

        logger.info("Reading data from Add_Prescriber sheet");
        excelReader.readExcel("Add_Prescriber");
        prescriberPage.populatePrescriberInfo(
                excelReader.testData.get("prescriberNPI"),
                excelReader.testData.get("prescriberFname"),
                excelReader.testData.get("prescriberLname"),
                excelReader.testData.get("prescriberTaxID"),
                excelReader.testData.get("prescriberStateLicense"),
                excelReader.testData.get("prescriberState"));

        logger.info("Adding Location");
        prescriberPage.clickAddLocation();
        prescriberPage.populatePrescriberAddressInfo(
                excelReader.testData.get("prescriberAddress1"),
                excelReader.testData.get("prescriberZipCode"),
                excelReader.testData.get("prescriberCity"),
                excelReader.testData.get("prescriberState"),
                excelReader.testData.get("prescriberPhoneNumber"),
                excelReader.testData.get("prescriberFaxNumber"));
        newRequestPage.scrollDown();
        patientPage.clickNext();

        logger.info("Entering treatment start date");
        logger.info("Reading data from Add_Clinical sheet");
        excelReader.readExcel("Add_Clinical");
        clinicalPage.enterTreatmentStartDate(excelReader.testData.get("TreatmentStartDate"));
        clinicalPage.clickPatientEnrolledInTrial();

        logger.info("Selecting Diagnosis Code");
        clinicalPage.enterICD10primaryDiagnCode(excelReader.testData.get("ICD10DiagnosisCode"));

        logger.info("Selecting 'Has patient been diagnosed with ASCVD and/or HeFH?'");
        clinicalPage.clickNotDiagnosedASCVD();
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

        logger.info("Verify 'PATIENT INFORMATION'");
        //prescriberAttestationNSubmitPage.verifyPatientInfo(
        //        excelReader.testData.get("FirstName"),
        //        excelReader.testData.get("LastName"),
        //        excelReader.testData.get("DOB"));

        //logger.info("Verify 'INSURANCE INFORMATION'");
        //logger.info("Verify 'PRESCRIBER INFORMATION'");
        //logger.info("Verify 'TREATMENT  INFORMATION'");

        newRequestPage.scrollDown();
        logger.info("Click checkBox on 'I certify that the above therapy is medically necessary...'");
        prescriberAttestationNSubmitPage.clickIcertify();

        newRequestPage.scrollDown();
        logger.info("Clicking 'Yes' on Can we contact the patient?");
        prescriberAttestationNSubmitPage.clickYEScontact();
        newRequestPage.scrollDown();

        logger.info("Reading data from Add_Clinical sheet");
        excelReader.readExcel("Add_Clinical");

        logger.info("Verify 'DATE OF CERTIFICATION'");
        prescriberAttestationNSubmitPage.verifySumbissionDate(excelReader.testData.get("SubmissionDate"));
        patientPage.clickNext();
        newRequestPage.scrollDown();

        logger.info("Clicking 'SUBMIT' button and Verify 'SUBMISSION SUCCESSFUL'");
        registerPage.clickSubmitRequest();
        prescriberAttestationNSubmitPage.verifySubmissionSuccessful();


        //------verify patient in Connect------------------------
//        logger.info("Reading data from Add_Patient1 sheet");
//        excelReader.readExcel("Add_Patient1");
//        logger.info("Launching Connect and Inputting credentials to log in");
//        connectPages.launchConnect(
//                excelReader.testData.get("Env"),
//                excelReader.testData.get("Username"),
//                excelReader.testData.get("Password"));
//        logger.info("Selecting username");
//        connectPages.selectUsername();
//
//        logger.info("Selecting 'Inclisiran Service Center'");
//        connectPages.selectInclisiran();
//
//        logger.info("Looking for the Patient");
//        connectPages.clickEntityRecordsPatients();
//        connectPages.inputPatientInfo(excelReader.testData.get("FirstName"), excelReader.testData.get("LastName"), excelReader.testData.get("DOB"));
//        connectPages.clickOnpatient();
//
//        logger.info("Verify 'Patient information' in Connect");
//        connectPages.verifyPatientInfo(excelReader.testData.get("FirstnLastName"),
//                excelReader.testData.get("Address1"),
//                excelReader.testData.get("Email"),
//                excelReader.testData.get("Phone"),
//                excelReader.testData.get("DOB"));

    }

}
