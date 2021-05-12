package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActivitiesCallLogs extends BasePage {

    public boolean isActivity(String activity){
        //List<String> activitiesAvailable = null;
        List<WebElement> activities = driver.findElements(By.className("editor-fieldWB"));
        for (WebElement temp: activities) {
            //activitiesAvailable.add(temp.getText());
            System.out.println(temp.getText());
            if(temp.getText().contains(activity)){
                return true;
            }
        }
        return false;
    }

    public void checkNthActivity(String i) throws Exception{
        driver.findElement(By.id("ActivityList_"+i+"__isChecked")).click();
        Thread.sleep(2000);
    }

    public String activityDateErrorMsg() throws Exception{
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/form/div[1]/div[2]/span/span")).getText();
    }
    public void subject(String subject) throws Exception{
        Thread.sleep(2000);
        driver.findElement(By.id("NoteSubject")).sendKeys(subject);
    }

    public void note(String note) throws Exception{
        Thread.sleep(2000);
        driver.findElement(By.id("NoteText")).sendKeys(note);
    }

    public void setDate() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = dateFormat.format(new Date()).toString();
        driver.findElement(By.id("txtName")).sendKeys(formattedDate);
    }

    public void clickSave() throws Exception{
        driver.findElement(By.id("save")).click();
        Thread.sleep(5000);
    }

}
