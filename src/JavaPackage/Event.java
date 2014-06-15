package JavaPackage;

public class Event {
	private int ID;
	private int eventID;
	private double fee;
	private int userID;
	private int places;
	private Route route;
	private boolean validation;
	private boolean type;
	
	public Event(int ID,int eventID,double fee,int userID,int places,boolean validation,boolean type,Route route) {
		this.ID = ID;
		this.eventID = eventID;
		this.fee = fee;
		this.userID = userID;
		this.places = places;
		this.route = route;
		this.type = type;
		this.validation = validation;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getEventID() {
		return eventID;
	}
	
	public double getPrice() {
		return fee;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public int getPlaces() {
		return places;
	}
	
	public boolean getValidation() {
		return validation;
	}
	
	public boolean getType() {
		return type;
	}
	
	public Route getRoute() {
		return route;
	}
}
