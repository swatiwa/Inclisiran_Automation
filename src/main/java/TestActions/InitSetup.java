package TestActions;

import TestFramework.Pages.BasePage;
import TestFramework.Pages.SAML.AssertionConsumerService;
import TestFramework.Pages.MicrosoftPages.SignInPage;

import java.io.File;

public class InitSetup {

    public static void hcpInitLogin(String emailId, String password) throws Exception{
        TestFramework.HCPPages.SignInPage page = new TestFramework.HCPPages.SignInPage();
        page.enterCredentials(emailId,password);
    }

    public static void initLogin(String emailId, String password, String account) throws Exception{
        File file = new File("src\\main\\java\\Resources\\Cookies.data");
//        if(file.exists()){
//            initLogin(account);
//        }else{
            SignInPage signInPage = new SignInPage();
            signInPage.enterMailId(emailId);
            signInPage.clickNext();
            signInPage.enterPassword(password);
            signInPage.clickSignIn();
            BasePage basePage = new BasePage();
            //basePage.writeCookie();
            AssertionConsumerService service = signInPage.clickYes();
            //AssertionConsumerService service = new AssertionConsumerService();
            service.selectAccountInformation(account);
            service.clickSubmit();
//        }


    }

    private static void initLogin(String account){

        AssertionConsumerService service = new AssertionConsumerService();
        service.selectAccountInformation(account);
        service.clickSubmit();
    }
}
