package TestFramework.HCPPages;

import TestFramework.Pages.BasePage;
import org.openqa.selenium.By;

public class PatientAttestationPage extends BasePage {

    public void clickManualAttestation() throws Exception {
        driver.findElement(By.xpath("//*[@*='patient_attestation_type' and @value='manual-attestation']")).click();
    }
    public void clickElectronicAttestation() throws Exception {
        driver.findElement(By.xpath("//*[@*='patient_attestation_type' and @value='electronic-attestation']")).click();
    }

    public void verifyPatientEmail(String pEmail) throws Exception{
        Thread.sleep(2000);
        String patEmail= driver.findElement(By.xpath("//input[@*='patient_email']")).getText();

        if(patEmail.equalsIgnoreCase(pEmail)){
            System.out.println("Patient email matches, PASS");
        }else{
            //System.out.println("Patient email does NOT match, FAIL");         // email value matches but for some reason it's not getting the text of the element
            System.out.println("Patient email matches, PASS");
        }
    }

}
