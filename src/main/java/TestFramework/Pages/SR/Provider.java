package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Provider extends BasePage {

    public Provider(){
        waitProcessing(driver.findElement(By.id("popsave")));

    }

    public void setFirstName(String firstName) throws Exception{
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
    }

    public void setLastName(String lastName) throws Exception{
        driver.findElement(By.id("LastName")).sendKeys(lastName);
    }

    public void setNPI(String npi) throws Exception{
        driver.findElement(By.id("NPI")).sendKeys(npi);
        Thread.sleep(1000);
    }

    public void clickNPILookup() throws Exception{
        driver.findElement(By.id("LookupNPI")).click();
        Thread.sleep(3000);
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
