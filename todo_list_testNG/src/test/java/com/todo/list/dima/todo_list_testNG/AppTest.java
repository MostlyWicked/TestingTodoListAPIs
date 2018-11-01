package com.todo.list.dima.todo_list_testNG;

import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import models.MyReporter;
import models.Task;
import models.TodoList;
import rest.TodoListRestClient;

public class AppTest {
	TodoListRestClient client;
	MyReporter report;
	String testname;
	
	@BeforeMethod
    public void handleTestMethodName(Method method)
    {
        testname = method.getName();
    }
	
	@BeforeTest
	public void testSetUp() {
		report.setTestname(testname);
	}
	
	@BeforeClass
	public void setUp() throws Exception{
		client = new TodoListRestClient();
		report = new MyReporter();
	}
	
	
	@Test(groups = {"e2e"})
    public void createTaskAndGetListE2E() throws Exception {
    	TodoList list = client.getAllTasks();
    	Task[] tasks = list.getTasks();
    	for(Task task : tasks) {
    		report.report("Task title: " + task.getTitle());
    		report.report("Task description: " + task.getDescription());
    	}
    } 
	
	@AfterClass
	public void cleanUp() {
	}
	
}
