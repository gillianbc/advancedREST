package org.gillianbc.advancedrest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.getName().equals(MyDate.class.getName())) {
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Calendar requestedDate = Calendar.getInstance();
					if ("tomorrow".equalsIgnoreCase(value)) {
						requestedDate.add(Calendar.DATE, 1);
					} else if ("yesterday".equalsIgnoreCase(value)) {
						requestedDate.add(Calendar.DATE, -1);
					}
					// Months are indexed from 0 in Calendar
					MyDate myDate = new MyDate(requestedDate.get(Calendar.DAY_OF_MONTH),
							requestedDate.get(Calendar.MONTH)+1, requestedDate.get(Calendar.YEAR));
					return rawType.cast(myDate);
				}

				@Override
				public String toString(T value) {
					if (value == null)
						return null;
					return value.toString();
				}

			};
		}

		return null;
	}

}
