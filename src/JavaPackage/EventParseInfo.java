package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class EventParseInfo extends ParseInfo {

	
	/**
	 * Public constructor
	 *
	 */
	public EventParseInfo(BasicDataSource source) {
		super(source);
	}

	/**
	 * returns Event by ID
	 * 
	 * @param ID
	 * 			id of Event
	 * 
	 * @return Event
	 * 			Event with given ID
	 *
	 */
	public Event getEventByID(int ID) {
		Event event = null;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("Events", ID, "ID");
			rs.next();
			int id = rs.getInt("ID");
			int places = rs.getInt("Places");
			String fromPlace = rs.getString("FromPlace");
			String toPlace = rs.getString("ToPlace");
			boolean validation = rs.getBoolean("Validation");
			boolean type = rs.getBoolean("Type");
			int userID = rs.getInt("UserID");
			double fee = rs.getDouble("Fee");
			double fromLatitude = rs.getDouble("FromLatitude");
			double fromLongitude = rs.getDouble("FromLongitude");
			double toLatitude = rs.getDouble("ToLatitude");
			double toLongitude = rs.getDouble("ToLongitude");
			Route route = new Route(fromPlace, toPlace, fromLongitude,
					fromLatitude, toLongitude, toLatitude);
			event = new Event(id, fee, userID, places, validation, type, route);
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	
	/**
	 * Returns List of last added events
	 * 
	 * @return ArrayList<Event>
	 * 			Last Added 15 Events
	 *
	 */
	public ArrayList<Event> getLastEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		EventConnection base = new EventConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectLastEvents(15);
			while (rs.next()) {
				int id = rs.getInt("ID");
				int places = rs.getInt("Places");
				String fromPlace = rs.getString("FromPlace");
				String toPlace = rs.getString("ToPlace");
				boolean validation = rs.getBoolean("Validation");
				boolean type = rs.getBoolean("Type");
				int user = rs.getInt("UserID");
				double fee = rs.getDouble("Fee");
				double fromLatitude = rs.getDouble("FromLatitude");
				double fromLongitude = rs.getDouble("FromLongitude");
				double toLatitude = rs.getDouble("ToLatitude");
				double toLongitude = rs.getDouble("ToLongitude");
				Route route = new Route(fromPlace, toPlace, fromLongitude,
						fromLatitude, toLongitude, toLatitude);
				Event event = new Event(id, fee, user, places, validation,
						type, route);
				events.add(event);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	/**
	 * returns Comments for given event
	 * 
	 * @param EventID
	 * 			id of Event
	 * 
	 * @return ArrayList<Comment>
	 * 			List of Comments for event
	 *
	 */
	public ArrayList<Comment> getComments(int EventID) {
		EventConnection ev = new EventConnection(source);
		ResultSet rs = ev.CommentsByID(EventID);
		ArrayList<Comment> result = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				int UserID = rs.getInt("UserID");
				String text = rs.getString("Comment");
				Date date = rs.getDate("Date");
				Comment com = new Comment(UserID, text, date);
				result.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ev.CloseConnection();
		return result;
	}

	/**
	 * Return Last ID of event Made by user
	 * 
	 * @param userID
	 * 			author of Event
	 * 
	 * @return Integer
	 * 			Last Event by user
	 *
	 */
	public int getLastID(int userID) {
		int ret = 0;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectEvent(userID, "Events", 1);
			if (rs.next())
				ret = rs.getInt("ID");
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * returns List of all Events
	 * 
	
	 * @return ArrayList<Event>
	 * 			all Events
	 *
	 */
	public ArrayList<Event> getEvents() {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ResultSet rs = base.selectAll("Events");
		ArrayList<Event> res = formEvents(rs);
		base.CloseConnection();
		return res;
	}

	/**
	 * returns Event made by certain user
	 * 
	 * @param UserID
	 * 			author of Event
	 * 
	 * @return ArrayList<Event>
	 * 			Events by user
	 *
	 */
	public ArrayList<Event> getUsersEvents(int UserID) {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ResultSet rs = base.selectByID("Events", UserID, "UserID");
		ArrayList<Event> res = formEvents(rs);
		base.CloseConnection();
		return res;
	}

	/**
	 * returns arraylist of events by given resultset
	 * 
	 * @param rs
	 * 			ResultSet of selected events
	 * 
	 * @return ArrayList<Event>
	 * 			parsed result set into list
	 *
	 */
	public ArrayList<Event> formEvents(ResultSet rs) {
		ArrayList<Event> res = new ArrayList<Event>();
		try {
			while (rs.next()) {
				int ID = rs.getInt("ID");
				int UserID = rs.getInt("UserID");
				int Places = rs.getInt("Places");
				double fee = rs.getDouble("Fee");
				double FromLongitude = rs.getDouble("FromLongitude");
				double FromLatitude = rs.getDouble("FromLatitude");
				double ToLongitude = rs.getDouble("ToLongitude");
				double ToLatitude = rs.getDouble("ToLatitude");
				String FromPlace = rs.getString("FromPlace");
				String ToPlace = rs.getString("ToPlace");
				Boolean Type = rs.getBoolean("Type");
				Boolean Validation = rs.getBoolean("Validation");
				Route r = new Route(FromPlace, ToPlace, FromLongitude,
						FromLatitude, ToLongitude, ToLatitude);
				Event e = new Event(ID, fee, UserID, Places, Validation, Type,
						r);
				res.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	
	/**
	 * Inserts Comment Into base
	 * 
	 * @param eventID
	 * 			event where comment is made
	 * 
	 * @param userID
	 * 			user who made commment
	 * 
	 * @param comment
	 * 			text body of comment
	 *
	 */
	public void InsertIntoComments(int eventID, int userID, String comment) {
		EventConnection ev = new EventConnection(source);
		ev.insertIntoComments(eventID, userID, comment);
		ev.CloseConnection();
	}

	/**
	 * Returns list of Dates for Event,
	 *  which is held everyday
	 * 
	 * @param eventID
	 * 			ID of Event
	 * 
	 * @param ArrayList<EventDate>
	 * 			Dates of Event
	 *
	 */
	public ArrayList<EventDate> EveryDayDates(int EventID) {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ArrayList<EventDate> res = new ArrayList<EventDate>();
		ResultSet rs = base.selectByID("Everyday", EventID, "EventID");
		try {
			while (rs.next()) {
				EventDate ev = new EventDate(rs.getInt("Day"),
						rs.getTime("StartTime"));
				res.add(ev);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.CloseConnection();
		return res;
	}

	/**
	 * Returns Time Stamp when event is held
	 * 
	 * @param EventID
	 * 			id of Event
	 * 
	 * @return Timestamp
	 * 			when Event is held
	 *
	 */
	public Timestamp EventDate(int EventID) {
		Timestamp dt = null;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		ResultSet rs = base.selectByID("Dates", EventID, "EventID");
		try {
			while (rs.next()) {
				dt = rs.getTimestamp("Date");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.CloseConnection();
		return dt;
	}

	/**
	 * Returns boolean if user has sent
	 * request to given event
	 * 
	 * @param UserID
	 * 			sender of request
	 * 
	 * @param EventID
	 * 			Event receiving request
	 * 
	 * @return boolean
	 * 			true if has sent, esle false
	 *
	 */
	public boolean HasRequest(int EventID, int UserID) {
		int check = 0;
		EventConnection ev = new EventConnection(source);
		ResultSet rs = ev.Request(UserID, EventID);
		try {
			while (rs.next())
				check++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ev.CloseConnection();
		return (check != 0);
	}

	/**
	 * Returns if two events are similar
	 * 
	 * @param Want
	 * 			want To go Event
	 * 
	 * @param temp
	 * 			second Event
	 * 
	 * @return boolean
	 * 			true if is similar, else false
	 *
	 */
	private boolean onceToOnce(WantToGo want, Event temp) {
		Timestamp current = null;
		boolean result = false;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		WantToGoParseInfo parse = new WantToGoParseInfo(
				(BasicDataSource) source);
		ArrayList<Timestamp> times = parse.getOnce(want.getID());
		ResultSet rs = base.selectByID("dates", temp.getID(), "eventID");
		try {
			if (rs.next()) {
				current = rs.getTimestamp("date");
			}
			if (current.after(times.get(0)) && current.before(times.get(1))) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		base.CloseConnection();
		return result;
	}

	/**
	 * Returns if two events are similar
	 * 
	 * @param Want
	 * 			want To go Event
	 * 
	 * @param temp
	 * 			second Event
	 * 
	 * @return boolean
	 * 			true if is similar, else false
	 *
	 */
	private boolean everyDayToEveryDay(WantToGo want, Event temp) {
		boolean result = false;
		WantToGoParseInfo parse = new WantToGoParseInfo(
				(BasicDataSource) source);
		ArrayList<WantToGoForEveryDay> times = parse.getEveryDay(want.getID());
		ArrayList<EventDate> eventDates = EveryDayDates(temp.getID());
		int count = 0;
		for (int j = 0; j < times.size(); j++) {
			WantToGoForEveryDay curr = times.get(j);
			int day = curr.getDay();
			Time start = curr.getStart();
			Time finish = curr.getFinish();
			for (int k = 0; k < eventDates.size(); k++) {
				EventDate currEventDate = eventDates.get(k);
				int eventDay = currEventDate.getDay();
				Time eventTime = currEventDate.getDate();
				if ((day == eventDay) && (eventTime.after(start))
						&& (eventTime.before(finish))) {
					result = true;
					count++;
					break;
				}
			}
			if (count > 0) {
				break;
			}
		}
		return result;
	}

	/**
	 * returns relevant Events after created WanttoGo
	 * 
	 * @param want
	 * 			WantToGo object
	 * 
	 * @return ArrayList<Event>
	 * 			Similar events to want
	 *
	 */
	public ArrayList<Event> getEventsForSearch(WantToGo want) {
		ArrayList<Event> result = new ArrayList<Event>();
		ArrayList<Event> allEvents = getEvents();
		for (int i = 0; i < allEvents.size(); i++) {
			Event temp = allEvents.get(i);
			if (temp.getValidation()) {
				if (want.getType()) {
					if (temp.getType()) {
						if (onceToOnce(want, temp)) {
							result.add(temp);
						}
					} else {
						result.add(temp);
					}
				} else {
					if (temp.getType()) {
						result.add(temp);
					} else {
						if (everyDayToEveryDay(want, temp)) {
							result.add(temp);
						}
					}
				}
			}
		}
		return result;
	}
}
