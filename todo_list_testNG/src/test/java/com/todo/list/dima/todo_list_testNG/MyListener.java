package com.todo.list.dima.todo_list_testNG;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.lang3.StringUtils;


//TODO - this class should be separated into two different classes - a reporter and a separate listener that instantiates it
public class MyListener implements ITestListener{

	private TestReporter report = TestReporter.getInstance();
	
	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		report.report("=====Test succeeded=====");
	}

	public void onTestFailure(ITestResult result) {
		report.report("=====Test failed=====");
		
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context){
		try {
		report.closeReportFile();
		}
		catch(Exception e){
			System.err.println(String.format("Failed to close file %s. Exception Message: %s",
					report.getFilePath(), e.getMessage()));
		}
	}

}
