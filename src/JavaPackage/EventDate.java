package JavaPackage;

import java.sql.Time;

public class EventDate {
	private Time data;
	private int day;

	/**
	 * Public constructor which returns EventDate object.
	 * 
	 * @param day
	 *            day when event is held.
	 * @param date
	 *            time when event is held.
	 */
	public EventDate(int day, Time date) {
		this.day = day;
		this.data = date;
	}

	/**
	 * Event has date(day) when it is held,and it returns day corresponding to
	 * numbers from zero to six.Each refers to day.for example 0 is Monday,1
	 * Tuesday and so on,Sunday is 6.
	 * 
	 * @return int day.
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Returns Time of event.Event has the day,but also concrete time when it is
	 * held on that day,so this getter method returns Time object.
	 * 
	 * @return Time data.
	 */
	public Time getDate() {
		return this.data;
	}

}
