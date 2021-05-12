package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class MessagePage extends BasePage {

    public void clickMessageIcon() throws Exception{
        Thread.sleep(2000);
        getScreenShot("clicking Message icon");
        driver.findElement(By.xpath("//a[@class='cmx__nav-msg-btn_link nav-link']")).click();
    }

    public void clickComposeMsg() throws Exception{
        Thread.sleep(2000);
        getScreenShot("clicking compose message");
        driver.findElement(By.xpath("//button[text()='COMPOSE MESSAGE']")).click();
    }

    public void enterLastNameNselect(String lName) throws Exception{
        Thread.sleep(2000);
        getScreenShot("entering patient last name");
        driver.findElement(By.xpath("//input[@class='rbt-input-hint']")).sendKeys(lName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@class='dropdown-item active']")).click();
    }

    public void selectMsgSubject() throws Exception{
        Thread.sleep(2000);
        getScreenShot("selecting Subject of the Message");
        driver.findElement(By.xpath("//select[@class='form-control is-invalid']")).click();
        driver.findElement(By.xpath("select some option")).click();
    }

    public void clickAttach() throws Exception{
        driver.findElement(By.xpath("//button[text()='ATTACH']")).click();
    }

    public void typeMessage(String textMsg) throws Exception{
        getScreenShot("Type given message into the text field");
        driver.findElement(By.name("message")).sendKeys(textMsg);
    }

    public void clickSend() throws Exception{
        getScreenShot("Clicking send Message");
        driver.findElement(By.xpath("//button[text()='SEND']")).click();
    }

    public void clickPatientWread() throws Exception{
        Thread.sleep(2000);
        getScreenShot("Clicking Patients with Read");
        driver.findElement(By.xpath("//input[@value='read']")).click();
    }

    public void clickPatientWunread() throws Exception{
        Thread.sleep(2000);
        getScreenShot("Clicking Patients with UnRead");
        driver.findElement(By.xpath("//input[@value='unread']")).click();
    }

    //CLick View

    //Click Reply

}
