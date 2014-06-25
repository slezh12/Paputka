package JavaPackage;

import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class WantToGoConnection extends BaseConnection {

	/**
	 * Public constructor
	 * 
	 */
	public WantToGoConnection(BasicDataSource source) {
		super(source);
	}

	/**
	 * Void Inserts new WantToGo event
	 * 
	 * @param userID
	 *            creator of want to go
	 * @param title
	 *            title of wantTogo
	 * @param FromLongitude
	 *            start place longiuted
	 * @param FromLatitude
	 *            start place latitude
	 * @param ToLongitude
	 *            destination longitude
	 * @param ToLatitude
	 *           destination latitude
	 * @param type
	 * 		type of event(everyday or once)
	 */
	public void insertIntoWantToGo(int userID, String title,
			double FromLongitude, double FromLatitude, double ToLongitude,
			double ToLatitude, boolean type) {
		try {
			stmt.executeUpdate("INSERT INTO WantToGo (userID, title, FromLongitude, FromLatitude, ToLongitude, ToLatitude, Type, Validation) VALUES ("
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
					+ ToLatitude + ", " + type + "," + true + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Inserts into everyday events
	 *            
	 * @param wantToGoID
	 * 			main event ID
	 * 
	 * @param startTime
	 * 			starting time of event
	 * 
	 * @param endTime
	 * 			ending time of event
	 * 
	 * @param day 
	 * 			day when event is held
	 */
	public void insertIntoWantToGoEveryday(int wantToGoID, String startTime,
			String endTime, int day) {
		try {
			stmt.executeUpdate("INSERT INTO WantToGoEveryday(wantToGoID, Day, StartDate, EndDate) VALUES ("
					+ wantToGoID
					+ ", "
					+ day
					+ ","
					+ "'"
					+ startTime
					+ "'"
					+ ", " + "'" + endTime + "'" + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * INserts into Dates when wantToGo event is held
	 *
	 *            
	 * @param wantToGoID
	 * 			ID of want to go event
	 * 
	 * @param startTime
	 * 			Starting time event
	 * 
	 * @param endTime
	 * 			ending time of event
	 */
	public void insertIntoWantToGoDates(int wantToGoID, String startTime,
			String endTime) {
		try {
			stmt.executeUpdate("INSERT INTO WantToGoDates (wantToGoID, StartDate, EndDate) VALUES ("
					+ wantToGoID
					+ ", "
					+ "'"
					+ startTime
					+ "'"
					+ ","
					+ "'"
					+ endTime + "'" + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes WantToGo, Checks if
	 * It is every day or once. 
	 *            
	 * @param wantToGoID
	 * 			Which Event Is Deleted
	 * 
	 * @param type
	 * 			true if once, else false
	 * 
	 */
	public void deleteWantToGo(int wantToGoID, boolean type) {
		try {
			if (type) {
				stmt.executeUpdate("delete from WantToGoDates where WantToGoID ="+wantToGoID);
			} else {
				stmt.executeUpdate("delete from WantToGoEveryday where WantToGoID ="+wantToGoID);
			}
			stmt.executeUpdate("delete from WantToGo where ID ="+wantToGoID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
