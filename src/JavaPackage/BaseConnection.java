package JavaPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class BaseConnection {

	protected Statement stmt;
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
	
	// order by date desc limit 1 <- es selectia
	public ResultSet selectEvent(int userID, String table) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " Where UserID  = " + userID);
		
		return rs;
	}


}
