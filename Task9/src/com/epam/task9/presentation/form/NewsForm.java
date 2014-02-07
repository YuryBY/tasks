package com.epam.task9.presentation.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.task9.model.News;

public class NewsForm {

	private News news;
	private List<News> newsList;
	private Map<Integer, Boolean> choosenNews = new HashMap<Integer, Boolean>();

	public NewsForm() {
		news = new News();
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {

		this.news = news;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public Map<Integer, Boolean> getChoosenNews() {
		return choosenNews;
	}

	public void setChoosenNews(Map<Integer, Boolean> choosenNews) {
		this.choosenNews = choosenNews;
	}

}
