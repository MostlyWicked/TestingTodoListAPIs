package models;



public class TodoList {
	private final Task[] tasks;

	public TodoList(Task[] tasks) {
		this.tasks = tasks;
	}
	
	public Task[] getTasks() {
		return tasks;
	}
}
