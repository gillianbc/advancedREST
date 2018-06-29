package org.gillianbc.advancedrest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER = "authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (!requestContext.getUriInfo().getPath().contains(SECURED_URL)) {
			//Not a secured resource, so let it through the filter
			return;
		}
		List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
		if (authHeaders != null && authHeaders.size() > 0) {
			String authToken = authHeaders.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			String decodedString = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			System.out.println("Count of tokens " + tokenizer.countTokens());
			String userName = tokenizer.nextToken();
			System.out.println("User: " + userName );
			String password = tokenizer.nextToken();
			System.out.println("User: " + userName + " password " + password);
			if ("user".equals(userName) && "password".equals(password)) {
				//user and password OK;  let it through the filter
				return;
			}
			
			Response unauthorisedStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity("User not allowed to access this resource")
					.build();
			//Stop it going through the filter
			requestContext.abortWith(unauthorisedStatus);
		}
	}

}
