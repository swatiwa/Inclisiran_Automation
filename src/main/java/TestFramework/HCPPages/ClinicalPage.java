package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ClinicalPage extends BasePage {

    //-------PRESCRIBER'S PRACTICE INFORMATION
    public void enterTreatmentStartDate(String TreatmentStartDate) throws Exception{
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@*='clinical_treatment_start_date']")).sendKeys(TreatmentStartDate);
    }
    public void clickPatientEnrolledInTrial() throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@*='clinical_patient_previously_enrolled']")).click();
    }

    //-------ICD-10 PRIMARY DIAGNOSIS CODE
    public void enterICD10primaryDiagnCode(String diagnosisCode) throws Exception{

        driver.findElement(By.xpath("//select[@*='clinical_patient_primary_diagnosed_select']")).sendKeys(diagnosisCode);
        //selecting 1st option
        //Thread.sleep(3000);
        //driver.findElement(By.xpath("//*[@*='search-typeahead-item-0']")).click();
    }


    //-------Has patient been diagnosed with ASCVD and/or HeFH?
    public void clickYESdiagnosed() throws Exception {
        driver.findElement(By.xpath("//*[@id='clinical_patient_diagnosed_yes']")).click();
    }
    public void clickNOTdiagnosed() throws Exception {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@*='clinical_patient_diagnosed' and @value='no']")).click();
    }

    //-------ADMINISTRATION INFORMATION
    public void clickPrescriberAcquiredAtPhysicianOffice() throws Exception {
        driver.findElement(By.xpath("//input[starts-with(@value,'Prescriber Acquired')]")).click();
    }
    public void clickAcquiredAtSpecialtyPharmacy() throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@value,'Prescriber Acquired')]")).click();
    }
    public void clickSpecialtyPharmacy() throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@value,'Specialty Pharmacy')]")).click();
    }
    public void clickAlternateInjectionSite() throws Exception {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@value,'Alternate Injection')]")).click();
    }
    public void clickYesDiagnosedASCVD() throws Exception {
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@*='clinical_patient_diagnosed' and @value='yes']")).click();
    }
    public void clickNotDiagnosedASCVD() throws Exception {
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@*='clinical_patient_diagnosed' and @value='no']")).click();
    }
}
