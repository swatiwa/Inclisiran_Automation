package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class KeyElements extends BasePage {

    public void clickClosedRadioButton() throws Exception {
        driver.findElement(By.id("Closed")).click();
        Thread.sleep(2000);
    }

    public void clickSaveButton() throws Exception {
        driver.findElement(By.id("save")).click();
        Thread.sleep(2000);
    }

    public void selectResolution(String resolution) throws Exception{
        Select select = new Select(driver.findElement(By.id("Resolution")));
        select.selectByVisibleText(resolution);
        Thread.sleep(2000);
    }

    public String resolutionErrorMessage() throws Exception{
       return driver.findElement(By.xpath("//*[@id=\"closedDate\"]/div[2]/span/span")).getText();
    }


}
