package com.epam.task9.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;

import org.apache.log4j.Logger;

import com.epam.task9.exeption.LogicalException;

public class News implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private static final Logger LOG = Logger.getLogger(News.class);

	/* exception message */
	private static final String INCORRECT_DATE_FORMAT = "Incorrect date format, must be \"MM/dd/yyyy\"";

	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "publication_date")
	private Calendar date;

	@Column(name = "brief")
	private String brief;

	@Column(name = "content")
	private String content;

	public News() {
		date = Calendar.getInstance();
	}

	public News(int id) {
		this.id = id;
		date = Calendar.getInstance();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStringDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date dateForFormat = date.getTime();
		return dateFormat.format(dateForFormat);

	}

	public void setStringDate(String stringDate) throws LogicalException {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			LOG.error(INCORRECT_DATE_FORMAT, e);
			parsedDate = Calendar.getInstance().getTime();
		}
		Calendar date = Calendar.getInstance();
		date.setTime(parsedDate);
		this.setDate(date);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		News copy = (News) super.clone();
		copy.date = (Calendar) date.clone();
		return copy;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", title=" + title
				+ ", date=" + date + ", brief=" + brief + ", content="
				+ content + "]";
	}

}
