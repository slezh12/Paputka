package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class WantToGoParseInfo extends ParseInfo{

	public WantToGoParseInfo(BasicDataSource source) {
		super(source);
	}

	public ArrayList<WantToGo> getWantToGos(int userID) {
		ArrayList<WantToGo> wanttogos = new ArrayList<WantToGo>();
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("WantToGo",userID,"UserID");
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String title = rs.getString("Title");
				double fromLatitude = rs.getDouble("FromLatitude");
				double fromLongitude = rs.getDouble("FromLongitude");
				double toLatitude = rs.getDouble("ToLatitude");
				double toLongitude = rs.getDouble("ToLongitude");
				boolean type = rs.getBoolean("Type");
				WantToGo want = new WantToGo(fromLongitude, fromLatitude, toLongitude, toLatitude, type, ID, userID, title);
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
			ResultSet rs = base.selectEvent(userID,"WantToGo");
			if (rs.next()) 
				ret = rs.getInt("ID");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
