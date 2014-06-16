package com.university.repository.applicant.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.repository.applicant.ApplicantRepository;
import com.university.repository.jdbc.ConnectionDaoUtil;
import com.university.repository.jdbc.DaoFactory;

public class ApplicantRepositoryJdbc implements ApplicantRepository {
    private DaoFactory factory;

    public ApplicantRepositoryJdbc(DaoFactory factory) {
	this.factory = factory;
    }

    @Override
    public boolean insertApplicant(Applicant applicant) {
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    String insertExamsSql = "INSERT INTO applicantexams (idApplicant, mark1, mark2, mark3) VALUES (?, ?, ?, ?)";
	    String insertApplicantSql = "INSERT INTO applicants (name, lastName, facultyId, certificate) VALUES (?,?,?,?)";

	    connection = factory.getConnection();
	    statement = connection.prepareStatement(insertApplicantSql,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, applicant.getApplicantName());
	    statement.setString(2, applicant.getApplicantLastName());
	    statement.setInt(3, applicant.getApplicantFacultyId());
	    statement.setDouble(4, applicant.getApplicantCertificate());

	    int affectedRows = statement.executeUpdate();
	    if (affectedRows == 0) {
		throw new SQLException(
			"Creating user failed, no rows affected.");
	    }

	    // applicant id
	    ResultSet generatedKeys = statement.getGeneratedKeys();
	    if (generatedKeys.next()) {
		applicant.setApplicantId(generatedKeys.getInt(1));
	    }

	    ConnectionDaoUtil.closeStatement(statement);

	    statement = connection.prepareStatement(insertExamsSql);
	    statement.setInt(1, applicant.getApplicantId());
	    statement.setDouble(2, applicant.getExams().get(0));
	    statement.setDouble(3, applicant.getExams().get(1));
	    statement.setDouble(4, applicant.getExams().get(2));

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
    public boolean deleteApplicant(int id) {
	Connection connection = null;
	Statement statement = null;

	try {
	    String deleteFromApplicants = "delete FROM applicants WHERE idApplicants = "
		    + id;
	    String deleteFromExams = "delete FROM applicantExams WHERE idApplicant = "
		    + id;

	    connection = factory.getConnection();

	    statement = connection.createStatement();
	    statement.addBatch(deleteFromApplicants);
	    statement.addBatch(deleteFromExams);

	    int result[] = statement.executeBatch();

	    if (result[0] < 0 || result[0] < 0)
		return false;

	    return true;

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return false;
    }

    @Override
    public Applicant findApplicant(int id) {
	Connection connection = null;
	PreparedStatement statement = null;
	Applicant applicant = null;
	ResultSet resultSet = null;

	try {
	    connection = factory.getConnection();
	    statement = connection
		    .prepareStatement("SELECT * FROM applicants WHERE idApplicants = ?");
	    statement.setInt(1, id);

	    resultSet = statement.executeQuery();

	    while (resultSet.next()) {
		applicant = new Applicant();
		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));
	    }

	    return applicant;

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return null;
    }

    @Override
    public Applicant findApplicant(String name) {
	Connection connection = null;
	PreparedStatement statement = null;
	Applicant applicant = null;
	ResultSet resultSet = null;

	try {
	    connection = factory.getConnection();
	    statement = connection
		    .prepareStatement("SELECT * FROM applicants WHERE name = ?");
	    statement.setString(1, name);

	    resultSet = statement.executeQuery();

	    while (resultSet.next()) {
		applicant = new Applicant();
		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));
	    }

	    return applicant;

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return null;
    }

    @Override
    public List<Applicant> getAllApplicant() {
	Connection connection = null;
	PreparedStatement statement = null;
	Applicant applicant = null;
	ResultSet resultSet = null;
	List<Applicant> applicantList = new ArrayList<Applicant>();

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement("SELECT * FROM applicants");
	    resultSet = statement.executeQuery();

	    while (resultSet.next()) {
		applicant = new Applicant();

		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));

