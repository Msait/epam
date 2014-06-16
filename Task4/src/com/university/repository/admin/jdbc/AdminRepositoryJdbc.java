package com.university.repository.admin.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.university.domain.admin.Admin;
import com.university.repository.admin.AdminRepository;
import com.university.repository.jdbc.ConnectionDaoUtil;
import com.university.repository.jdbc.DaoFactory;

public class AdminRepositoryJdbc implements AdminRepository {
    
    private DaoFactory daoFactory;
    
    public AdminRepositoryJdbc(DaoFactory factory) {
	daoFactory =  factory;
    }

    @Override
    public Admin findAdmin(int id) {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Admin admin = null;
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement( "select * from admins where idAdmin= ?" );
	    statement.setInt(1,  id);
	    
	    resultSet = statement.executeQuery();
	    
	    while (resultSet.next()) {
		admin = new Admin();
		admin.setAdminId( resultSet.getInt(1) );
		admin.setName( resultSet.getString(2) );
		admin.setPass( resultSet.getString(3) );
	    }
	    
	    return admin;
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return null;
    }

    @Override
    public Admin findAdmin(String name) {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Admin admin = null;
	
	try {
	    connection = daoFactory.getConnection();
	    statement = connection.prepareStatement( "select * from admins where adminName= ?" );
	    statement.setString(1,  name);
	    
	    resultSet = statement.executeQuery();
	    
	    while (resultSet.next()) {
		admin = new Admin();
		admin.setAdminId( resultSet.getInt(1) );
		admin.setName( resultSet.getString(2) );
		admin.setPass( resultSet.getString(3) );
	    }
	    
	    return admin;
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	return null;
    }

}
