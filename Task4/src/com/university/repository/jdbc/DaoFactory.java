package com.university.repository.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.university.repository.admin.AdminRepository;
import com.university.repository.applicant.ApplicantRepository;
import com.university.repository.faculty.FacultyRepository;
import com.university.repository.journal.JournalRepository;
import com.university.repository.subject.SubjectRepository;

public abstract class DaoFactory {

    public enum ConnTypes {MySqlDaoFactory};
    
    /**
     * Get DaoFactory
     * @param type connection type
     * @return instance DaoFactory
     */
    public static DaoFactory getDaoFactory(ConnTypes type) {
	switch (type) {
	case MySqlDaoFactory:
	    return new MySqlDaoFactory();

	default:
	    return null;
	}
    }
    
    public abstract ApplicantRepository getApplicantsDao();
    public abstract SubjectRepository getSubjectDao();
    public abstract FacultyRepository getFacultyDao();
    public abstract AdminRepository getAdminDao();
    public abstract JournalRepository getJournalDao();
    public abstract Connection getConnection() throws SQLException;
    
}
