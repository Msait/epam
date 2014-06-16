package com.university.repository.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.university.repository.admin.AdminRepository;
import com.university.repository.admin.jdbc.AdminRepositoryJdbc;
import com.university.repository.applicant.ApplicantRepository;
import com.university.repository.applicant.jdbc.ApplicantRepositoryJdbc;
import com.university.repository.faculty.FacultyRepository;
import com.university.repository.faculty.jdbc.FacultyRepositoryJdbc;
import com.university.repository.journal.JournalRepository;
import com.university.repository.journal.jdbc.JournalRepositoryJdbc;
import com.university.repository.subject.SubjectRepository;
import com.university.repository.subject.jdbc.SubjectRepositoryJdbc;

public class MySqlDaoFactory extends DaoFactory {
    
    private static final Logger logger = Logger.getLogger( MySqlDaoFactory.class );
    private static BoneCP connectionPool = null;
    
    public static void createConnectionPool( BoneCPConfig config ) {
	try {
	    logger.debug("Create connectionPool");
	    connectionPool = new BoneCP(config);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public static void closeConnectionPool() {
	connectionPool.shutdown();
    }
    
    @Override
    public Connection getConnection() throws SQLException {
	Connection connection = null;
	
	logger.debug("Connect to database");
	connection = connectionPool.getConnection();
	return connection;
    }

    @Override
    public ApplicantRepository getApplicantsDao() {
        return new ApplicantRepositoryJdbc(this);
    }

    @Override
    public SubjectRepository getSubjectDao() {
	return new SubjectRepositoryJdbc(this);
    }

    @Override
    public FacultyRepository getFacultyDao() {
	return new FacultyRepositoryJdbc(this);
    }

    @Override
    public AdminRepository getAdminDao() {
	return new AdminRepositoryJdbc(this);
    }

    @Override
    public JournalRepository getJournalDao() {
	return new JournalRepositoryJdbc(this);
    }

}
