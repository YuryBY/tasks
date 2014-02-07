package com.epam.hibernate.tag.util;

public final class PageCounter {
	private PageCounter() {}
	
	public static int getPageCount(int itemOnPage, int itemCount) {
		int pageCount = itemCount / itemOnPage;
		if(itemCount % itemOnPage != 0) {
			pageCount++;
		}
		return pageCount;
	}

}
