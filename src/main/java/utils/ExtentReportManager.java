package utils;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    public static String reportPath;

    private ExtentReportManager() {
        
    }

    public static ExtentReports getReportInstance() {
        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                    .format(new Date());
            
            reportPath = System.getProperty("user.dir")
                    + "/reports/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Automation Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        return getReportInstance().createTest(testName);
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String screenshotDir = "screenshots";
            new File(screenshotDir).mkdirs();
            
            String path = System.getProperty("user.dir") + "/" + screenshotDir + "/" + screenshotName + ".png";

            File dest = new File(path);

            FileUtils.copyFile(src, dest);

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
