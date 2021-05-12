package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class ResetPasswordLinkExpiredPage extends BasePage {
    public Boolean isUsernameNotPresentTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase(" We do not recognize your entry. Please try again.");

    }
    public Boolean isLinkSentTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/div[3]")).getText();
        return element.equalsIgnoreCase("We sent a link to your email address.\n" +
                "\n" +
                "Please note that the link will expire in 24 hours.\n" +
                "\n" +
                "If you do not see the reset password link in your inbox, check your spam folder for emails coming from Caremetx System Messages.\n" +
                "\n" +
                "If you need to send the a link again, click HERE.");

    }
    public void enterUsername(String userName) throws Exception {
        getScreenShot("Entering username");
        driver.findElement(By.id("username")).sendKeys(userName);
    }
    public void clickResetMyPasswordLink() throws Exception{
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[3]/button")).click();
    }
}
