package reports;


import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	@BeforeClass
	public static ExtentReports initialize() {
	
		String path	=	System.getProperty("user.dir")+"\\reports\\ExtentTestReport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setReportName("OpenMRS Automation Results");
		sparkReporter.config().setDocumentTitle("Test Results : ");
		ExtentReports report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Tester: ", "Bakkiyalakshmi");
		return report;
	}
}
