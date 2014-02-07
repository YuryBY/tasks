package com.epam.hibernate.util.resource;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MessageManager: give messages from file messages.properties.
 * 
 * @author Sheiko Aliaksandr
 * @version 1.0 8 April 2013
 */
public final class PropertyManager {
	private static final HashMap<String, ResourceBundle> RESOURCE_BUNDLES = new HashMap<>();
	private static final ReentrantLock LOCK = new ReentrantLock();

	private PropertyManager() {
	}

	public static String getProperty(String key, String pathProperties) {
		ResourceBundle currentBundle = RESOURCE_BUNDLES.get(pathProperties);
		if (currentBundle == null) {
			try {
				LOCK.lock();
				if (RESOURCE_BUNDLES.get(pathProperties) == null) {
					RESOURCE_BUNDLES.put(pathProperties,
							ResourceBundle.getBundle(pathProperties));
				}
				currentBundle = RESOURCE_BUNDLES.get(pathProperties);
			} finally {
				LOCK.unlock();
			}
		}
		return currentBundle.getString(key);
	}

	public static Properties getProperties(String filePath) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(filePath);
		Enumeration<String> keys = resourceBundle.getKeys();
		Properties properties = new Properties();
		String currentKey;
		while (keys.hasMoreElements()) {
			currentKey = keys.nextElement();
			properties.put(currentKey, resourceBundle.getString(currentKey));
		}
		return properties;
	}
}
