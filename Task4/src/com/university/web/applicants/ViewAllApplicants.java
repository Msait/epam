package com.university.web.applicants;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.applicants.ApplicantService;
import com.university.service.applicants.ApplicantServiceImpl;
import com.university.web.controller.Controller;

public class ViewAllApplicants implements Controller {
    private final Logger logger = Logger.getLogger( ViewAllApplicants.class );

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) {
	
	DaoFactory daoFactory = DaoFactory.getDaoFactory(ConnTypes.MySqlDaoFactory);
	ApplicantService service = new ApplicantServiceImpl(daoFactory.getApplicantsDao());
	
	Map<Applicant, Faculty> applicants =  service.getUnRegisteredApplicantWithFaculty();
	
	logger.debug("Applicant list: " + applicants.size());
	logger.debug("return applicants list");
	request.setAttribute("applicants", applicants);
	
	return "viewAllApplicants";
    }

}
