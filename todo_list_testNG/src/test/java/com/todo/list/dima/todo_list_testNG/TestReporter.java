package com.todo.list.dima.todo_list_testNG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

public class TestReporter {
	
	private static TestReporter instance;
	private File reportFile;
	private String filePath;
	private BufferedWriter writer;
	private final String DEFAULT_FILE_PATH = "C:/Users/Admin/Desktop/Dima/reports/";
	private String testname;
	private int level;
	
	private TestReporter() throws Exception{
		initReporter();
	}
	
	public void initReporter() throws Exception {
		closeReportFile();
		if(filePath == null)
			setFilePath(DEFAULT_FILE_PATH);
		reportFile = new File(filePath + "TestReport_" + LocalDateTime.now().toString().replace(':', '-') + ".txt");
		try {
		writer = new BufferedWriter(new FileWriter(reportFile));
		}
		catch(Exception e){
			System.err.println(String.format("Failed to open file %s. Exception Message: %s",
					reportFile.getPath(), e.getMessage()));
		}
		
		level = 0;
		if(testname != null)
			report("Start of test: " + testname);
	}
	
	public static TestReporter getInstance(){
		if(instance == null)
			try {
			instance = new TestReporter();
			}
			catch(Exception e) {/*TODO - proper handling*/}
		return instance;
	}
	
	public void report(String str) {
		try {
		LocalDateTime now = LocalDateTime.now();
		writer.write(StringUtils.repeat("	", level) + String.format("%s.%s.%s: ", now.getHour(), now.getMinute(), now.getSecond()) + str + "\r\n");
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
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setTestname(String testname) {
		this.testname = testname;
	}
	
	public void closeReportFile() throws Exception {
		if(writer != null)
			writer.close();
	}
}
