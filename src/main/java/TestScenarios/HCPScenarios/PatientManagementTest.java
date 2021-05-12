package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.*;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PatientManagementTest extends HCPBaseTestClass {

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
    Test Patient Management page and Patient profile
    */


    @Test
    public void managePatient() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient2 sheet");
        excelReader.readExcel("Add_Patient2");

        logger.info("Click 'Patient Management'");
        managementPage.clickPatientManagement();

        logger.info("Click 'Patients with Updated'");
        managementPage.clickPatientsWithActions();

        logger.info("Filtering Patients with updates with LastName'");
        managementPage.selectLastNameFilterNSearch(excelReader.testData.get("LastName"));

        logger.info("Clicking Actions button");
        managementPage.clickActions();

        logger.info("Clicking 'Patient Profile'");
        managementPage.clickPatientProfile();

        logger.info("Verify 'PATIENT INFORMATION'");
        /*prescriberAttestationNSubmitPage.verifyPatientInfo(
                excelReader.testData.get("FirstName"),
                excelReader.testData.get("LastName"),
                excelReader.testData.get("DOB"));*/

        logger.info("Clicking 'Alerts'");
        managementPage.clickAlerts();

        logger.info("Clicking 'Request History'");
        managementPage.clickRequestHistory();

        logger.info("Clicking 'Details'");
        managementPage.clickDetails();

        logger.info("Clicking 'DOCUMENTS', VIEW and Close");
        managementPage.clickDocuments();
        managementPage.clickViewDocument();
        //newRequestPage.scrollDown();
        managementPage.clickClose();



    }




}
