package TestFramework.Pages.SAML;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.Index;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class AssertionConsumerService extends BasePage {

    public void selectAccountInformation(String account){
        waitProcessing(driver.findElement(By.id("UserName")));
        Select accSelect = new Select(driver.findElement(By.id("UserName")));
        accSelect.selectByValue(account);
    }

    public Index clickSubmit(){
        driver.findElement(By.xpath("//*[@id=\"MultipleUsersSSOForm\"]/fieldset/div[2]/input")).click();
        return new Index();
    }
}
