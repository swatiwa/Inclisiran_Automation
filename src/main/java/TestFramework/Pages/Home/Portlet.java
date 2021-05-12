package TestFramework.Pages.Home;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.Reports.Reports;
import TestFramework.Pages.SR.Create;
import org.openqa.selenium.By;

public class Portlet extends BasePage {

    public void ignoreTimeZoneWarning(){
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[2]")).click();
    }

    public Create addNewServiceRequest(String programID){
        driver.get("https://summa.dev.caremetx.com/SR/SR/Create?ProgramID="+programID); //127 //125
        return new Create();
    }

    public Reports openReports(String programID) throws Exception {
        driver.get("https://summatest.caremetx.com/Reporting?programID=" + programID);
        Thread.sleep(5000);
        return new Reports();
    }

    public Create createContact(String programID) throws Exception{
        driver.get("https://summa.dev.caremetx.com/Contact/Contact/Create?programID="+programID);
        Thread.sleep(5000);
        return new Create();
    }


}
