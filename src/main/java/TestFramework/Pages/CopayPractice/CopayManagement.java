package TestFramework.Pages.CopayPractice;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class CopayManagement extends BasePage {

    public CopayManagement() throws Exception{
        Thread.sleep(5000);
        waitProcessing(driver.findElement(By.id("ListDynamicDataAndValue_0__FieldValue")));
    }

    public void checkCommercialInsurance() throws Exception{

        Thread.sleep(3000);
        driver.findElement(By.id("ListDynamicDataAndValue_0__FieldValue")).click();
    }

    public void checkPatientConsent()throws Exception{

        Thread.sleep(3000);
        driver.findElement(By.id("ListDynamicDataAndValue_1__FieldValue")).click();
    }

    public void checkPatientDiagnosis()throws Exception{

        Thread.sleep(3000);
        driver.findElement(By.id("ListDynamicDataAndValue_2__FieldValue")).click();
    }

    public void clickApprove()throws Exception{

        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/section/form/div[1]/fieldset/div[1]/input")).click();
        Thread.sleep(5000);
    }
}
