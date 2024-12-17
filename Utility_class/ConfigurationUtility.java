package com.hibernate.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConfigurationUtility {

	
public static SessionFactory getsFactory() {
		
		// Create a StandardServiceRegistry using the configuration file (config.xml)
		StandardServiceRegistry ssRegistry=new StandardServiceRegistryBuilder().configure("config.xml").build();
		
		 // Build metadata from the service registry
	       Metadata metadata=new MetadataSources(ssRegistry).getMetadataBuilder().build();
			
	    // Build the SessionFactory from the metadata
		  SessionFactory sFactory=metadata.buildSessionFactory();
		
		// Return the created SessionFactory
		return sFactory;
	}
}
