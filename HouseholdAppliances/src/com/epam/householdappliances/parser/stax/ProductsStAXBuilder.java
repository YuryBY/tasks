package com.epam.householdappliances.parser.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.epam.householdappliances.parser.IProductsBuilder;
import com.epam.householdappliances.parser.object.Category;
import com.epam.householdappliances.parser.object.Product;
import com.epam.householdappliances.parser.object.Subcategory;
import com.epam.householdappliances.resource.ParsingElementsEnum;

public final class ProductsStAXBuilder implements IProductsBuilder {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger
			.getLogger(ProductsStAXBuilder.class);

	private static ProductsStAXBuilder instance = null;

	private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

	private ProductsStAXBuilder() {
	}

	public static ProductsStAXBuilder getInstance() {
		if (instance == null) {
			INSTANCE_LOCK.lock();
			try {
				if (instance == null) {
					instance = new ProductsStAXBuilder();
				}
			} finally {
				INSTANCE_LOCK.unlock();
			}
		}
		return instance;

	}

	@Override
	public Set<Category> buildCategoriesSet(String fileName) {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		Set<Category> categories = new HashSet<Category>();
		FileInputStream fileInputStream = null;
		String name;
		try {
			fileInputStream = new FileInputStream(fileName);
			XMLStreamReader reader = inputFactory
					.createXMLStreamReader(fileInputStream);
			Category currentCategory = null;
			Subcategory currentSubcategory = null;
			Product currentProduct = null;

			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					ParsingElementsEnum productEnum = stringToEnum(name);
					switch (productEnum) {
					case CATEGORY:
						currentCategory = new Category();
						currentCategory.setName(reader.getAttributeValue(null,
								ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
						categories.add(currentCategory);
						break;
					case SUBCATEGORY:
						currentSubcategory = new Subcategory();
						currentSubcategory.setName(reader.getAttributeValue(
								null,
								ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
						currentCategory.addSubcategory(currentSubcategory);
						break;
					case PRODUCT:
						currentProduct = buildProduct(reader);
						currentSubcategory.addProduct(currentProduct);
						break;
					default:
						break;
					}

				}
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			LOG.error(e);
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		return categories;
	}

	@SuppressWarnings("incomplete-switch")
	private Product buildProduct(XMLStreamReader reader) {
		Product product = new Product();
		product.setName(reader.getAttributeValue(null,
				ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
		String name;
		ParsingElementsEnum productEnum;
		try {
			while (reader.hasNext()) {
				int type = reader.next();
				switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					name = reader.getLocalName();
					productEnum = stringToEnum(name);
					switch (productEnum) {
					case PRODUCER:
						product.setProducer(getXMLText(reader));
						break;
					case MODEL:
						product.setModel(getXMLText(reader));
						break;
					case DATE_ISSUE:
						product.setDateIssue(getXMLText(reader));
						break;
					case COLOR:
						product.setColor(getXMLText(reader));
						break;
					case MISS:
						product.setMissMessage(getXMLText(reader));
						break;
					case PRICE:
						product.setPrice(Float.parseFloat(getXMLText(reader)));
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					name = reader.getLocalName();
					productEnum = stringToEnum(name);
					if (productEnum == ParsingElementsEnum.PRODUCT) {
						return product;
					}
					break;
				default:
					break;
				}
			}
		} catch (XMLStreamException e) {
			LOG.error(e);
		}
		return product;
	}

	private String getXMLText(XMLStreamReader reader) {
		String text = null;
		try {
			if (reader.hasNext()) {
				reader.next();
				text = reader.getText().trim();
			}
		} catch (XMLStreamException e) {
			LOG.error(e);
		}
		return text;
	}

	private ParsingElementsEnum stringToEnum(String name) {
		return ParsingElementsEnum.valueOf(name.toUpperCase().trim()
				.replaceAll("-", "_"));
	}
}
