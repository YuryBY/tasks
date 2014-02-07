package com.epam.stuffshop.command.catalog;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.stuffshop.command.util.TransformerSource;
import com.epam.stuffshop.exception.ControllerException;
import com.epam.stuffshop.resource.XSLTPathsConstant;

public final class ShowAddForm extends CatalogManageCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ControllerException {

		StringWriter stringWriter = new StringWriter();
		String XSLFile = XSLTPathsConstant.PRODUCT_FORM_FILE_PATH;

		Transformer transformer;
		try {
			transformer = TransformerSource.newTransformer(XSLFile);
			setTransformerParameters(request, transformer);
		} catch (TransformerConfigurationException e) {
			LOG.error(e);
			throw new ControllerException("Transformer isn't gotten. ", e);
		}
		readLock.lock();
		try {
			transformer.transform(new StreamSource(inputFile),
					new StreamResult(stringWriter));
		} catch (TransformerException e) {
			LOG.error(e);
			throw new ControllerException("Transformation is failed. ", e);
		} finally {
			readLock.unlock();
		}

		request.setAttribute("withoutPreviousPageButton", true);

		createPageContent(stringWriter, request);
		forvardRequest(request, response);

	}

}
