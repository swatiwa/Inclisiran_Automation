package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.SR.Details;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.testng.Assert;
import java.text.SimpleDateFormat;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegisterPage extends BasePage {

    //--------LOGIN PAGE
    public void clickRegister() throws Exception{
        //waitProcessing(driver.findElement(By.xpath("//span[text()='REGISTER']")),10);
        getScreenShot("Clicking 'REGISTER'");
        waitFor(5);
        driver.findElement(By.xpath("//*[contains(text(),'REGISTER')]")).click();
    }

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //--------WELCOME PAGE
    public void clickRegister2() throws Exception{
        waitFor(2);
        getScreenShot("Clicking 'REGISTER'");
        driver.findElement(By.xpath("//button[contains(text(),'REGISTER')]")).click();
    }

    //--------ORGANIZATION INFORMATION
    public void enterNPI(String orgNPI) throws Exception{
        driver.findElement(By.name("organization_npi")).sendKeys(orgNPI);
    }
    public void enterOrgName(String orgName) throws Exception{
        driver.findElement(By.id("organization_name")).sendKeys(orgName);
    }
    public void enterAddress(String orgAddress) throws Exception{
        driver.findElement(By.id("address_1")).sendKeys(orgAddress);
    }
    public void enterCity(String orgCity) throws Exception{
        driver.findElement(By.id("city")).sendKeys(orgCity);
    }
    public void enterState(String orgState) throws Exception{
        driver.findElement(By.id("state")).sendKeys(orgState);
    }
    public void enterZipCode(String orgZipCode) throws Exception{
        driver.findElement(By.id("zip")).sendKeys(orgZipCode);
    }
    public void enterTaxID(String orgTaxID) throws Exception{ ;
        driver.findElement(By.id("tax_id")).sendKeys(orgTaxID);
    }
    public void enterPhoneNumber(String orgPhNumber) throws Exception{
        driver.findElement(By.id("organization_phone")).sendKeys(orgPhNumber);
    }
    public void enterFaxNumber(String orgFaxNumber) throws Exception{
        driver.findElement(By.id("organization_fax")).sendKeys(orgFaxNumber);
    }
    public void clickNext() throws Exception{
        waitFor(2);
        getScreenShot("Clicking 'Next'");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }



    public void clickSave() throws Exception{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,0)");
        driver.findElement(By.id("saveWithoutBottomMargin")).click();
        waitFor(5);

    }

    public void inputPrescriberNPI(String prescriberNPI) throws Exception{
        waitFor(1);
        //Are you Prescriber? 'Yes'
        driver.findElement(By.xpath("//input[@*='is_prescriber' and @value='yes']")).click();
        waitFor(1);
        driver.findElement(By.xpath("//input[@*='administrator_prescriber_npi']")).sendKeys(prescriberNPI);

    }

    public void clickDown() throws Exception {
        WebDriver driver = new ChromeDriver();
        WebElement text = driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[1]/p[42]"));

        Actions action = new Actions(driver);
        Actions seriesOfActions = action;
        action.click();
        action.keyDown(text, Keys.PAGE_DOWN);
        seriesOfActions.perform();

    }

    public void clickOnArticle1() throws Exception{
        waitFor(2);
        getScreenShot("Terms and Conditions");
        focusElement(driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[1]/p[42]")));
        driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[1]/p[42]")).click();

    }

    public void clickOnArticle2() throws Exception{
        waitFor(2);
        getScreenShot("Business Associate Agreement");
        focusElement(driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[2]/p[36]")));
        driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[2]/p[36]")).click();
    }


    public void scrollDownAnd() throws Exception {
        WebElement pane = driver.findElement(By.xpath("//div[@data-testid='cmx__terms-content']"));

        List<WebElement> endOfPaneCount = driver.findElements(By.xpath("//p[contains(text(), 'Any legal')]"));
        while (endOfPaneCount.size() == 0) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pane);
            JavascriptExecutor jse = (JavascriptExecutor) driver;    //.findElement(By.xpath(""));
            waitFor(2);
            jse.executeScript("window.scrollBy(0,250)", "p[contains(text(), 'Any legal')]");////p[contains(text(), 'Any legal')]
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            endOfPaneCount = driver.findElements(By.xpath("//p[contains(text(), 'Any legal')]"));
        }
    }


    public void scrollDownAgreement1() throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)","//p[contains(text(), 'Any legal')]");
        waitFor(2);

        jse.executeScript("window.scrollBy(0,250)","//p[contains(text(), 'Any legal')]");
        waitFor(2);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);

