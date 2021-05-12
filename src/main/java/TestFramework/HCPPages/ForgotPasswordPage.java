package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ForgotPasswordPage extends BasePage {

    public void clickForgotPasswordLink() throws Exception{
        driver.findElement(By.linkText("Forgot Your Password?")).click();
    }
    public void clickRegisterLink() throws Exception{
        driver.findElement(By.xpath("//*[@id=\"cache1\"]")).click();
    }
    public Boolean isDontHaveAnAccountTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id='root']/div[3]/div/div/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase("Don't have an account?");

    }
    public Boolean isForgotYourPasswordTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"u612_text\"]/p/span")).getText();
        return element.equalsIgnoreCase("Forgot Your Password?");

    }
    public Boolean isPasswordResetTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/div/h6")).getText();
        return element.equalsIgnoreCase("Enter your Username to begin resetting your password.");

    }
    public void enterUsername(String userName) throws Exception {
        driver.findElement(By.id("username")).sendKeys(userName);
    }

    public void clickSetMyPassword() throws Exception{
        driver.findElement(By.xpath("//button[text()='SET MY NEW PASSWORD']")).click();
    }

    public void clickResetMyPassword() throws Exception{
        driver.findElement(By.xpath("//button[text()='RESET MY PASSWORD']")).click();
    }

    public Boolean isUsernameRequiredTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[1]/div/div")).getText();
        return element.equalsIgnoreCase("Username is a required field");

    }
    public Boolean isUsernameNotPresentTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase(" We do not recognize your entry. Please try again.");
    }

    public Boolean isResetYourPasswordTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase("Reset Your Password");

    }
    public Boolean isEnterYourPasswordTextPresent() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id=\"root\"]/div[3]/div/div/div[2]/div/form/div[2]/div/p/text()")).getText();
        return element.equalsIgnoreCase("Enter and confirm your new password");
    }

    public Boolean isUsernameNotPresentTextPresent2() throws Exception {
        String element= driver.findElement(By.xpath("//*[@id='root']/div[3]/div/div/div[2]/div/div[2]/div/h2/text()")).getText();
        return element.equalsIgnoreCase(" SUCCESS");
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


}
