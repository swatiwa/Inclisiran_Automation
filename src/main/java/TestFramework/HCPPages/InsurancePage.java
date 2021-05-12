package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class InsurancePage extends BasePage {

    public void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //--------DOES THE PATIENT HAVE INSURANCE?:
    public void selectYESorNOinsurance(String insurance) throws Exception{

       waitFor(2);
        if(insurance.equalsIgnoreCase("Yes")){
            driver.findElement(By.xpath("//*[@*='patient_has_insurance' and @value='yes']")).click();
        }else{
            driver.findElement(By.xpath("//*[@*='patient_has_insurance' and @value='no']")).click();
        }
    }

    public void selectYesInsurance() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//*[@*='patient_has_insurance' and @value='yes']")).click();
    }

    public void selectNoInsurance() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//*[@*='patient_has_insurance' and @value='no']")).click();
    }

    public void select1stOption() throws Exception {
        waitFor(2);
        driver.findElement(By.xpath("//input[@class='rbt-input-main']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Medicare Part B')]")).click();
        //driver.findElement(By.xpath("//a[@*='patient_insurances_typeahead-item-0']")).click();
    }

    //--------WHO DOES THE PATIENT HAVE INSURANCE THROUGH?
    public void selectPatientInsurance(String patientInsuranceName) throws Exception {
        //Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='rbt-input-main']")).click();
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(By.xpath("//a[@class='dropdown-item']")));
        select.selectByVisibleText(patientInsuranceName);
    }
    public void searchPatientOtherInsurance(String patientOtherInsurance) throws Exception {
        driver.findElement(By.xpath("//input[@id='patient_other_insurance']")).sendKeys(patientOtherInsurance);
    }
    //--------MEDICAL INSURANCE
    //--------Primary Plan:
    public void EnterPrimaryPlanName(String PrimaryPlanName) throws Exception {
        waitFor(4);
        driver.findElement(By.xpath("//*[@*='primary_plan_name']")).sendKeys(PrimaryPlanName);
    }
    public void EnterPrimaryPlanId(String PrimaryPlanID) throws Exception {
        driver.findElement(By.xpath("//*[@*='primary_plan_cardholder_id']")).sendKeys(PrimaryPlanID);
    }
    public void EnterPrimaryGroupNumber(String PrimaryGroupNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='primary_plan_group_number']")).sendKeys(PrimaryGroupNumber);
    }
    public void EnterPrimaryPlanPhoneNumber(String PrimaryPhoneNumber) throws Exception {
        driver.findElement(By.xpath("//input[@*='primary_plan_phone_number']")).sendKeys(PrimaryPhoneNumber);
    }

    //--------Secondary Plan:
    public void EnterSecondaryPlanName(String SecondaryPlanName) throws Exception {
        driver.findElement(By.xpath("//*[@*='secondary_plan_name']")).sendKeys(SecondaryPlanName);
    }
    public void EnterSecondaryPlanID(String SecondaryPlanID) throws Exception {
        driver.findElement(By.xpath("//*[@*='secondary_plan_cardholder_id']")).sendKeys(SecondaryPlanID);
    }
    public void EnterSecondaryGroupNumber(String SecondaryGroupNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='secondary_plan_group_number']")).sendKeys(SecondaryGroupNumber);
    }
    public void EnterSecondaryPlanPhoneNumber(String SecondaryPlanPhoneNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='secondary_plan_phone_number']")).sendKeys(SecondaryPlanPhoneNumber);
    }

    //--------PHARMACY OR PRESCRIPTION INSURANCE:
    public void EnterPharmacyPlanName(String PharmacyPlanName) throws Exception {
        driver.findElement(By.xpath("//*[@*='pharmacy_plan_name']")).sendKeys(PharmacyPlanName);
    }
    public void EnterPharmacyCardID(String PharmacyCardID) throws Exception {
        driver.findElement(By.xpath("//*[@*='pharmacy_cardholder_id']")).sendKeys(PharmacyCardID);
    }
    public void EnterPharmacyGroupID(String PharmacyGroupNumber) throws Exception {
        driver.findElement(By.xpath("//*[@*='pharmacy_rx_group_number']")).sendKeys(PharmacyGroupNumber);
    }
    public void EnterRxBIN(String PharmacyBIN) throws Exception {
        driver.findElement(By.xpath("//*[@*='pharmacy_rx_bin_iin']")).sendKeys(PharmacyBIN);
    }
    public void EnterRxPCN(String PharmacyPCN) throws Exception {
        driver.findElement(By.xpath("//*[@*='pharmacy_rx_pcn']")).sendKeys(PharmacyPCN);
    }
    public void EnterPharmacyPhone(String PharmacyPhone) throws Exception {
        driver.findElement(By.xpath("//*[@*='pharmacy_phone_number']")).sendKeys(PharmacyPhone);
    }

    //--------UPLOAD COPY OF INSURANCE CARD(S):
    public void clickAttach() throws Exception {
        driver.findElement(By.xpath("//button[text()='ATTACH']")).click();
    }
    //--------
    public void clickBack() throws Exception {
        driver.findElement(By.xpath("//button[text()='BACK']")).click();
    }

    //--------populate INFO methods
    public void populatePrimaryInsuranceInfo(String PrimaryPlanName, String PrimaryPlanID,
                                             String PrimaryGroupNumber, String PrimaryPhoneNumber) throws Exception {
        EnterPrimaryPlanName(PrimaryPlanName);
        EnterPrimaryPlanId(PrimaryPlanID);
        EnterPrimaryGroupNumber(PrimaryGroupNumber);
        EnterPrimaryPlanPhoneNumber(PrimaryPhoneNumber);
        BasePage.getScreenShot("Entering Primaty insurance info");
    }
    public void populateSecondaryInsuranceInfo(String SecondaryPlanName, String SecondaryPlanID,
                                               String SecondaryGroupNumber, String SecondaryPlanPhoneNumber) throws Exception {
        EnterSecondaryPlanName(SecondaryPlanName);
        EnterSecondaryPlanID(SecondaryPlanID);
        EnterSecondaryGroupNumber(SecondaryGroupNumber);
        EnterSecondaryPlanPhoneNumber(SecondaryPlanPhoneNumber);
        BasePage.getScreenShot("Entering Secondary insurance info");
    }
    public void populatePharmacyInfo(String PharmacyPlanName, String PharmacyCardID,
                                     String PharmacyGroupNumber, String PharmacyBIN,
                                     String PharmacyPCN,String PharmacyPhone) throws Exception {
        EnterPharmacyPlanName(PharmacyPlanName);
        EnterPharmacyCardID(PharmacyCardID);
        EnterPharmacyGroupID(PharmacyGroupNumber);
        EnterRxBIN(PharmacyBIN);
        EnterRxPCN(PharmacyPCN);
        EnterPharmacyPhone(PharmacyPhone);
        BasePage.getScreenShot("Entering Pharmacy info");
    }
}