//        JavascriptExecutor jse = (JavascriptExecutor) driver;    //.findElement(By.xpath(""));
//        waitFor(2);
//        jse.executeScript("window.scrollBy(0,250)","//*[@id='root']/form/div[5]/div[1]/p[42]");
//        waitFor(2);

        //WebElement textbox = driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[2]"));
//        textbox.sendKeys(Keys.ARROW_DOWN);
//        textbox.sendKeys(Keys.PAGE_DOWN);
//        textbox.sendKeys(Keys.ARROW_DOWN);
    }

    public void scrollDownAgreement2() throws Exception{

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)","(//p[contains(text(), 'Bethesda, MD')])[2]");
        waitFor(2);
        jse.executeScript("window.scrollBy(0,250)","(//p[contains(text(), 'Bethesda, MD')])[2]");
        waitFor(2);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);

    }



    public void focusElement(WebElement elmnt){
        Coordinates coordinate = ((Locatable)elmnt).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort().moveBy(0,20);
        coordinate.inViewPort().move(coordinate.inViewPort().getX(),coordinate.inViewPort().getY()+500);
    }

    //--------ADMINISTRATOR INFORMATION
    public void enterAdminFirstName(String adminFname) throws Exception{
        getScreenShot("ADMINISTRATOR INFORMATION");
        waitFor(2);
        driver.findElement(By.id("administrator_first_name")).sendKeys(adminFname);
    }
    public void enterAdminLastName(String adminLname) throws Exception{
        driver.findElement(By.id("administrator_last_name")).sendKeys(adminLname);
    }
    public void enterAdminPhNumber(String adminPhNumber) throws Exception{
        driver.findElement(By.id("administrator_phone")).sendKeys(adminPhNumber);
    }
    public void enterAdminFaxNumber(String adminFaxNumber) throws Exception{
        driver.findElement(By.id("administrator_fax")).sendKeys(adminFaxNumber);
    }
    public void enterAdminEmail(String adminEmail) throws Exception{
        driver.findElement(By.id("administrator_email")).sendKeys(adminEmail);
    }
    public void enterAdminUsername(String adminUsername) throws Exception{
        driver.findElement(By.id("administrator_username")).sendKeys(adminUsername);
    }
    //--------PRESCRIBER INFORMATION
    public void enterPrescriberNPI(String prescriberNPI) throws Exception{
        waitFor(3);
        getScreenShot("PRESCRIBER INFORMATION");
        driver.findElement(By.xpath("//*[@class=\"rbt-input-main form-control rbt-input\"]")).sendKeys(prescriberNPI);
    }
    public void enterPrescriberFname(String prescriberFname) throws Exception{
        driver.findElement(By.name("prescriber_first_name")).sendKeys(prescriberFname);
    }
    public void enterPrescriberLname(String prescriberLname) throws Exception{
        driver.findElement(By.name("prescriber_last_name")).sendKeys(prescriberLname);
    }
    public void enterPrescriberTaxID(String prescriberTaxID) throws Exception{
        waitFor(2);
        driver.findElement(By.name("prescriber_tax_id")).sendKeys(prescriberTaxID);
    }
    public void enterPrescriberStateLicense(String prescriberStateLicense) throws Exception{
        driver.findElement(By.id("prescriber_state_license")).sendKeys(prescriberStateLicense);
    }
    public void enterPrescriberState(String prescriberState) throws Exception{
        driver.findElement(By.name("prescriber_licensing_state")).sendKeys(prescriberState);
    }

    //--------
