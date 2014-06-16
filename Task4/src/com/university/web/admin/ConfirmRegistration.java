package com.university.web.admin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.journal.JournalService;
import com.university.service.journal.JournalServiceImpl;
import com.university.web.controller.Controller;

public class ConfirmRegistration implements Controller {
    private final Logger logger = Logger.getLogger( ConfirmRegistration.class );

    @Override
    public String handleRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	
	String[] checked = request.getParameterValues("applicant");
	List<String> studentIds = Arrays.asList( checked );
	
	
	DaoFactory daoFactory = DaoFactory.getDaoFactory(ConnTypes.MySqlDaoFactory);
	JournalService journalService = new JournalServiceImpl( daoFactory.getJournalDao() );
	
	logger.debug("insert list of applicant ids to journal");
	logger.debug("insert list: " + studentIds);
	
	for (String string : studentIds) {
	    Integer id = Integer.parseInt(string);
	    journalService.insertToJournal( id );
	}
	return "redirect:viewAdminPage";
    }

}
