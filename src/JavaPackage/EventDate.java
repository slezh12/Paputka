package JavaPackage;

import java.sql.Time;

public class EventDate {
	private Time data;
	private int day;
	public EventDate(int day, Time date){
		this.day = day;
		this.data = date;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public Time getDate(){
		return this.data;
	}
	
	
}
