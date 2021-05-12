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

public class CHG0038213_Novartis14 extends BaseTestClass {
    @Test
    public void createTask() throws Exception{

        ExcelReader excelReader = new ExcelReader();
        excelReader.readExcel("Novartis14");
        Index index = new Index();
        Portlet portlet = index.selectInclisiran();

        logger.info("Clicking on Adding service Request");
        Create create = portlet.addNewServiceRequest("250");

        CreateNewContext context=null;
        context=new CreateNewContext(logger, create);

        logger.info("checking on "+excelReader.testData.get("Drug"));
        create.selectTherapyDrug("Inclisiran");

        logger.info("Selecting " + excelReader.testData.get("SRType"));
        create.selectSRtype("Appeal");
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

        List<String> descriptions = Arrays.asList("Contact plan for appeal requirements", "Contact HCP to provide appeal requirements", "Contact HCP for additional information",
        "Contact plan for appeal status", "Report appeal determination to HCP", "Review new SR document", "Contact FRM to notify status", "Contact HCP for appeal status - 1st attempt", "Contact HCP for appeal status - 2nd attempt",
                "Contact HCP for appeal status - 3rd attempt");

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

        processDates.clickSave();

        Notes notes = details.clickNotes();
        notes.clickAddNew();
        notes.setSubject("Complete PEF Received");
        notes.setNoteContent("PEF received complete. Complete SRF Notification to HCP faxed.");
        notes.clickSave();

        details.clickPatient();
        PatientStatus patientStatus = details.clickPatientStatus();
        patientStatus.setTherapyDrug("Inclisiran");

        List<String> status = Arrays.asList("Pending - Enrollment in Progress", "Active - On Commercial Product", "Cancelled - HCP Withdrew Request"
        ,"Discontinued - Adverse Event", "Discontinued - PAP Enrollment Deactivated", "Cancelled - Patient Withdrew Request", "Active - Direct Dispense from SP",
             "Pending - Benefit Investigation in Progress", "Pending - Prior Authorization in Progress", "Active - PAP Approved", "Cancelled - Alternative Treatment Prescribed",
                "Pending - Appeal in Progress", "Pending - Prescriber Decision", "Pending - Triaged to SP", "Pending - Other");

        for (String temp: status) {
            patientStatus.setStatus(temp);
            Thread.sleep(1000);
        }

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
        srHubStatus.setStatus("Appeal - Appeal In Progress");
        srHubStatus.setSttusDate(currentDate);
        srHubStatus.clickSave();

        KeyElements keyElements = details.clickKeyElements();
        keyElements.clickClosedRadioButton();
        //Assertions
        keyElements.clickSaveButton();
        Assert.assertEquals("Resolution is required", keyElements.resolutionErrorMessage());
        ///

        List<String> resolutions = Arrays.asList("Appeal Approved", "Appeal Denied", "Cancelled - HCP non-responsive/failed to provide info", "Cancelled - HCP Request",
                "Cancelled - HCP Request - Alternative therapy prescribed", "Cancelled- No option for Appeal", "Cancelled - SR created in error", "Cancelled - FRM Request");

        for (String temp: resolutions) {
            keyElements.selectResolution(temp);
            Thread.sleep(1000);
        }

        keyElements.selectResolution("Appeal Approved");
        keyElements.clickSaveButton();



        Reports reports = portlet.openReports("250");
        reports.selectReport("Rpt #101 - Incoming Fax Reconciliation Report");
        reports.setStartDate(currentDate);
        reports.setEndDate(currentDate);
        reports.clickRunButton();


    }
}
