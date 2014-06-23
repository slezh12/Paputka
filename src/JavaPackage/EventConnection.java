package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class EventConnection extends BaseConnection {

	/**
	 * Constructs EventConnection object with provided BasicDataSource object.
	 * 
	 * @param source
	 *            BasicDataSource object representing connection pool.
	 */
	public EventConnection(BasicDataSource source) {
		super(source);
	}

	/**
	 * inserts new value into Events table.
	 * 
	 * @param userID
	 *            ID of user.
	 * @param places
	 *            number of places on event.
	 * @param fee
	 *            fee for participation on event.
	 * @param FromLongitude
	 *            longitude of start place.
	 * @param FromLatitude
	 *            latitude of start place.
	 * @param ToLongitude
	 *            longitude of finish place.
	 * @param ToLatitude
	 *            latitude of finish place.
	 * @param fromPlace
	 *            name of start place.
	 * @param toPlace
	 *            name of finish place.
	 * @param type
	 *            type of event,if true than it is held once,otherwise it is
	 *            permanent.
	 * @param validation
	 *            validation of event.
	 */
	public void insertIntoEvents(int userID, int places, double fee,
			double FromLongitude, double FromLatitude, double ToLongitude,
			double ToLatitude, String fromPlace, String toPlace, boolean type,
			boolean validation) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
							+ userID
							+ ","
							+ places
							+ ","
							+ fee
							+ ","
							+ FromLongitude
							+ ","
							+ FromLatitude
							+ ","
							+ ToLongitude
							+ ","
							+ ToLatitude
							+ ","
							+ "'"
							+ fromPlace
							+ "'"
							+ ","
							+ "'"
							+ toPlace
							+ "'"
							+ ","
							+ type + "," + validation + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserts new value into Dates table.
	 * 
	 * @param eventID
	 *            id of event.
	 * @param date
	 *            date of event.
	 */
	public void insertIntoDates(int eventID, String date) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Dates (eventID , date) VALUES("
							+ eventID + "," + "'" + date + "'" + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserts new value into Everyday table.
	 * 
	 * @param eventID
	 *            id of event.
	 * @param startTime
	 *            time when event starts.
	 * @param day
	 *            day of the week when event is held.
	 */
	public void insertIntoEveryday(int eventID, String startTime, int day) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Everyday (eventID,startTime,day) VALUES("
							+ eventID
							+ ","
							+ "'"
							+ startTime
							+ "'"
							+ ","
							+ day
							+ ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserts new value into Participants table.
	 * 
	 * @param eventID
	 *            ID of event.
	 * @param userID
	 *            ID of user.
	 */
	public void insertIntoParticipants(int eventID, int userID) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Participants (eventID,userID) VALUES("
							+ eventID + "," + userID + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * counts participants of concrete event.
	 * 
	 * @param eventID
	 *            ID of event.
	 * 
	 * @return result number of participants.
	 */
	public int getParticipantsByEventID(int eventID) {
		ResultSet rs;
		int result = 0;
		try {
			String name = "count(id)";
			rs = stmt.executeQuery("select " + name
					+ " from participants where eventID =" + eventID);
			rs.next();
			result = rs.getInt(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * inserts new value into Comments table.
	 * 
	 * @param eventID
	 *            id of event.
	 * @param userID
	 *            ID of user.
	 * @param comment
	 *            text of comment.
	 */
	public void insertIntoComments(int eventID, int userID, String comment) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Comments (eventID,userID,comment,date) VALUES("
							+ eventID
							+ ","
							+ userID
							+ ","
							+ "'"
							+ comment
							+ "'" + "," + " now() " + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * updates value of Events table.
	 * 
	 * @param eventID
	 *            id of event.
	 * @param validation
	 *            validation of event.
	 */
	public void updateEvent(int eventID, boolean validation) {
		try {
			super.stmt.executeUpdate("UPDATE Events Set Validation = "
					+ validation + " WHERE ID = " + eventID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * selects comments on concrete event which are sorted in decreasing order.
	 * 
	 * @param eventID
	 *            id of event.
	 */
	public ResultSet CommentsByID(int eventID) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM Comments WHERE EventID = "
					+ eventID + " order by ID desc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * inserts value into Requests table.
	 * 
	 * @param UserID
	 *            id of user.
	 * @param EventID
	 *            id of event.
	 * @param text
	 *            text of request.
	 */
	public void InsertIntoRequsets(int UserID, int EventID, String text) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Requests(UserID, EventID, Text, Acception, Date) VALUES ("
							+ UserID
							+ ","
							+ EventID
							+ ","
							+ "'"
							+ text
							+ "'"
							+ ", 0, now())");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * selects request for concrete user and event.
	 * 
	 * @param UserID
	 *            id of user.
	 * @param EventID
	 *            id of event.
	 * @return rs returns all info.
	 */
	public ResultSet Request(int UserID, int EventID) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM Requests Where UserID = "
					+ UserID + " and EventID = " + EventID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * selects last num(integer) events from Events table.
	 * 
	 * @param num
	 *            number of events.
	 * @return rs returns all info.
	 */
	public ResultSet selectLastEvents(int num) throws SQLException {
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM Events ORDER BY ID DESC LIMIT "
						+ num);
		return rs;
	}

}
