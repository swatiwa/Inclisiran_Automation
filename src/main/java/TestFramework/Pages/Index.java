package TestFramework.Pages;

import TestFramework.Pages.Home.Portlet;
import org.openqa.selenium.By;

public class Index extends BasePage{

    public Portlet selectPathwaysProgramList(){
        waitProcessing(driver.findElement(By.xpath("//*[@id=\"main\"]/div/fieldset/ul/li[2]/span/a")));
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/fieldset/ul/li[2]/span/a")).click();
        return new Portlet();
    }

    public Portlet selectLonsurf(){
        waitProcessing(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/fieldset/ul/li[1]/span/a")));
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/fieldset/ul/li[1]/span/a")).click();
        return new Portlet();
    }
    public Portlet selectVerrica(){
        waitProcessing(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/fieldset/ul/li[3]/span/a")));
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/fieldset/ul/li[3]/span/a")).click();
        return new Portlet();
    }

    public Portlet selectInclisiran(){
        //waitProcessing(driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/fieldset/ul/li[3]/span/a")));
        //driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/fieldset/ul/li[1]/span/a")).click();
        waitProcessing(driver.findElement(By.xpath("//a[text()='Inclisiran Service Center']")));
        driver.findElement(By.xpath("//a[text()='Inclisiran Service Center']")).click();
        return new Portlet();
    }


}
