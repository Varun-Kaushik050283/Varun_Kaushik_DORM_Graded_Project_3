/**
 * 
 */
package com.greatlearning.db.config;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

/**
 * Database configuration final class to setting up the configuration to read
 * hibernate.cfg.xml file
 */
public final class DatabaseConfigurator {

	private static Configuration configuration;

	static {
		try {
			configuration = new Configuration();
			configuration.configure("com\\greatlearning\\db\\config\\hibernate.cfg.xml");
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace()[0] + " " + e.toString());
		}
	}

	public static Configuration getDbConfiguration() {
		return configuration;
	}

}
