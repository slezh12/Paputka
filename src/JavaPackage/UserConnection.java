package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class UserConnection extends BaseConnection {

	public UserConnection(BasicDataSource source) {
		super(source);
	}

	public void insertIntoUsers(String firstName, String lastName,
			String eMail, String password, boolean gender, String birthdate) {
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
					+ gender + "," + birthdate + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getInfoByMail(String name) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM Users where EMail = \""
				+ name + "\"");
		return rs;
	}

	public ResultSet selectRatings(int secondID) throws SQLException {
		ResultSet rs = null;
		// code here
		return rs;
	}

	public void insertIntoRequestss(int userID, int eventID, String text,
			int acception, String date) {
		// code here
	}

	public void updateRequestss(int requestID, int acception) {
		// code here
	}
	
	public void update(String table, String column, String value, int UserID) {
		// code here
	}
	
	public void insert(String table, String column, String value, int UserID) {
		// code here
	}
	
	public void insertIntoRatings(int firstID, int secondID, int rating) {
		// code here
	}

}
