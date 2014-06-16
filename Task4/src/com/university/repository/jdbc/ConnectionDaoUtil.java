package com.university.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDaoUtil {
    
    public static boolean closeConnection(Connection connection) {
	try {
	    // close connection
	    if ( connection != null ) {
	        if ( !connection.isClosed() ) {
	    	connection.close();
	        }
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}
	
	return true;
    }

    public static void closeStatement(PreparedStatement preparedStatement) {
	if (preparedStatement != null) {
	    try {
		preparedStatement.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }
    
    public static void closeStatement(Statement statement) {
	if (statement != null) {
	    try {
		statement.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }
    
}
