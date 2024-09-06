package com.greatlearning.collection;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * final class to load up the captions of Ticket Form by reading
 * TicketForm.properties file following Singleton pattern (one instance shared
 * by all threads)
 */
final class TicketFormWidgetsCaptionPropertiesLoader {

	private static TicketFormWidgetsCaptionPropertiesLoader instance;
	private Map<String, String> ticketTrackerApplicationTicketFormWidgetsCaptionMap;

	private TicketFormWidgetsCaptionPropertiesLoader() {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("/com/greatlearning/resources/TicketForm.properties");
		try {
			prop.load(stream);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString() + " " + e.getStackTrace()[0]);
		}
		ticketTrackerApplicationTicketFormWidgetsCaptionMap = new HashMap<>();

		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			ticketTrackerApplicationTicketFormWidgetsCaptionMap.put(entry.getKey().toString(),
					entry.getValue().toString());
		}

	}

	public static TicketFormWidgetsCaptionPropertiesLoader getInstance() {
		return null == instance ? new TicketFormWidgetsCaptionPropertiesLoader() : instance;
	}

	public Map<String, String> fetchCaptions() {
		return ticketTrackerApplicationTicketFormWidgetsCaptionMap;
	}

}
