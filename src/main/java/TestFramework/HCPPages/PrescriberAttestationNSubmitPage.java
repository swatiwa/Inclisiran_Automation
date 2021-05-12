package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PrescriberAttestationNSubmitPage extends BasePage {

    public Boolean verifyInfo(String patientFirstName) throws Exception {
        String element= driver.findElement(By.xpath("//*[@id='patient_first_name']")).getText();
        System.out.println("Patient first name: " +element);
        return element.equalsIgnoreCase("Patient first name: "+element);

        //String element2= driver.findElement(By.xpath("//*[@id='patient_last_name']")).getText();
        //return element2.equalsIgnoreCase("Patient last name: "+element2);

        //String element3= driver.findElement(By.xpath("//*[@id='patient_dob']")).getText();
        //return element3.equalsIgnoreCase("Patient DOB: "+element3);
    }

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //-------Verify PATIENT INFORMATION
    public void verifyPatientInfo(String patientFirstName, String patientLastName, String patientDOB) throws Exception {

        waitFor(2);
        BasePage.getScreenShot("Patient information validation");

        String element1= driver.findElement(By.xpath("//*[@id='patient_first_name']")).getText();
            System.out.println("Patient first name: "+element1);
        String element2= driver.findElement(By.xpath("//*[@id='patient_last_name']")).getText();
            System.out.println("Patient last name: "+element2);
        String element3= driver.findElement(By.xpath("//*[@id='patient_dob']")).getText();
            System.out.println("Patient DOB: "+element3);

        if(element1.equalsIgnoreCase(patientFirstName)){
            System.out.println("Patient first name matches, PASS.");
        }else{
            System.out.println("Patient first name doe NOT match, FAIL.");
        }
        if(element2.equalsIgnoreCase(patientLastName)){
            System.out.println("Patient last name matches, PASS.");
        }else{
            System.out.println("Patient last name does NOT match, FAIL.");
        }
        if(element3.equalsIgnoreCase(patientDOB)){
            System.out.println("Patient DOB matches, PASS.");
        }else{
            System.out.println("Patient DOB does NOT match, FAIL.");
        }
    }

    //-------Verify INSURANCE INFORMATION

    //-------Verify PRESCRIBER INFORMATION

    //-------Verify TREATMENT INFORMATION

    //-------Verify ADMINISTRATION INFORMATION

    //-------PLEASE READ THE FOLLOWING ATTESTATION AND ACKNOWLEDGE TO PROCEED
    public void clickIcertify() throws Exception {
        waitFor(1);
        BasePage.getScreenShot("ATTESTATION AND ACKNOWLEDGE TO PROCEED");
        driver.findElement(By.xpath("//*[@*='prescriber_attestation_is_certified']")).click();
    }

    //-------Can we contact the patient if they have issues with enrollment?
    public void clickYEScontact() throws Exception {
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@*='can_contact_patient']")).click();
    }

    public void enterSignature(String signature) throws Exception{
        waitProcessing(driver.findElement(By.xpath("//*[@*='prescriber_attestation_signature']")),10);
        driver.findElement(By.xpath("//*[@*='prescriber_attestation_signature']")).sendKeys(signature);
    }

    //-------Verify DATE OF CERTIFICATION
    public void verifySumbissionDate(String submissionDate) throws Exception {

        Thread.sleep(2500);
        BasePage.getScreenShot("'Submission date' validation");
        String date1= driver.findElement(By.xpath("//*[contains(text(),'2021')]")).getText();
            System.out.println("'Date' on page: "+date1);
            System.out.println("'System date': "+submissionDate);

        if(date1.equalsIgnoreCase(submissionDate)){
            System.out.println("'Submission date' matches 'Current date', PASS");
            System.out.println("");
        }else{
            System.out.println("'Submission date' does NOT match 'Current date', FAIL");
            System.out.println("");
        }
    }

    //-------Verify Successful Submission
    public void verifySubmissionSuccessful() throws Exception{
        waitFor(5);
        getScreenShot("'SUBMISSION' page");
        String submission= driver.findElement(By.xpath("//h4[text()='submission successful']")).getText();
        Assert.assertEquals(submission.trim().toLowerCase(),("submission successful"),"Expected 'Submission Successful' text not displayed");
    }

}
