/**
 * 
 */
package com.greatlearning.domain.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.greatlearning.db.sessionmgt.TicketTrackerApplicationSessionCreator;
import com.greatlearning.domain.entity.Ticket;

/**
 * Ticket Data Access Object class intended to work with the Ticket Entity of
 * Ticket Tracker Application Database
 */
@Component
public final class TicketDao {

	/**
	 * To fetch all the tickets from the Ticket Tracker Application database's
	 * Ticket Table To Be used on the search form (first landing page) on loading of
	 * page.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int countTickets() {

		List<Ticket> tickets = null;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making transaction to fetch all the ticket records from the database
			 */
			session.beginTransaction();
			tickets = session.createQuery("FROM Ticket").list();
			session.getTransaction().commit();

		} finally {
			session.close();
		}
		return tickets.size();
	}

	/**
	 * To search the database for the tickets having the searchKeyword present in
	 * title or ticket_short_description of any existing tickets
	 * 
	 * @param searchKeyword
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> searchTickets(String searchKeyword) {

		List<Ticket> tickets = null;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making transaction to fetch all the ticket records from the database where
			 * the keyword is present in ticket_title or ticket_Short_Description
			 */
			session.beginTransaction();
			tickets = session.createQuery("FROM Ticket " + " WHERE title like '%" + searchKeyword + "'  "
					+ "    OR title like '%" + searchKeyword + "%' " + "    OR title like '" + searchKeyword + "%' "
					+ "    OR descr like '%" + searchKeyword + "'  " + "    OR descr like '%" + searchKeyword + "%' "
					+ "    OR descr like '" + searchKeyword + "%' ").list();
			session.getTransaction().commit();

		} finally {
			session.close();
		}
		return tickets;
	}

	/**
	 * To fetch all the tickets from the Ticket Tracker Application database's
	 * Ticket Table To Be used on the search form (first landing page) on loading of
	 * page.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> fetchTickets() {

		List<Ticket> tickets = null;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making transaction to fetch all the ticket records from the database
			 */
			session.beginTransaction();
			tickets = session.createQuery("FROM Ticket").list();
			session.getTransaction().commit();

		} finally {
			session.close();
		}
		return tickets;
	}

	/**
	 * To save the ticket record based on the existence of ticketId To be used in
	 * Create/Update operations of the CRUD Ticket Tracker Application application
	 * 
	 * @param ticket
	 * @return
	 */
	public boolean createTicket(Ticket ticket) {

		boolean ticketCreated = false;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making first transaction to save ticket record into database
			 */
			session.beginTransaction();
			session.save(ticket);
			session.getTransaction().commit();

			/**
			 * Making second transaction to fetch saved ticket record from the database
			 */
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Ticket> createdTicket = session
					.createQuery(" FROM Ticket WHERE title = '" + ticket.getTicketTitle() + "'").list();
			session.getTransaction().commit();

			/**
			 * Verifying whether the new ticket has been created in the database
			 */
			ticketCreated = (null != createdTicket);

		} finally {
			session.close();
		}
		return ticketCreated;
	}

	/**
	 * To fetch a single ticket record based on the ticketId To be used in Read
	 * operation of the CRUD Ticket Tracker Application application
	 * 
	 * @param ticketId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> readTicket(Integer id) {
		List<Ticket> fetchedTicket = null;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making transaction to fetch ticket record from the database
			 */
			session.beginTransaction();

			fetchedTicket = session.createQuery("FROM Ticket WHERE ticket_id = " + id).list();
			session.getTransaction().commit();

		} finally {
			session.close();
		}
		return fetchedTicket;
	}

	/**
	 * To update the ticket record based on the existence of ticketId To be used in
	 * Create/Update operations of the CRUD Ticket Tracker Application application
	 * 
	 * @param ticket
	 * @return
	 */
	public boolean updateTicket(Ticket ticket) {

		boolean ticketUpdated = false;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {
			/**
			 * Making first transaction to update ticket record in the database
			 */
			session.beginTransaction();
			session.createQuery("UPDATE Ticket " + " SET title = '" + ticket.getTicketTitle() + "', descr = '"
					+ ticket.getTicketShortDescription() + "' WHERE ticket_id = '" + ticket.getId() + "'")
					.executeUpdate();
			session.getTransaction().commit();

			/**
			 * Making second transaction to fetch updated ticket record from the database
			 */
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Ticket> updatedTicket = session
					.createQuery("FROM Ticket WHERE title = '" + ticket.getTicketTitle() + "' OR descr = '"
							+ ticket.getTicketShortDescription() + "' AND ticket_id = '" + ticket.getId() + "'")
					.list();
			session.getTransaction().commit();

			/**
			 * Verifying whether the existing ticket has been updated in the database
			 */
			ticketUpdated = (null != updatedTicket);

		} finally {
			session.close();
		}

		return ticketUpdated;
	}

	/**
	 * To delete the ticket record based on the existence of ticketId To be used in
	 * Delete operation of the CRUD Ticket Tracker Application application
	 * 
	 * @param ticketId
	 * @return void
	 */
	public boolean deleteTicket(Integer id) {

		boolean ticketDeleted = false;

		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making first transaction to delete ticket record into database
			 */
			session.beginTransaction();
			session.createQuery("DELETE FROM Ticket WHERE ticket_id = " + id).executeUpdate();
			session.getTransaction().commit();

			/**
			 * Making second transaction to fetch deleted ticket record from the database
			 */
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Ticket> deletedTicket = session.createQuery("FROM Ticket WHERE ticket_id =" + id).list();
			session.getTransaction().commit();

			/**
			 * Verifying whether the new ticket has been deleted in the database
			 */
			ticketDeleted = (null != deletedTicket);

		} finally {
			session.close();
		}
		return ticketDeleted;
	}

	/**
	 * Validation method to verify whether the generated ticketId is present in
	 * database
	 * 
	 * @return
	 */
	public boolean checkForExistenceOfTicketId(Integer ticketId) {
		
		/**
		 * Getting new session from session factory
		 */
		Session session = TicketTrackerApplicationSessionCreator.createSession();

		try {

			/**
			 * Making transaction to fetch ticket record from the database
			 */
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Ticket> fetchedTickets = session.createQuery("FROM Ticket WHERE ticket_id = " + ticketId).list();
			session.getTransaction().commit();
			
			return fetchedTickets.size() > 0;
			
		} finally {
			session.close();
		}

	}

}
