package JavaPackage;

public class Event {
	private int ID;
	private double fee;
	private int userID;
	private int places;
	private Route route;
	private boolean validation;
	private boolean type;

	/**
	 * Public constructor which returns new Event object.
	 * 
	 * @param ID
	 *            unique ID of event.
	 * @param fee
	 *            price for the participants of event.
	 * @param userID
	 *            ID of user who made the event.
	 * @param places
	 *            number of places on event.
	 * @param validation
	 *            validation of event,if true event has not yet been held.
	 * @param type
	 *            type of event,if true once,false permanent.
	 * @param route
	 *            coordinates and names of to and from places.
	 */
	public Event(int ID, double fee, int userID, int places,
			boolean validation, boolean type, Route route) {
		this.ID = ID;
		this.fee = fee;
		this.userID = userID;
		this.places = places;
		this.route = route;
		this.type = type;
		this.validation = validation;
	}

	/**
	 * this is setter method for validation of event.
	 * 
	 * @param value
	 *            value which will be set to validation
	 */
	public void setValidation(boolean value) {
		this.validation = value;
	}

	/**
	 * Returns unique ID of event.
	 * 
	 * @return int ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Returns price which is set for event by user.
	 * 
	 * @return double fee.
	 */
	public double getPrice() {
		return fee;
	}

	/**
	 * Returns ID of user who posted event.
	 * 
	 * @return int userID.
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Returns number of places on event.
	 * 
	 * @return int places.
	 */
	public int getPlaces() {
		return places;
	}

	/**
	 * Returns validation of event.if validation is true it means that event has
	 * not yet finished,otherwise it returns false and it means that event is
	 * over.
	 * 
	 * @return boolean validation.
	 */
	public boolean getValidation() {
		return validation;
	}

	/**
	 * Returns type of event.if type is true than event is held just
	 * once,otherwise it is permanent.
	 * 
	 * @return boolean type.
	 */
	public boolean getType() {
		return type;
	}

	/**
	 * Returns Route object of event.which saves info about coordinates of
	 * places and it's names.
	 * 
	 * @return Route route.
	 */
	public Route getRoute() {
		return route;
	}
}
