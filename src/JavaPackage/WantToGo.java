package JavaPackage;

public class WantToGo {
	private int ID;
	private int userID;
	private String title;
	private double fromLongitude;
	private double fromLatitude;
	private double toLongitude;
	private double toLatitude;
	private boolean type;
	private boolean validation;

	public WantToGo(double fromLongitude, double fromLatitude,
			double toLongitude, double toLatitude, boolean type, int ID,
			int userID,String title,boolean validation) {
		this.fromLatitude = fromLatitude;
		this.fromLongitude = fromLongitude;
		this.toLatitude = toLatitude;
		this.toLongitude = toLongitude;
		this.ID = ID;
		this.type = type;
		this.userID = userID;
		this.title = title;
		this.validation = validation;
	}
	
	public double getFromLongitude() {
		return fromLongitude;
	}
	
	public double getFromLatitude() {
		return fromLatitude;
	}
	
	public double getToLongitude() {
		return toLongitude;
	}
	
	public double getToLatitude() {
		return toLatitude;
	}

	public int getID() {
		return ID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean getType() {
		return type;
	}
	
	public boolean getValidation() {
		return validation;
	}
}
