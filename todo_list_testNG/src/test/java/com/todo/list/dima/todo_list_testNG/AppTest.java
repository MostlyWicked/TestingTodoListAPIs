package com.todo.list.dima.todo_list_testNG;

import org.testng.Assert;
import org.testng.annotations.*;

import rest.RestClient;

public class AppTest {
	
	//private String SERVER_JAR_PATH = "C:/Users/Admin/Desktop/Dima/run_server_sim.jar";
	
	Process serverProcess;
	
	/*
	@BeforeClass
	public void setUp() throws Exception{
		//serverProcess = Runtime.getRuntime().exec(new String[]{"java", "-jar", SERVER_JAR_PATH});
	}
	*/
	
	@Test(groups = {"e2e"})
    public void createTaskAndGetListE2E() throws Exception {
    	RestClient client = new RestClient();
    	client.getAllTasks();
    }
	
	/*
	@AfterClass
	public void cleanUp() {
		//serverProcess.destroy();
	}
	*/
}
