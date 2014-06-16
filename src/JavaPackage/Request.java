package JavaPackage;

import java.sql.Timestamp;

public class Request {
	private int ID;
	private int eventID;
	private String text;
	private int fromUserID;
	private int Acception;
	private Timestamp datetime;
	
	public Request(int ID,int eventID,String text,int fromUserID,int Acception,Timestamp datetime) {
		this.ID = ID;
		this.eventID = eventID;
		this.text = text;
		this.fromUserID = fromUserID;
		this.Acception = Acception;
		this.datetime = datetime;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getEventID() {
		return eventID;
	}
	
	public String getText() {
		return text;
	}
	
	public int getFromUserID() {
		return fromUserID;
	}
	
	public int getAcception() {
		return Acception;
	}
	
	public Timestamp getDatetime() {
		return datetime;
	}
}
