package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class WantToGoParseInfo extends ParseInfo {

	public WantToGoParseInfo(BasicDataSource source) {
		super(source);
	}

	public ArrayList<WantToGo> getWantToGos(int userID) {
		ArrayList<WantToGo> wanttogos = new ArrayList<WantToGo>();
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("WantToGo", userID, "UserID");
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String title = rs.getString("Title");
				double fromLatitude = rs.getDouble("FromLatitude");
				double fromLongitude = rs.getDouble("FromLongitude");
				double toLatitude = rs.getDouble("ToLatitude");
				double toLongitude = rs.getDouble("ToLongitude");
				boolean type = rs.getBoolean("Type");
				WantToGo want = new WantToGo(fromLongitude, fromLatitude,
						toLongitude, toLatitude, type, ID, userID, title);
				wanttogos.add(want);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wanttogos;
	}

	public int getLastID(int userID) {
		int ret = 0;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectEvent(userID, "WantToGo");
			if (rs.next())
				ret = rs.getInt("ID");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public WantToGo getWantToGo(int id, int userID) {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		WantToGo want = null;
		try {
			ResultSet rs = base.selectByID("wanttogo", id, "id");
			if (rs.next()) {
				int ID = rs.getInt("id");
				String title = rs.getString("Title");
				double fromLatitude = rs.getDouble("FromLatitude");
				double fromLongitude = rs.getDouble("FromLongitude");
				double toLatitude = rs.getDouble("ToLatitude");
				double toLongitude = rs.getDouble("ToLongitude");
				boolean type = rs.getBoolean("Type");
				want = new WantToGo(fromLongitude, fromLatitude, toLongitude,
						toLatitude, type, ID, userID, title);
				base.CloseConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return want;
	}

	public ArrayList<WantToGoForEveryDay> getEveryDay(int id) {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ArrayList<WantToGoForEveryDay> list = new ArrayList<WantToGoForEveryDay>();
		try {
			ResultSet rs = base
					.selectByID("WantToGoEveryday", id, "WantToGoID");
			while (rs.next()) {
				int day = rs.getInt("day");
				Time start = rs.getTime("StartDate");
				Time finish = rs.getTime("EndDate");
				WantToGoForEveryDay temp = new WantToGoForEveryDay(day, start,
						finish);
				list.add(temp);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Timestamp> getOnce(int id) {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ArrayList<Timestamp> list = new ArrayList<Timestamp>();
		try {
			ResultSet rs = base
					.selectByID("WantToGoDates", id, "WantToGoID");
			rs.next();
			Timestamp start = rs.getTimestamp("StartDate");
			Timestamp finish = rs.getTimestamp("EndDate");
			list.add(start);
			list.add(finish);
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
