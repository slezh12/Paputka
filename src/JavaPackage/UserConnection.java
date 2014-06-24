package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class UserConnection extends BaseConnection {

	/**
	 * public constructor
	 * 
	 */
	public UserConnection(BasicDataSource source) {
		super(source);
	}

	/**
	 * Inserts into users, after registration
	 *          
	 * @param eMail
	 * 			email of user
	 * 
	 * @param firstName
	 * 			first name of user
	 * 
	 * @param lastName
	 * 			last name of user
	 * 
	 * @param password
	 * 			hashed password of user
	 * 
	 * @boolean gender
	 * 			true if male, false if female
	 * 
	 * @param birthdate
	 * 			birth date represented by string
	 * 
	 */
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

	/**
	 * Returns result set of user selected by Mail
	 *       
	 * @param name
	 * 			email of user
	 * 
	 * @return ResultSet
	 * 			resultSet of User
	 */
	public ResultSet getInfoByMail(String name) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM Users where EMail = \""
				+ name + "\"");
		return rs;
	}

	
	/**
	 * Inserts into requests, after user making it
	 *       
	 * @param userID
	 * 			sender of request
	 * 
	 * @param eventID
	 * 			event where request is sent
	 * 
	 * @param text
	 * 			string of request text
	 * 
	 * @param acception
	 * 			acception is 0, request has no answer
	 * 
	 * @param date
	 * 			when request is sent
	 */
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

	/**
	 * updates request after answering it
	 *       
	 * @param requestID
	 * 			requestID which is updated
	 * 
	 * @param acception
	 * 			result of answer
	 */
	public void updateRequests(int requestID, int acception) {
		try {
			stmt.executeUpdate("UPDATE  Requests Set  Acception = " + acception
					+ " WHERE ID = " + requestID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update of Rating, after giving rating to user
	 *       
	 * @param ratingID
	 * 			ID of rating
	 * 
	 * @return rating
	 * 			integer representing rating
	 */
	public void updateRatings(int ratingID, int rating) {
		try {
			stmt.executeUpdate("UPDATE  Ratings Set Rating= " + rating
					+ " WHERE ID = " + ratingID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * common update query for tables. update
	 * table set column equals to given value 
	 * for special user
	 *       
	 * @param table
	 * 			table which is updated
	 * 
	 * @param column
	 *			column which is updated
	 *
	 * @param value
	 * 			new value for column
	 * 
	 * @param UserID
	 * 			update for this user
	 *
	 */
	public void update(String table, String column, String value, int UserID) {
		try {
			stmt.executeUpdate("UPDATE " + table + " Set " + column + " = "
					+ "'" + value + "'" + " WHERE UserID = " + UserID);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * common insert query for tables. insert
	 * given values for column into table  
	 * for special user
	 *       
	 * @param table
	 * 			table were insert is made
	 * 
	 * @param column
	 *			columns were are values inserted
	 *
	 * @param value
	 * 			values
	 * 
	 * @param UserID
	 * 			ID of user
	 *
	 */
	public void insert(String table, String column, String value, int UserID) {
		try {
			stmt.executeUpdate("INSERT INTO " + table + "( " + column + ","
					+ "UserID) VALUES(" + "'" + value + "'" + "," + UserID
					+ ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * insert into ratings, which was given
	 * from FirstID user to secondID user
	 *       
	 * 
	 * @param firstID
	 * 			user who gave rating
	 * 
	 * @param secondID
	 * 			user who recieved rating
	 * 
	 * @param rating
	 * 			value of rating
	 *
	 */
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
	/**
	 * Requests which received user
	 *       
	 * 
	 * @param UserID
	 * 			ID of User
	 * @return resultSet
	 * 			result set of users
	 */
	public ResultSet getUsersRequests(int UserID) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("select Requests.ID, EventID, Requests.UserID, Text, Acception, Date from Requests join Events on EventID = Events.ID where Events.UserID = "
						+ UserID);
		return rs;
	}

	/**
	 * Returns ResultSet if participant is in event
	 * 
	 * @param firstUserID
	 * 			user who participated in event
	 * 
	 * @param secondUserID
	 * 			user who held event
	 * 
	 * @return ResultSet
	 * 			containing participantID, eventID,
	 * 			 event type, event validation
	 *
	 */
	public ResultSet getRaitingBoolean(int firstUserID, int secondUserID)
			throws SQLException {
		ResultSet rs = stmt
				.executeQuery("select Participants.ID,EventID,Events.Type,Events.Validation from participants join events on participants.eventid=events.id  where Participants.userID = "
						+ firstUserID + " and events.UserID = " + secondUserID);
		return rs;
	}

	/**
	 * returns rating which fristID user gave to SecondId user
	 * 
	 * @param FirstID
	 * 			user who made rating
	 * 
	 * @param SecondID
	 * 			user who received rating
	 * 
	 * @return ResultSet
	 * 			result set of given rating
	 *
	 */
	public ResultSet getRating(int FirstID, int SecondID) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("select * from Ratings where FirstID = "
						+ FirstID + " and SecondID=" + SecondID);
		return rs;
	}

	/**
	 * returns similar users after performed search
	 * 
	 * @param firstName
	 * 			first name of user who is searched
	 * 
	 * @param LastName
	 * 			last name of user who is searched
	 * 
	 * @return ResultSet
	 * 			Result set of users' with similar name and surname
	 *
	 */
	public ResultSet getUsersByName(String firstName, String LastName) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM Users WHERE FirstName like "
					+ "'%" + firstName + "%'" + "or LastName like" + "'%" + LastName + "%'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
