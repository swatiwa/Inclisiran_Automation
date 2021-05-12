package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class PatientManagementPage extends BasePage {

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickPatientManagement() throws Exception{
        waitFor(2);
        getScreenShot("clicking Patient Management under Patients tab");
        driver.findElement(By.xpath("//*[@*='patient-dropdown']")).click();
        waitFor(1);
        driver.findElement(By.xpath("//*[contains(text(),'Patient Management')]")).click();
    }
    //-------
    public void clickStartNewRequestFromPatientMngmntPage() throws Exception{
        waitFor(2);
        getScreenShot("clicking Start New request within Patient Management page");
        driver.findElement(By.xpath("//button[text()='START NEW REQUEST']")).click();
    }

    //-------
    public void clickPatientsWithActions() throws Exception{
        waitFor(1);
        getScreenShot("clicking Patient with Actions button");
        driver.findElement(By.xpath("//label[text()='PATIENTS WITH ACTIONS']")).click();
    }

    public void clickPatientsWithUpdates() throws Exception{
        waitFor(1);
        getScreenShot("clicking Patients with Updates button");
        driver.findElement(By.xpath("//label[text()='PATIENTS WITH UPDATES']")).click();
    }

    //-------Filtering
    public void selectLastNameFilterNSearch(String patName) throws Exception{
        waitFor(1);
        getScreenShot("Selecting the Patient we found after filtering");
        driver.findElement(By.xpath("//span[@class='ag-icon ag-icon-menu']")).click();
        waitFor(1);
        driver.findElement(By.xpath("//*[@id='ag-39-input']")).sendKeys(patName);
    }

    public void clickActions() throws Exception{
        Thread.sleep(2000);
        getScreenShot("clicking Actions: button");
        waitProcessing(driver.findElement(By.xpath("//div[@class='text-center text-primary']")),10);
        driver.findElement(By.xpath("//div[@class='text-center text-primary']")).click();
    }

    public void clickViewDocument() throws Exception{
        Thread.sleep(2000);
        getScreenShot("clicking View button under Documents tab");
        waitProcessing(driver.findElement(By.xpath("//a[@class='p-0 nav-link']")),10);
        driver.findElement(By.xpath("//a[@class='p-0 nav-link']")).click();
    }

    public void clickClose() throws Exception{
        Thread.sleep(4000);
        getScreenShot("clicking Close");
        driver.findElement(By.xpath("//button[text()='Close']")).click();
    }

    public void clickPatientProfile() throws Exception{
        Thread.sleep(2000);
        getScreenShot("clicking Patient Profile");
        waitProcessing(driver.findElement(By.xpath("//a[text()='Patient Profile']")),10);
        driver.findElement(By.xpath("//a[text()='Patient Profile']")).click();
    }
    public void clickStartService() throws Exception{
        getScreenShot("clicking Start Service");
        waitProcessing(driver.findElement(By.xpath("//a[text()='Start a Service']")),10);
        driver.findElement(By.xpath("//a[text()='Start a Service']")).click();
    }
    public void clickSendMessage() throws Exception{
        waitProcessing(driver.findElement(By.xpath("//a[text()='Send a Message']")),10);
        driver.findElement(By.xpath("//a[text()='Send a Message']")).click();
    }
    public void clickMoreActions() throws Exception{
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//*[@col-id='2']")).click();
        driver.findElement(By.xpath("//*[@class='ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value ag-cell-focus ag-cell-popup-editing']")).click();
    }

    //-------
    public void clickAlerts() throws Exception{
        Thread.sleep(2000);
        waitProcessing(driver.findElement(By.xpath("//a[text()='ALERTS']")),10);
        driver.findElement(By.xpath("//a[text()='ALERTS']")).click();
    }
    public void clickRequestHistory() throws Exception{
        Thread.sleep(2000);
        waitProcessing(driver.findElement(By.xpath("//a[text()='REQUEST HISTORY']")),10);
        driver.findElement(By.xpath("//a[text()='REQUEST HISTORY']")).click();
    }
    public void clickDetails() throws Exception{
        Thread.sleep(2000);
        waitProcessing(driver.findElement(By.xpath("//a[text()='DETAILS']")),10);
        driver.findElement(By.xpath("//a[text()='DETAILS']")).click();
    }
    public void clickDocuments() throws Exception{
        Thread.sleep(2000);
        waitProcessing(driver.findElement(By.xpath("//a[text()='DOCUMENTS']")),10);
        driver.findElement(By.xpath("//a[text()='DOCUMENTS']")).click();
    }




}
