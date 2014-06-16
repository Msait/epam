package com.university.web.faculty;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.faculty.Faculty;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.faculty.FacultyService;
import com.university.service.faculty.FacultyServiceImpl;
import com.university.web.controller.Controller;

public class ViewAllFaculties implements Controller {
    private final Logger logger = Logger.getLogger(ViewAllFaculties.class);

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) {
	
	logger.error("Go into ViewAllFaculties Controller");
	DaoFactory factory = DaoFactory
		.getDaoFactory(ConnTypes.MySqlDaoFactory);

	FacultyService service = new FacultyServiceImpl(factory.getFacultyDao());

	List<Faculty> faculties = service.findAllFaculties();

	logger.debug("return faculties list");
	request.setAttribute("faculties", faculties);

	return "viewAllFaculties";
    }

}
