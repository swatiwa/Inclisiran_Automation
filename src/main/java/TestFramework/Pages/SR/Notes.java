package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class Notes extends BasePage {

    public void clickAddNew() throws Exception{
        driver.findElement(By.linkText("Create New Note")).click();
        Thread.sleep(3000);
    }

    public void setSubject(String subject) throws Exception{
        driver.findElement(By.id("Subject")).sendKeys(subject);
        Thread.sleep(2000);
    }

    public void setNoteContent(String note) throws Exception{
        driver.findElement(By.id("NoteText")).sendKeys(note);
        Thread.sleep(2000);
    }

    public void clickSave() throws Exception{
        driver.findElement(By.id("save")).click();
        Thread.sleep(5000);


    }
}
