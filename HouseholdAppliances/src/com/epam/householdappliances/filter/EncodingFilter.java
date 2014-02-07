package com.epam.householdappliances.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class EncodingFilter. Sets CharacterEncoding
 * for request an response.
 * 
 * @author Yury Bakhmutski
 * @version 1.0
 * @since 2013-04-10
 */
@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter implements Filter {
	private String code;

	/**
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter("encoding");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();

		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		code = null;
	}
}
