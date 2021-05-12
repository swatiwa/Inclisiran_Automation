package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class ProcessDates extends BasePage {

    public void setDateOfNthRow(int i, String date) throws Exception{
        driver.findElement(By.id("SRDates["+String.valueOf(i-1)+"]")).sendKeys(date);
        Thread.sleep(2000);
    }

    public void clickSave() throws Exception{
        driver.findElement(By.id("save")).click();
        Thread.sleep(5000);
    }
}