//    public void scrollDown() throws Exception{
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[1]/p[42]/text()")).click();    //sendKeys(Keys.PAGE_DOWN);
//    }

    public void scrollDown() throws Exception{
        WebElement element = driver.findElement(By.xpath("//*[@id='root']/form/div[5]/div[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //waitFor(4);
    }
    public void clickCheckBox1() throws Exception{
        waitFor(3);
        getScreenShot("Business Acknowledgement agreement");
        driver.findElement(By.id("acknowledge_terms")).click();
    }
    public void clickCheckBox2() throws Exception{
        waitFor(2);
        getScreenShot("Business Acknowledgement agreement");
        driver.findElement(By.xpath("//input[@id='acknowledge_baa']")).click();
    }
    public void clickCheckBox3() throws Exception{
        driver.findElement(By.xpath("//input[@id='acknowledge_language']")).click();
    }
    public void enterSignature(String signature) throws Exception{
        waitFor(1);
        getScreenShot("Signature before Submission");
        driver.findElement(By.xpath("//input[@*='signature']")).sendKeys(signature);
    }

    public void clickSubmit() throws Exception{
        waitFor(2);
        getScreenShot("clicking 'Submit'");
        driver.findElement(By.xpath("//*[contains(text(),'SUBMIT')]")).click();
    }

    public void clickSubmitRequest() throws Exception{
        waitFor(7);
        getScreenShot("clicking 'Submit'");
        driver.findElement(By.xpath("//button[contains(text(),'submit')]")).click();
    }

    public void verifyRegistrationSubmitted() throws Exception {
        waitFor(5);
        String expectedTxt = "Registration Submitted";
        String actualTxt =  driver.findElement(By.xpath("//*[contains(text(),'Registration Submitted')]")).getText();

        getScreenShot("Verify 'Registration has been successfully submitted'");
        Assert.assertEquals(actualTxt.trim(),expectedTxt.trim(),"Successful Registration page not displayed");
        System.out.println("Expected text: '" +expectedTxt +"' | "+ "Actual text: '"+actualTxt+"'");

    }

    public void clickClose() throws Exception{
        waitProcessing(driver.findElement(By.xpath("//button[text()='CLOSE']")),10);
        getScreenShot("Clicking Close button");
        driver.findElement(By.xpath("//button[text()='CLOSE']")).click();
    }

    public void verifyDate() throws Exception{
        getScreenShot("Current date verification");

        SimpleDateFormat systemDate1 = new SimpleDateFormat("MM/dd/yyyy");
        String systemDate = systemDate1.format(new Date());
        String dateOnPage=  driver.findElement(By.xpath("//*[contains(@value,'/20')]")).getAttribute("value");

        System.out.println("Expected date is: '" +systemDate +"' | "+ "Actual date '"+dateOnPage+"'");
        Assert.assertEquals(systemDate,dateOnPage,"Date is not displayed or incorrect");

    }

    public void quitChrome() throws Exception{
        driver.quit();
    }


    //----------------------------------------------------------------
    public void populateOrganizationInfo(String orgNPI, String orgName,
                                         String orgAddress,String orgCity,
                                         String orgState,String orgZipCode,String orgTaxID,
                                         String orgPhNumber, String orgFaxNumber) throws Exception {
        enterNPI(orgNPI);
        enterOrgName(orgName);
        enterAddress(orgAddress);
        enterCity(orgCity);
        enterState(orgState);
        enterZipCode(orgZipCode);
        enterTaxID(orgTaxID);
        enterPhoneNumber(orgPhNumber);
        enterFaxNumber(orgFaxNumber);
    }
    public void populateAdministratorInfo(String adminFname, String adminLname,
                                          String adminPhNumber, String adminFaxNumber,
                                          String adminEmail, String adminUsername) throws Exception {
        enterAdminFirstName(adminFname);
        enterAdminLastName(adminLname);
        enterAdminFaxNumber(adminFaxNumber);
        enterAdminPhNumber(adminPhNumber);
        enterAdminEmail(adminEmail);
        enterAdminUsername(adminUsername);
    }
    public void populatePrescriberInfo(String prescriberNPI, String prescriberFname,
                                       String prescriberLname, String prescriberTaxID,
                                       String prescriberStateLicense, String prescriberState) throws Exception {
        enterPrescriberNPI(prescriberNPI);
        enterPrescriberFname(prescriberFname);
        enterPrescriberLname(prescriberLname);
        enterPrescriberTaxID(prescriberTaxID);
        enterPrescriberStateLicense(prescriberStateLicense);
        enterPrescriberState(prescriberState);
    }
}
