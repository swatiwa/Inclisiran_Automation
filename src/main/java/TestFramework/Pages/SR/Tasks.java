package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Tasks extends BasePage {

    public Tasks(){
        waitProcessing(driver.findElement(By.id("SRTask_TickleDueDate")));
    }

    public void setTaskDueDate(String date){
        driver.findElement(By.id("SRTask_TickleDueDate")).sendKeys(date);
    }

    public void selectAssignToGroup(String groupName){
        Select select = new Select(driver.findElement(By.id("SRTask_UserGroupID")));
        select.selectByVisibleText(groupName);
    }
    public String taskPriorityErrorMsg() throws Exception{
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/fieldset/form/div[2]/div[4]/span/span")).getText();
    }
    public String taskDescriptionErrorMsg() throws Exception{
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/fieldset/form/div[2]/div[2]/span/span")).getText();
    }
    public String taskDueDateErrorMsg() throws Exception{
        return driver.findElement(By.xpath("/html/body/div[2]/div/div/section/fieldset/form/div[1]/div[2]/span/span")).getText();
    }

    public void selectAssignTo(String assignTo){
        Select select = new Select(driver.findElement(By.id("SRTask_TickleAssignment")));
        select.selectByVisibleText(assignTo);
    }

    public void selectAssignTo(int i){
        Select select = new Select(driver.findElement(By.id("SRTask_TickleAssignment")));
        select.selectByIndex(i);
    }

    public void selectTaskDescription(int index){
        Select select = new Select(driver.findElement(By.id("SRTask_SR_ActivityListID")));
        select.selectByIndex(index);
    }

    public void selectTaskDescription(String index){
        Select select = new Select(driver.findElement(By.id("SRTask_SR_ActivityListID")));
        select.selectByVisibleText(index);
    }

    public void selectTaskPriority(String priority){
        Select select = new Select(driver.findElement(By.id("SRTask_Priority")));
        select.selectByVisibleText(priority);
    }

    public void clickSave(){
        driver.findElement(By.xpath("/html/body/div[2]/div/div/section/fieldset/form/div[3]/input")).click();
    }
}
