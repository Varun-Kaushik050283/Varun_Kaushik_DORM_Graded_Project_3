/**
 * 
 */
package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.collection.TicketTrackerApplicationCaptionsLoader;
import com.greatlearning.domain.entity.Ticket;
import com.greatlearning.service.TicketManagementService;
import com.greatlearning.util.TicketTrackerApplicationUtil;

/**
 * TicketController to receive all the requests related to update in ticket
 * tracker database
 */
@Controller
public class TicketController {

	@Autowired
	private TicketManagementService ticketManagementService;

	/**
	 * Navigate to new ticket page
	 * 
	 * @param modelMap
	 * @param ticketModel
	 * @return
	 */
	@RequestMapping("new_ticket")
	public String displayNewTicketPage(ModelMap modelMap, Model ticketModel) {

		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getTicketFormWidgetsCaptionMap());

		Ticket ticket = new Ticket();

		ticketModel.addAttribute("ticketModel", ticket);
		ticketModel.addAttribute("newTicket", true);
		ticketModel.addAttribute("ticketEditorCaption", "newTicketPageTitle");

		return "ticket_editor";
	}

	/**
	 * Navigate to edit ticket page
	 * 
	 * @param modelMap
	 * @param ticketModel
	 * @param id
	 * @return
	 */
	@RequestMapping("edit_ticket")
	public String displayEditTicketPage(ModelMap modelMap, Model model, @ModelAttribute("ticketModel") Ticket ticket,
			@RequestParam("id") int id) {
		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getTicketFormWidgetsCaptionMap());

		List<Ticket> ticketsRetrieved = ticketManagementService.readTicket(id);

		/**
		 * Since ticket_Id is system_generated and is Primary Key hence there is only
		 * one ticket record in the retrieved collection
		 */
		Ticket ticketFetched = null;
		if (ticketsRetrieved.size() > 0) {
			ticketFetched = ((Ticket) ticketsRetrieved.get(0));
		}

		model.addAttribute("ticketModel", ticketFetched);
		model.addAttribute("newTicket", false);
		model.addAttribute("ticketEditorCaption", "editTicketPageTitle");

		return "ticket_editor";
	}

	/**
	 * navigate to search tickets page
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("display_tickets")
	public String displaySearchPage(ModelMap modelMap) {

		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getSearchFormWidgetsCaptionMap());

		TicketTrackerApplicationUtil.buildSearchTicketsPageModel(modelMap, ticketManagementService);

		return "search_tickets";
	}

	/**
	 * search tickets from the database and display the details on search_tickets
	 * page, according to the ticket Search Keyword
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("search_tickets")
	public String searchTickets(ModelMap modelMap, @RequestParam("searchKeyword") String searchKeyword) {

		searchKeyword = searchKeyword.substring(0, searchKeyword.length() - 1);

		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getSearchFormWidgetsCaptionMap());

		TicketTrackerApplicationUtil.buildSearchTicketsPageModel(modelMap, ticketManagementService, searchKeyword);

		return "search_tickets";
	}

	/**
	 * command to delete ticket
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("delete_ticket")
	public String deleteTicket(ModelMap modelMap, @ModelAttribute("ticketModel") Ticket ticket) {

		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getSearchFormWidgetsCaptionMap());

		ticketManagementService.deleteTicket(ticket.getId());

		TicketTrackerApplicationUtil.buildSearchTicketsPageModel(modelMap, ticketManagementService);

		return "search_tickets";
	}

	/**
	 * Command to update/create ticket depending on existence of ticket_id
	 * 
	 * @param modelMap
	 * @param ticketModel
	 * @param ticketTitle
	 * @param ticketShortDescription
	 * @return
	 */
	@RequestMapping("save_ticket")
	public String saveTicket(ModelMap modelMap, @ModelAttribute("ticketModel") Ticket ticket,
			@RequestParam("id") int ticketId) {
		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getSearchFormWidgetsCaptionMap());

		boolean valueSaved = TicketTrackerApplicationUtil.createOrUpdateTicketInDatabase(modelMap, ticket, ticketId,
				ticket.getTicketTitle(), ticket.getTicketShortDescription(), ticketManagementService);

		if (valueSaved) {
			TicketTrackerApplicationUtil.buildSearchTicketsPageModel(modelMap, ticketManagementService);
		}

		return "search_tickets";
	}

	/**
	 * Command to view ticket in read Only mode
	 * 
	 * @param modelMap
	 * @param ticketModel
	 * @param id
	 * @return
	 */
	@RequestMapping("view_ticket")
	public String viewTicket(ModelMap modelMap, Model ticketModel, @ModelAttribute("ticketModel") Ticket ticket) {

		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getTicketFormWidgetsCaptionMap());

		List<Ticket> ticketsRetrieved = ticketManagementService.readTicket(ticket.getId());

		/**
		 * Since ticket_Id is system_generated and is Primary Key hence there is only
		 * one ticket record in the retrieved collection
		 */
		Ticket ticketFetched = null;
		if (ticketsRetrieved.size() > 0) {
			ticketFetched = ((Ticket) ticketsRetrieved.get(0));
		}

		ticketModel.addAttribute("ticketModel", ticketFetched);
		ticketModel.addAttribute("newTicket", false);
		ticketModel.addAttribute("ticketEditorCaption", "viewTicketPageTitle");

		return "ticket_editor";
	}

}
