package com.university.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.service.auth.AuthService;
import com.university.service.auth.AuthServiceImpl;
import com.university.web.controller.Controller;

public class LogOutPage implements Controller {
    private final Logger logger = Logger.getLogger( LogOutPage.class );

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	
	AuthService authService = new AuthServiceImpl();
	authService.logout(request, response);
	request.setAttribute("logged", "false");
	
	logger.debug("Session invalidate. Redirect to /viewAllFaculties");
	return "redirect:/Task4";
    }

}
