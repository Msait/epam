package com.university.web.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.applicants.ApplicantService;
import com.university.service.applicants.ApplicantServiceImpl;
import com.university.service.auth.AuthService;
import com.university.service.auth.AuthServiceImpl;
import com.university.web.controller.Controller;

public class ViewAdminPage implements Controller {
    private final Logger logger = Logger.getLogger(ViewAdminPage.class);

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	DaoFactory daoFactory = DaoFactory
		.getDaoFactory(ConnTypes.MySqlDaoFactory);

	ApplicantService service = new ApplicantServiceImpl(
		daoFactory.getApplicantsDao());

	Map<Applicant, Faculty> applicants = null;
	AuthService authService = new AuthServiceImpl();

	if (authService.isAuthorized(request, response)) {
	    logger.debug("get applicants list");
	    applicants = service.getUnRegisteredApplicantWithFaculty();
	    request.setAttribute("applicants", applicants);

	    return "viewAdminPage";
	} else if (authService.login(request, response)) {

	    logger.debug("get applicants list");
	    applicants = service.getUnRegisteredApplicantWithFaculty();
	    request.setAttribute("applicants", applicants);

	    return "viewAdminPage";
	}

	logger.debug("SendRedirect to /");
	return "redirect:/Task4";

    }

}
