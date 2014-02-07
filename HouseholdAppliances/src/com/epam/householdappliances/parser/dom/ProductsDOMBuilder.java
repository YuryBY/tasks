package com.epam.householdappliances.parser.dom;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.householdappliances.parser.IProductsBuilder;
import com.epam.householdappliances.parser.object.Category;
import com.epam.householdappliances.parser.object.Product;
import com.epam.householdappliances.parser.object.Subcategory;
import com.epam.householdappliances.resource.ParsingElementsEnum;

public final class ProductsDOMBuilder implements IProductsBuilder {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger
			.getLogger(ProductsDOMBuilder.class);

	private static ProductsDOMBuilder instance = null;

	private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

	private DocumentBuilder docBuilder;

	private ProductsDOMBuilder() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOG.error(e);

		}
	}

	public static ProductsDOMBuilder getInstance() {
		if (instance == null) {
			INSTANCE_LOCK.lock();
			try {
				if (instance == null) {
					instance = new ProductsDOMBuilder();
				}
			} finally {
				INSTANCE_LOCK.unlock();
			}
		}
		return instance;

	}

	@Override
	public Set<Category> buildCategoriesSet(String fileName) {
		Set<Category> categories = new HashSet<Category>();
		Document doc = null;

		try {
			doc = docBuilder.parse(fileName);
		} catch (SAXException | IOException e) {
			LOG.error(e);
		}

		Element root = doc.getDocumentElement();

		NodeList categoryNodes = root
				.getElementsByTagName(ParsingElementsEnum.TNS_CATEGORY
						.getValue());
		for (int i = 0; i < categoryNodes.getLength(); i++) {
			Element categoryElement = (Element) categoryNodes.item(i);

			Category currentCategory = new Category();
			currentCategory
					.setName(categoryElement
							.getAttribute(ParsingElementsEnum.NAME_ATTRIBUTE
									.getValue()));
			categories.add(currentCategory);

			NodeList subcategoryNodes = categoryElement
					.getElementsByTagName(ParsingElementsEnum.TNS_SUBCATEGORY
							.getValue());
			for (int j = 0; j < subcategoryNodes.getLength(); j++) {
				Element subcategoryElement = (Element) subcategoryNodes.item(j);
				Subcategory currentSubcategory = new Subcategory();
				currentSubcategory.setName(subcategoryElement
						.getAttribute(ParsingElementsEnum.NAME_ATTRIBUTE
								.getValue()));
				currentCategory.addSubcategory(currentSubcategory);

				NodeList productNodes = subcategoryElement
						.getElementsByTagName(ParsingElementsEnum.TNS_PRODUCT
								.getValue());
				for (int k = 0; k < productNodes.getLength(); k++) {
					Element productElement = (Element) productNodes.item(k);
					Product currentProduct = buildProduct(productElement);
					currentSubcategory.addProduct(currentProduct);
				}

			}
			docBuilder.reset();
		}

		return categories;
	}

	private Product buildProduct(Element productElement) {
		Product currentProduct = new Product();
		currentProduct.setName(productElement
				.getAttribute(ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
		currentProduct.setProducer(getElementText(productElement,
				ParsingElementsEnum.TNS_PRODUCER.getValue()));
		currentProduct.setModel(getElementText(productElement,
				ParsingElementsEnum.TNS_MODEL.getValue()));
		currentProduct.setDateIssue(getElementText(productElement,
				ParsingElementsEnum.TNS_DATE.getValue()));
		currentProduct.setColor(getElementText(productElement,
				ParsingElementsEnum.TNS_COLOR.getValue()));

		if (isContained(productElement,
				ParsingElementsEnum.TNS_PRICE.getValue())) {
			float price = Float.parseFloat(getElementText(productElement,
					ParsingElementsEnum.TNS_PRICE.getValue()));
			currentProduct.setPrice(price);
		} else {
			currentProduct.setMissMessage(getElementText(productElement,
					ParsingElementsEnum.TNS_MISS.getValue()));
		}
		return currentProduct;
	}

	private String getElementText(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}

	private boolean isContained(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		if (nList.item(0) == null) {
			return false;
		} else {
			return true;
		}
	}

}
