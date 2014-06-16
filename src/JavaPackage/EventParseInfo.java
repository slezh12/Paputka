package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
