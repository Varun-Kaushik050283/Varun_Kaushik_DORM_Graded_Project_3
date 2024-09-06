/**
 * 
 */
package com.greatlearning.db.sessionmgt;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.greatlearning.db.config.DatabaseConfigurator;

/**
 * Session factory final class providing essential factory code to further
 * provide session for database transaction
 */
final class TicketTrackerApplicationSessionFactory {

	static SessionFactory fetchSessionFactory() throws HibernateException {
		SessionFactory sessionFactory = null;
		if (null != DatabaseConfigurator.getDbConfiguration()) {
			try {
				sessionFactory = DatabaseConfigurator.getDbConfiguration().addPackage("com.greatlearning.domain.entity")
						.buildSessionFactory();
			} catch (HibernateException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace()[0] + " " + e.toString());
				throw e;
			}
		}
		return sessionFactory;
	}

}
