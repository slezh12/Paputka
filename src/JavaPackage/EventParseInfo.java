package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class EventParseInfo extends ParseInfo {

	public EventParseInfo(BasicDataSource source) {
		super(source);
	}

	public Event getEventByID(int ID) {
		Event event = null;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("Events", ID, "ID");
			rs.next();
			int id = rs.getInt("ID");
			int places = rs.getInt("Places");
			String fromPlace = rs.getString("FromPlace");
			String toPlace = rs.getString("ToPlace");
			boolean validation = rs.getBoolean("Validation");
			boolean type = rs.getBoolean("Type");
			int userID = rs.getInt("UserID");
			double fee = rs.getDouble("Fee");
			double fromLatitude = rs.getDouble("FromLatitude");
			double fromLongitude = rs.getDouble("FromLongitude");
			double toLatitude = rs.getDouble("ToLatitude");
			double toLongitude = rs.getDouble("ToLongitude");
			Route route = new Route(fromPlace, toPlace, fromLongitude, fromLatitude, toLongitude, toLatitude);
			event = new Event(id, fee, userID, places, validation, type, route);
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	public ArrayList<Comment> getComments(int EventID){
		EventConnection ev = new EventConnection(source);
		ResultSet rs = ev.CommentsByID(EventID);
		ArrayList<Comment> result = new ArrayList<Comment>();
		 try {
			while (rs.next()) {
				 int UserID = rs.getInt("UserID");
				 String text = rs.getString("Comment");
				 Date date = rs.getDate("Date");
				 Comment com = new Comment(UserID, text, date);
				 result.add(com);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 ev.CloseConnection();
		return result;
	}
	
	public int getLastID(int userID) {
		int ret = 0;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectEvent(userID,"Events");
			if (rs.next()) 
				ret = rs.getInt("ID");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
