package rest;

public abstract class RestClient {
	
	protected final String server;
	
	protected RestClient(String server) {
		this.server = server;
	}

}
