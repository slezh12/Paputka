package JavaPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class ParseInfo {
	protected BasicDataSource source;

	/**
	 * Constructs ParseInfo object with provided BasicDataSource object.
	 * 
	 * @param BasicDataSource
	 *            BasicDataSource object representing connection pool.
	 */
	public ParseInfo(BasicDataSource source) {
		this.source = source;
	}

	/**
	 * gets private info about users by id of user,column name and name of
	 * table.
	 * 
	 * @param UserID
	 *            id of user.
	 * @param columnName
	 *            name of column.
	 * @param table
	 *            name of table.
	 * @return ret column name value.
	 */
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

	/**
	 * gets status info by ID of user.
	 * 
	 * @param id
	 *            id of user.
	 * @return ret returns status.
	 */
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

	/**
	 * gets telephon by user ID.
	 * 
	 * @param id
	 *            id of user.
	 * @return ret telephon.
	 */
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
