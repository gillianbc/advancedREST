package org.gillianbc.advancedrest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Request Headers " + requestContext.getHeaders());
		System.out.println("Method " + requestContext.getMethod() + " URI: " + requestContext.getUriInfo().getBaseUri() + requestContext.getUriInfo().getPath());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println("Response Headers " + responseContext.getHeaders());
	}

}
