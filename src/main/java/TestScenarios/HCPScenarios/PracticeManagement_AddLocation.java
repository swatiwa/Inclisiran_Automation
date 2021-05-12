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

public class PracticeManagement_AddLocation extends HCPBaseTestClass {

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
    Add 'Location'

    */

    @Test
    public void addLocation() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from PracticeManagement_Add_Location sheet");
        excelReader.readExcel("PM_Add_Location");

        logger.info("Navigate to 'Add Location' screen");
        practiceManagement.clickUserDropDown();
        practiceManagement.clickPracticeManagement();
        practiceManagement.clickLocationsTab();

        logger.info("Clicking 'Add Location' button");
        practiceManagement.clickAddLocationButton();

        logger.info("Verifying we have landed on the expected 'Add Location' page");
        practiceManagement.verifyLocationPage(excelReader.testData.get("locationInfoPage"));

        logger.info("Inputting all required 'Location' information");
        practiceManagement.populateLocationInfo(
                excelReader.testData.get("Address1"),
                excelReader.testData.get("Address2"),
                excelReader.testData.get("ZipCode"),
                excelReader.testData.get("City"),
                excelReader.testData.get("State"),
                excelReader.testData.get("PhoneNumber"),
                excelReader.testData.get("FaxNumber"));

        logger.info("Clicking 'Add Location and Close' button");
        practiceManagement.clickAddLocationButtonNclose();

        //patientPage.quitChromeDriver();

    }


}
