package com.terrapay.bugmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.terrapay.bugmanagement.model.Bug;

public class BugDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/bugproject2?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	String INSERT_BUGS_SQL = "INSERT INTO bugs" + "  (email, priority, status, projectOwner, creationDate, "
			+ "completionDate,description) VALUES " + " (?, ?, ?, ?,?,?,?);";

	private static final String SELECT_ALL_BUGS = "select * from bugs";
	private static final String UPDATE_BUGS_SQL = "update bugs set email = ?,priority= ?, status =?,"
			+ " projectOwner =?, creationDate =?, completionDate =?, description =? where id = ?;";
	private static final String DELETE_BUGS_SQL = "delete from bugs where id = ?;";

	private static final String SELECT_BUG_BY_ID = "select id,email,priority,status,projectOwner ,creationDate ,completionDate ,description  from users where id =?";

	public BugDao() {

	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertBug(Bug bug) throws SQLException {
		System.out.println(INSERT_BUGS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BUGS_SQL)) {
			// preparedStatement.setInt(1, id);
			preparedStatement.setString(1, bug.getEmail());
			preparedStatement.setString(2, bug.getPriority());
			preparedStatement.setString(3, bug.getStatus());
			preparedStatement.setString(4, bug.getProjectOwner());
			preparedStatement.setString(5, bug.getCreationDate());
			preparedStatement.setString(6, bug.getCompletionDate());
			preparedStatement.setString(7, bug.getDescription());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public List<Bug> selectAllBugs() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Bug> bugs = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BUGS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String priority = rs.getString("priority");
				String status = rs.getString("status");
				String projectOwner = rs.getString("projectOwner");
				String creationDate = rs.getString("creationDate");
				String completionDate = rs.getString("completionDate");
				String description = rs.getString("description");
				bugs.add(new Bug(id, email, priority, status, projectOwner, creationDate, completionDate, description));
				System.out.println(bugs);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bugs;
	}

	public Bug selectBug(int id) {
		Bug bug = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BUG_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String email = rs.getString("email");
				String priority = rs.getString("priority");
				String status = rs.getString("status");
				String projectOwner = rs.getString("projectOwner");
				String creationDate = rs.getString("creationDate");
				String completionDate = rs.getString("completionDate");
				String description = rs.getString("description");
				bug = new Bug(id, email, priority, status, projectOwner, creationDate, completionDate, description);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bug;
	}

	public boolean updateBug(Bug bug) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BUGS_SQL);) {
			preparedStatement.setString(1, bug.getEmail());
			preparedStatement.setString(2, bug.getPriority());
			preparedStatement.setString(3, bug.getStatus());
			preparedStatement.setString(4, bug.getProjectOwner());
			preparedStatement.setString(5, bug.getCreationDate());
			preparedStatement.setString(6, bug.getCompletionDate());
			preparedStatement.setString(7, bug.getDescription());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean deleteBug(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BUGS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
