/**
 * 
 */
package com.greatlearning.service;

import java.util.List;

import com.greatlearning.domain.entity.Ticket;

/**
 * Ticket Management service inclined to cater the management of Ticket Tracker
 * Application database
 */
public interface TicketManagementService {

	/**
	 * To search the database for the tickets having the searchKeyword present in
	 * title or ticket_short_description of any existing tickets
	 * 
	 * @param searchKeyword
	 * @return
	 */
	public List<Ticket> searchTickets(String searchKeyword);

	/**
	 * Create ticket service: Inclined to insert new tickets into database
	 * 
	 * @param ticket
	 * @return Ticket
	 */
	public boolean createTicket(Ticket ticket);

	/**
	 * Read the details of ticket service: Inclined to fetch the details of a ticket
	 * from the database according to Id
	 * 
	 * @param ticketId
	 * @return Optional<Ticket>
	 */
	public List<Ticket> readTicket(Integer ticketId);

	/**
	 * Read the details of ALL the tickets service: Inclined to fetch the details of
	 * ALL the tickets from the database
	 * 
	 * @return List<Ticket>
	 */
	public List<Ticket> readAllTickets();

	/**
	 * Count number of tickets service: Inclined to count the number of tickets in
	 * the database
	 * 
	 * @return int
	 */
	public int countTickets();

	/**
	 * Update ticket service: Inclined to update existing ticket in the database
	 * 
	 * @param ticket
	 * @return Ticket
	 */
	public boolean updateTicket(Ticket ticket);

	/**
	 * Delete the details of ticket service: Inclined to delete the details of a
	 * ticket from the database according to Id
	 * 
	 * @param ticketId
	 * @return void
	 */
	public void deleteTicket(Integer ticketId);
	
	/**
	 * Validation method to verify whether the generated ticketId is present in
	 * database
	 * 
	 * @param ticketId
	 * @return
	 */
	public boolean checkForExistenceOfTicketId(Integer ticketId);

}
