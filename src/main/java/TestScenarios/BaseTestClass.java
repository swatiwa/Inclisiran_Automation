package TestScenarios;

import TestActions.InitSetup;
import TestFramework.Models.ITestBaseStructure;
import TestFramework.Pages.BasePage;
import TestFramework.Pages.BrowserDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTestClass implements ITestBaseStructure {

    public static Properties prop;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extentReports;
    public static ExtentTest logger;
    private String path = "src\\Reports";
    //private String randomValue = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    public String getRandomValue(){
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

    @BeforeTest
    public void BeforeTest(){

        Props();
        htmlReporter = new ExtentHtmlReporter(path+"\\Report"+getRandomValue()+".html");
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
    public void SetUp(){



    }   // setting browsers

    @BeforeMethod
    public void BeforeTestMethod(Method method) {
        logger = extentReports.createTest(method.getName());

        BasePage.Initialize(BrowserDriver.Chrome, prop.getProperty("env"));
        try{
            InitSetup.initLogin(prop.getProperty("emailId"), prop.getProperty("pass"), prop.getProperty("account"));
        }catch (Exception e){
            TearDown();
            System.out.println("Failed at Setup : BeforeClass method");
        }
    }

    @AfterMethod
    public void AfterTestMethod(ITestResult result) throws Exception{
        String random = getRandomValue().toLowerCase();
        System.out.println("After Method");
        if(result.isSuccess()){
            BasePage.getScreenShot(random);
            logger.log(Status.PASS, result.getName() + " Passed",
                    MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\"+random+".png").build());
            logger.addScreenCaptureFromPath("Screenshots\\"+random+".png");
        }else{
            BasePage.getScreenShot(random);
            logger.log(Status.FAIL, "Test case failed",
                    MediaEntityBuilder.createScreenCaptureFromPath("Screenshots\\"+random+".png").build());
            logger.addScreenCaptureFromPath("Screenshots\\"+random+".png");


        }
        BasePage.TearDown();
    }

    @AfterClass
    public void TearDown() {
        System.out.println("TearDOwn");

        //Methods to implement after class tests
    }

    @AfterTest
    public void AfterTest(){
        extentReports.flush();
    }

    public static void Props(){
        try{
            prop = new Properties();
            FileInputStream stream = new FileInputStream("src\\main\\java\\TestScenarios\\config.properties");
            prop.load(stream);

        }catch (Exception e){
            BasePage.TearDown();
            System.out.println("Config file not available");
        }

    }

}
