package TestFramework.Pages.Reports;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Reports extends BasePage {

    public void selectReport(String reportValue) throws Exception{
        Select select = new Select(driver.findElement(By.id("ReportName")));
        select.selectByVisibleText(reportValue);
        Thread.sleep(4000);
    }

    public void setStartDate(String date) throws Exception{
        Thread.sleep(2000);
        driver.findElement(By.id("startDate")).sendKeys(date);
    }

    public void setEndDate(String date) throws Exception{
        Thread.sleep(2000);
        driver.findElement(By.id("endDate")).sendKeys(date);
    }

    public void clickRunButton() throws Exception{
        driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(12000);
    }
}
