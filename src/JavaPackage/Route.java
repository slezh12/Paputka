package JavaPackage;

public class Route {
	private String fromPlace;
	private String toPlace;
	private double fromLongitude;
	private double fromLatitude;
	private double toLongitude;
	private double toLatitude;

	/**
	 * Public constructor which returns Route object.
	 * 
	 * @param fromPlace
	 *            name of start place of event.
	 * @param toPlace
	 *            name of finish place of event.
	 * @param fromLongitude
	 *            longitude of start place.
	 * @param fromLatitude
	 *            latitude of start place.
	 * @param toLongitude
	 *            longitude of finish place.
	 * @param toLatitude
	 *            latitude of finish place.
	 */
	public Route(String fromPlace, String toPlace, double fromLongitude,
			double fromLatitude, double toLongitude, double toLatitude) {
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.fromLatitude = fromLatitude;
		this.fromLongitude = fromLongitude;
		this.toLatitude = toLatitude;
		this.toLongitude = toLongitude;
	}

	/**
	 * Returns name of finish place of event.
	 * 
	 * @return string toPlace.
	 */
	public String getToPlace() {
		return toPlace;
	}

	/**
	 * Returns name of start place of event.
	 * 
	 * @return string fromPlace.
	 */
	public String getFromPlace() {
		return fromPlace;
	}

	/**
	 * Returns longitude of start place of event.
	 * 
	 * @return double fromLongitude.
	 */
	public double getFromLongitude() {
		return fromLongitude;
	}

	/**
	 * Returns latitude of start place of event.
	 * 
	 * @return double fromLatitude.
	 */
	public double getFromLatitude() {
		return fromLatitude;
	}

	/**
	 * Returns longitude of finish place of event.
	 * 
	 * @return double toLongitude.
	 */
	public double getToLongitude() {
		return toLongitude;
	}

	/**
	 * Returns latitude of finish place of event.
	 * 
	 * @return double toLatitude.
	 */
	public double getToLatitude() {
		return toLatitude;
	}
}
