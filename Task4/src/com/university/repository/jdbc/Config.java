package com.university.repository.jdbc;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jolbox.bonecp.BoneCPConfig;

@WebListener
public class Config implements ServletContextListener {

    private static final String ATTRIBUTE_NAME = "config";
    private static final String LOG4J_CONFIG_LOCATION = "log4j-config-location";
    private final Logger logger = Logger.getLogger( getClass() );

    public static Config getInstance(ServletContext context) {
	return (Config) context.getAttribute(ATTRIBUTE_NAME);
    }

    public void contextInitialized(ServletContextEvent sce) {
	
	ServletContext context = sce.getServletContext();
	String dbName = context.getInitParameter("dbname");
	String dbuser = context.getInitParameter("dbuid");
	String dbpass = context.getInitParameter("dbupass");
	String log4jConfigFile = context.getInitParameter(LOG4J_CONFIG_LOCATION);
	
	PropertyConfigurator.configure( context.getRealPath("") + File.separator + log4jConfigFile );
	logger.info("Logger Initialized successfuly");
	
	BoneCPConfig config = new BoneCPConfig();
	
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    config.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
	    config.setUsername(dbuser);
	    config.setPassword(dbpass);
	    config.setMinConnectionsPerPartition(5);
	    config.setMaxConnectionsPerPartition(10);
	    config.setPartitionCount(1);
	    
	    MySqlDaoFactory.createConnectionPool( config );
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	MySqlDaoFactory.closeConnectionPool();
    }

}
