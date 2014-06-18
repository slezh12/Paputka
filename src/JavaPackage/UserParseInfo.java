package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class UserParseInfo extends ParseInfo {

	public UserParseInfo(BasicDataSource source) {
		super(source);
	}

	public boolean isUserAlreadyInBase(String email) {
		UserConnection base = new UserConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.getInfoByMail(email);
			if (rs.isBeforeFirst()) {
				return true;
			}
			base.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public User getUser(String email, String password) {
		User user = null;
		password = Hash.calculateHashCode(password);
		UserConnection base = new UserConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.getInfoByMail(email);
			if (rs.isBeforeFirst()) {
				rs.next();
				if (rs.getString("Password").equals(password)) {
					Integer id = rs.getInt("ID");
					String first = rs.getString("FirstName");
					String last = rs.getString("LastName");
					boolean gender = rs.getBoolean("Gender");
					Date birthdate = rs.getDate("BirthDate");
					user = new User(id, first, last, gender, birthdate, email);
				}
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByID(int ID) {
		User user = null;
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("Users", ID, "ID");
			rs.next();
			Integer id = rs.getInt("ID");
			String first = rs.getString("FirstName");
			String last = rs.getString("LastName");
			boolean gender = rs.getBoolean("Gender");
			Date birthdate = rs.getDate("BirthDate");
			String email = rs.getString("EMail");
			user = new User(id, first, last, gender, birthdate, email);
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// sxvisi requestebi chemtan
	public ArrayList<Request> getOthrRequests(int userID) {
		ArrayList<Request> requests = new ArrayList<Request>();
		UserConnection base = new UserConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.getUsersRequests(userID);
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String text = rs.getString("Text");
				int fromUserID = rs.getInt("UserID");
				int eventID = rs.getInt("EventID");
				Timestamp datetime = rs.getTimestamp("Date");
				int Acception = rs.getInt("Acception");
				Request req = new Request(ID, eventID, text, fromUserID,
						Acception, datetime);
				requests.add(req);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	// chemi requestebi sxvebtan
	public ArrayList<Request> getMyRequests(int userID) {
		ArrayList<Request> requests = new ArrayList<Request>();
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.selectByID("Requests", userID, "UserID");
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String text = rs.getString("Text");
				int fromUserID = rs.getInt("UserID");
				int eventID = rs.getInt("EventID");
				Timestamp datetime = rs.getTimestamp("Date");
				int Acception = rs.getInt("Acception");
				Request req = new Request(ID, eventID, text, fromUserID,
						Acception, datetime);
				requests.add(req);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	public void insertIntoStatuses(String about, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.insert("Statuses", "status", about, id);
		connect.CloseConnection();
	}

	public void insertIntoTel(String tel, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.insert("Tel", "PhoneNumber", tel, id);
		connect.CloseConnection();
	}

	public void updateStatuses(String about, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.update("Statuses", "status", about, id);
		connect.CloseConnection();
	}

	public void updateTel(String tel, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.update("Tel", "PhoneNumber", tel, id);
		connect.CloseConnection();
	}
}
