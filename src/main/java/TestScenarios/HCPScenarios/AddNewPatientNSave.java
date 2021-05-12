package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.ConnectPages;
import TestFramework.HCPPages.PatientPage;
import TestFramework.HCPPages.HomePage;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import TestScenarios.BaseTestClass;
import TestScenarios.DBScenarios.MostRecentPatientCreated;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import TestActions.InitSetup;

public class AddNewPatientNSave extends HCPBaseTestClass {

    HomePage homePage = new HomePage();
    PatientPage patientPage= new PatientPage();
    MostRecentPatientCreated mostRecentPatientCreated = new MostRecentPatientCreated();
    ConnectPages connectPages = new ConnectPages();

    @BeforeClass
    public static void env() {
        setEnv("HCP_ENV");
    }

    /*
    This test adds a patient and clicks save
    Verifies patient has been created successfully in Connect
    */

    @Test//(ignoreMissingDependencies = true)
    public void createNewPatient() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from Add_Patient2 sheet");
        excelReader.readExcel("Add_Patient2");

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
//------verify patient in Connect------
        //Index index = new Index();
        //Portlet portlet = index.selectInclisiran();

        logger.info("Launching Connect and Inputting credentials to log in");
        //InitSetup.initLogin("autotester@caremetx.com", "P@tients!", "autotester");
        connectPages.launchConnect( excelReader.testData.get("Env"),
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
        connectPages.verifyPatientInfo( excelReader.testData.get("FirstnLastName"),
                                        excelReader.testData.get("Address1"),
                                        excelReader.testData.get("Email"),
                                        excelReader.testData.get("Phone"),
                                        excelReader.testData.get("DOB"));

        logger.info("Closing the Browser");
        //connectPages.closeBrowserAndChrome();

    //Close after test is completed

    }

//    @Test
//    public void test() throws Exception {
//        //mostRecentPatientCreated.getMostRecentPatientCreated();
//        System.out.println("Test patient is created successfully "+mostRecentPatientCreated.getMostRecentPatientCreated());
//    }

}
