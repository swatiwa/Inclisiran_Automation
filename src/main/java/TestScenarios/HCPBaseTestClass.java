package TestScenarios;

import ProviderPortalFramework.Pages.BasePage;
import TestActions.InitSetup;
import TestFramework.HCPPages.HomePage;
import TestFramework.Models.ITestBaseStructure;
import TestFramework.Pages.BrowserDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Predicates;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static ProviderPortalFramework.Pages.BasePage.driver;

public class HCPBaseTestClass implements ITestBaseStructure {

    public static Properties prop;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extentReports;
    public static ExtentTest logger;
    private String path = "src\\Reports";
    private static String envNameBeforetest = null;

    //private String randomValue = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    public String getRandomValue() {
        return new SimpleDateFormat("yyyy_MM_dd_hhmmss").format(new Date());
    }

    @BeforeTest
    public void BeforeTest() {

        Props();
        htmlReporter = new ExtentHtmlReporter(path + "\\Report " + getRandomValue() + ".html");
        htmlReporter.config().setDocumentTitle("Automation Report Portal");
        htmlReporter.config().setReportName("Execution Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Browser", "Chrome Browser");
        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("Tester Name", prop.getProperty("TesterName"));

    } // setting up reports

    @BeforeClass
    public void SetUp() {


    }   // setting browsers


    public static String setEnv(String env) {
        envNameBeforetest = env;
        return env;
    }

    @BeforeMethod

    @Override
    public void BeforeTestMethod(Method method) {

        logger = extentReports.createTest(method.getName());

        TestFramework.Pages.BasePage.initializeHCpPage(BrowserDriver.Chrome, prop.getProperty(envNameBeforetest));
        //TestFramework.Pages.BasePage.initializeHCpPage(BrowserDriver.Chrome, prop.getProperty("HCP_ENV"));
        //TestFramework.Pages.BasePage.initializeHCpPage(BrowserDriver.IE, prop.getProperty("HCP_ENV"));

        try {
            //Inclisiran Dev
            if (prop.getProperty(envNameBeforetest).contains("DEV")) {
                if (!method.getName().equalsIgnoreCase("register")) {
                    InitSetup.hcpInitLogin("autouser2", "???");
                    //InitSetup.initLogin("autotester@caremetx.com", "???", "autotester");
                    //InitSetup.hcpInitLogin("autouser3", "???");
                }

            //Inclisiran UAT
            else if(prop.getProperty("HCP_ENV").contains("UAT")){
                if (!method.getName().equalsIgnoreCase("register")) {
                    InitSetup.hcpInitLogin("autouser3", "???");
                }

            }
            //HCP Demo portal Dev
            }else if(prop.getProperty("HCP_ENV").contains("DemoDev")){
                //if (!method.getName().equalsIgnoreCase("register") || (!method.getName().equalsIgnoreCase("resetMyPassword"))) {
                //if (!method.getName().equalsIgnoreCase("register") && (!method.getName().equalsIgnoreCase("resetMyPassword"))) {
                if (!method.getName().equalsIgnoreCase("register")) {
                    InitSetup.hcpInitLogin("autotest_tenant_dev", " ");
                }

            //HCP Demo portal UAT
            }else if(prop.getProperty("HCP_ENV").contains("DemoUAT")){
                if (!method.getName().equalsIgnoreCase("register")) {
                    InitSetup.hcpInitLogin("autotest_tenant_uat", " ");
                }
            }
            /*
                InitSetup.hcpInitLogin("autouser3", " ");
                if (!method.getName().equalsIgnoreCase("registerOrg")) {
                    InitSetup.hcpInitLogin("autouser2", " ");
                }

                InitSetup.hcpInitLogin("autouser3", " ");
             */

        } catch (Exception e) {
        }
    }


    @AfterMethod
    public void AfterTestMethod(ITestResult result) throws Exception {
        String random = getRandomValue().toLowerCase();
        System.out.println("After Method");
        if (result.isSuccess()) {
            TestFramework.Pages.BasePage.getScreenShot(random);
            logger.log(Status.PASS, result.getName() + " Passed",
                    MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + random + ".png").build());
            logger.addScreenCaptureFromPath("Screenshots\\" + random + ".png");
        } else {
            TestFramework.Pages.BasePage.getScreenShot(random);
            logger.log(Status.FAIL, "Test case failed",
                    MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\" + random + ".png").build());
            logger.addScreenCaptureFromPath("Screenshots\\" + random + ".png");
        }
        ProviderPortalFramework.Pages.BasePage.TearDown();
    }

    @AfterClass
    public void TearDown() {
        System.out.println("TearDOwn");
        //Methods to implement after class tests

        //BasePage.driver.quit();
    }

    @AfterTest
    public void AfterTest() {
        extentReports.flush();
    }

    public static void Props() {
        try {
            prop = new Properties();
            FileInputStream stream = new FileInputStream("src\\main\\java\\TestScenarios\\config.properties");
            prop.load(stream);

        } catch (Exception e) {
            BasePage.TearDown();
            System.out.println("Config file not available");
        }

    }


    //   @Override
//    public void BeforeTestMethod(Method method){
//        BasePage.initializeHCpPage(BrowserDriver.Chrome);
//        try{
//            InitSetup.hcpInitLogin("autouser3", "Tester20!");
//        }catch (Exception e){
//
//        }
//    }


}
