package Resoureces;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentReportManager {
	
	
 
    private static ExtentReports extentReports;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;
 
    static {
    	
        // Set up the ExtentSparkReporter to write reports to "extent-report.html"
        sparkReporter = new ExtentSparkReporter("Extent Report/RBL.html");
        sparkReporter.config().setReportName("RBL Loan Application Project");
        sparkReporter.config().setDocumentTitle("Two wheeler loan process");
 
        // Create an instance of ExtentReports and attach the reporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        
    }
 
    public static ExtentReports getExtentReports() {
        return extentReports;
    }
 
    public static ExtentTest createTest(String testName) {
        return extentReports.createTest(testName);
    }
 
    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}