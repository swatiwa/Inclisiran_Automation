package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.HomePage;
import TestFramework.HCPPages.PatientPage;
import TestScenarios.DBScenarios.MostRecentPatientCreated;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewPatientNSave500error extends HCPBaseTestClass {

    HomePage homePage = new HomePage();
    PatientPage patientPage = new PatientPage();
    MostRecentPatientCreated mostRecentPatientCreated = new MostRecentPatientCreated();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
    Add patient and Click save
    Verify 'Failed to fetch: 500' error
    As of December 2020:
    'Failed to fetch: 500' error is expected when we input phone numbers except: 222-222-22-22 and fax numbers except: 111-111-11-11
    */

    @Test//(ignoreMissingDependencies = true)
    public void createNewPatient() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient4 sheet");
        excelReader.readExcel("Add_Patient4");

        logger.info("Clicking 'Add Patient'");
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

        logger.info("Clicking 'SAVE PATIENT AND CLOSE' button");
        patientPage.clickSavePatient();

        logger.info("Verify 'Failed to fetch: 500' error");
        patientPage.error500();
    }
}
