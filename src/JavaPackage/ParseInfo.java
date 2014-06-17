package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
