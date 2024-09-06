package com.greatlearning.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * final class to load up the captions of Entire Application through instances
 * of CaptionsLoader classes of Search_Form and Ticket_Form and following
 * Singleton pattern (one instance shared by all threads)
 */
public final class TicketTrackerApplicationCaptionsLoader {

	private static TicketTrackerApplicationCaptionsLoader instance;
	private Map<String, String> ticketTrackerApplicationSearchFormWidgetsCaptionMap;
	private Map<String, String> ticketTrackerApplicationTicketFormWidgetsCaptionMap;

	private TicketTrackerApplicationCaptionsLoader() {
		ticketTrackerApplicationSearchFormWidgetsCaptionMap = new HashMap<>();
		ticketTrackerApplicationSearchFormWidgetsCaptionMap
				.putAll(SearchFormWidgetsCaptionPropertiesLoader.getInstance().fetchCaptions());
		ticketTrackerApplicationTicketFormWidgetsCaptionMap = new HashMap<>();
		ticketTrackerApplicationTicketFormWidgetsCaptionMap
				.putAll(TicketFormWidgetsCaptionPropertiesLoader.getInstance().fetchCaptions());
	}

	public static TicketTrackerApplicationCaptionsLoader getInstance() {
		return null == instance ? new TicketTrackerApplicationCaptionsLoader() : instance;
	}

	public Map<String, String> getSearchFormWidgetsCaptionMap() {
		return ticketTrackerApplicationSearchFormWidgetsCaptionMap;
	}

	public Map<String, String> getTicketFormWidgetsCaptionMap() {
		return ticketTrackerApplicationTicketFormWidgetsCaptionMap;
	}
}
