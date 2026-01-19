package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.TestResultManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        TestResultManager.addResult(result.getMethod().getMethodName(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestResultManager.addResult(result.getMethod().getMethodName(), false);
    }
}
