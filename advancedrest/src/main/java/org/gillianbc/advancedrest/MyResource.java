package org.gillianbc.advancedrest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("{something}/test")
//@Singleton
/*  By default, a new resource instance is created when it is requested.
 * The @Singleton overrides this so that only one instance of the resource is created
 * (irrespective of no of browser windows).  Doesn't work with PathParam
 * */
public class MyResource {
	private int count;
	
	//e.g. http://localhost:8080/advanced-jaxrs-01/webgbc/apple/test/?somequery=milk
	@PathParam("something") private String paramfield;
	@QueryParam("somequery") private String queryfield;
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	count++;
        return "Got it!" + count + " param field was " + paramfield + " query field was " + queryfield;
    }
}
