package TestScenarios.Inclisiran;

import TestActions.CreateNewContext;
import TestFramework.Pages.Home.Portlet;
import TestFramework.Pages.Index;
import TestFramework.Pages.SR.Create;
import TestScenarios.BaseTestClass;
import TestScenarios.DBScenarios.NewContactFirstName;
import org.testng.annotations.Test;

public class AddNewContactDBValidation extends BaseTestClass {

    @Test
    public void createContactAndValidate() throws Exception{

        Index index = new Index();
        Portlet portlet = index.selectInclisiran();

        logger.info("Clicking on Adding service Request");
        Create create = portlet.createContact("250");

        CreateNewContext context=null;
        context=new CreateNewContext(logger, create);

//        context.CreateNewContact("firstName", "lastName", "Other",
//                "irstLine", "city", "20003", "DC");


        Create.Contact contact = create.createContactObj();
        logger.info("Entering data");
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.ContactType("Other");
        contact.setContactAddress("firstLine","20003","city","DC");

        logger.info("Clicking save");
        contact.pageClickSave();
        try{
            contact.pageContinueSave();
        }catch (Exception e){}

        NewContactFirstName newContactFirstName = new NewContactFirstName();
        String firstNameFromDB = newContactFirstName.firstName();
//
        System.out.println(firstNameFromDB);


    }
}
