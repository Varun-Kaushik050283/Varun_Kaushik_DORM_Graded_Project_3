/**
 * 
 */
package com.greatlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.collection.TicketTrackerApplicationCaptionsLoader;
import com.greatlearning.service.TicketManagementService;
import com.greatlearning.util.TicketTrackerApplicationUtil;

/**
 * WelcomeController to receive requests from first landing page
 */
@Controller
public class WelcomeController {

	@Autowired
	private TicketManagementService ticketManagementService;

	@RequestMapping("/")
	public String displayWelcomePage(ModelMap modelMap) {

		modelMap.addAllAttributes(
				TicketTrackerApplicationCaptionsLoader.getInstance().getSearchFormWidgetsCaptionMap());

		TicketTrackerApplicationUtil.buildSearchTicketsPageModel(modelMap, ticketManagementService);

		return "search_tickets";
	}

}
