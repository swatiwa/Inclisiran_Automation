package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

public class Facility extends BasePage {
    public Facility(){
        waitProcessing(driver.findElement(By.id("popsave")));
    }

    public void setOrganisationName(String name) throws Exception{
        driver.findElement(By.id("OrganizationName")).sendKeys(name);
    }

    public void setOrganizationType(String type) throws Exception{
        Select select = new Select(driver.findElement(By.id("OrganizationType")));
        select.selectByVisibleText(type); //Facility
    }

    public void clickSave() throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.getElementById(\"popsave\").click()");
        Thread.sleep(5000);
    }

    public void clickContinueSave() throws Exception{
        Thread.sleep(5000);
        waitProcessing(driver.findElement(By.id("POPContinueSave")));
        driver.findElement(By.id("POPContinueSave")).click();
        Thread.sleep(6000);
    }
}
