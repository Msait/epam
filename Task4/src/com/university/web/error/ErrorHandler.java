package com.university.web.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.web.applicants.RegisterApplicant;
import com.university.web.controller.Controller;

public class ErrorHandler implements Controller {
    private final Logger logger = Logger.getLogger( RegisterApplicant.class );

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	
	String id = (String) request.getParameter("faculty");
	logger.debug("Get Id: " + id);
	
	request.setAttribute("faculty", id);
	
	return "actionNotAllowed";
    }

}
