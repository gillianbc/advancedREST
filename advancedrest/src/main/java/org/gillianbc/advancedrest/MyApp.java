package org.gillianbc.advancedrest;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("webgbc")
public class MyApp extends Application{
/*
 * By default, the @ApplicationPath annotation above causes jersey to scan through
 * all the classes on the class path and look for those that have the @Path annotation
 * and register all of these as resources. 
 * 
 *  If you wanted to manually control which resources were available, you could use the 
 *  method below to make a set of the classes you want available as resources in your app
 *  
 *  For example, returning an empty set like this should switch all resources OFF
 * */
	
//	public Set<Class<?>> getClasses(){
//		Set<Class<?>> resourceSet = new HashSet<Class<?>>();
////		resourceSet.add(MyResource.class);
//		return resourceSet;
//	}
}
