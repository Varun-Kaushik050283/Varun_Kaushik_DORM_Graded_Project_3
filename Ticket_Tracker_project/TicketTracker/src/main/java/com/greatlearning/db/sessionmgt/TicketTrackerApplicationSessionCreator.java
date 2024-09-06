/**
 * 
 */
package com.greatlearning.db.sessionmgt;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.greatlearning.db.config.DatabaseConfigurator;

/**
 * Session Creator final class providing essential code to create new session
 * for different database transactions
 * 
 */
public final class TicketTrackerApplicationSessionCreator {

	public static Session createSession() throws HibernateException {
		Session session = null;
		if (null != DatabaseConfigurator.getDbConfiguration()
				&& null != TicketTrackerApplicationSessionFactory.fetchSessionFactory()) {
			try {
				session = TicketTrackerApplicationSessionFactory.fetchSessionFactory().openSession();
			} catch (HibernateException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace()[0] + " " + e.toString());
				throw e;
			}
		}
		return session;
	}

}
