package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
//import com.google.common.base.Predicates;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientPage extends BasePage {


    //--------PERSONAL INFORMATION
    public void enterFirstName(String fName) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_first_name']")).sendKeys(fName);
    }

    public void enterLastName(String lName) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_last_name']")).sendKeys(lName);
    }

    public void enterDob(String dob) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_dob']")).sendKeys(dob);
    }

    public void selectGender(String gender) throws Exception {
        if (gender.equalsIgnoreCase("M")) {
            driver.findElement(By.xpath("//*[text()='Male']")).click();
        } else {
            driver.findElement(By.xpath("//*[text()='Female']")).click();
        }
    }

    public void selectLanguage(String language) throws Exception {
        waitFor(1);
        if (language.equalsIgnoreCase("english")) {
            driver.findElement(By.xpath("//*[@*='patient_preferred_language']")).click();
            waitFor(1);
            driver.findElement(By.xpath("//option[text()='English']")).click();
        } else if (language.equalsIgnoreCase("Spanish")) {
            driver.findElement(By.xpath("//*[@*='patient_preferred_language']")).click();
            waitFor(1);
            driver.findElement(By.xpath("//option[text()='Spanish']")).click();
        } else {
            driver.findElement(By.xpath("//*[@*='patient_preferred_language']")).click();
            waitFor(1);
            driver.findElement(By.xpath("//option[text()='Other']")).click();
        }
    }


    //--------CONTACT AND ADDRESS INFORMATION
    public void enterEmail(String email) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_email']")).sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//*[@name='patient_mobile_number']")).sendKeys(phoneNumber);
    }

    public void selectContactmethod() throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//*[@id='patient_message_option-mobile']")).click();
    }

    public void enterStreetAddress(String strAddress) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_address_1']")).sendKeys(strAddress);
    }

    public void enterStreetAddress2(String strAddress2) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_address_2']")).sendKeys(strAddress2);
    }

    public void enterZipCode(String zipCode) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_zip']")).sendKeys(zipCode);
    }

    public void enterCity(String city) throws Exception {
        driver.findElement(By.xpath("//*[@name='patient_city']")).sendKeys(city);
    }

    public void setOrganizationType(String type) throws Exception {
        Select select = new Select(driver.findElement(By.id("OrganizationType")));
        select.selectByVisibleText(type); //Facility
    }

    public void selectState(String state) throws Exception {
        Select select = new Select(driver.findElement(By.xpath("//*[@name='patient_state']")));
        select.selectByVisibleText(state);
    }

    public void clickStartNewRequest() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//button[text()='START NEW REQUEST']")).click();
    }


    public void populatePatientInfo(String fName, String lName, String dob,
                                    String gender, String language, String email,
                                    String phoneNumber, String strAddress, String strAddress2, String zipCode,
                                    String city, String state) throws Exception {
        enterFirstName(fName);
        enterLastName(lName);
        enterDob(dob);
        selectGender(gender);
        selectLanguage(language);
        enterEmail(email);
        enterPhoneNumber(phoneNumber);
        enterStreetAddress(strAddress);
        enterStreetAddress2(strAddress2);
        enterZipCode(zipCode);
        enterCity(city);
        selectState(state);
        //clickAddPatientSubmit();
    }

    //--------
    public void clickAddPatient() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//button[text()='ADD A PATIENT']")).click();
    }

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickAddPatientSubmit() throws Exception {
        waitFor(3);
        getScreenShot("'ADD PATIENT AND START A REQUEST'");
        driver.findElement(By.xpath("//button[contains(text(),'ADD PATIENT AND START A REQUEST')]")).click();
        waitFor(2);
    }

    public void clickSavePatient() throws Exception {
        waitFor(2);
        getScreenShot("SAVE PATIENT AND CLOSE");
        driver.findElement(By.xpath("//button[text()='SAVE PATIENT AND CLOSE']")).click();
    }

    public void error500() throws Exception {
        waitFor(2);
        getScreenShot("Failed to Fetch: 500");
        String errorText = driver.findElement(By.xpath("//p[text()='Failed to fetch: 500 ']")).getText();
        //as of December 2020 we were told to use 222-222-22-22 for phone# and 111-111-11-11 for all fax#
        //so the error message 'Failed to fetch: 500' is expected and test is considered as passed
        Assert.assertEquals(errorText.trim(),("Failed to fetch: 500").trim(),"Error not displayed");
    }

    public void clickAddPatientNselect() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//button[text()='ADD PATIENT AND SELECT']")).click();
    }

    public void clickCancel() throws Exception {
        waitFor(1);
        driver.findElement(By.xpath("//button[text()='CANCEL']")).click();
    }

    public void clickNext() throws Exception {
        waitFor(2);
        getScreenShot("Clicking 'NEXT'");
        driver.findElement(By.xpath("//button[text()='next']")).click();
    }

    public void quitChromeDriver() throws Exception {
        waitFor(2);
        driver.close();
        driver.quit();
    }

    //-------Verify PATIENT INFORMATION
    public void verifyPatientInfo(String patientFirstName, String patientLastName, String patientDOB) throws Exception {

        waitFor(10);
        BasePage.getScreenShot("Patient information validation");

        String pFname = driver.findElement(By.xpath("(//*[@*='patient_first_name'])[2]")).getAttribute("value");
        Assert.assertEquals(pFname.trim(),patientFirstName.trim(),"FirstName not displayed or incorrect");
        System.out.println("Actual First Name " +pFname +" ==== "+ "expectedFirstName "+patientFirstName);
        //System.out.println("Patient first name: " + pFname);

        String pLname = driver.findElement(By.xpath("(//*[@*='patient_last_name'])[2]")).getAttribute("value");
        Assert.assertEquals(pLname.trim(),patientLastName.trim(),"LastName not displayed or incorrect");
        System.out.println("Actual LastName " +pLname +" ==== "+ "expectedLastName "+patientLastName);
        //System.out.println("Patient last name: " + pLname);

        String pDOB = driver.findElement(By.xpath("(//*[@*='dob'])[2]")).getAttribute("value");
        Assert.assertEquals(pDOB.substring(0,10).trim(),patientDOB.trim(),"DOB not displayed or incorrect");
        System.out.println("Actual dob " +pDOB +" ==== "+ "expectedDOB "+patientDOB);
        //System.out.println("Patient DOB: " + pDOB);

    }

}
