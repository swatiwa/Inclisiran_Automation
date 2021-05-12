package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    public void clickCreateNewPatient() throws Exception{
        waitFor(6);
        driver.findElement(By.xpath("//a[starts-with(text(), 'P') and contains(text(), 'Patient')]")).click();
        waitFor(3);
            //using contains() and starts-with() because locators differ in Dev vs UAT
        driver.findElement(By.xpath("//*[starts-with(text(), 'Add') and contains(text(), 'Patient')]")).click();
        waitFor(2);
    }

    public void clickStartNewRequest() throws Exception{
        driver.findElement(By.xpath("//button[text()='START A NEW REQUEST']")).click();
        waitFor(2);
    }

    public void clickRecources() throws Exception{
        driver.findElement(By.xpath("//a[text()='Resources']")).click();
        waitFor(2);
    }

    public void enterPatientIntoSearchInput(String patientName) throws Exception {
        waitFor(3);
        driver.findElement(By.xpath("//input[@placeholder='Patient Last Name or DOB']")).sendKeys(patientName);
        waitFor(3);
        driver.findElement(By.xpath("//*[@id='search-typeahead-item-0']")).click();
    }


    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
