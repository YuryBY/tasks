package com.epam.stuffshop.command.catalog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.stuffshop.command.util.TransformerSource;
import com.epam.stuffshop.exception.ControllerException;
import com.epam.stuffshop.resource.ParametersEnum;
import com.epam.stuffshop.resource.XSLTPathsConstant;
import com.epam.stuffshop.validation.ValidationUtil;

public final class AddProduct extends CatalogManageCommand {

	private static final String REDIRECT_URL = "controller?command=to products&categoryName={0}&subcategoryName={1}";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {

		StringWriter stringWriter = new StringWriter();
		String XSLFile = XSLTPathsConstant.VALIDATE_PRODUCT_FILE_PATH;

		ValidationUtil validationUtil = new ValidationUtil();

		Transformer transformer;
		try {
			transformer = TransformerSource.newTransformer(XSLFile);
			setTransformerParameters(request, validationUtil, transformer);
		} catch (TransformerConfigurationException e) {
			LOG.error(e);
			throw new ControllerException(e);
		}

		long modifydeDateBefore = 0;
		readLock.lock();
		try {
			modifydeDateBefore = new File(XSLTPathsConstant.XML_FILE_PATH)
					.lastModified();
			transformer.transform(new StreamSource(inputFile),
					new StreamResult(stringWriter));
		} catch (TransformerException e) {
			LOG.error(e);
			throw new ControllerException("Transformation is failed. ", e);
		} finally {
			readLock.unlock();
		}

		stringWriter.getBuffer().setLength(0);

		if (validationUtil.validationResult()) {

			XSLFile = XSLTPathsConstant.ADD_PRODUCT_FILE_PATH;
			try {
				transformer = TransformerSource.newTransformer(XSLFile);
			} catch (TransformerConfigurationException e) {
				LOG.error(e);
				throw new ControllerException(e);
			}
			setTransformerParameters(request, validationUtil, transformer);

			readLock.lock();
			try {
				transformer.transform(new StreamSource(inputFile),
						new StreamResult(stringWriter));
			} catch (TransformerException e) {
				LOG.error(e);
				throw new ControllerException(e);
			} finally {
				readLock.unlock();
			}

			writeLock.lock();
			try (FileWriter fileWriter = new FileWriter(inputFile)) {
				long modifydeDateAfter = new File(inputFile).lastModified();

				if (modifydeDateAfter != modifydeDateBefore) {
					stringWriter.getBuffer().setLength(0);
					transformer.transform(new StreamSource(inputFile),
							new StreamResult(stringWriter));
				}
				
				fileWriter.append(stringWriter.getBuffer());
				fileWriter.flush();
			} catch (IOException e) {
				LOG.error(e);
				throw new ControllerException(e);
			} catch (TransformerException e) {
				LOG.error(e);
			} finally {
				writeLock.unlock();
			}

			try {
				response.sendRedirect(MessageFormat.format(
						REDIRECT_URL,
						request.getParameter(ParametersEnum.CATEGORY_NAME_JSP
								.getParameterName()),
						request.getParameter(ParametersEnum.SUBCATEGORY_NAME_JSP
								.getParameterName())));
			} catch (IOException e) {
				LOG.error(e);
				throw new ControllerException(e);
			}

		} else {

			XSLFile = XSLTPathsConstant.PRODUCT_FORM_FILE_PATH;
			try {
				transformer = TransformerSource.newTransformer(XSLFile);
			} catch (TransformerConfigurationException e) {
				LOG.error(e);
				throw new ControllerException(e);
			}

			validationUtil.detectFailedMessages();
			setTransformerParameters(request, validationUtil, transformer);

			readLock.lock();
			try {
				transformer.transform(new StreamSource(inputFile),
						new StreamResult(stringWriter));
			} catch (TransformerException e) {
				LOG.error(e);
				throw new ControllerException(e);
			} finally {
				readLock.unlock();
			}

			request.setAttribute("withoutPreviousPageButton", true);
			createPageContent(stringWriter, request);
			forvardRequest(request, response);
		}
	}

