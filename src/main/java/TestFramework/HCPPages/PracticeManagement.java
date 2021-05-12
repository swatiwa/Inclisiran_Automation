package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PracticeManagement extends BasePage {

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickUserDropDown() throws Exception {
        waitFor(2);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='user-menu-dropdown']")).click();
    }

    public void clickPracticeManagement() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'Practice Management')]")).click();
    }

    public void clickMyAccount() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'My Account')]")).click();
    }

    public void clickLogOut() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'Logout')]")).click();
    }
//====================================================================================================
    public void clickUsersTab() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'USERS')]")).click();
    }

    public void clickAddUserButton() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'ADD A USER')]")).click();
    }

    public void verifyUserPage(String userInfoPage) throws Exception {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.getScreenShot("Verifying we have landed on the expected 'Add User' page");

        String addUserPagetxt = driver.findElement(By.xpath("//*[@*='users_information_section']")).getText();
        Assert.assertEquals(addUserPagetxt.trim(),userInfoPage.trim(),"User page not displayed or incorrect");
        System.out.println("Actual User page text " +addUserPagetxt+ " === " + "expectedPage " +userInfoPage);
    }

    public void selectUserType(String userType) throws Exception {
        if (userType.equalsIgnoreCase("Contact")) {
            driver.findElement(By.xpath("//*[contains(text(),'--Select One--')]")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[contains(text(),'Contact')]")).click();
        } else if (userType.equalsIgnoreCase("Provider")) {
            driver.findElement(By.xpath("//*[contains(text(),'--Select One--')]")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[contains(text(),'Provider')]")).click();
        }
    }
    public void enterUserFirstName(String fName ) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='prescriber_first_name']")).sendKeys(fName);
    }
    public void enterUserLastName(String lName ) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='prescriber_last_name']")).sendKeys(lName);
    }
    public void enterUserEmail(String userEmail) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='prescriber_email']")).sendKeys(userEmail);
    }
    public void enterConfirmUserEmail(String userEmail) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='prescriber_confirm_email']")).sendKeys(userEmail);
    }
    public void enterUsername(String username) throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//*[@*='username']")).sendKeys(username);
    }

    public void populateUserInfo(String userType, String fName, String lName,
                                 String email1, String email2, String username) throws Exception {
        selectUserType(userType);
        enterUserFirstName(fName);
        enterUserLastName(lName);
        enterUserEmail(email1);
        enterConfirmUserEmail(email2);
        enterUsername(username);
    }

    public void clickAddUserAndClose() throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//*[contains(text(),'users information')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'ADD USER AND CLOSE')]")).click();
        waitFor(5);
    }

    public void enterPrescriberNPI(String prescriberNPI) throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//*[@*='prescriber_npi']")).sendKeys(prescriberNPI);
    }
//====================================================================================================
    public void clickPrescribersTab() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'PRESCRIBERS')]")).click();
    }

    public void clickAddPresciberButton() throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//*[contains(text(),'ADD A PRESCRIBER')]")).click();
    }

    public void verifyPrescriberPage(String prescriberInfoPage) throws Exception {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.getScreenShot("Verifying we have landed on the expected 'Add Prescriber' page");

        String addPrescriberPagetxt = driver.findElement(By.xpath("//*[contains(text(),'information')]")).getText();
        Assert.assertEquals(addPrescriberPagetxt.trim(),prescriberInfoPage.trim(),"Prescriber page not displayed or incorrect");
        System.out.println("Actual Prescriber page text " +addPrescriberPagetxt+ " === " + "expectedPage " +prescriberInfoPage);
    }

    public void clickAddPrescriberButtonNclose() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//*[contains(text(),'CLOSE')]")).click();
    }

    public void populatePrescriberInfo(String prescriberNPI, String prescriberfName, String prescriberMiddleName, String prescriberlName,
                                 String prescriberTaxID, String prescriberLicenseNumber, String prescriberState) throws Exception {
        enterPrescriberNPI(prescriberNPI);
        enterPrescriberfName(prescriberfName);
        enterPrescriberMiddleName(prescriberMiddleName);
        enterPrescriberlName(prescriberlName);
        enterPrescriberTaxID(prescriberTaxID);
        enterPrescriberLicense(prescriberLicenseNumber);
        selectState(prescriberState);
    }
    public void enterUserNPI(String prescriberNPI ) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_npi']")).sendKeys(prescriberNPI);
    }
    public void enterPrescriberfName(String prescriberfName ) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_first_name']")).sendKeys(prescriberfName);
    }
    public void enterPrescriberMiddleName(String prescriberMiddleName) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_middle_name']")).sendKeys(prescriberMiddleName);
    }
    public void enterPrescriberlName(String prescriberlName) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_last_name']")).sendKeys(prescriberlName);
    }
    public void enterPrescriberTaxID(String prescriberTaxID) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_tax_id']")).sendKeys(prescriberTaxID);
    }
    public void enterPrescriberLicense(String prescriberLicenseNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_state_license_number']")).sendKeys(prescriberLicenseNumber);
    }

    //Update the method by adding states: Currently it has 'California' option only
    public void selectState(String prescriberState) throws Exception {
        waitFor(1);
        if (prescriberState.equalsIgnoreCase("California")) {
            driver.findElement(By.xpath("//*[@*='prescriber_licensing_state']")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[contains(text(),'California')]")).click();
        }
    }


//====================================================================================================
    public void clickLocationsTab() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'LOCATIONS')]")).click();
        //*[lower-case(@title)='empire burlesque']
    }

    public void clickAddLocationButton() throws Exception {
        driver.findElement(By.xpath("//*[contains(text(),'ADD A LOCATION')]")).click();
    }

    public void verifyLocationPage( String locationInfoPage) throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.getScreenShot("Verifying we have landed on the expected 'Add Location' page");

        String addLocationPagetxt = driver.findElement(By.xpath("//*[contains(text(),'information')]")).getText();
        Assert.assertEquals(addLocationPagetxt.trim(),locationInfoPage.trim(),"Location page not displayed or incorrect");
        System.out.println("Actual Location page text " +addLocationPagetxt+ " === " + "expectedPage " +locationInfoPage);

    }
    public void populateLocationInfo(String address1, String address2, String zipCode, String city,
                                       String state, String phoneNumber, String faxNumber) throws Exception {
        enterAddress1(address1);
        enterAddress2(address2);
        enterZipcode(zipCode);
        enterCity(city);
        selectLocationState(state);
        enterPhoneNumber(phoneNumber);
        enterFaxNumber(faxNumber);
    }
    public void enterAddress1(String address1 ) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_location_address_1']")).sendKeys(address1);
    }
    public void enterAddress2(String address2 ) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_location_address_2']")).sendKeys(address2);
    }
    public void enterZipcode(String zipCode) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_location_zip']")).sendKeys(zipCode);
    }
    public void enterCity(String city) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_location_city']")).sendKeys(city);
    }
    public void enterPhoneNumber(String phoneNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_location_phone_1']")).sendKeys(phoneNumber);
    }
    public void enterFaxNumber(String faxNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='prescriber_location_fax_number']")).sendKeys(faxNumber);
    }

    //Update the method by adding states: Currently it has 'Virginia' option only
    public void selectLocationState(String state) throws Exception {
        waitFor(1);
        if (state.equalsIgnoreCase("Virginia")) {
            driver.findElement(By.xpath("//*[@*='prescriber_location_state']")).click();
            waitFor(1);
            driver.findElement(By.xpath("//*[contains(text(),'Virginia')][1]")).click();
        }
    }

    public void clickAddLocationButtonNclose() throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//*[contains(text(),'CLOSE')]")).click();
        waitFor(1);
    }


