package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PrescriberPage extends BasePage {

    //--------SELECT OR ADD A PRESCRIBER
    public void selectPrescriber(String prescriberName) throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("(//input[@*='rbt-input-main form-control rbt-input border-left-0'])[1]")).sendKeys(prescriberName);
        waitFor(2);
        driver.findElement(By.xpath("//*[@*='search-typeahead-item-0']")).click();
    }
    public void clickAddPrescriber() throws Exception {
        waitFor(3);
        driver.findElement(By.xpath("//button[text()='ADD A PRESCRIBER']")).click();
    }

    //--------PRESCRIBER INFORMATION
    public void enterPrescriberNPI(String prescriberNPI) throws Exception{
        waitFor(2);
        driver.findElement(By.xpath("//*[@*='prescriber_npi']")).sendKeys(prescriberNPI);
    }
    public void enterPrescriberFname(String prescriberFname) throws Exception{
        driver.findElement(By.xpath("//*[@*='prescriber_first_name']")).sendKeys(prescriberFname);
    }
    public void enterPrescriberLname(String prescriberLname) throws Exception{
        driver.findElement(By.xpath("//*[@*='prescriber_last_name']")).sendKeys(prescriberLname);
    }
    public void enterPrescriberTaxID(String prescriberTaxID) throws Exception{
        driver.findElement(By.xpath("//*[@*='prescriber_tax_id']")).sendKeys(prescriberTaxID);
    }
    public void enterPrescriberStateLicense(String prescriberStateLicense) throws Exception{
        driver.findElement(By.xpath("//input[@*='prescriber_state_license_number']")).sendKeys(prescriberStateLicense);
    }
    public void enterPrescriberState(String prescriberState) throws Exception{
        driver.findElement(By.xpath("//*[@*='prescriber_licensing_state']")).sendKeys(prescriberState);
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
        BasePage.getScreenShot("Entering Prescriber info");
    }

    //---------PRESCRIBER'S PRACTICE INFORMATION
    //Verify ORGANIZATION NAME, ORGANIZATION NPI, ORGANIZATION TAX ID


    //---------SELECT OR ADD A LOCATION for Prescriber
    public void searchPrescriberLocation(String prescrLocation) throws Exception {
        waitFor(5);
        //driver.findElement(By.xpath("(//input[@*='rbt-input-main form-control rbt-input border-left-0'])[2]")).sendKeys(prescriberLocation);
        driver.findElement(By.xpath("(//input[@class='rbt-input-hint'])[3]")).sendKeys(prescrLocation);
        driver.findElement(By.xpath("(//input[@*='rbt-input-main form-control rbt-input border-left-0'])[2]")).sendKeys(prescrLocation);
        waitFor(2);
        driver.findElement(By.xpath("//*[@*='search-typeahead-item-0']")).click();
    }


    //---------SELECT OR ADD A LOCATION FOR THE PROVIDER
    public void searchLocation(String providerLocation) throws Exception {
//        driver.findElement(By.xpath("//input[@id='patient_other_insurance']")).sendKeys(providerLocation);
//        Select select = new Select(driver.findElement(By.xpath("//input[@id='patient_other_insurance']")));
//        select.selectByVisibleText(providerLocation);
        driver.findElement(By.xpath("(//input[@class='rbt-input-hint'])[3]")).sendKeys(providerLocation);

        waitFor(2);
        driver.findElement(By.xpath("//*[@*='search-typeahead-item-0']")).click();




    }


    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    //---------PRESCRIBER ADDRESS
    // *appears if you click 'Add Location' button
    public void clickAddLocation() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//button[text()='ADD A LOCATION']")).click();
    }
    public void enterPrescriberAddress(String PrescriberAddress) throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//input[@*='prescriber_location_address_1']")).sendKeys(PrescriberAddress);
    }
    public void enterPrescriberZipCode(String PrescriberZipCode) throws Exception {
        driver.findElement(By.xpath("//input[@*='prescriber_location_zip']")).sendKeys(PrescriberZipCode);
    }
    public void enterPrescriberCity(String PrescriberCity) throws Exception {
        driver.findElement(By.xpath("//input[@*='prescriber_location_city']")).sendKeys(PrescriberCity);
    }
    public void enterPrescriberLocState(String PrescriberLocState) throws Exception {
        driver.findElement(By.xpath("//select[@*='prescriber_location_state']")).sendKeys(PrescriberLocState);
    }
    public void enterPrescriberPhoneNumber(String PrescriberPhoneNumber) throws Exception {
        driver.findElement(By.xpath("//input[@*='prescriber_location_phone_1']")).sendKeys(PrescriberPhoneNumber);
    }
    public void enterPrescriberFaxNumber(String PrescriberFaxNumber) throws Exception {
        driver.findElement(By.xpath("//input[@*='prescriber_location_fax_number']")).sendKeys(PrescriberFaxNumber);
    }
    public void populatePrescriberAddressInfo(String PrescriberAddress, String PrescriberZipCode,
                                       String PrescriberCity, String PrescriberLocState,
                                       String PrescriberPhoneNumber, String PrescriberFaxNumber) throws Exception {
        enterPrescriberAddress(PrescriberAddress);
        enterPrescriberZipCode(PrescriberZipCode);
        enterPrescriberCity(PrescriberCity);
        enterPrescriberLocState(PrescriberLocState);
        enterPrescriberPhoneNumber(PrescriberPhoneNumber);
        enterPrescriberFaxNumber(PrescriberFaxNumber);
        BasePage.getScreenShot("Entering Prescriber address info");
    }

    public void selectPayment(String paymentMethod) throws Exception {
        if (paymentMethod.equalsIgnoreCase("CHECK")) {
            driver.findElement(By.xpath("//*[text()='Check']")).click();
        } else {
            driver.findElement(By.xpath("//*[text()='EFT']")).click();
        }
    }

}
