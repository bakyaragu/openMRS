package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import qa_assessment_testcase.BaseTest;
import reports.ExtentReport;


public class Listeners extends BaseTest implements ITestListener{

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static ExtentReports report = ExtentReport.initialize();
	public static ExtentTest test;
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		test = report.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		extentTest.get().log(Status.INFO, "TestCase: "+result.getMethod().getMethodName()+" is Started");		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.get().log(Status.PASS, "TestCase: "+result.getMethod().getMethodName()+" Passed");
		Markup markUp = MarkupHelper.createLabel("TestCase: "+result.getMethod().getMethodName()+" Passed", ExtentColor.GREEN);
		extentTest.get().pass(markUp);
		String methodName = result.getMethod().getMethodName();
		try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		String filePath = getScreenshot(methodName, driver);
		
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.get().log(Status.FAIL, "Test Case "+result.getMethod().getMethodName()+" is Failed");
		Markup markUp = MarkupHelper.createLabel("TestCase: "+result.getMethod().getMethodName()+" Failed", ExtentColor.RED);
		extentTest.get().fail(markUp);
		String methodName = result.getMethod().getMethodName();
		try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		String filePath = getScreenshot(methodName, driver);
		
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, "Test Case "+result.getMethod().getMethodName()+" is Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	
	}

}
