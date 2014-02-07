package com.epam.hibernate.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.epam.hibernate.tag.util.PageCounter;

public final class NavigateTag extends TagSupport {
	private static final long serialVersionUID = -315887389278571222L;
	private static final Logger LOGGER = Logger.getLogger(NavigateTag.class);
	private int notesCount;
	private int notesOnPage;

	public void setNotesCount(int notesCount) {
		this.notesCount = notesCount;
	}

	public void setNotesOnPage(int notesOnPage) {
		this.notesOnPage = notesOnPage;
	}

	@Override
	public int doStartTag() throws JspTagException {
		JspWriter out = pageContext.getOut();
		String contextPath = pageContext.getRequest().getServletContext()
				.getContextPath();
		int pageCount = PageCounter.getPageCount(notesOnPage, notesCount);
		try {
			out.write("<div class='navigation'>");
			out.write("<a href='EmployeeAction.do?method=list&pageItem.itemOnPage="
					+ notesOnPage
					+ "&pageItem.currentPage=1' class='toFirstPage'>&lt;&lt;to first page</a>");
			out.write("<img src='" + contextPath
					+ "/img/back.png' class='previousButton' "
					+ "onclick='previous(" + notesCount + ", " + notesOnPage
					+ ")'/>");
			out.write("<div class='container'></div>");
			out.write("<img src='" + contextPath + "/img/next.png' "
					+ "class='nextButton' onclick='next(" + notesCount + ", "
					+ notesOnPage + ")'/>");
			out.write("<a href='EmployeeAction.do?method=list&pageItem.itemOnPage="
					+ notesOnPage
					+ "&pageItem.currentPage=" + pageCount
					+ "' class='toLastPage'>to last page&gt;&gt;</a>");
			out.write("</div>");
		} catch (IOException e) {
			LOGGER.error(e);
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
