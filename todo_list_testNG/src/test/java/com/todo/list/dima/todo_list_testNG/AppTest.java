package com.todo.list.dima.todo_list_testNG;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;import org.testng.annotations.BeforeSuite;import org.testng.annotations.BeforeTest;import org.testng.annotations.Listeners;import org.testng.annotations.Test;import models.Task;
import models.TodoList;
import rest.TodoListRestClient;
@Listeners({com.todo.list.dima.todo_list_testNG.MyListener.class})
@Test
public class AppTest {
	TodoListRestClient client;
	TestReporter report = TestReporter.getInstance();
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
	
	@BeforeSuite
	public void setUp() throws Exception{
		client = new TodoListRestClient();
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
}
