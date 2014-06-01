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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void CloseConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet getInfoByMail(String name) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM Users where EMail = \""
				+ name + "\"");
		return rs;
	}

}
