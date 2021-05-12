package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.CopayPractice.CopayManagement;
import TestFramework.Pages.Patient.PatientStatus;
import org.openqa.selenium.By;

import java.sql.Struct;

public class Details extends BasePage {

    public Details(){
        try{
            waitProcessing(driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/button")));
        }catch (Exception e){}

    }

    public void acceptAlert(){
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/button")).click();
    }

    public String getContactName(){
        String contactName =  driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[6]/fieldset/div[2]")).getText();
        System.out.println("Contact Name "+contactName);
        return contactName;

    }

    public CopayManagement clickCopayManagement() throws Exception{
        driver.findElement(By.linkText("Copay Management")).click();
        return new CopayManagement();
    }

    public String getPatientName(){
        String patientName = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[6]/fieldset/div[6]")).getText();
        System.out.println("Patient Name "+ patientName);
        return patientName;
    }

    public String getProviderName(){
        String providerName = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[6]/fieldset/div[13]")).getText();
        System.out.println("Provider Name "+providerName);
        return providerName;
    }

    public String getSRStatus(){
        String srStatus = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[7]/fieldset[1]/div[14]")).getText();
        System.out.println("Sr Stauts "+srStatus);
        return srStatus;
    }

    public Tasks clickTasks() throws Exception{
        driver.findElement(By.xpath("//*[@id=\"srNavMenu\"]/div[1]/ul/li[1]/ul/li[6]/a")).click();
        Thread.sleep(5000);
        return new Tasks();
    }

    public ActivitiesCallLogs clickActivitiesCallLogs() throws Exception{
        driver.findElement(By.linkText("Activities & Call Logs")).click();
        Thread.sleep(8000);
        return new ActivitiesCallLogs();
    }

    public ProcessDates clickProcessDates() throws Exception{
        driver.findElement(By.linkText("Process Dates")).click();
        Thread.sleep(8000);
        return new ProcessDates();
    }

    public Notes clickNotes() throws Exception{
        driver.findElement(By.linkText("Notes")).click();
        Thread.sleep(8000);
        return new Notes();
    }

    public void clickPatient() throws Exception{
        driver.findElement(By.xpath("//*[@id='myAccordion']/h3[2]/a/strong")).click();
        Thread.sleep(1000);
    }

    public PatientStatus clickPatientStatus() throws Exception{
        driver.findElement(By.linkText("Patient Status")).click();
        Thread.sleep(5000);
        return new PatientStatus();
    }

    public SrHubStatus clickSRHubStatus() throws Exception{
        driver.findElement(By.linkText("SR Hub Status")).click();
        Thread.sleep(5000);
        return new SrHubStatus();
    }

    public KeyElements clickKeyElements() throws Exception{
        driver.findElement(By.linkText("Key Elements")).click();
        Thread.sleep(5000);
        return new KeyElements();
    }



}
