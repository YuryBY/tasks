package com.epam.hibernate.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.epam.hibernate.servlet.EmployeeController;

public final class ContextContainer {
	public static final ApplicationContext SPRING_CONTEXT = new FileSystemXmlApplicationContext(
			EmployeeController.getServletPath() + Constant.BEAN_PATH);

	private ContextContainer() {
	}

}
