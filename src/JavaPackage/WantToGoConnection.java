package JavaPackage;

import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class WantToGoConnection extends BaseConnection {

	public WantToGoConnection(BasicDataSource source) {
		super(source);
	}

	public void insertIntoRequestss(int userID, String title,
			double FromLongitude, double FromLatitude, double ToLongitude,
			double ToLatitude, boolean type) {
		try {
			stmt.executeUpdate("INSERT INTO WantToGo (userID, title, FromLongitude, FromLatitude, ToLongitude, ToLatitude, Type) VALUES ("
					+ userID
					+ ", "
					+ "'"
					+ title
					+ "'"
					+ ", "
					+ FromLongitude
					+ ", "
					+ FromLatitude
					+ ", "
					+ ToLongitude
					+ ", "
					+ ToLatitude + ", " + type + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertIntoWantToGoEveryday(int wantToGoID, String sartTime,
			String endTime, int day) {
		try {
			stmt.executeUpdate("INSERT INTO WantToGoEveryday(wantToGoID, Day, StartDate, EndDate) VALUES ("
					+ wantToGoID
					+ ", "
					+ day
					+ ","
					+ "'"
					+ sartTime
					+ "'"
					+ ", " + "'" + endTime + "'" + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertIntoWantToGoDates(int wantToGoID, String sartTime,
			String endTime) {
		try {
			stmt.executeUpdate("INSERT INTO WantToGoDates (wantToGoID, StartDate, EndDate) VALUES ("
					+ wantToGoID
					+ ", "
					+ "'"
					+ sartTime
					+ "'"
					+ ","
					+ "'"
					+ endTime + "'" + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
