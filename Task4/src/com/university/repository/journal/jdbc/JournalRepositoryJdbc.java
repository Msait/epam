package com.university.repository.journal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.domain.journal.Journal;
import com.university.repository.jdbc.ConnectionDaoUtil;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.journal.JournalRepository;

public class JournalRepositoryJdbc implements JournalRepository {
    
    private DaoFactory daoFactory;
    
    public JournalRepositoryJdbc(DaoFactory daoFactory) {
	this.daoFactory = daoFactory;
    }

    @Override
    public boolean insertStudentToJournal(int id) {
	String sql = "insert into journal (idRegStudent) value (?)";
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    
	    return statement.execute();
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return false;
    }

    @Override
    public Journal getJournal() {
	String sql = "select * from journal";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Journal journal = new Journal();
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement(sql);
	    
	    resultSet = statement.executeQuery();
	    
	    while ( resultSet.next() ) {
		journal.setJournalId( resultSet.getInt(1) );
		journal.setApplicantId( resultSet.getInt(2) );
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return journal;
    }

    @Override
    public boolean deleteStudentFromJournal(int id) {
	String sql = "delete from journal where idRegStudent = ?";
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    
	    return statement.execute();
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return false;
    }

    @Override
    public Applicant findStudentInJournal(int id) {
	String sql = "select idApplicants, name, lastName, facultyId, certificate, mark1, mark2, mark3 "
		+ "from "
		+ "journal, applicants, applicantexams  "
		+ "where idRegStudent = ? and idRegStudent = idApplicants and idApplicant = idRegStudent;";
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Applicant applicant = null;
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    
	    resultSet = statement.executeQuery();
	    
	    while ( resultSet.next() ) {
		applicant = new Applicant();
		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));
		applicant.setExam(resultSet.getInt(6));
		applicant.setExam(resultSet.getInt(7));
		applicant.setExam(resultSet.getInt(8));
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return applicant;
    }

    @Override
    public Map<Applicant, Faculty> getAllStudents() {
	String sql = "SELECT idApplicants, name, lastName, facultyId, certificate, mark1, mark2, mark3, facultyName, f.limit, f.idExams, "
		+ "SUM(mark1 + mark2 + mark3 + certificate) as mysum "
		+ "FROM journal, applicants, applicantexams, faculty as f "
		+ "WHERE idRegStudent = idApplicants and idApplicant = idApplicants and facultyId = idFaculty "
		+ "GROUP BY idApplicants "
		+ "ORDER BY mysum DESC, facultyName ASC ";
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Applicant applicant = null;
	Faculty faculty = null;
	Map<Applicant, Faculty> list = new LinkedHashMap<Applicant, Faculty>();
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement(sql);
	    
	    resultSet = statement.executeQuery();
	    
	    while ( resultSet.next() ) {
		applicant = new Applicant();
		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));
		applicant.setExam(resultSet.getInt(6));
		applicant.setExam(resultSet.getInt(7));
		applicant.setExam(resultSet.getInt(8));
		
		faculty = new Faculty();
		faculty.setFacultyId(resultSet.getInt(4));
		faculty.setFacultyName(resultSet.getString(9));
		faculty.setFacultyLimit(resultSet.getInt(10));
		faculty.setIdExams(resultSet.getInt(11));
		
		list.put(applicant, faculty);
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return list;
    }

}
