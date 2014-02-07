package com.epam.householdappliances.parser.sax;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.householdappliances.parser.IProductsBuilder;
import com.epam.householdappliances.parser.object.Category;

public final class ProductsSAXBuilder implements IProductsBuilder {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger
			.getLogger(ProductsSAXBuilder.class);

	private static ProductsSAXBuilder instance = null;

	private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

	private ProductsSAXBuilder() {
	}

	public static ProductsSAXBuilder getInstance() {
		if (instance == null) {
			INSTANCE_LOCK.lock();
			try {
				if (instance == null) {
					instance = new ProductsSAXBuilder();
				}
			} finally {
				INSTANCE_LOCK.unlock();
			}
		}
		return instance;

	}

	@Override
	public Set<Category> buildCategoriesSet(String fileName) {
		ProductsHandler sph;
		XMLReader reader = null;
		Set<Category> categories = new HashSet<Category>();
		sph = new ProductsHandler();
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(sph);
			reader.parse(fileName);
			categories = sph.getCategoryList();
		} catch (IOException | SAXException e) {
			LOG.error(e);
		}
		return categories;
	}

}
