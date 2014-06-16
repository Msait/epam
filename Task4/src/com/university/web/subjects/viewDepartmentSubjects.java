package com.university.web.subjects;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.faculty.Faculty;
import com.university.domain.subject.Subject;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.faculty.FacultyService;
import com.university.service.faculty.FacultyServiceImpl;
import com.university.service.subject.SubjectService;
import com.university.service.subject.SubjectServiceImpl;
import com.university.web.controller.Controller;

public class viewDepartmentSubjects implements Controller {
    private final Logger logger = Logger.getLogger( viewDepartmentSubjects.class );

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) {
	
	
	Integer id = Integer.parseInt(request.getParameter("id"));
	logger.debug("get attribute faculty id: " + id);

	DaoFactory daoFactory = DaoFactory.getDaoFactory(ConnTypes.MySqlDaoFactory);
	SubjectService serviceSubjectService = new SubjectServiceImpl( daoFactory.getSubjectDao() );
	FacultyService facultyService = new FacultyServiceImpl( daoFactory.getFacultyDao() );

	List<Subject> facultySubjects = serviceSubjectService.getAllSubjectsForFaculty(id);
	Faculty faculty = facultyService.findFaculty(id);
	
	logger.debug("return list of subjects");
	request.setAttribute("facultySubjects", facultySubjects);
	request.setAttribute("faculty", faculty);

	return "viewDepartmentSubjects";
    }

}
