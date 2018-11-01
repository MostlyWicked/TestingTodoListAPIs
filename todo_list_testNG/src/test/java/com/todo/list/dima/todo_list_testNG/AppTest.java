package com.todo.list.dima.todo_list_testNG;

public class AppTest {
	TodoListRestClient client;
	MyReporter report;
	String testname;
	
	@BeforeMethod
    public void handleTestMethodName(Method method)
    {
        testname = method.getName(); 
        tjuhf gj fhgj fhgjfghjgfhj 
    }
	
	@BeforeTest
	public void testSetUp() {
		report.setTestname(testname);
	}
	
	@BeforeClass
	public void setUp() throws Exception{
		client = new TodoListRestClient();
		report = new Reporter();
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
