package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;

public class ConnectPages extends BasePage {

    public void launchConnect(String stageEnv, String cmtxUserName, String cmtxUserNamePwd ) throws Exception {

        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        //launching gmail on new tab
        driver.switchTo().window(tabs.get(1));

        //Launch Connect UAT env
        if(stageEnv.equalsIgnoreCase("UAT")){
            driver.get("https://summatest.caremetx.com/");

        //Launch Connect Dev env
        }else if(stageEnv.equalsIgnoreCase("DEV")){
            driver.get("https://summa.dev.caremetx.com/");

        //Launch Connect Training env
        }else if(stageEnv.equalsIgnoreCase("Training")) {
            driver.get("https://summa-training.caremetx.com/");
        }
        //inputting credentials to log in
        Thread.sleep(3000);
        waitProcessing(driver.findElement(By.xpath("//input[@name='loginfmt']")),10);
            driver.findElement(By.xpath("//input[@name='loginfmt']")).sendKeys(cmtxUserName);
        waitProcessing(driver.findElement(By.xpath("//input[@type='submit']")),10);
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitProcessing(driver.findElement(By.xpath("//input[@name='passwd']")),10);
            driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys(cmtxUserNamePwd);
            driver.findElement(By.xpath("//input[@type='submit']")).click();

        waitProcessing(driver.findElement(By.xpath("//div[text()='Stay signed in?']")),10);
        boolean signInQuestion = driver.findElement(By.xpath("//div[text()='Stay signed in?']")).isDisplayed();
        if(signInQuestion){
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        }else{
            System.out.println("'Stay signed in?' dialog window NOT displayed.");
        }

    }

    public void inputCTMXuserName(String cmtxUserName) throws Exception {
        Thread.sleep(3000);
        waitProcessing(driver.findElement(By.xpath("//input[@name='loginfmt']")),10);
            driver.findElement(By.xpath("//input[@name='loginfmt']")).sendKeys(cmtxUserName);
        waitProcessing(driver.findElement(By.xpath("//input[@type='submit']")),10);
            driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void inputCTMXuserNamePwd(String cmtxUserNamePwd) throws Exception {
        //Thread.sleep(1000);
        waitProcessing(driver.findElement(By.xpath("//input[@name='passwd']")),10);
            driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys(cmtxUserNamePwd);
            driver.findElement(By.xpath("//input[@type='submit']")).click();

        waitProcessing(driver.findElement(By.xpath("//div[text()='Stay signed in?']")),10);
        boolean signInQuestion = driver.findElement(By.xpath("//div[text()='Stay signed in?']")).isDisplayed();
        if (signInQuestion){
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        }else{
            System.out.println("'Stay signed in?' dialog window NOT displayed.");
        }
    }

    public void selectUsername() throws Exception{
        //Thread.sleep(2000);
        waitProcessing(driver.findElement(By.xpath("//select[@id='UserName']")),10);
        getScreenShot("Pick a User name");
        driver.findElement(By.xpath("//select[@id='UserName']")).click();
        //change this locator to pick your own test username from the dropdown
        driver.findElement(By.xpath("//option[@value='autotester']")).click();
        //Thread.sleep(500);
        waitProcessing(driver.findElement(By.xpath("//input[@type='submit']")),10);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void selectInclisiran() throws Exception{
        //Thread.sleep(500);
        waitProcessing(driver.findElement(By.xpath("//a[text()='Inclisiran Service Center']")),10);
        driver.findElement(By.xpath("//a[text()='Inclisiran Service Center']")).click();
    }

    public void clickEntityRecordsPatients() throws Exception{
        //Thread.sleep(5000);
        waitProcessing(driver.findElement(By.xpath("//a[text()='Entity Records']")),10);
        driver.findElement(By.xpath("//a[text()='Entity Records']")).click();
        driver.findElement(By.xpath("//a[text()='Patients']")).click();
    }

    public void inputPatientInfo(String fName, String lName, String DOB) throws Exception{
        waitProcessing(driver.findElement(By.xpath("//a[text()='Entity Records']")),10);
        //click Advanced Search
        driver.findElement(By.xpath("//a[@class='slick-slidetoggle']")).click();
        //input patient info
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(fName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lName);
        driver.findElement(By.xpath("//input[@id='DOB']")).sendKeys(DOB);
        //click Search Patient
        driver.findElement(By.xpath("//input[@name='btnsearch']")).click();

    }

    public void clickOnpatient() throws Exception{
        Thread.sleep(500);
        waitProcessing(driver.findElement(By.xpath("//*[@id='grid']/tbody/tr/td[1]/a")),10);
        driver.findElement(By.xpath("//*[@id='grid']/tbody/tr/td[1]/a")).click();
    }

    public void getPatientID() throws Exception{
        waitProcessing(driver.findElement(By.xpath("//h1[@class='padding']")),10);
        driver.findElement(By.xpath("//*[@id='grid']/tbody/tr/td[1]/a")).getText();
    }


    public void verifyPatientInfo(String ptFirstLastName, String ptAddress1, String ptEmail, String ptPhone, String ptDOB) throws Exception{

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        Thread.sleep(3000);
        //waitProcessing(driver.findElement(By.xpath("//*[contains(text(),'Patient Centric View')]")),10);

        String pName  = driver.findElement(By.xpath("//th[@style='text-align:left;background-color: white']")).getText();
        String pAddress = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[3]/div/div[1]/div[1]/div/table/tbody/tr[2]/th")).getText();
        String pEmail = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[3]/div/div[1]/div[1]/div/table/tbody/tr[4]/th")).getText();
        String pDOB = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[3]/div/div[1]/div[1]/div/table/tbody/tr[6]/th[1]")).getText();
        String pPhone = driver.findElement(By.xpath("/html/body/div[2]/div/div/section/div[3]/div/div[1]/div[1]/div/table/tbody/tr[5]/th")).getText();

        if (pName.contains(ptFirstLastName)) {
            System.out.println("Expected 'Patient info' text: '" + pName + "' Displayed, PASS.");
        } else {
            System.out.println("Expected 'Patient info' text: '" + pName + "' Not Displayed, Fail.");
        }
        if (pAddress.contains(ptAddress1))  {
            System.out.println("Expected 'Patient address' text: '" + pAddress + "' Displayed, PASS.");
        } else {
            System.out.println("Expected 'Patient address' text: '" + pAddress + "' Not Displayed, Fail.");
        }
        if (pEmail.contains(ptEmail)) {
            System.out.println("Expected 'Patient email' text: '" + pEmail + "' Displayed, PASS.");
        } else {
            System.out.println("Expected 'Patient email' text: '" + pEmail + "' Not Displayed, Fail.");
        }
        if (pDOB.contains(ptDOB))  {
            System.out.println("Expected 'Patient DOB' text: '" + pDOB + "' Displayed, PASS.");
        } else {
            System.out.println("Expected 'Patient DOB' text: '" + pDOB + "' Not Displayed, Fail.");
        }
        if (pPhone.contains(ptPhone)) {
            System.out.println("Expected 'Patient phone number' text: '" + pPhone + "' Displayed, PASS.");
        } else {
            System.out.println("Expected 'Patient phone number' text: '" + pPhone + "' Not Displayed, Fail.");
        }
    }

    public void closeBrowserAndChrome() throws Exception {
        Thread.sleep(3000);
        System.out.println("Testing completed");
        //driver.switchTo().window(tabs.get(1)).close();
        driver.close();
        driver.quit();

    }

}
