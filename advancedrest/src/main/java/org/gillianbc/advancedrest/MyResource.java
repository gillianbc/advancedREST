package org.gillianbc.advancedrest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("test")  //i.e. http://localhost:8080/advancedrest/webgbc/test
public class MyResource {
	private int count;
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces("text/gilldate")  //my own custom media type - the name is arbitrary
    public Date getIt() {
        return Calendar.getInstance().getTime();
    }
}
