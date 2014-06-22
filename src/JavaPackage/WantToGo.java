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

	/**
	 * Public constructor which returns WantToGo object.
	 * 
	 * @param fromLongitude
	 *            longitude of start place of want to go event.
	 * @param fromLatitude
	 *            latitude of start place of want to go event.
	 * @param toLongitude
	 *            longitude of finish place of want to go event.
	 * @param toLatitude
	 *            latitude of finish place of want to go event.
	 * @param type
	 *            type of want to go event, once or permanent.
	 * @param ID
	 *            unique ID of want to go event.
	 * @param userID
	 *            user who made want to go event.
	 * @param title
	 *            title of want to go event.
	 * @param validation
	 *            validation of want to go event.
	 */
	public WantToGo(double fromLongitude, double fromLatitude,
			double toLongitude, double toLatitude, boolean type, int ID,
			int userID, String title, boolean validation) {
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

	/**
	 * Returns longitude of start place of want to go event.
	 * 
	 * @return double fromLongitude.
	 */
	public double getFromLongitude() {
		return fromLongitude;
	}

	/**
	 * Returns latitude of start place of want to go event.
	 * 
	 * @return double fromLatitude.
	 */
	public double getFromLatitude() {
		return fromLatitude;
	}

	/**
	 * Returns longitude of finish place of want to go event.
	 * 
	 * @return double toLongitude.
	 */
	public double getToLongitude() {
		return toLongitude;
	}

	/**
	 * Returns latitude of finish place of want to go event.
	 * 
	 * @return double toLatitude.
	 */
	public double getToLatitude() {
		return toLatitude;
	}

	/**
	 * Returns unique ID of want to go event.
	 * 
	 * @return int ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Returns unique ID of user who made want to go event.
	 * 
	 * @return int userID.
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Returns title of want to go event.
	 * 
	 * @return string title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns type of want to go event.if it is true than it is held just
	 * once,otherwise it is permanent
	 * 
	 * @return boolean type.
	 */
	public boolean getType() {
		return type;
	}

	/**
	 * Returns validation of want to go event.if it is true than it is already
	 * held,otherwise it is permanent.
	 * 
	 * @return boolean type.
	 */
	public boolean getValidation() {
		return validation;
	}
}
