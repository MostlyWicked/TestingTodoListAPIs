package models;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.lang3.StringUtils;

public class MyReporter implements ITestListener{

	private File reportFile;
	private String filePath;
	private BufferedWriter writer;
	private final String DEFAULT_FILE_PATH = "C:/Users/Admin/Desktop/Dima/reports/";
	private String testname;
	private int level;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void setTestname(String testname) {
		this.testname = testname;
	}
	
	public void onTestStart(ITestResult result) {
		level = 0;
		if(testname != null)
			report("Start of test: " + testname);
		
	}

	public void onTestSuccess(ITestResult result) {
		report("=====Test succeeded=====");
	}

	public void onTestFailure(ITestResult result) {
		report("=====Test failed=====");
		
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		if(filePath == null)
			setFilePath(DEFAULT_FILE_PATH);
		reportFile = new File(filePath + "TestReport_" + LocalDateTime.now() + ".txt");
		try {
		writer = new BufferedWriter(new FileWriter(reportFile));
		}
		catch(Exception e){
			System.err.println(String.format("Failed to open file %s. Exception Message: %s",
					reportFile.getPath(), e.getMessage()));
		}
	}

	public void onFinish(ITestContext context){
		try {
		writer.close();
		}
		catch(Exception e){
			System.err.println(String.format("Failed to close file %s. Exception Message: %s",
					reportFile.getPath(), e.getMessage()));
		}
	}
	
	public void report(String str) {
		try {
		writer.write(StringUtils.repeat("	", level) + Calendar.getInstance().getTime() + ": " + str + "\r\n");
		}
		catch(Exception e){
			System.err.println(String.format("Failed to write to file %s. Exception Message: %s",
					reportFile.getPath(), e.getMessage()));
		}
	}
	
	public void startLevel(String str) {
		level++;
		report(str);
	}
	
	public void stopLevel() {
		level--;
	}

}
