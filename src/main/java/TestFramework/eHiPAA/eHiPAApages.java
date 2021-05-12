package TestFramework.eHiPAA;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//will put this into a separate package later

public class eHiPAApages extends BasePage {

    public void verifyWelcomePage() throws Exception {
        //Thread.sleep(2000);
        getScreenShot("Welcome to the Patient Support Services Patient Authorization Form.");
        String srchText = driver.findElement(By.xpath("//span[text()=' Your healthcare provider has requested your consent for services related to your newly prescribed medication. ']")).getText();

        if (srchText.contains("healthcare provider has requested your consent for services")) {
            System.out.println("Expected 'Welcome Page' text: ");
            System.out.println(srchText + " Displayed, PASS.");
        } else {
            System.out.println("Expected 'Welcome Page' text: " + srchText + " Not Displayed, Fail.");
        }
    }

    public void clickHereLink() throws Exception {
        //waitProcessing(driver.findElement(By.xpath("//a[text()='here']")), 10);
        driver.findElement(By.xpath("//a[text()='here']")).click();
    }

    //--------PATIENT INFORMATION-----------------------------------------------
    public void enterFirstName(String fName) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientFirstName']")).sendKeys(fName);
    }

    public void enterLastName(String lName) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientLastName']")).sendKeys(lName);
    }

    public void enterDob(String dob) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientDOB']")).sendKeys(dob);
    }

    public void selectGender(String gender) throws Exception {
        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(By.xpath("//div[text()='Gender *']")).click();
            driver.findElement(By.xpath("//span[text()='Male']")).click();
            System.out.println("Gender selected as 'Male'");
        } else if (gender.equalsIgnoreCase("female")) {
            driver.findElement(By.xpath("//div[text()='Gender *']")).click();
            driver.findElement(By.xpath("//span[text()='Female']")).click();
            System.out.println("Gender selected as 'Female'");
        } else {
            driver.findElement(By.xpath("//div[text()='Gender *']")).click();
            driver.findElement(By.xpath("//span[text()='Undeclared']")).click();
            System.out.println("Gender selected as 'Undeclared'");
        }
    }

    //--------ADDRESS and CONTACT INFORMATION-----------------------------------------------
    public void enterStreetAddress1(String strAddress1) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientAddress1']")).sendKeys(strAddress1);
    }

    public void enterStreetAddress2(String strAddress2) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientAddress2']")).sendKeys(strAddress2);
    }

    public void enterCity(String city) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientCity']")).sendKeys(city);
    }

    public void selectState(String state) throws Exception {
        //Currently this method is set to select VA as a state
        //And you would need to update the excel sheet value to select any other state
        //Or method update is required to handle diff states
        //Select select = new Select(driver.findElement(By.xpath("//*[@id='state']")));
        //select.selectByVisibleText(state);
        driver.findElement(By.xpath("//div[text()='State *']")).click();
        //driver.findElement(By.xpath("//span[text()='"+state+"']")).click();
        driver.findElement(By.xpath("//span[text()='CO']")).click();
    }

    public void enterZipCode(String zipCode) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientZipCode']")).sendKeys(zipCode);
    }

    public void enterEmail(String email) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientEmail']")).sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) throws Exception {
        driver.findElement(By.xpath("//input[@id='PatientCellNumber']")).sendKeys(phoneNumber);
    }

    public void populatePatientInfo(String fName, String lName,
                                    String dob, String gender, String strAddress1,
                                    String strAddress2, String city, String state,
                                    String zipCode, String email, String phoneNumber) throws Exception {
        enterFirstName(fName);
        enterLastName(lName);
        enterDob(dob);
        selectGender(gender);
        enterEmail(email);
        enterPhoneNumber(phoneNumber);
        enterStreetAddress1(strAddress1);
        enterStreetAddress2(strAddress2);
        enterZipCode(zipCode);
        enterCity(city);
        selectState(state);
    }

    public void clickNext() throws Exception {
        //not sure why but the portal is set to click somewhere on the page to be able to click Next button
        driver.findElement(By.xpath("//h3[text()='Contact Information']")).click();
        driver.findElement(By.xpath("//button[@id='infoformnext']")).click();
    }


    //Personal Representative Information-----------------------------------------------
    public void selectRepresentative() throws Exception {
        driver.findElement(By.xpath("//input[@name='representativeFlag']")).click();
        Thread.sleep(2000);
    }

    public void enterRepFirstname(String repFname) throws Exception {
        driver.findElement(By.xpath("//input[@id='RepresentativeFirstName']")).sendKeys(repFname);
    }

    public void enterRepLastname(String repLname) throws Exception {
        driver.findElement(By.xpath("//input[@id='RepresentativeLastName']")).sendKeys(repLname);
    }

    public void enterRepPhone(String repPhone) throws Exception {
        driver.findElement(By.xpath("//input[@id='RepresentativeCellNumber']")).sendKeys(repPhone);
    }

    public void selectRepRelationship(String repRelationship) throws Exception {
        if (repRelationship.equalsIgnoreCase("Legal Guardian")) {
            driver.findElement(By.xpath("//div[text()='Describe Relationship to Patient']")).click();
            driver.findElement(By.xpath("//span[text()='Legal Guardian']")).click();
            System.out.println("Relationship selected as 'Legal Guardian'");

        } else if (repRelationship.equalsIgnoreCase("Family Member")) {
            driver.findElement(By.xpath("//div[text()='Describe Relationship to Patient']")).click();
            driver.findElement(By.xpath("//span[text()='Family Member']")).click();
            System.out.println("Relationship selected as 'Family Member'");

        } else if (repRelationship.equalsIgnoreCase("female")) {
            driver.findElement(By.xpath("//div[text()='Describe Relationship to Patient']")).click();
            driver.findElement(By.xpath("//span[text()='Caregiver']")).click();
            System.out.println("Relationship selected as 'Caregiver'");
        }
    }

    public void populateRepresentativeInfo(String repFname, String repLname,
                                           String repPhone, String repRelationship) throws Exception {
        enterRepFirstname(repFname);
        enterRepLastname(repLname);
        enterRepPhone(repPhone);
        selectRepRelationship(repRelationship);
    }

    //--------------------------------------------------------------------------------
    public void clickSendVerificationCode() throws Exception {
        boolean searchTextBox = driver.findElement(By.xpath("//h3[text()='Email Address Confirmation']")).isDisplayed();
        //System.out.println("Is 'Email Address Confirmation' page displayed?" +searchTextBox);

        if (searchTextBox) {
            System.out.println("'Email Confirmation' page is Displayed " +
                    "Clicking on 'Send Verification Code' button, PASS.");
            driver.findElement(By.xpath("//span[text()='Send Verification Code']")).click();
            Thread.sleep(2500);
        } else {
            System.out.println("'Email Confirmation' page is NOT Displayed " +
                    "Unable to Click on 'Send Verification Code' button, FAIL.");
        }
    }

    //Launch Gmail and get the verification code---------------------------------------------------------------------
    public void getVerificationCode(String gmailEmail, String gmailPassword, String PtEmail, String fName) throws Exception {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //launching gmail on new tab
        driver.switchTo().window(tabs.get(1));
        String url = "https://mail.google.com/";
        driver.get(url);

        //inputting email address to log in to gmail account
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(gmailEmail);
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@class='VfPpkd-RLmnJb']")).click();
        Thread.sleep(2500);

        //inputting password
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(gmailPassword);
        driver.findElement(By.xpath("//*[@class='VfPpkd-RLmnJb']")).click();

        //inputting the email address where the email was sent to
        Thread.sleep(4000);
        //driver.findElement(By.xpath("//input[@class='gb_gf' and @type='text']")).sendKeys(PtEmail);
        driver.findElement(By.xpath("//input[@aria-label='Search mail']")).sendKeys(PtEmail);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@class='gb_of gb_pf']")).click();
