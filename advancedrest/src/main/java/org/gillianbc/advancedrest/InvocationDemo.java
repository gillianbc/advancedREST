package org.gillianbc.advancedrest;


import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gillianbc.messenger.model.Message;



public class InvocationDemo {
	public static void main(String[] args) {
		// as we're in a static method main, we need an instance so that we can call its
		// methods
		InvocationDemo demo = new InvocationDemo();
		Invocation invocation = demo.preparePostMessage("hello dolly", "anon");
		Response response = invocation.invoke();
		System.out.println("Response status " + response.getStatus());
		
		invocation = demo.prepareRequestMessagesByYear(2018);
		response = invocation.invoke();
		System.out.println("Response status " + response.getStatus() + "\n" + response.readEntity(new GenericType<List<Message>>() {}));
	}

	public Invocation prepareRequestMessagesByYear(int year) {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/advancedrest/webgbc")
				.path("messages")
				.queryParam("year", year)
				.request(MediaType.APPLICATION_JSON)
				.buildGet();
		
	}
	
	public Invocation preparePostMessage(String messageText, String author) {
		Message message = new Message(messageText, author);
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080/advancedrest/webgbc")
				.path("messages")
				.request(MediaType.APPLICATION_JSON)
				.buildPost(Entity.json(message));
		
	}
}
