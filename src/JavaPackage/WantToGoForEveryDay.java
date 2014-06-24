package JavaPackage;

import java.sql.Time;

public class WantToGoForEveryDay {
	private int day;
	private Time start;
	private Time finish;

	/**
	 * Public Constructor for WantToGoForEveryDay
	 *      
	 * @param day
	 *			day when event is held
	 *	@param start
	 *			start time of event
	 * @param finish
	 * 			end time of event
	 */
	public WantToGoForEveryDay(int day, Time start, Time finish) {
		this.day = day;
		this.start = start;
		this.finish = finish;
	}

	/**
	 * Returns day when event is held
	 *      
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Returns time when event starts
	 *      
	 */
	public Time getStart() {
		return start;
	}
	
	/**
	 * Returns time when event is finished
	 *      
	 */
	public Time getFinish() {
		return finish;
	}

}
