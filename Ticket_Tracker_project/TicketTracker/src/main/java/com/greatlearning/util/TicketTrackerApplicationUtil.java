/**
 * 
 */
package com.greatlearning.util;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import org.springframework.ui.ModelMap;

import com.greatlearning.domain.entity.Ticket;
import com.greatlearning.service.TicketManagementService;

/**
 * Utility final class having static methods to provide internal processing
 * logic to the proposed application
 */
public final class TicketTrackerApplicationUtil {

	/**
	 * To build model for search tickets page, in order to display total number
	 * tickets along with their details from the database according to the tickets
	 * count retrieved from database
	 * 
	 * @param modelMap
	 * @param ticketManagementService
	 */
	public static void buildSearchTicketsPageModel(ModelMap modelMap, TicketManagementService ticketManagementService) {
		/**
		 * To retrieve the count of total number of tickets from the database
		 */
		int ticketCount = ticketManagementService.countTickets();
		if (0 != ticketCount) {

			/**
			 * Read operation performed to fetch details of all the tickets from the
			 * database
			 */
			List<Ticket> tickets = ticketManagementService.readAllTickets();
			modelMap.addAttribute("tickets", tickets);
			modelMap.addAttribute("ticketsExist", true);
		} else {
			ticketCount = 0;
			modelMap.addAttribute("ticketsExist", false);
		}
	}

	/**
	 * To build model for search tickets page, in order to display total number
	 * tickets along with their details from the database according to the tickets
	 * count retrieved from database and the ticket_search keyword
	 * 
	 * @param modelMap
	 * @param ticketManagementService
	 */
	public static void buildSearchTicketsPageModel(ModelMap modelMap, TicketManagementService ticketManagementService,
			String searchKeyword) {
		/**
		 * To retrieve the count of total number of tickets from the database
		 */
		int ticketCount = ticketManagementService.countTickets();
		if (0 != ticketCount) {

			/**
			 * Read(Search) operation performed to fetch details of all the tickets from the
			 * database according to the search_keyword provided from user
			 */
			List<Ticket> tickets = ticketManagementService.searchTickets(searchKeyword);
			modelMap.addAttribute("tickets", tickets);
			modelMap.addAttribute("ticketsExist", true);
		} else {
			ticketCount = 0;
			modelMap.addAttribute("ticketsExist", false);
		}
	}

	/**
	 * To create or update the ticket in database according to the existence of the
	 * ticket_Id
	 * 
	 * @param modelMap
	 * @param ticketModel
	 * @param ticketTitle
	 * @param ticketShortDescription
	 * @param ticketManagementService
	 * @return
	 */
	public static boolean createOrUpdateTicketInDatabase(ModelMap modelMap, Ticket ticket, int id, String ticketTitle,
			String ticketShortDescription, TicketManagementService ticketManagementService) {
		/**
		 * Read operation to fetch ticket from database according to the ticket_Id
		 * retrieved from ticket model
		 */
		List<Ticket> ticketsFetched = ticketManagementService.readTicket(id);

		/**
		 * Since ticket_Id is system_generated and is Primary Key hence there is only
		 * one ticket record in the retrieved collection
		 */
		Ticket ticketFetched = null;
		if (ticketsFetched.size() > 0) {
			ticketFetched = ((Ticket) ticketsFetched.get(0));
		}

		/**
		 * Update operation having updated Ticket Short Description if the ticket is
		 * found in the database
		 */
		if (null != ticketFetched && ticketsFetched.size() > 0 && ticketFetched.getTicketTitle().equals(ticketTitle)) {
			ticketFetched.setTicketShortDescription(ticketShortDescription);
			return ticketManagementService.updateTicket(ticketFetched);
		}

		/**
		 * Create operation having Title and Ticket Short Description, if the ticket is
		 * NOT found in the database
		 */
		else {

			/**
			 * Generate created_On date as current date
			 */
			java.util.Date todayDate = new java.util.Date();
			Date createdOn = new Date(todayDate.getTime());

			/**
			 * Generate ticket_id by generating random number
			 */
			Random random = new Random();
			int ticketId;
			while (true) {
				ticketId = random.nextInt(1000000000);
				if(!ticketManagementService.checkForExistenceOfTicketId(ticketId)) {
					break;
				};
			}

			/**
			 * Instantiate new Ticket using user provided inputs
			 */
			Ticket newTicket = new Ticket(ticketId, ticketTitle, ticketShortDescription, createdOn);

			/**
			 * Create new ticket by invoking TicketDAO Create Ticket method
			 */
			return ticketManagementService.createTicket(newTicket);
		}
	}

}
