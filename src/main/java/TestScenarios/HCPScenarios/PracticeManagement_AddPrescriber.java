package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.ConnectPages;
import TestFramework.HCPPages.HomePage;
import TestFramework.HCPPages.PatientPage;
import TestFramework.HCPPages.PracticeManagement;
import TestScenarios.DBScenarios.MostRecentPatientCreated;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PracticeManagement_AddPrescriber extends HCPBaseTestClass {

    HomePage homePage = new HomePage();
    PatientPage patientPage= new PatientPage();
    PracticeManagement practiceManagement = new PracticeManagement();
    ConnectPages connectPages = new ConnectPages();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
    Practice Management:
    Add 'Prescriber'

    */

    @Test
    public void addPrescriber() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from PracticeManagement_Add_Prescriber sheet");
        excelReader.readExcel("PM_Add_Prescriber");

        logger.info("Navigate to 'Add Prescriber' screen");
        practiceManagement.clickUserDropDown();
        practiceManagement.clickPracticeManagement();
        practiceManagement.clickPrescribersTab();

        logger.info("Clicking 'Add Prescriber' button");
        practiceManagement.clickAddPresciberButton();

        //logger.info("Verifying we have landed on the expected 'Add Prescriber' page");
        //practiceManagement.verifyPrescriberPage(excelReader.testData.get("prescriberInfoPage"));

        logger.info("Inputting all required Prescriber information");
        practiceManagement.populatePrescriberInfo(
                excelReader.testData.get("PrescriberNPI"),
                excelReader.testData.get("PrescriberFirstName"),
                excelReader.testData.get("PrescriberMiddleName"),
                excelReader.testData.get("PrescriberLastName"),
                excelReader.testData.get("PrescriberTaxID"),
                excelReader.testData.get("PrescriberLicenseNumber"),
                excelReader.testData.get("PrescriberStateLicense"));

        logger.info("Clicking 'Add Prescriber and Close' button");
        practiceManagement.clickAddPrescriberButtonNclose();


    }


}
