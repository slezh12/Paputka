package JavaPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class BaseConnection {

	private Statement stmt;
	private Connection con;

	public BaseConnection(ServletContext context) {
		DataSource source = (DataSource) context.getAttribute("connectionPool");
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

	public ResultSet selectByUserID(String table, int ID, boolean whichTable) {
		ResultSet rs = null;
		String columnName = "";
		if (whichTable) 
			columnName = "UserID";
		else 
			columnName = "EventID";
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

}
