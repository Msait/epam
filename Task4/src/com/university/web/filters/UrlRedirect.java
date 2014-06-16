package com.university.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class UrlRedirect
 */
@WebFilter("/*")
public class UrlRedirect implements Filter {
    private final Logger logger = Logger.getLogger( UrlRedirect.class );

    /**
     * Default constructor.
     */
    public UrlRedirect() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {}

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest httpServletRequest, ServletResponse httpServletResponse,
	    FilterChain chain) throws IOException, ServletException {

	httpServletRequest.setCharacterEncoding( "UTF-8" );
	
	HttpServletRequest request = (HttpServletRequest) httpServletRequest;
	HttpServletResponse response = (HttpServletResponse) httpServletResponse;
	String path = request.getRequestURI().substring(request.getContextPath().length());
	
	logger.debug("come into filter webPath: " + path);
	if (path.startsWith("/resources") ){
	    chain.doFilter(request, response);
	} else {
	    StringBuffer url = new StringBuffer("/pages");
	    url.append(path);
	    
	    logger.debug("Redirect to: " + url);
	    
	    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(url.toString());
	    dispatcher.forward(httpServletRequest, httpServletResponse);
	    
	}
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
