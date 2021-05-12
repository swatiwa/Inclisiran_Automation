package TestFramework.Pages;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;


public class BasePage {
    public static WebDriver driver;
    public static String initStageUrl;

    public static void initializeHCpPage(BrowserDriver browser, String env){
        if(browser == BrowserDriver.Chrome){
            System.setProperty("webdriver.chrome.driver","src\\main\\java\\Resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        if(browser == BrowserDriver.IE){
            System.setProperty("webdriver.ie.driver","src\\main\\java\\Resources\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        //driver.get(envUrl);

        //Launch HCP portal Dev env
        if(env.equalsIgnoreCase("dev")){
            driver.get("https://caremetxhcpportal-dev.caremetx.com/");

        //Launch HCP portal Training env
        }else if(env.equalsIgnoreCase("training")){
            driver.get("https://servicecenterportal-training.caremetx.com/");

        //Launch eHiPAA portal
        }else if(env.equalsIgnoreCase("eHiPAA")){
            //Inclisiran
            driver.get("https://servicecenter.ehipaatest.com/");
            //ByYourSide
            //driver.get("https://byyourside.ehipaatest.com/");

        //Launch HCP portal UAT env
        }else if(env.equalsIgnoreCase("uat")){
            driver.get("https://servicecenterportal-uat.caremetx.com/");

        //Launch Demo HCPPortal Dev env
        }else if(env.equalsIgnoreCase("DemoDev")){
            driver.get("http://standardhcpportal-dev.caremetx.com/");

        //Launch Demo HCPPortal UAT env
        }else if(env.equalsIgnoreCase("DemoUAT")){
            driver.get("https://standardhcpportal-demo.caremetx.com");
        }


    }

    public static void Initialize(BrowserDriver browser, String stageEnv){
        if(browser == BrowserDriver.Chrome){
            System.setProperty("webdriver.chrome.driver","src\\main\\java\\Resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        if(browser == BrowserDriver.IE){
            System.setProperty("webdriver.ie.driver","src\\main\\java\\Resources\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        String envUrl = null;

        //Launch Connect UAT env
        if(stageEnv.contains("UAT")){
            envUrl = "https://summatest.caremetx.com/";

        //Launch Connect Training env
        }else if(stageEnv.contains("Training")){
            envUrl = "https://summa-training.caremetx.com/";

        //Launch Connect Dev env
        }else {
            envUrl = "https://summa.dev.caremetx.com/";
        }
        initStageUrl = envUrl;


        BasePage basePage = new BasePage();
        try{
            File file = new File("src\\main\\java\\Resources\\Cookies.data");
//            if(file.exists()){
                //basePage.readCookie();
                driver.manage().window().maximize();
//                driver.get(envUrl);
            driver.get("https://summa.dev.caremetx.com/");
//            }else{
//                driver.get("https://summa.dev.caremetx.com/");
//                basePage.writeCookie();
//                System.out.println("File does not exist");
//
//            }

        }catch (Exception e){

        }


    }

    public static void TearDown(){
        //driver.quit();
    }

    public static void waitStalenessFor(WebElement element){
        new WebDriverWait(driver, 180).until(ExpectedConditions.stalenessOf(element));
    }

    public static void waitProcessing(WebElement element){
        try {
            new WebDriverWait(driver, 180).until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            //
        }
    }// login - {user/pass - login}


    public static String getScreenShot(String shotName) throws Exception{
        TakesScreenshot ts = (TakesScreenshot)driver;
        String dest = "src\\Reports\\Screenshots\\"+shotName+".png";

        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(dest);
        Files.copy(source, destination);

        return dest;
    }

    public void writeCookie() throws Exception {

        File file = new File("src\\main\\java\\Resources\\Cookies.data");
        try
        {
            // Delete old file if exists
            //file.delete();
            file.createNewFile();
            FileWriter fileWrite = new FileWriter(file);
            BufferedWriter Bwrite = new BufferedWriter(fileWrite);
            // loop for getting the cookie information

            // loop for getting the cookie information
            for(Cookie ck : driver.manage().getCookies())
            {
                Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));
                Bwrite.newLine();
            }
            Bwrite.close();
            fileWrite.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void readCookie() throws Exception {
        driver.get("http://login.microsoftonline.com");
        Thread.sleep(5000);
        File file = new File("src\\main\\java\\Resources\\Cookies.data");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String strline;
        while ((strline = bufferReader.readLine()) != null) {
            StringTokenizer token = new StringTokenizer(strline, ";");
            while (token.hasMoreTokens()) {
                try {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();

                    Date todayss = new Date();
                    long ltime = todayss.getTime() + 8 * 24 * 60 * 60 * 1000;
                    Date today8 = new Date(ltime);

                    Cookie ck = new Cookie(name, value, domain, path, today8);
                    System.out.println(ck);
                    driver.manage().addCookie(ck); // This will add the stored cookie to your current session

                } catch (Exception e) {

                    System.out.println("Failed to add cookies");
                }
            }
        }
    }


    public static void waitProcessing(WebElement element, long Seconds){
        try {
            new WebDriverWait(driver, Seconds).until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            //
        }
    }

}
