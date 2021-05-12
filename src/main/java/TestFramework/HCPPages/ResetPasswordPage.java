package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class ResetPasswordPage extends BasePage {
    public Boolean isResetYourPasswordTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase("Reset Your Password");
    }

    public Boolean isEnterYourPasswordTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase("Enter and confirm your new password");
    }

    public Boolean isSetMyNewPasswordTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase("Enter and confirm your new password");
    }

    public void resetPassword(String password, String confirmPass) throws Exception{
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("password")).sendKeys(confirmPass);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[3]/button")).click();
    }
}