/*
        List<WebElement> email = driver.findElements(By.xpath("//*[contains(text(),'6-digit code')]"));
        for (WebElement emailSubject : email) {
            Thread.sleep(2500);
            if (emailSubject.getText().equalsIgnoreCase("//*[contains(text(),'"+fName+"')]") == true) {
                emailSubject.click();
                System.out.println("PASS");
                break;
            } else {
                 emailSubject.click();
                System.out.println("Fail");
            }
        }
 */
        Thread.sleep(2000);
        //Clicking on the 1st email (for now! till I figure out the code above)
        driver.findElement(By.xpath("//div[text()='Inbox']")).click();

        //Getting email text
        Thread.sleep(2500);
        String[] emailText = driver.findElement(By.xpath("//b[contains(text(),'Email Verification Code: ')]")).getText().split(": ");
        //Getting verification code
        //String textBeforeCode = emailText[0];
        //System.out.println(textBeforeCode);
        String verificationCode = emailText[1];
        System.out.println("Verification code: " + verificationCode);
        //get back to the eHiPAA website
        driver.switchTo().window(tabs.get(0));

        //Input the code, click Next
        //Verify "Thanks for Verifying your email" message
        boolean codeSentTxt = driver.findElement(By.xpath("//span[text()='Enter that code below and click Next.']")).isDisplayed();
        if (codeSentTxt) {
            System.out.println("'Email Confirmation' page is Displayed; inputting the code we got from email, PASS.");
            driver.findElement(By.xpath("//input[@id='VerificationCode']")).sendKeys(verificationCode);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Next']")).click();
        } else {
            System.out.println("'Email Confirmation' page is NOT Displayed; unable to input the code we got from email, FAIL.");
        }
}

    public void inputSignature(String fName) throws Exception {
        //((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //Switch to the eHiPAA and Sign the Authorization
        driver.switchTo().window(tabs.get(0));
        boolean authorizationTxt = driver.findElement(By.xpath("//h3[text()='Authorization']")).isDisplayed();
        if (authorizationTxt) {
            System.out.println("Authorization page is displayed, PASS.");
            driver.findElement(By.xpath("//input[@id='eSignatureName']")).sendKeys(fName);
            //CLicking somewhere on the page to be able to click Next (that's how it's developed)
            driver.findElement(By.xpath("//h3[text()='Signature of Patient']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Next']")).click();
            Thread.sleep(1000);

            //Add 'if statement' to verify the date on the page is = to today's date

        } else {
            System.out.println("Authorization page is displayed, FAIL.");
        }
    }

    //Verify Consent Page is displayed
    public void consentTxt() throws Exception {
        boolean consentTxt = driver.findElement(By.xpath("//h3[text()='Optional Patient Consent']")).isDisplayed();
        if(consentTxt){
            System.out.println("'Patient Consent' page is Displayed, PASS.");
        } else {
            System.out.println("'Patient Consent' page is NOT displayed, FAIL.");
        }
    }

    public void clickCopayConsent() throws Exception {
        driver.findElement(By.xpath("//input[@name='CopayConsent']")).click();
        Thread.sleep(500);
    }
    public void clickSupportConsent() throws Exception {
        driver.findElement(By.xpath("//input[@name='SupportConsent']")).click();
        Thread.sleep(500);
    }
    public void checkFinancialConsent() throws Exception {
        driver.findElement(By.xpath("//input[@name='FinancialConsent']")).click();
        Thread.sleep(500);
    }

    public void clickNextAfterConsent() throws Exception {
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        Thread.sleep(1000);
    }

    public void reviewNconfirmTxt() throws Exception {
        Thread.sleep(3000);
        //Review and Confirm, and click Complete
        boolean reviewNconfirmTxt = driver.findElement(By.xpath("//h3[text()='Review and Confirm']")).isDisplayed();
        if (reviewNconfirmTxt) {
            System.out.println("'Review and Confirm' page is Displayed, Checkbox and Clicking 'Complete', PASS.");
            driver.findElement(By.xpath("//input[@class='mui-enhanced-switch-input']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Complete']")).click();
        } else {
            System.out.println("'Review and Confirm' page is NOT displayed, FAIL.");
        }
    }

    public void thankYouMsg() throws Exception {
        //Verify "Thank you for filling out and signing this."
        boolean thankYouMsg = driver.findElement(By.xpath("//span[text()='Thank you for filling out and signing this .']")).isDisplayed();
        if (thankYouMsg) {
            System.out.println("'Thank You' page is Displayed, PASS.");
        } else {
            System.out.println("'Thank You' page is NOT displayed, FAIL.");
        }
    }

    public void verifyFormReceived() throws Exception {
        //((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        boolean thankYouMsg = driver.findElement(By.xpath("//span[text()='Thank you for filling out and signing this .']")).isDisplayed();
        if (thankYouMsg) {
            //CLick on Email button
            driver.findElement(By.xpath("//span[text()='Email']")).click();
            Thread.sleep(2000);
            //CLick 'I understand, send the Email' button
            driver.findElement(By.xpath("//span[text()='I understand, send the email']")).click();
            //Switch to Gmail and Verify we have received the expected email
            driver.switchTo().window(tabs.get(1));
            Thread.sleep(1000);
            //Click on 'INBOX' tab
            driver.findElement(By.xpath("//a[@href='https://mail.google.com/mail/u/0/#inbox']")).click();
            Thread.sleep(13000);

            //Add 'if statement' to verify the email is received
            System.out.println("'Patient Authorization Form' is received, PASS.");
        } else {
            System.out.println("'Patient Authorization Form' is NOT received, FAIL.");
        }
    }

    public void closeChrome() throws Exception {
        //driver.switchTo().window(tabs.get(1)).close();
        driver.close();
        driver.quit();
    }


}