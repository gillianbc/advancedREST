package org.gillianbc.advancedrest;

import java.util.Calendar;
import java.util.Date;

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
@Path("test")
//@Singleton
/*  By default, a new resource instance is created when it is requested.
 * The @Singleton overrides this so that only one instance of the resource is created
 * (irrespective of no of browser windows).  Doesn't work with PathParam and QueryParam
 * as these are specific to the instance.  If you need singleton, then you need to use the
 * PathParam and QueryParam as part of the method signature.  Singleton resources are 
 * created before the request comes in i.e. when server started
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
    public Date getIt() {
        return Calendar.getInstance().getTime();
    }
}
