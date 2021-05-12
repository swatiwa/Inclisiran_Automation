package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.Select;


public class NewRequestPage extends BasePage {

    //--------WHICH PATIENT IS THIS REQUEST FOR?
    public void searchPatient(String patientName) throws Exception {
        waitProcessing(driver.findElement(By.xpath("//input[@class='rbt-input-main form-control rbt-input border-left-0']")),20);
        driver.findElement(By.xpath("//input[@class='rbt-input-main form-control rbt-input border-left-0']")).sendKeys(patientName);
        Thread.sleep(7000);
        getScreenShot("Selecting a patient");
        driver.findElement(By.xpath("//*[contains(@class,'dropdown-item')][1]")).click();
    }

    //public void clickOnDropDown() throws Exception {
    //    waitProcessing(driver.findElement(By.xpath("//a[@class='dropdown-item active']")),10);
    //    driver.findElement(By.xpath("//a[@class='dropdown-item active']")).click();
    //}

    public void clickAddPatient() throws Exception {
        driver.findElement(By.xpath("//button[text()='ADD A PATIENT']")).click();
    }

    //--------WOULD YOU LIKE FULL SUPPORT?
    public void selectWouldYouLikeFullSupport() throws Exception {
        waitProcessing(driver.findElement(By.xpath("//input[@id='full-support-option']")),10);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='full-support-option']")).click();
    }

    //--------WOULD YOU LIKE INDIVIDUAL SERVICES?
    public void selectInsuranceDeterminationAndCoverageReview() throws Exception {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@value='insurance-review']")).click();
    }
    public void selectCopayEnrollment() throws Exception {
        driver.findElement(By.xpath("//*[@value='copay-enrollment']")).click();
    }
    public void selectCopayClaimSubmission() throws Exception {
        driver.findElement(By.xpath("//*[@value='copay-claim']")).click();
    }
    public void selectPatientEducationAndSupportResources() throws Exception {
        driver.findElement(By.xpath("//*[@value='patient-resources']")).click();
    }
    public void selectPharmacyElectronicBenefitVerification() throws Exception {
        driver.findElement(By.xpath("//*[@value='pharmacy-ebv']")).click();
    }
    public void selectMedicalElectronicBenefitVerification() throws Exception {
        driver.findElement(By.xpath("//*[@value='medical-ebv']")).click();
    }
    public void selectPharmacyPriorAuthorization() throws Exception {
        driver.findElement(By.xpath("//*[@value='pharmacy-epa']")).click();
    }
    public void selectMedicalPriorAuthorization() throws Exception {
        driver.findElement(By.xpath("//*[@value='medical-epa']")).click();
    }
    //--------
    public void clickStartNewRequestFromRequestPage() throws Exception{
        Thread.sleep(5000);
        //waitProcessing(driver.findElement(By.xpath("//button[text()='START A NEW REQUEST']")),10);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='START A NEW REQUEST']")).click();
    }
    public void clickStartRequest() throws Exception{
        waitProcessing(driver.findElement(By.xpath("//button[text()='START REQUEST']")),10);
        driver.findElement(By.xpath("//button[text()='START REQUEST']")).click();
    }

    public void scrollDown() throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        jse.executeScript("window.scrollBy(0,500)","");

    }
    public void scrollUp() throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        jse.executeScript("window.scrollBy(0,-500)","");

    }

}
