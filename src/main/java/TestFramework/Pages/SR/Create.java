package TestFramework.Pages.SR;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Create extends BasePage {

    public Create(){
//        waitProcessing(driver.findElement(By.id("saveWithoutBottomMargin")));
    }

    public void selectSRSourceNew(String srSource) throws Exception{
        Select select = new Select(driver.findElement(By.id("ListDynamicDataAndValue_0__FieldValue")));
        select.selectByVisibleText(srSource);
        Thread.sleep(3000);
    }

    public void selectTherapyDrug(String drug){
        driver.findElement(By.xpath("//input[@data-label='"+drug+"']")).click();
    }

    public void selectSRtype(String srType) throws Exception{
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(By.id("SR_TypeFullDesc")));
        select.selectByVisibleText(srType);
        Thread.sleep(3000);
    }
    public void selectSRSource(String srSource) throws Exception{
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(By.id("ListDynamicDataAndValue_2__FieldValue")));
        select.selectByVisibleText(srSource);
        Thread.sleep(3000);
    }
    public void selectSRMedium(String srMedium) throws Exception{
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(By.id("ListDynamicDataAndValue_4__FieldValue")));
        select.selectByVisibleText(srMedium);
        Thread.sleep(3000);
    }
    public WebElement isPatientContactOfSR(){
        return driver.findElement(By.id("isPatientContactOfSR"));
    }
    public WebElement isProviderContactOfSR(){
        return driver.findElement(By.id("isProviderContactOfSR"));
    }

    public Details clickSave() throws Exception{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,0)");
        driver.findElement(By.id("saveWithoutBottomMargin")).click();
        Thread.sleep(5000);
        return new Details();
    }

    public void setGroupOne(String groupValue) throws Exception{
        Select select = new Select(driver.findElement(By.id("UserGroupID1")));
        select.selectByVisibleText(groupValue);
    }

    public void setAssignmentType(String type) throws Exception{
        Select select = new Select(driver.findElement(By.id("AssignmentType1")));
        select.selectByVisibleText(type);
    }

    public void selectSRtypeNthCheckBox(int i){
        //for clicking on first check box in SR Type i = 1, for second checkbox i = 2....
        List<WebElement> elements = driver.findElements(By.name("CheckboxResults"));
        if(elements.size() >= i && i > 0){
            elements.get(i-1).click();
        }
    }

    public void srfComplete(){
        driver.findElement(By.id("MissInfoDataAndValues_0__FieldValue")).click();

    }


    public Contact createNewContact() throws Exception{
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"contactDiv\"]/table/tbody/tr/td[3]/a")));
        driver.findElement(By.xpath("//*[@id=\"contactDiv\"]/table/tbody/tr/td[3]/a")).click();
        return new Contact();
    }

    public Contact createContactObj() throws Exception{
        return new Contact();
    }

    public Patient createNewPatient() throws Exception{
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"patientDiv\"]/table/tbody/tr/td[3]/a")));
        driver.findElement(By.xpath("//*[@id=\"patientDiv\"]/table/tbody/tr/td[3]/a")).click();
        Thread.sleep(5000);
        return new Patient();
    }

    public Provider createNewProvider() throws Exception{
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"providerDiv\"]/table/tbody/tr/td[3]/a")));
        driver.findElement(By.xpath("//*[@id=\"providerDiv\"]/table/tbody/tr/td[3]/a")).click();
        Thread.sleep(5000);
        return new Provider();
    }

    public Facility createNewFacility() throws Exception{
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"organizationsDiv\"]/table/tbody/tr/td[3]/a")));
        driver.findElement(By.xpath("//*[@id=\"organizationsDiv\"]/table/tbody/tr/td[3]/a")).click();
        Thread.sleep(5000);
        return new Facility();
    }



    public class Contact{
        public Contact() throws Exception{
            //driver.findElement()
            Thread.sleep(5000);
            waitProcessing(driver.findElement(By.id("FirstName")));
        }

        public void setFirstName(String firstName){
            driver.findElement(By.id("FirstName")).sendKeys(firstName);
        }

        public void setLastName(String lastName){
            driver.findElement(By.id("LastName")).sendKeys(lastName);
        }

        public void ContactType(String value){
            Select select = new Select(driver.findElement(By.id("ContactType")));
            select.selectByVisibleText(value);
        }
        public void setContactAddress(String firstLine, String zip, String city, String state){
            driver.findElement(By.id("Address1")).sendKeys(firstLine);
            driver.findElement(By.id("ZIP")).sendKeys(zip);
            setAdressType("Home");
            //driver.findElement(By.id("City")).sendKeys(city);
            selectState(state);
        }
        public void selectState(String state){
            Select stateDropDown = new Select(driver.findElement(By.id("State")));
            stateDropDown.selectByVisibleText(state);
        }

        public void setAdressType(String type){
            Select stateDropDown = new Select(driver.findElement(By.id("AddressType")));
            stateDropDown.selectByVisibleText(type);
        }

        public Create clickSave(){
            driver.findElement(By.id("popsave")).click();
            return new Create();
        }

        public void pageClickSave() throws Exception{
            driver.findElement(By.id("save")).click();
            Thread.sleep(5000);

        }

        public void clickContinueSave() throws Exception{
            Thread.sleep(5000);
            //waitProcessing(driver.findElement(By.id("POPContinueSave")));
            try{
                driver.findElement(By.id("POPContinueSave")).click();
                Thread.sleep(6000);
            }catch (Exception e){}

        }

        public void pageContinueSave() throws Exception{
            Thread.sleep(5000);
            //waitProcessing(driver.findElement(By.id("POPContinueSave")));
            try{
                driver.findElement(By.id("ContinueSave")).click();
                Thread.sleep(6000);
            }catch (Exception e){}

        }
    }

    public class Patient{
        public Patient(){
            waitProcessing(driver.findElement(By.id("LastName")));
        }

        public void setFirstName(String firstName){
            driver.findElement(By.id("FirstName")).sendKeys(firstName);
        }

        public void setLastName(String lastName){
            driver.findElement(By.id("LastName")).sendKeys(lastName);
        }

        public Create clickSave(){
            driver.findElement(By.id("popsave")).click();
            return new Create();
        }

        public void clickContinueSave() throws Exception{
            Thread.sleep(5000);
            //waitProcessing(driver.findElement(By.id("POPContinueSave")));
            try{
                driver.findElement(By.id("POPContinueSave")).click();
                Thread.sleep(6000);
            }catch (Exception e){}

        }
    }

}
