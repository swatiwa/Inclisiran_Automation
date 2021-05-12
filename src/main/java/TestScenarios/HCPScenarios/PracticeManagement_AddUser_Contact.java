package TestScenarios.HCPScenarios;

import TestFramework.HCPPages.ConnectPages;
import TestFramework.HCPPages.HomePage;
import TestFramework.HCPPages.PatientPage;
import TestFramework.HCPPages.PracticeManagement;
import TestScenarios.HCPBaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PracticeManagement_AddUser_Contact extends HCPBaseTestClass {

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

    Add a new 'CONTACT' User
    Verify we receive 'Service Center: Account Created' email
    Setting a new password
    Verify we can log in successfully into the Portal
    */

    @Test
    public void addContactUser() throws Exception{

        ExcelReader excelReader = new ExcelReader();

        logger.info("Reading data from PracticeManagement_Add_User_Contact sheet");
        excelReader.readExcel("PM_Add_User_Contact");

        logger.info("Navigate to 'Add User' screen");
        practiceManagement.clickUserDropDown();
        practiceManagement.clickPracticeManagement();
        practiceManagement.clickUsersTab();

        logger.info("Clicking 'Add User' button");
        practiceManagement.clickAddUserButton();

        logger.info("Verifying we have landed on the expected 'Add User' page");
        practiceManagement.verifyUserPage(excelReader.testData.get("userInfoPage"));

        logger.info("Selecting 'Contact' user role and Inputting all required user information");
        practiceManagement.populateUserInfo(
                excelReader.testData.get("UserType"),
                excelReader.testData.get("UserFirstName"),
                excelReader.testData.get("UserLastName"),
                excelReader.testData.get("UserEmail"),
                excelReader.testData.get("UserEmail"),
                excelReader.testData.get("Username"));

        logger.info("Clicking 'Add User and Close' button");
        practiceManagement.clickAddUserAndClose();

        logger.info("Verifying we have received the email with the account access link");
        practiceManagement.getAccessLink(
                excelReader.testData.get("GmailAddress"),
                excelReader.testData.get("GmailPassword"),
                excelReader.testData.get("UserEmail"));

        logger.info("Inputting new password");
        practiceManagement.enterNewPassword(excelReader.testData.get("UserNewPassword"));

        logger.info("Verifying we have successfully set the password");
        practiceManagement.verifySuccessText();

        logger.info("Entering new credentials and clicking 'Log In' button");
        practiceManagement.enterNewCredentials(
                excelReader.testData.get("Username"),
                excelReader.testData.get("UserNewPassword"));

        logger.info("Verifying we have landed on the expected portal 'Home page'");
        practiceManagement.verifyHomePage();

        logger.info("Closing the Browser");
        //patientPage.quitChromeDriver();

    }

}
