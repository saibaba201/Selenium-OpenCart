package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportsManger implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;
	public void onStart(ITestContext context) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName="Test-Report"+timeStamp+".html";
		sparkReporter= new ExtentSparkReporter(".\\reports\\"+reportName);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("E-commerce website testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User-Name", "Saibaba Areti");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String Browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", Browser);
		
	List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
	
	if(!includeGroups.isEmpty()) {
		extent.setSystemInfo("Groups",includeGroups.toString());
	}
	}

	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got Successfully Executed");

	}

	public void onTestFailure(ITestResult result) {
	test=extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.FAIL, result.getMethod()+"got failed");
	test.log(Status.INFO, result.getThrowable().getMessage());
	try {
		String imgPath=new BaseClass().caputureScreenShot(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getMethod()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReport=new File(pathOfExtentReport);
		try {
		Desktop.getDesktop().browse(extentReport.toURI());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
