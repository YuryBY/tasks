package com.epam.stuffshop.resource;

import java.util.ResourceBundle;

/**
 * MessageManager: give messages from file messages.properties.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */
public final class MessageManager {
	/**Contains  ResourceBundle object for file config.properties*/
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle("properties.messages");

	private MessageManager() {
	}

	/**
	 * Gives messages from config.properties by key.
	 * 
	 * @param key is key of message.
	 * @return message by key from messages.properties
	 */
	public static String getProperty(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}

}
