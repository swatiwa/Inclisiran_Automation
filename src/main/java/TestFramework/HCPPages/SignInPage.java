package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class SignInPage extends BasePage {

    public HomePage enterCredentials(String userName, String pass) throws Exception{


        //add condition to element displ or not
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(pass);
        getScreenShot("Signing in");
        driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
        Thread.sleep(3000);
        return new HomePage();
    }
    public void clickForgotPasswordLink() throws Exception{
        driver.findElement(By.xpath("//span[text()='Forgot Your Password?']")).click();
    }
}
