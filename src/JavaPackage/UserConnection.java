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
					+ gender + "," + "'" + birthdate + "'" + ")");
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
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM Ratings where SecondID = "
						+ secondID);
		return rs;
	}

	public void insertIntoRequestss(int userID, int eventID, String text,
			int acception, String date) {
		try {
			stmt.executeUpdate("INSERT INTO Requests (UserID , EventID, Text , Acception ,  Date) VALUES("
					+ userID
					+ ","
					+ eventID
					+ ","
					+ "'"
					+ text
					+ "'"
					+ ","
					+ acception + "," + date + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateRequestss(int requestID, int acception) {
		try {
			stmt.executeUpdate("UPDATE  Requests Set  Acception = " + acception
					+ " WHERE ID = " + requestID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String table, String column, String value, int UserID) {
		try {
			stmt.executeUpdate("UPDATE " + table + " Set " + column + " = "
					+ "'" + value + "'" + " WHERE UserID = " + UserID);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insert(String table, String column, String value, int UserID) {
		try {
			stmt.executeUpdate("INSERT INTO " + table + "( " + column + ","
					+ "UserID) VALUES(" + "'" + value + "'" + "," + UserID
					+ ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertIntoRatings(int firstID, int secondID, int rating) {
		try {
			stmt.executeUpdate("INSERT INTO RATINGS (FirstID, SecondID, Rating) VALUES( "
					+ firstID + ", " + secondID + ", " + rating + " )");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// shentan ro modis requesti
	public ResultSet getUsersRequests(int UserID) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("select Requests.ID, EventID, Requests.UserID, Text, Acception, Date from Requests join Events on EventID = Events.ID where Events.UserID = "
						+ UserID);
		return rs;
	}

}
