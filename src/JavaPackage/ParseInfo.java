package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class ParseInfo {
	protected BasicDataSource source;

	public ParseInfo(BasicDataSource source) {
		this.source = source;
	}

	public String getPrivateInfo(int UserID, String columnName, String table) {
		String ret = "";
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID(table, UserID, "UserID");
			if (rs.next())
				ret = rs.getString(columnName);
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public String getInfoAboutStatuses(int id) {
		String ret = "";
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("Statuses", id, "UserID");
			if (rs.next())
				ret = rs.getString("status");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public String getInfoAboutTel(int id) {
		String ret = "";
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("Tel", id, "UserID");
			if (rs.next())
				ret = rs.getString("PhoneNumber");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<Event> getEvents(){
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ResultSet rs = base.selectAll("Events");
		ArrayList<Event> res = new ArrayList<Event>();
		try {
			while(rs.next()){
				int ID = rs.getInt("ID");
				int UserID = rs.getInt("UserID");
				int Places = rs.getInt("Places");
				double fee = rs.getDouble("Fee");
				double FromLongitude = rs.getDouble("FromLongitude");
				double FromLatitude = rs.getDouble("FromLatitude");
				double ToLongitude = rs.getDouble("ToLongitude");
				double ToLatitude = rs.getDouble("ToLatitude");
				String FromPlace = rs.getString("FromPlace");
				String ToPlace = rs.getString("ToPlace");
				Boolean Type = rs.getBoolean("Type");
				Boolean Validation = rs.getBoolean("Validation");
				Route r = new Route(FromPlace, ToPlace, FromLongitude, FromLatitude, ToLongitude, ToLatitude);
				Event e = new Event(ID, fee, UserID, Places, Validation, Type, r);
				res.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.CloseConnection();
		return res;
		
	}

}
