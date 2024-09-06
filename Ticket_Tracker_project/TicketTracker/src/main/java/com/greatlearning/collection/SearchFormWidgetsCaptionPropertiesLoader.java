package com.greatlearning.collection;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * final class to load up the captions of Search Form by reading
 * SearchForm.properties file following Singleton pattern (one instance shared
 * by all threads)
 */
final class SearchFormWidgetsCaptionPropertiesLoader {

	private static SearchFormWidgetsCaptionPropertiesLoader instance;
	private Map<String, String> ticketTrackerApplicationSearchFormWidgetsCaptionMap;

	private SearchFormWidgetsCaptionPropertiesLoader() {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("/com/greatlearning/resources/SearchForm.properties");
		try {
			prop.load(stream);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString() + " " + e.getStackTrace()[0]);
		}
		ticketTrackerApplicationSearchFormWidgetsCaptionMap = new HashMap<>();

		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			ticketTrackerApplicationSearchFormWidgetsCaptionMap.put(entry.getKey().toString(),
					entry.getValue().toString());
		}

	}

	public static SearchFormWidgetsCaptionPropertiesLoader getInstance() {
		return null == instance ? new SearchFormWidgetsCaptionPropertiesLoader() : instance;
	}

	public Map<String, String> fetchCaptions() {
		return ticketTrackerApplicationSearchFormWidgetsCaptionMap;
	}

}
