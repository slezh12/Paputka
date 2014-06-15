package JavaPackage;

public class Route {
	private String fromPlace;
	private String toPlace;
	private double fromLongitude;
	private double fromLatitude;
	private double toLongitude;
	private double toLatitude;
	
	public Route(String fromPlace,String toPlace,double fromLongitude,double fromLatitude,double toLongitude,double toLatitude) {
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.fromLatitude = fromLatitude;
		this.fromLongitude = fromLongitude;
		this.toLatitude = toLatitude;
		this.toLongitude = toLongitude;
	}
	
	public String getToPlace() {
		return toPlace;
	}
	
	public String getFromPlace() {
		return fromPlace;
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
}
