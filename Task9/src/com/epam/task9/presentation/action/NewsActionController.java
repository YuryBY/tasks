package com.epam.task9.presentation.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.epam.task9.database.dao.INewsDAO;
import com.epam.task9.model.News;
import com.epam.task9.presentation.LocaleBean;
import com.epam.task9.presentation.form.NewsForm;
import com.epam.task9.resource.NewsFeedConstants;

public class NewsActionController {

	/** logger use Log4j library. @see (http://logging.apache.org/log4j/) */
	private static final Logger LOG = Logger
			.getLogger(NewsActionController.class);

	private INewsDAO newsDAO;

	// private NewsForm newsForm;

	public NewsActionController() {
		LOG.info("Action controller is construct!");
	}

	public void setNewsDAO(INewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public String list(NewsForm newsForm) throws Exception {

		newsForm.setNewsList(newsDAO.getList());

		return NewsFeedConstants.NEWS_LIST_PAGE;
	}

	public String add(NewsForm newsForm) throws Exception {

		newsForm.setNews(new News());

		return NewsFeedConstants.ADD_EDIT_NEWS_PAGE;
	}

	public String edit(NewsForm newsForm) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();

		int id = Integer.valueOf(params
				.get(NewsFeedConstants.REQUEST_PARAMETER_NEWS_ID));

		newsForm.setNews(newsDAO.fetchById(id));

		return NewsFeedConstants.ADD_EDIT_NEWS_PAGE;
	}

	public String delete(NewsForm newsForm) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();

		if (params.containsKey(NewsFeedConstants.REQUEST_PARAMETER_NEWS_ID)) {

			int id = Integer.valueOf(params
					.get(NewsFeedConstants.REQUEST_PARAMETER_NEWS_ID));
			newsDAO.delete(id);

		} else {

			Map<Integer, Boolean> selectedNews = newsForm.getChoosenNews();
			Iterator<Map.Entry<Integer, Boolean>> iterator = selectedNews
					.entrySet().iterator();
			List<Integer> idList = new ArrayList<Integer>();

			while (iterator.hasNext()) {
				Map.Entry<Integer, Boolean> unit = iterator.next();

				if (unit.getValue().equals(true)) {
					idList.add(unit.getKey());
				}
			}
			newsDAO.deleteList(idList);
		}

		return NewsFeedConstants.NEWS_LIST_PAGE
				+ NewsFeedConstants.PAGE_REDIRECT_APPENDER;

	}

	public String save(NewsForm newsForm) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();

		int id = Integer.valueOf(params
				.get(NewsFeedConstants.REQUEST_PARAMETER_NEWS_ID));
		News news = newsForm.getNews();

		if (id != 0) {
			newsDAO.update(news);
		} else {
			int createdNewsId = newsDAO.save(news);
			news.setId(createdNewsId);
		}

		return NewsFeedConstants.NEWS_DETAILS_PAGE
				+ NewsFeedConstants.PAGE_REDIRECT_APPENDER;
	}

	public String details(NewsForm newsForm) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();

		int id = Integer.valueOf(params
				.get(NewsFeedConstants.REQUEST_PARAMETER_NEWS_ID));
		newsForm.setNews(newsDAO.fetchById(id));

		return NewsFeedConstants.NEWS_DETAILS_PAGE;
	}

	public void initForm(NewsForm newsForm) throws Exception {
		newsForm.setNewsList(newsDAO.getList());
	}

	public void changeLocale(LocaleBean localeBean) throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, String> params = facesContext.getExternalContext()
				.getRequestParameterMap();

		String locale = params.get(NewsFeedConstants.REQUEST_PARAMETER_LOCALE);

		localeBean.setLocale(locale);

	}

}
