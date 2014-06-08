package JavaPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class BaseConnection {

	private Statement stmt;
	private Connection con;

	public BaseConnection(BasicDataSource source) {
		try {
			con = source.getConnection();
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void CloseConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getInfoByMail(String name) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM Users where EMail = \""
				+ name + "\"");
		return rs;
	}

	// viyenebt statusebis telefoebis da msgavsi infromaciis ID it amosagebad.
	public ResultSet selectByID(String table, int ID, String columnName) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM " + table + " where "
					+ columnName + " = \"" + ID + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet selectAll(String table) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM " + table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void insertIntoUsers(String firstName, String lastName,
			String eMail, String password, boolean gender, Date birthdate) {
		try {
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ firstName
					+ "'"
					+ ","
					+ "'"
					+ lastName
					+ "'"
					+ ","
					+ "'"
					+ eMail
					+ "'"
					+ ","
					+ "'"
					+ password
					+ "'"
					+ ","
					+ gender + birthdate + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
