package com.epam.hibernate.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.epam.hibernate.tag.util.PageCounter;

public final class CurrentPageTag extends TagSupport {
	private static final long serialVersionUID = -5325791386677202706L;
	private static final Logger LOGGER = Logger
			.getLogger(CurrentPageTag.class);
	private int pageNumber;
	private int noteCountOnPage;
	private int noteCount;
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setNoteCountOnPage(int noteCountOnPage) {
		this.noteCountOnPage = noteCountOnPage;
	}

	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}

	@Override
	public int doStartTag() throws JspTagException {
			JspWriter out = pageContext.getOut();
			try {
				int pageCount = PageCounter.getPageCount(noteCountOnPage, noteCount);
				out.write("<div class='currentPage'>Current page: <i>" + pageNumber 
						+ "</i> of <i>" + pageCount + "</i></div>");
			} catch (IOException e) {
				LOGGER.error(e);
				throw new JspTagException(e.getMessage());
			}
		return SKIP_BODY;
	}

}
