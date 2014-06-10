package JavaPackage;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class EventConnection extends BaseConnection {

	public EventConnection(BasicDataSource source) {
		super(source);
	}

	public void insertIntoRequestss(int userID, int places, double fee,
			double FromLongitude, double FromLatitude, double ToLongitude,
			double ToLatitude, String fromPlace, String toPlace, boolean type,
			boolean validation) {
		// code here
	}
	
	public void insertIntoDates(int eventID, String date) {
		// code here
	}
	
	public void insertIntoEveryday(int eventID, String sartTime, int day) {
		// code here
	}
	
	public void insertIntoParticipants(int eventID, int userID) {
		// code here
	}
	
	public void insertIntoComments(int eventID, int userID, String comment,String datetime) {
		// code here
	}
	
	public void updateEvents(int eventID, boolean validation) {
		// code here
	}
}
