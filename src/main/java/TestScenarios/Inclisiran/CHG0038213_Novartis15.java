package TestScenarios.Inclisiran;

import TestActions.CreateNewContext;
import TestFramework.Pages.BasePage;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import TestFramework.Pages.Patient.PatientStatus;
import TestFramework.Pages.Reports.Reports;
import TestFramework.Pages.SR.*;
import TestScenarios.BaseTestClass;
import Utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static TestScenarios.BaseTestClass.logger;

public class CHG0038213_Novartis15 extends BaseTestClass {
    @Test
    public void createTask() throws Exception{

        ExcelReader excelReader = new ExcelReader();
        excelReader.readExcel("Novartis15");
        Index index = new Index();
        Portlet portlet = index.selectInclisiran();

        logger.info("Clicking on Adding service Request");
        Create create = portlet.addNewServiceRequest("250");

        CreateNewContext context=null;
        context=new CreateNewContext(logger, create);

        logger.info("checking on "+excelReader.testData.get("Drug"));
        create.selectTherapyDrug("Inclisiran");

        logger.info("Selecting " + excelReader.testData.get("SRType"));
        create.selectSRtype("Benefit Investigation");
        create.selectSRtypeNthCheckBox(1);


        context.CreateNewContact(excelReader.testData.get("FirstName"),excelReader.testData.get("LastName"),excelReader.testData.get("ContactType"),
                excelReader.testData.get("Address1"),excelReader.testData.get("Zip"),excelReader.testData.get("City"),excelReader.testData.get("State"));

//        context=new CreateNewContext(logger, create);
//          context.CreateNewContact("Sam", "Bandara", "Provider");

        context.createNewPatient(excelReader.testData.get("PatientFname"), excelReader.testData.get("PatientLname"));
        String npi = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).substring(4);
        context.createNewProvider(excelReader.testData.get("ProviderFName"), excelReader.testData.get("ProviderLName"), npi);
        context.createNewFacility(excelReader.testData.get("FacilityName"), excelReader.testData.get("FacilityType"));

        create.selectSRSourceNew("Patient");
        //Assertions
        Assert.assertFalse(create.isPatientContactOfSR().isEnabled());
        Assert.assertTrue(create.isProviderContactOfSR().isEnabled());
        create.setGroupOne("Intake");
        logger.info("Click Save");
        Details details = create.clickSave();
        Thread.sleep(8000);

        ActivitiesCallLogs activitiesCallLogs = details.clickActivitiesCallLogs();
        activitiesCallLogs.setDate();
        activitiesCallLogs.checkNthActivity("3");
        activitiesCallLogs.subject("test");
        activitiesCallLogs.note("note");

        activitiesCallLogs.clickSave();
        System.out.println(activitiesCallLogs.isActivity("Faxed Missing Information Request to HCP"));

        Tasks tasks = details.clickTasks();
        String currentDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        logger.info("Enter tasks details");
        tasks.setTaskDueDate(currentDate);
        List<String> groups = Arrays.asList("Intake", "Reimbursement", "Management", "Field Reimbursement Team");
        for (String temp: groups) {
            tasks.selectAssignToGroup(temp);
        }
        tasks.selectAssignToGroup("Intake");
        Thread.sleep(3000);
        tasks.selectAssignTo("tcato");

        List<String> descriptions = Arrays.asList("Contact HCP for missing information", "Contact Patient for missing consent",
                "Contact patient for missing insurance information", "Complete Welcome Call");

        for (String temp: descriptions) {
            tasks.selectTaskDescription(temp);
        }
        //tasks.selectTaskDescription(1);
        tasks.selectTaskPriority("High");

        logger.info("Click save");
        tasks.clickSave();
        Thread.sleep(4000);

        //Process Dates
        ProcessDates processDates = details.clickProcessDates();

        processDates.setDateOfNthRow(1, currentDate);
        processDates.setDateOfNthRow(2, currentDate);
        processDates.setDateOfNthRow(3, currentDate);
        processDates.setDateOfNthRow(4, currentDate);
        processDates.setDateOfNthRow(5, currentDate);
        processDates.clickSave();

        Notes notes = details.clickNotes();
        notes.clickAddNew();
        notes.setSubject("Complete PEF Received");
        notes.setNoteContent("PEF received complete. Complete SRF Notification to HCP faxed.");
        notes.clickSave();

        details.clickPatient();
        PatientStatus patientStatus = details.clickPatientStatus();
        patientStatus.setTherapyDrug("Inclisiran");
        patientStatus.setStatus("Pending - Enrollment in Progress");
        patientStatus.setStatusDAte(currentDate);
        patientStatus.clickSave();

        SrHubStatus srHubStatus = details.clickSRHubStatus();
//        Assertions
        srHubStatus.clickSave();

        Assert.assertTrue(srHubStatus.getStatusErrorMsg().contains("Status is required."));
        Assert.assertTrue(srHubStatus.getStatusDateerrorMsg().contains("Status Date is required."));
//        List<String> statusValues = Arrays.asList("Bridge - Triaged to Bridge Administrator",
//                "PA - In Progress", "PA - Complete");
//        for (String temp: statusValues) {
//            srHubStatus.setSttusDate(temp);
//        }
//
        srHubStatus.setStatus("Patient Intake - Incomplete Intake");
        srHubStatus.setSttusDate(currentDate);
        srHubStatus.clickSave();

        KeyElements keyElements = details.clickKeyElements();
        keyElements.clickClosedRadioButton();
        //Assertions
        keyElements.clickSaveButton();
        Assert.assertEquals("Resolution is required", keyElements.resolutionErrorMessage());
        ///
        keyElements.selectResolution("Bridge Dispensed");
        keyElements.clickSaveButton();



        Reports reports = portlet.openReports("250");
        reports.selectReport("Rpt #101 - Incoming Fax Reconciliation Report");
        reports.setStartDate(currentDate);
        reports.setEndDate(currentDate);
        reports.clickRunButton();


    }
}
