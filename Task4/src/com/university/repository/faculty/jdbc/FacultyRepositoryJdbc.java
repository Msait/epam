package com.university.repository.faculty.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.university.domain.faculty.Faculty;
import com.university.repository.faculty.FacultyRepository;
import com.university.repository.jdbc.ConnectionDaoUtil;
import com.university.repository.jdbc.DaoFactory;

public class FacultyRepositoryJdbc implements FacultyRepository {

    private DaoFactory factory;

    public FacultyRepositoryJdbc(DaoFactory factory) {
	this.factory = factory;
    }

    @Override
    public boolean insertFaculty(Faculty faculty) {
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("INSERT INTO faculty "
	    			+ "(facultyName, limit, idExams)"
	    			+" VALUES (?, ?, ?)");
	    
	    statement.setString(1, faculty.getFacultyName());
	    statement.setInt(2, faculty.getFacultyLimit());
	    statement.setInt(3, faculty.getIdExams());
	    
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
    public boolean deleteFaculty(int id) {
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("delete from faculty where idFaculty = ?");
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
    public List<Faculty> findAllFaculties() {

	List<Faculty>facultyList = new ArrayList<Faculty>();
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Faculty faculty = null;
	
	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("select * from faculty");
	    resultSet = statement.executeQuery();
	    
	    while ( resultSet.next() ) {
		faculty = new Faculty();
		faculty.setFacultyId(resultSet.getInt(1));
		faculty.setFacultyName(resultSet.getString(2));
		faculty.setFacultyLimit(resultSet.getInt(3));
		faculty.setIdExams(resultSet.getInt(4));
		
		facultyList.add(faculty);
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return facultyList;
    }

    @Override
    public Faculty findFaculty(int id) {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Faculty faculty = null;
	
	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("select * from faculty where idFaculty = ?");
	    statement.setInt(1, id);
	    resultSet = statement.executeQuery();
	    
	    while ( resultSet.next() ) {
		faculty = new Faculty();
		faculty.setFacultyId(resultSet.getInt(1));
		faculty.setFacultyName(resultSet.getString(2));
		faculty.setFacultyLimit(resultSet.getInt(3));
		faculty.setIdExams(resultSet.getInt(4));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return faculty;
    }

    @Override
    public Faculty findFaculty(String name) {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Faculty faculty = null;
	
	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("select * from faculty where facultyName = ?");
	    statement.setString(1, name);
	    resultSet = statement.executeQuery();
	    
	    while ( resultSet.next() ) {
		faculty = new Faculty();
		faculty.setFacultyId(resultSet.getInt(1));
		faculty.setFacultyName(resultSet.getString(2));
		faculty.setFacultyLimit(resultSet.getInt(3));
		faculty.setIdExams(resultSet.getInt(4));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return faculty;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
	String sql = "UPDATE faculty SET facultyName=?, limit=?, idExams=? WHERE idFaculty=?";
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setString(1, faculty.getFacultyName());
	    statement.setInt(2, faculty.getFacultyLimit());
	    statement.setInt(3, faculty.getIdExams());
	    statement.setInt(4, faculty.getFacultyId());
	    
	    return statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return false;
    }

}
