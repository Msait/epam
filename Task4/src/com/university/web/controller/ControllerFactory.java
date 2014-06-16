package com.university.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.university.web.admin.LogOutPage;
import com.university.web.admin.ConfirmRegistration;
import com.university.web.admin.ViewAdminPage;
import com.university.web.applicants.RegisterApplicant;
import com.university.web.applicants.ViewAllApplicants;
import com.university.web.applicants.ViewAllStudents;
import com.university.web.error.ErrorHandler;
import com.university.web.faculty.ViewAllFaculties;
import com.university.web.subjects.viewDepartmentSubjects;

public class ControllerFactory {
    private static final Map<String, Controller> controllers;
    private final static Logger logger = Logger.getLogger( ControllerFactory.class );
    
    static {
	controllers = new HashMap<String, Controller>();
	controllers.put("/viewAllFaculties", new ViewAllFaculties());
	controllers.put("/viewAllApplicants", new ViewAllApplicants());
	controllers.put("/viewDepartmentSubjects", new viewDepartmentSubjects());
	controllers.put("/viewAllStudents", new ViewAllStudents());
	controllers.put("/viewAdminPage", new ViewAdminPage());
	controllers.put("/registerApplicant", new RegisterApplicant());
	controllers.put("/confirmRegistration", new ConfirmRegistration());
	controllers.put("/actionNotAllowed", new ErrorHandler());
	controllers.put("/logOut", new LogOutPage());
    }
    
    public static Controller getController(HttpServletRequest request) {
	logger.info( "PathInfo: " + request.getPathInfo());
	String path = request.getPathInfo();
	
	Controller c = new ViewAllFaculties();
	
	if ( controllers.get( path ) != null ) {
	    c = controllers.get( path );
	} else {
	    logger.info( "No such page found" );
	}
	
	return c;
    }
    
}
