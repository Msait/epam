package com.university.repository.subject.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.university.domain.subject.Subject;
import com.university.repository.jdbc.ConnectionDaoUtil;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.subject.SubjectRepository;

public class SubjectRepositoryJdbc implements SubjectRepository {
    DaoFactory factory;

    public SubjectRepositoryJdbc(DaoFactory factory) {
	this.factory = factory;
    }

    @Override
    public Subject getSubject(int id) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Subject subject = null;

	try {
	    connection = factory.getConnection();
	    preparedStatement = connection
		    .prepareStatement("SELECT * FROM subjects WHERE idSubjects = ?");
	    preparedStatement.setInt(1, id);
	    resultSet = preparedStatement.executeQuery();
	    subject = new Subject();

	    while (resultSet.next()) {
		subject.setSubjectId(resultSet.getInt(1));
		subject.setSubjectName(resultSet.getString(2));
	    }

	    return subject;

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(preparedStatement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return null;
    }

    @Override
    public boolean insertSubject(Subject subject) {
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = factory.getConnection();
	    statement = connection
		    .prepareStatement("INSERT INTO subjects (subjectName) VALUES (?)");
	    statement.setString(1, subject.getSubjectName());
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
    public List<Subject> getAllSubjects() {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	List<Subject> subjectList = new ArrayList<Subject>();

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("SELECT * FROM subjects");
	    resultSet = statement.executeQuery();
	    Subject subject = null;

	    while (resultSet.next()) {
		subject = new Subject();
		subject.setSubjectId(resultSet.getInt(1));
		subject.setSubjectName(resultSet.getString(2));
		subjectList.add(subject);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return subjectList;
    }

    @Override
    public List<Subject> getAllSubjectsForFaculty(int id) {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	List<Subject> subjectList = new ArrayList<Subject>();

	try {
	    
	    String sql = "select s1, sub1.subjectName, s2, sub2.subjectName, s3, sub3.subjectName from "
	    	+ "((select idSubject1 as s1 from facultyExams where idFacultyExams = (select idExams from faculty where idFaculty=?)) as r1 join subjects as sub1 on idSubjects = s1),"
	    	+ "((select idSubject2 as s2 from facultyExams where idFacultyExams = (select idExams from faculty where idFaculty=?)) as r2 join subjects as sub2  on idSubjects = s2),"
	    	+ "((select idSubject3 as s3 from facultyExams where idFacultyExams = (select idExams from faculty where idFaculty=?)) as r3 join subjects as sub3  on idSubjects = s3)";
	    
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    statement.setInt(2, id);
	    statement.setInt(3, id);
	    
	    resultSet = statement.executeQuery();
	    Subject subject = null;

	    while (resultSet.next()) {
		subject = new Subject();
		subject.setSubjectId(resultSet.getInt(1));
		subject.setSubjectName(resultSet.getString(2));
		subjectList.add(subject);
		
		subject = new Subject();
		subject.setSubjectId(resultSet.getInt(3));
		subject.setSubjectName(resultSet.getString(4));
		subjectList.add(subject);
		
		subject = new Subject();
		subject.setSubjectId(resultSet.getInt(5));
		subject.setSubjectName(resultSet.getString(6));
		subjectList.add(subject);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return subjectList;
    }

}
