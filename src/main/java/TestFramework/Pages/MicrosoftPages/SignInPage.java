package TestFramework.Pages.MicrosoftPages;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.SAML.AssertionConsumerService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SignInPage extends BasePage {

    public SignInPage(){
        driver.manage().window().maximize();
        driver.get("https://summa.dev.caremetx.com/");
    }

    public void enterMailId(String mailId) throws Exception{
        //Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//input[@name='loginfmt']"));
        waitProcessing(element);
        element.sendKeys(mailId);
    }

    public void clickNext() throws Exception{
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void clickSignIn() throws Exception{

        //waitStalenessFor(driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")));
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void enterPassword(String password) throws Exception{
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//input[@name='passwd']"));
        element.sendKeys(password);
    }

    public AssertionConsumerService clickYes() throws Exception{
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//input[@type='submit']"));
        //waitStalenessFor(element);
        element.click();
        return new AssertionConsumerService();
    }



}
