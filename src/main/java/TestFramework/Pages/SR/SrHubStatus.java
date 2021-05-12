package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SrHubStatus extends BasePage {

    public void setStatus(String status) throws Exception{
        Select select = new Select(driver.findElement(By.id("SRHubStatusListID")));
        select.selectByVisibleText(status);
        Thread.sleep(2000);
    }

    public void setSttusDate(String date) throws Exception{
        driver.findElement(By.id("SRStatusDate")).sendKeys(date);
        Thread.sleep(2000);
    }

    public void clickSave() throws Exception{
        driver.findElement(By.id("save")).click();
        Thread.sleep(5000);
    }

    public String getStatusErrorMsg() throws Exception{
       return driver.findElement(By.xpath("//*[@id=\"frmhubstatus\"]/div/fieldset/div[2]/span/span")).getText();
    }

    public String getStatusDateerrorMsg(){
        return driver.findElement(By.xpath("//*[@id=\"frmhubstatus\"]/div/fieldset/div[4]/span/span")).getText();
    }
}
