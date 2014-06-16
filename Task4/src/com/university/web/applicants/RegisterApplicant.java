package com.university.web.applicants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.applicant.Applicant;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.applicants.ApplicantService;
import com.university.service.applicants.ApplicantServiceImpl;
import com.university.web.controller.Controller;

public class RegisterApplicant implements Controller {
    
    private final Logger logger = Logger.getLogger( RegisterApplicant.class );

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	// collect all data from form
	logger.debug("Collect data from applicant");
	
	DaoFactory daoFactory = DaoFactory.getDaoFactory(ConnTypes.MySqlDaoFactory);
	ApplicantService applicantService = new ApplicantServiceImpl( daoFactory.getApplicantsDao() );
	
	Applicant applicant = new Applicant();
	Map<String, String[]> params = request.getParameterMap();
	
	Set<String> keys = params.keySet();
	List<Integer> exams = new ArrayList<Integer>();
	
	for (String key : keys) {
	    String val[] = params.get(key);
	    
	    if ( key.equals("aname") ) {
		applicant.setApplicantName(val[0]);
	    } else if ( key.equals("alastname") ) {
		applicant.setApplicantLastName(val[0]);
	    } else if ( key.equals("acertificate") ) {
		
		if ( checkVal(val[0]) ) {
		    applicant.setApplicantCertificate(Integer.parseInt( val[0] ));
		} else {
		    return "redirect:actionNotAllowed";
		}
		
	    } else if ( key.equals("faculty") ) {
		applicant.setApplicantFacultyId( Integer.parseInt( val[0] ));
	    } else {
		if ( checkVal(val[0]) ) {
		    exams.add( Integer.parseInt( val[0] ) );
		} else {
		    return "redirect:actionNotAllowed";
		}
	    }
	}
	
	applicant.setExams(exams);
	
	// update table applicants and applicantExams
	applicantService.insertApplicant(applicant);
	logger.debug( "New applicant is inserted" );
	
	return "redirect:/Task4";
    }
    
    private boolean checkVal(String val) {
	
	try {
	    Integer intValue = Integer.parseInt(val);
	    
	    if (intValue > 100 || intValue < 0) 
		return false;
	    
	} catch (NumberFormatException e) {
	    return false;
	}
	
	return true;
    }
}
