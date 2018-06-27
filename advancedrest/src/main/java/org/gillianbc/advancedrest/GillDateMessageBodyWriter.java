package org.gillianbc.advancedrest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/*
 * Converts from Date to my custom mediatype for the output stream
 */

@Provider
@Produces("text/gilldate")
public class GillDateMessageBodyWriter implements MessageBodyWriter<Date> {

	@Override
	public boolean isWriteable(Class<?> dataType, Type genericType, Annotation[] annotations, MediaType mediaType) {
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
		System.out.println("Date is " + date);
		
		responseStream.write(("My date "+ date.toString()).getBytes());  //needs to be a byte array for the stream

	}
	
	@Override
	public long getSize(Date t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// deprecated
		return -1;
	}

}
