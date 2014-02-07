package com.epam.householdappliances.parser.sax;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.householdappliances.parser.object.Category;
import com.epam.householdappliances.parser.object.Product;
import com.epam.householdappliances.parser.object.Subcategory;
import com.epam.householdappliances.resource.ParsingElementsEnum;

final class ProductsHandler extends DefaultHandler {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger.getLogger(ProductsHandler.class);

	private EnumSet<ParsingElementsEnum> textEnum;
	private ParsingElementsEnum currentEnum;
	private Set<Category> categoryList;
	private Category currentCategory;
	private Subcategory currentSubcategory;
	private Product currentProduct;

	ProductsHandler() {
		categoryList = new HashSet<Category>();
		textEnum = EnumSet.range(ParsingElementsEnum.PRODUCER,
				ParsingElementsEnum.MISS);
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	@Override
	public void startDocument() {
		LOG.debug("SAX parser is started.");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		switch (localName) {
		case "product":
			currentProduct = new Product();
			currentProduct.setName(attributes
					.getValue(ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
			break;
		case "subcategory":
			currentSubcategory = new Subcategory();
			currentSubcategory.setName(attributes
					.getValue(ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
			break;
		case "category":
			currentCategory = new Category();
			currentCategory.setName(attributes
					.getValue(ParsingElementsEnum.NAME_ATTRIBUTE.getValue()));
			break;
		case "products":
			break;

		default:
			ParsingElementsEnum temp = ParsingElementsEnum.valueOf(localName
					.toUpperCase().replaceAll("-", "_"));
			if (textEnum.contains(temp)) {
				currentEnum = temp;
			} else {
				LOG.error("Value" + temp + "isn't identifyed!");
				throw new SAXException("Value isn't identifyed!");
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String text = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case PRODUCER:
				currentProduct.setProducer(text);
				break;
			case MODEL:
				currentProduct.setModel(text);
				break;
			case DATE_ISSUE:
				currentProduct.setDateIssue(text);
				break;
			case COLOR:
				currentProduct.setColor(text);
				break;
			case PRICE:
				currentProduct.setPrice(Float.parseFloat(text));
				break;
			case MISS:
				currentProduct.setMissMessage(text);
				break;
			default:
				LOG.error("Character isn't detected!");
				throw new SAXException("Character isn't detected!");
			}
		}
		currentEnum = null;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		switch (localName) {
		case "category":
			categoryList.add(currentCategory);
			break;
		case "subcategory":
			currentCategory.addSubcategory(currentSubcategory);
			break;
		case "product":
			currentSubcategory.addProduct(currentProduct);
			break;
		}
	}

	@Override
	public void endDocument() {
		LOG.debug("SAX parser is finished.");
	}

}
