package TestScenarios.Inclisiran;

import TestActions.CreateNewContext;
import TestFramework.Pages.CopayPractice.CopayManagement;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import TestFramework.Pages.SR.Create;
import TestFramework.Pages.SR.Details;
import TestScenarios.BaseTestClass;
import Utils.ExcelReader;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewRequestInclisiran extends BaseTestClass {

    @Test
    public void createTask() throws Exception{

        ExcelReader excelReader = new ExcelReader();
        excelReader.readExcel("TestSheetA");
        Index index = new Index();
        Portlet portlet = index.selectInclisiran();

        logger.info("Clicking on Adding service Request");
        Create create = portlet.addNewServiceRequest("250");

        CreateNewContext context=null;
        context=new CreateNewContext(logger, create);

        logger.info("checking on "+excelReader.testData.get("Drug"));
        create.selectTherapyDrug("Inclisiran");

        logger.info("Selecting " + excelReader.testData.get("SRType"));
        create.selectSRtype("Copay Assistance");
        create.selectSRtypeNthCheckBox(1);

        context.CreateNewContact(excelReader.testData.get("FirstName"),excelReader.testData.get("LastName"),excelReader.testData.get("ContactType"),excelReader.testData.get("Address1"),excelReader.testData.get("City"),excelReader.testData.get("Zip"),excelReader.testData.get("State"));

//        context=new CreateNewContext(logger, create);
//          context.CreateNewContact("Sam", "Bandara", "Provider");

        context.createNewPatient(excelReader.testData.get("PatientFname"), excelReader.testData.get("PatientLname"));
        String npi = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).substring(4);
        context.createNewProvider(excelReader.testData.get("ProviderFName"), excelReader.testData.get("ProviderLName"), npi);
        context.createNewFacility(excelReader.testData.get("FacilityName"), excelReader.testData.get("FacilityType"));

        create.selectSRSourceNew("Copay Vendor");
        create.setGroupOne("Test");
        logger.info("Click Save");
        Details details = create.clickSave();
        Thread.sleep(5000);
        CopayManagement copayManagement =  details.clickCopayManagement();
        copayManagement.checkCommercialInsurance();
        copayManagement.checkPatientConsent();
        copayManagement.checkPatientDiagnosis();
        copayManagement.clickApprove();
    }
}
