package com.university.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.service.auth.AuthService;
import com.university.service.auth.AuthServiceImpl;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ControlServlet.class);

    private void processRequest(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html;charset=UTF-8");

	String view = null;
	Controller controller = ControllerFactory.getController(request);
	AuthService authService = new AuthServiceImpl();

	logger.info("processRequest");

	try {
	    view = controller.handleRequest(request, response);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	if (authService.isAuthorized(request, response)) {
	    request.setAttribute("logged", true);
	} else {
	    request.setAttribute("logged", false);
	}

	if (view.startsWith("redirect:")) {
	    response.sendRedirect(view.substring(9));
	} else {
	    dispatch(request, response, view);
	}
    }

    private void dispatch(HttpServletRequest request,
	    HttpServletResponse response, String view) throws ServletException,
	    IOException {

	String prefix = "/WEB-INF/view/";
	String suffix = ".jsp";

	logger.debug("forward to: " + prefix + view + suffix);

	RequestDispatcher dispatcher = request.getRequestDispatcher(prefix
		+ view + suffix);
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	processRequest(request, response);
    }

}
