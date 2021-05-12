package TestFramework.Models;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public interface ITestBaseStructure {

    @BeforeTest
    public void BeforeTest(); // setting up the reports objects

    @BeforeClass
    public void SetUp(); //

    @BeforeMethod
    public void BeforeTestMethod(Method method);

    @AfterMethod
    public void AfterTestMethod(ITestResult result)throws Exception;

    @AfterClass
    public void TearDown();

    @AfterTest
    public void AfterTest();
}
