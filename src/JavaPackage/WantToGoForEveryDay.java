package JavaPackage;

import java.sql.Time;

public class WantToGoForEveryDay {
	private int day;
	private Time start;
	private Time finish;

	public WantToGoForEveryDay(int day, Time start, Time finish) {
		this.day = day;
		this.start = start;
		this.finish = finish;
	}

	public int getDay() {
		return day;
	}

	public Time getStart() {
		return start;
	}

	public Time getFinish() {
		return finish;
	}

}
