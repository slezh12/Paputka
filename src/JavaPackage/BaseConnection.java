package JavaPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class BaseConnection {

	protected Statement stmt;
	private Connection con;

	/**
	 * Constructs BaseConnection object with provided BasicDataSource object.
	 * 
	 * @param BasicDataSource
	 *            BasicDataSource object representing connection pool.
	 */
	public BaseConnection(BasicDataSource source) {
		try {
			con = source.getConnection();
			stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for closing connection.
	 * 
	 */
	public void CloseConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for many tables,it is not concrete,for example it
	 * takes info from tables like:statuses,tel and so on.
	 * 
	 * @param table
	 *            table is the name of table from which info is carried.
	 * @param ID
	 *            ID is unique number of value in table.
	 * @param columnName
	 *            name of column in the table.
	 * @return rs ResultSet which contains all info.
	 */
	public ResultSet selectByID(String table, int ID, String columnName) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM " + table + " where "
					+ columnName + " = \"" + ID + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Returns all info from the table which is provided.
	 * 
	 * @param table
	 *            table is the name of table from which info is carried.
	 * @return rs ResultSet which contains all info.
	 */
	public ResultSet selectAll(String table) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM " + table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * Selects last num(integer) events of concrete user.
	 * 
	 * @param userID
	 *            ID of user.
	 * @param table
	 *            table is the name of table in the base.
	 * @param num
	 *            number of results which should be carried out.
	 * @return rs ResultSet which contains all info
	 */
	public ResultSet selectEvent(int userID, String table, int num)
			throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM " + table
				+ " Where UserID  = " + userID + " ORDER BY ID DESC LIMIT "
				+ num);
		return rs;
	}

}