	private void setTransformerParameters(HttpServletRequest request,
			ValidationUtil validationFailedMessages, Transformer transformer) {

		super.setTransformerParameters(request, transformer);

		String nameMessage = validationFailedMessages.getNameMessage();
		String producerMessage = validationFailedMessages.getProducerMessage();
		String modelMessage = validationFailedMessages.getModelMessage();
		String priceMessage = validationFailedMessages.getPriceMessage();
		String dateMessage = validationFailedMessages.getDateMessage();
		String colorMessage = validationFailedMessages.getColorMessage();

		String productName = request
				.getParameter(ParametersEnum.PRODUCT_NAME_JSP
						.getParameterName());
		transformer.setParameter(
				ParametersEnum.PRODUCT_NAME_TRANSFORMARION.getParameterName(),
				productName);
		String productProducer = request
				.getParameter(ParametersEnum.PRODUCT_PRODUCER_JSP
						.getParameterName());
		transformer.setParameter(ParametersEnum.PRODUCT_PRODUCER_TRANSFORMARION
				.getParameterName(), productProducer);
		String productModel = request
				.getParameter(ParametersEnum.PRODUCT_MODEL_JSP
						.getParameterName());
		transformer.setParameter(
				ParametersEnum.PRODUCT_MODEL_TRANSFORMARION.getParameterName(),
				productModel);
		String productColor = request
				.getParameter(ParametersEnum.PRODUCT_COLOR_JSP
						.getParameterName());
		transformer.setParameter(
				ParametersEnum.PRODUCT_COLOR_TRANSFORMARION.getParameterName(),
				productColor);
		String productDate = request
				.getParameter(ParametersEnum.PRODUCT_DATE_ISSUE_JSP
						.getParameterName());
		transformer.setParameter(
				ParametersEnum.PRODUCT_DATE_ISSUE_TRANSFORMARION
						.getParameterName(), productDate);
		String productPrice = request
				.getParameter(ParametersEnum.PRODUCT_PRICE_JSP
						.getParameterName());

		if (isGoodsPresent(productPrice)) {
			transformer.setParameter(
					ParametersEnum.PRODUCT_PRICE_TRANSFORMARION
							.getParameterName(), productPrice);
		} else {
			transformer.setParameter(
					ParametersEnum.PRODUCT_IS_MISS_TRANSFORMARION
							.getParameterName(), productPrice);
		}

		if (validationFailedMessages != null) {
			transformer.setParameter(
					ParametersEnum.VALIDATION_UTIL_OBJECT.getParameterName(),
					validationFailedMessages);
		}

		if (nameMessage != null) {
			transformer.setParameter(
					ParametersEnum.WRONG_PRODUCT_NAME.getParameterName(),
					nameMessage);
		}
		if (producerMessage != null) {
			transformer.setParameter(
					ParametersEnum.WRONG_PRODUCER.getParameterName(),
					producerMessage);
		}
		if (modelMessage != null) {
			transformer
					.setParameter(
							ParametersEnum.WRONG_MODEL.getParameterName(),
							modelMessage);
		}
		if (dateMessage != null) {
			transformer.setParameter(
					ParametersEnum.WRONG_DATE.getParameterName(), dateMessage);
		}
		if (colorMessage != null) {
			transformer
					.setParameter(
							ParametersEnum.WRONG_COLOR.getParameterName(),
							colorMessage);
		}
		if (priceMessage != null) {
			transformer
					.setParameter(
							ParametersEnum.WRONG_PRICE.getParameterName(),
							priceMessage);
		}
	}

	private boolean isGoodsPresent(String state) {
		boolean isPresent = false;

		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]*");
		Matcher matcher = pattern.matcher(state);
		if (matcher.find()) {
			isPresent = true;
		}

		return isPresent;
	}

}