//====================================================================

    //Launch Gmail ---------------------------------------------------------------------
    public void getAccessLink(String gmailEmail, String gmailPassword, String PtEmail) throws Exception {
        waitFor(10);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //launching gmail on new tab
        driver.switchTo().window(tabs.get(1));
        String url = "https://mail.google.com/";
        driver.get(url);

        //inputting Gmail log in credentials
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(gmailEmail);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='VfPpkd-RLmnJb']")).click();
        //driver.findElement(By.xpath("//*[@*='identifierNext']")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(gmailPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='passwordNext']")).click();

        //inputting the user email address into 'Search bar'
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Wait till we receive email
        waitFor(10);
        //driver.findElement(By.xpath("//input[@class='gb_gf' and @type='text']")).sendKeys(PtEmail);
        driver.findElement(By.xpath("//input[contains(@aria-label,'Search mail')]")).sendKeys(PtEmail);

        //Clicking on the 1st matching email
        waitFor(5);
        driver.findElement(By.xpath("//*[@*='gs_ars50_0']")).click();
        //driver.findElement(By.xpath("//div[text()='Inbox']")).click();

        //CLick on the 'CLICK HERE' link in the email
        waitFor(5);
        driver.findElement(By.xpath("//a[contains(text(),'CLICK HERE')]")).click();
    }

    //Verify 'SUCCESS' text
    public void verifySuccessText() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.getScreenShot("Verifying we have landed on the expected 'Success' page");

        String successPage = " SUCCESS";
        String Success = driver.findElement(By.xpath("//*[contains(text(),' SUCCESS')]")).getText();

        Assert.assertEquals(Success.trim(),successPage.trim(),"Success page not displayed or incorrect");
        System.out.println("Actual page text " +Success+ " === " + "expectedPage " +successPage);

    }

    public void enterNewPassword(String newPassword) throws Exception {

        //((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //landing on the new tab
        driver.switchTo().window(tabs.get(2));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='new_password']")).sendKeys(newPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@*='confirm_password']")).sendKeys(newPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'SET MY NEW PASSWORD')]")).click();
    }

    public void enterNewCredentials(String newUsername, String newPassword) throws Exception {
        enterUsername(newUsername);
        driver.findElement(By.xpath("//*[@*='password']")).sendKeys(newPassword);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[contains(text(),'LOGIN')]")).click();
    }

    //Verify Home page
    public void verifyHomePage() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BasePage.getScreenShot("Verifying we have landed on the expected 'Home page'");

        String expectedPage = "SERVICE CENTER SUPPORT";
        String actualPage = driver.findElement(By.xpath("//*[contains(text(),'SERVICE CENTER SUPPORT')]")).getText();

        Assert.assertEquals(actualPage.trim(),expectedPage.trim(),"'Home page' not displayed or incorrect");
        System.out.println("Actual page text " +actualPage+ " === " + "expectedPage " +expectedPage);

    }




}