		applicantList.add(applicant);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return applicantList;
    }

    @Override
    public Map<Applicant, Faculty> getAllApplicantWithFaculty() {
	String sql = "select "
		+ "idApplicants, name, lastName, facultyId, certificate, facultyName, f.limit, idExams, mark1, mark2, mark3 "
		+ "from " + "applicants, faculty as f, applicantexams "
		+ "where "
		+ "facultyId = idFaculty and idApplicants = idApplicant "
		+ "ORDER by idApplicants, name, lastName";

	Connection connection = null;
	PreparedStatement statement = null;
	Applicant applicant = null;
	Faculty faculty = null;
	ResultSet resultSet = null;
	Map<Applicant, Faculty> applicantList = new LinkedHashMap<Applicant, Faculty>();

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    resultSet = statement.executeQuery();

	    while (resultSet.next()) {
		applicant = new Applicant();

		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));
		applicant.setExam(resultSet.getInt(9));
		applicant.setExam(resultSet.getInt(10));
		applicant.setExam(resultSet.getInt(11));

		faculty = new Faculty();
		faculty.setFacultyId(resultSet.getInt(4));
		faculty.setFacultyName(resultSet.getString(6));
		faculty.setFacultyLimit(resultSet.getInt(7));
		faculty.setIdExams(resultSet.getInt(8));

		applicantList.put(applicant, faculty);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}

	return applicantList;
    }

    @Override
    public Map<Applicant, Faculty> getRegisteredApplicantWithFaculty() {
	return getRegisteredApplicants(true);
    }

    @Override
    public Map<Applicant, Faculty> getUnRegisteredApplicantWithFaculty() {
	return getRegisteredApplicants(false);
    }

    public Map<Applicant, Faculty> getRegisteredApplicants(boolean registered) {
	 String sql = null;
	if (registered) {
	    sql = "SELECT idApplicants, name, lastName, facultyId, certificate, facultyName,f.limit, idExams, mark1, mark2, mark3"
		    + "FROM "
		    + "applicants, applicantexams, faculty as f "
		    + "WHERE "
		    + "idApplicants in (SELECT idRegStudent as idApplicants FROM journal ) and idApplicants = idApplicant and idFaculty = facultyId "
		    + "ORDER by idApplicants, name, lastName";
	} else {
	    sql = "SELECT idApplicants, name, lastName, facultyId, certificate, facultyName,f.limit, idExams, mark1, mark2, mark3 "
		    + "FROM "
		    + "applicants, applicantexams, faculty as f "
		    + "WHERE "
		    + "idApplicants not in (SELECT idRegStudent as idApplicants FROM journal ) and idApplicants = idApplicant and idFaculty = facultyId "
		    + "ORDER by idApplicants, name, lastName";
	}
	Connection connection = null;
	PreparedStatement statement = null;
	Applicant applicant = null;
	Faculty faculty = null;
	ResultSet resultSet = null;
	Map<Applicant, Faculty> applicantList = new LinkedHashMap<Applicant, Faculty>();

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    resultSet = statement.executeQuery();

	    while (resultSet.next()) {
		applicant = new Applicant();

		applicant.setApplicantId(resultSet.getInt(1));
		applicant.setApplicantName(resultSet.getString(2));
		applicant.setApplicantLastName(resultSet.getString(3));
		applicant.setApplicantFacultyId(resultSet.getInt(4));
		applicant.setApplicantCertificate(resultSet.getInt(5));
		applicant.setExam(resultSet.getInt(9));
		applicant.setExam(resultSet.getInt(10));
		applicant.setExam(resultSet.getInt(11));

		faculty = new Faculty();
		faculty.setFacultyId(resultSet.getInt(4));
		faculty.setFacultyName(resultSet.getString(6));
		faculty.setFacultyLimit(resultSet.getInt(7));
		faculty.setIdExams(resultSet.getInt(8));

		applicantList.put(applicant, faculty);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    ConnectionDaoUtil.closeStatement(statement);
	    ConnectionDaoUtil.closeConnection(connection);
	}
	
	if ( applicantList.isEmpty() ) {
	    return null;
	}
	
	return applicantList;
    }


}
