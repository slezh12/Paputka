package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class WantToGoParseInfo extends ParseInfo {

	/**
	 * Public Constructor for WantToGoParseInfo
	 *
	 */
	public WantToGoParseInfo(BasicDataSource source) {
		super(source);
	}

	/**
	 * Returns WantToGoes created by certain user
	 *       
	 *	@param UserID
	 *			user who created wantTogoes
	 * @param ArrayList<WantToGo>
	 * 			list of wantToGo objects
	 */
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
				boolean validation = rs.getBoolean("Validation");
				WantToGo want = new WantToGo(fromLongitude, fromLatitude,
						toLongitude, toLatitude, type, ID, userID, title,
						validation);
				wanttogos.add(want);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wanttogos;
	}

	/**
	 * Returns ID of last event, created by user
	 *       
	 *	@param UserID
	 *			user who created event
	 * @return Integer  
	 * 			Id of last user's event
	 */
	public int getLastID(int userID) {
		int ret = 0;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectEvent(userID, "WantToGo", 1);
			if (rs.next())
				ret = rs.getInt("ID");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Returns WantToGo by given ID
	 *       
	 *	@param UserID
	 *			user who created wantTogo
	 * @param WantToGo
	 * 			WantToGo with Given ID
	 */
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
				boolean validation = rs.getBoolean("Validation");
				want = new WantToGo(fromLongitude, fromLatitude, toLongitude,
						toLatitude, type, ID, userID, title, validation);
				base.CloseConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return want;
	}
	
	/**
	 * Returns WantToGoForEveryDays with given ID
	 *       
	 *	@param id
	 *			id for WantToGo event
	 * @param ArrayList<WantToGoForEveryDay>
	 * 			list of wantToGoForEveryDay objects
	 */
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

	/**
	 * Returns WantToGoes which is held only once by wantToGo ID
	 *       
	 *	@param id
	 *			id of wantToGo event
	 * @param ArrayList<WantToGo>
	 * 			list of wantToGo objects
	 */
	public ArrayList<Timestamp> getOnce(int id) {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ArrayList<Timestamp> list = new ArrayList<Timestamp>();
		try {
			ResultSet rs = base.selectByID("WantToGoDates", id, "WantToGoID");
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
