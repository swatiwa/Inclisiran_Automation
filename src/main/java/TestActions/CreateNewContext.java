package TestActions;

import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import TestFramework.Pages.SR.Create;
import TestFramework.Pages.SR.Facility;
import TestFramework.Pages.SR.Provider;
import com.aventstack.extentreports.ExtentTest;

public class CreateNewContext {
    public static Create create;
    public static ExtentTest logger;
    public CreateNewContext(ExtentTest test, Create createObj){
        logger = test;
        create = createObj;
    }

    public void CreateNewContact(String firstName, String lastName, String contactType, String firstLine,
                                 String city, String zip, String state) throws Exception{

        logger.info("Clicking on Create New - Contact");
        Create.Contact contact = create.createNewContact();

        logger.info("Entering data");
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.ContactType(contactType);
        contact.setContactAddress(firstLine,city,zip,state);

        logger.info("Clicking save");
        contact.clickSave();
        try{
            contact.clickContinueSave();
        }catch (Exception e){}
//        contact.clickContinueSave();
    }

    public void createNewPatient(String firstName, String lastName) throws Exception{

        logger.info("Clicking on create new Patient");
        Create.Patient patient =  create.createNewPatient();

        logger.info("Entering data");
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.clickSave();
        try{
            patient.clickContinueSave();
        }catch (Exception e){}

    }

    public void createNewProvider(String firstName, String lastName, String nPI) throws Exception{

        logger.info("click on create new for provider");
        Provider provider = create.createNewProvider();

        logger.info("Set First name");
        provider.setFirstName(firstName);
        logger.info("Set last Name");
        provider.setLastName(lastName);
        logger.info("set NPI");
        provider.setNPI(nPI);

        logger.info("Click save");
        provider.clickSave();
        try{
            provider.clickContinueSave();
        }catch (Exception e){}

    }

    public void createNewFacility(String orgName, String orgType) throws Exception{

        logger.info("Click on create new - facility");
        Facility facility = create.createNewFacility();
        logger.info("Enter org name and type");
        Thread.sleep(2000);
        facility.setOrganisationName(orgName);
        facility.setOrganizationType(orgType);
        logger.info("Click on save");
        facility.clickSave();
        logger.info("Click on continue save");
        try{
            Thread.sleep(5000);
            facility.clickContinueSave();
        }catch (Exception e){}

    }
}
