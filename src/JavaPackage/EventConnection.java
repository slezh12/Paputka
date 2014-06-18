package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class EventConnection extends BaseConnection {

	public EventConnection(BasicDataSource source) {
		super(source);
	}

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

	public void insertIntoDates(int eventID, String date) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Dates (eventID , date) VALUES("
							+ eventID + "," + "'" + date + "'" + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	public void insertIntoParticipants(int eventID, int userID) {
		try {
			super.stmt
					.executeUpdate("INSERT INTO Participants (eventID,userID) VALUES("
							+ eventID + "," + userID + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
							+ "'" + ","  + " now() "  + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEvent(int eventID, boolean validation) {
		try {
			super.stmt.executeUpdate("UPDATE Events Set Validation = "
					+ validation + " WHERE ID = " + eventID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet CommentsByID(int eventID){
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM Comments WHERE EventID = "+ eventID + " order by ID desc");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
