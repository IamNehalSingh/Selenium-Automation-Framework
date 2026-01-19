package base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReportManager;
import utils.Log;
import utils.ReportUtils;
import utils.EmailUtils;

@Listeners(listeners.TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
 
   @BeforeSuite
    public void setupReport() {
        ReportUtils.cleanOldReports();
        extent = ExtentReportManager.getReportInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {

        test = ExtentReportManager.createTest(method.getName());

        Log.info("Starting WebDriver..");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        test.info("Browser launched");

        driver.get("https://www.naukri.com/nlogin/login");
        test.info("Navigated to Naukri Login Page");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {

            test.log(Status.PASS, "Test Passed");

        } else if (result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath = ExtentReportManager
                    .captureScreenshot(driver, result.getName());

            test.log(Status.FAIL, result.getThrowable());

            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }

        } else {
            test.log(Status.SKIP, "Test Skipped");
        }

        if (driver != null) {
            driver.quit();
            Log.info("Browser closed");
        }
    }   
   
    @AfterSuite
    public void tearDownReport() {
        System.out.println("AFTER SUITE STARTED");
        extent.flush(); 
        String reportPath = ExtentReportManager.reportPath;
        System.out.println("Report path: " + reportPath);
        EmailUtils.sendTestReport(reportPath); 
        System.out.println("EMAIL SENT SUCCESSFULLY");
    }
}
