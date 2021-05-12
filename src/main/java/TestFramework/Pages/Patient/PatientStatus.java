package TestFramework.Pages.Patient;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PatientStatus extends BasePage {

    public void setTherapyDrug(String drug) throws Exception{
        Select select = new Select(driver.findElement(By.id("DrugName")));
        select.selectByVisibleText(drug);
        Thread.sleep(2000);
    }

    public void setStatus(String status) throws Exception{
        Select select = new Select(driver.findElement(By.id("PatientStatusListID")));
        select.selectByVisibleText(status);
        Thread.sleep(2000);
    }

    public void setStatusDAte(String date) throws Exception{
        driver.findElement(By.id("PatStatusDte")).sendKeys(date);
        Thread.sleep(2000);
    }

    public void clickSave() throws Exception{
        driver.findElement(By.id("erase")).click();
        Thread.sleep(5000);
    }

    public String getTherayDrugErrorMsg(){
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/form/fieldset/div[2]/span/span")).getText();
    }

    public String getStatusErrorMsg(){
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/form/fieldset/div[4]/span/span")).getText();
    }

    public String getStatusDateErrorMsg(){
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/form/fieldset/div[6]/span/span")).getText();
    }
}
