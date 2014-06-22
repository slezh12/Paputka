package JavaPackage;

import java.sql.Timestamp;

public class Request {
	private int ID;
	private int eventID;
	private String text;
	private int fromUserID;
	private int Acception;
	private Timestamp datetime;

	/**
	 * Public constructor which returns Request object.
	 * 
	 * @param ID
	 *            unique ID of request.
	 * @param eventID
	 *            ID of event on which this request is sent.
	 * @param text
	 *            text of request.
	 * @param fromUserID
	 *            ID of user who sent the request.
	 * @param Acception
	 *            indicator of request acceptance.
	 * @param datetime
	 *            date and time when request was sent.
	 */
	public Request(int ID, int eventID, String text, int fromUserID,
			int Acception, Timestamp datetime) {
		this.ID = ID;
		this.eventID = eventID;
		this.text = text;
		this.fromUserID = fromUserID;
		this.Acception = Acception;
		this.datetime = datetime;
	}

	/**
	 * Returns unique ID of Request.
	 * 
	 * @return int ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Returns unique eventID of event on which this request is sent.
	 * 
	 * @return int eventID.
	 */
	public int getEventID() {
		return eventID;
	}

	/**
	 * Returns text of Request.
	 * 
	 * @return string text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns unique ID of user who sent the Request.
	 * 
	 * @return int fromUserID.
	 */
	public int getFromUserID() {
		return fromUserID;
	}

	/**
	 * Returns 0 if request has not yet be seen by the event owner for whom
	 * request is sent,1 if the owner of event accepted request and 2 if
	 * rejected.
	 * 
	 * @return int Acception.
	 */
	public int getAcception() {
		return Acception;
	}

	/**
	 * Returns date and time when Request was sent.
	 * 
	 * @return Timestamp datetime.
	 */
	public Timestamp getDatetime() {
		return datetime;
	}
}
