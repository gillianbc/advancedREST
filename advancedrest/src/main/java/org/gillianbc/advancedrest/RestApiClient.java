package org.gillianbc.advancedrest;

import java.util.Objects;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gillianbc.messenger.model.Message;

import com.sun.research.ws.wadl.Request;

public class RestApiClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		Message message;
		Message responseMessage;
		
		WebTarget basetarget = client.target("http://localhost:8080/advancedrest/webgbc");
		WebTarget messagesTarget = basetarget.path("messages");
		
		responseMessage = postMessage(messagesTarget,"message A","Gill");
		
		System.out.println("\nPost Response: " + responseMessage.getId());
		String idStr = Objects.toString(responseMessage.getId(),null);
		
		message = getMessage(messagesTarget, idStr);
		System.out.println("\nGet Response:" + message);
		
		responseMessage = postMessage(messagesTarget,"message B","Gill");
		
		System.out.println("Post Response: " + responseMessage.getId());
		
		message = getMessage(messagesTarget, Long.toString(responseMessage.getId()));
		System.out.println("\nGet Response:" + message);
	}

	private static Message getMessage(WebTarget messagesTarget, String idStr) {
		Message message;
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}"); // token that we'll sub later

		message = singleMessageTarget
				.resolveTemplate("messageId", idStr)
				.request(MediaType.APPLICATION_JSON)
				.get().readEntity(Message.class);
		return message;
	}

	private static Message postMessage(WebTarget messagesTarget, String messageText, String author) {
		Message message;
		message = new Message(messageText,author); 
		System.out.println("\nNew Message " + message);
		Response response = messagesTarget
				.request()
				.post(Entity
				.json(message));
		Message responseMessage = response.readEntity(Message.class);
		return responseMessage;
	}

}
