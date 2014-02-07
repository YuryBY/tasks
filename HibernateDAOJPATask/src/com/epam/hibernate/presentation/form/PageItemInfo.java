package com.epam.hibernate.presentation.form;

import java.io.Serializable;

public final class PageItemInfo implements Serializable {
	private static final long serialVersionUID = 6245811582715681391L;
	private int itemOnPage;
	private int itemCount;
	private int currentPage;
	private String listItemPage;

	public int getItemOnPage() {
		return itemOnPage;
	}

	public void setItemOnPage(int itemOnPage) {
		this.itemOnPage = itemOnPage;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getListItemPage() {
		return listItemPage;
	}

	public void setListItemPage(String listItemPage) {
		this.listItemPage = listItemPage;
	}
}
