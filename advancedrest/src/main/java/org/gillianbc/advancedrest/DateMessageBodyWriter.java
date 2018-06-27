/**
 * 
 */
package org.gillianbc.advancedrest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * @author Gillian.Bladen-Clark This is for converting a java.util.Date to
 *         TEXT/PLAIN as Jersey know how to convert to JSON or XML, but not to
 *         plain text
 *	e.g. http://localhost:8080/advanced-jaxrs-01/webgbc/test
 */
@Provider
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyWriter implements MessageBodyWriter<Date> {

	@Override
	public boolean isWriteable(Class<?> dataType, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// Jax-rs scans thro' all classes that implement MessageBodyWriter and finds one
		// that can process the required datatype e.g. Date. So we need to state in this
		// method that we can process a Date. The datatype passed in can be a subclass
		// of Date - that's what the isAssignableFrom() checks
		return Date.class.isAssignableFrom(dataType);
	}

	@Override
	public void writeTo(Date date, 
			Class<?> datatype, 
			Type genericType, 
			Annotation[] annotations, 
			MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, 
			OutputStream responseStream)
			throws IOException, WebApplicationException {
		responseStream.write(date.toString().getBytes());  //needs to be a byte array for the stream

	}

	@Override
	public long getSize(Date t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// This method is deprecated. The recommendation is to return -1
		return -1;
	}

}
