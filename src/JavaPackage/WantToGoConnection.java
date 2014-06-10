package JavaPackage;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class WantToGoConnection extends BaseConnection {

	public WantToGoConnection(BasicDataSource source) {
		super(source);
	}

	public void insertIntoRequestss(int userID, String title,
			double FromLongitude, double FromLatitude, double ToLongitude,
			double ToLatitude, boolean type) {
		// code here
	}
	
	public void insertIntoWantToGoEveryday(int wantToGoID, String sartTime, String endTime,int day) {
		// code here
	}
	
	public void insertIntoWantToGoDates(int wantToGoID, String sartTime, String endTime) {
		// code here
	}
}
