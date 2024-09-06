/**
 * 
 */
package com.greatlearning.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.domain.dao.TicketDao;
import com.greatlearning.domain.entity.Ticket;
import com.greatlearning.service.TicketManagementService;

/**
 * Ticket Management service inclined to cater the management of Ticket Tracker
 * Application database
 */
@Service
public class TicketManagementServiceImpl implements TicketManagementService {

	@Autowired
	private TicketDao ticketManager;

	/**
	 * Create ticket service: Inclined to insert new tickets into database
	 * 
	 * @param ticket
	 * @return
	 */
	public boolean createTicket(Ticket ticket) {
		return ticketManager.createTicket(ticket);
	}

	/**
	 * Read the details of ticket service: Inclined to fetch the details of a ticket
	 * from the database according to Id
	 * 
	 * @param ticketId
	 * @return
	 */
	public List<Ticket> readTicket(Integer ticketId) {
		return ticketManager.readTicket(ticketId);
	}

	/**
	 * Read the details of ALL the tickets service: Inclined to fetch the details of
	 * all the tickets from the database
	 * 
	 * @return
	 */
	public List<Ticket> readAllTickets() {
		return ticketManager.fetchTickets();
	}

	/**
	 * Count number of tickets service: Inclined to count the number of tickets in
	 * the database
	 * 
	 * @return Long
	 */
	public int countTickets() {
		return ticketManager.countTickets();
	}

	/**
	 * Update ticket service: Inclined to update existing ticket in the database
	 * 
	 * @param ticket
	 * @return
	 */
	public boolean updateTicket(Ticket ticket) {
		return ticketManager.updateTicket(ticket);
	}

	/**
	 * Delete the details of ticket service: Inclined to delete the details of a
	 * ticket from the database according to Id
	 * 
	 * @param ticketId
	 * @return void
	 */
	public void deleteTicket(Integer ticketId) {
		ticketManager.deleteTicket(ticketId);
	}

	/**
	 * To search the database for the tickets having the searchKeyword present in
	 * title or ticket_short_description of any existing tickets
	 * 
	 * @param searchKeyword
	 * @return
	 */
	@Override
	public List<Ticket> searchTickets(String searchKeyword) {
		return ticketManager.searchTickets(searchKeyword);
	}

	/**
	 * Validation method to verify whether the generated ticketId is present in
	 * database
	 * 
	 * @param ticketId
	 * @return
	 */
	@Override
	public boolean checkForExistenceOfTicketId(Integer ticketId) {
		return ticketManager.checkForExistenceOfTicketId(ticketId);
	}
}
