package com.university.web.applicants;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.journal.JournalService;
import com.university.service.journal.JournalServiceImpl;
import com.university.web.controller.Controller;

public class ViewAllStudents implements Controller {
    private final Logger logger = Logger.getLogger(ViewAllStudents.class);

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) {

	Map<Applicant, Faculty> appplicantList = null;
	Map<Applicant, Faculty> students = new LinkedHashMap<Applicant, Faculty>();

	DaoFactory daoFactory = DaoFactory
		.getDaoFactory(ConnTypes.MySqlDaoFactory);
	JournalService journalService = new JournalServiceImpl(
		daoFactory.getJournalDao());

	appplicantList = journalService.getAllStudents();
	
	logger.debug("check if applicants is sorted");
	logger.debug("Show all applicants");
	show(appplicantList);
	
	// facultets
	Iterator<Map.Entry<Applicant, Faculty>> it = appplicantList.entrySet().iterator();
	while ( it.hasNext() ) {
	    
	    Map.Entry<Applicant, Faculty> pair = it.next();
	    Integer limits = pair.getValue().getFacultyLimit();
	    logger.debug("Faculty limit: " + limits);
	    
	    sumCreditsByFaculty(appplicantList, pair, limits, students);
	}

	logger.debug("return student list");
	request.setAttribute("students", students);
	
	return "viewAllStudents";
    }
    
    private void show (Map<Applicant, Faculty> l) {
	for (Applicant a : l.keySet()) {
	    System.out.println(a + "\n" + l.get(a));
	}
	
    }

    private void sumCreditsByFaculty(
	    Map<Applicant, Faculty> appplicantList,
	    Map.Entry<Applicant, Faculty> currentPair,
	    Integer lim, Map<Applicant, Faculty> resultList) {
	
	Iterator<Map.Entry<Applicant, Faculty>> innerIt = appplicantList.entrySet().iterator();
	while ( innerIt.hasNext() ) {
		Map.Entry<Applicant, Faculty> nextPair = innerIt.next();
		
		Integer current = currentPair.getValue().getFacultyId();
		Integer next = nextPair.getValue().getFacultyId();
		
		if ( next.equals(current) && lim > 0) {
		    logger.debug("check faculty [current, next] : " + current + " - " + next);
		    resultList.put(nextPair.getKey(), nextPair.getValue());
		    --lim;
		}
	    }
	
    }

}
