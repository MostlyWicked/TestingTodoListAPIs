package rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import models.TodoList;

public class RestClient {
	private final String server;
	
	public RestClient(String server) {
		this.server = server;
	}
	
	public RestClient() {
		this.server = "http://localhost:8080";
	}
	
	public TodoList getAllTasks() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String url = String.format("%s/get_all_tasks", server);
		String todoListStr = fetchJson(url).toString();
		System.out.println(todoListStr);//TODO - remove (this is for testing purposes)
		TodoList todoList = mapper.readValue(todoListStr, TodoList.class);
		return todoList;
	}
	
	private JsonNode fetchJson(String url) throws Exception {
		HttpResponse<JsonNode> response = Unirest.get(url)
				.header("Content-Type", "application/json")
				.asJson();
		String retryHeader = response.getHeaders().getFirst("Retry-After");

		if (response.getStatus() == 200) {
			return response.getBody();
		}
		else
			throw new Exception("Returned status: " + response.getStatus());
	}

}
