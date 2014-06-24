package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class UserParseInfo extends ParseInfo {


	/**
	 *Public Constructor
	 */
	public UserParseInfo(BasicDataSource source) {
		super(source);
	}


	/**
	 * Returns if email is already in base
	 *            
	 * @param email
	 * 			email of user
	 * 
	 * @return boolean
	 * 			true if is in base, else false
	 */
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

	/**
	 * Returns object User, after LogIn      
	 *      
	 * @param email
	 * 			email of user
	 * 
	 * @param password
	 *			password Entered By user
	 * 
	 * @return user
	 * 			null if password or email doesn't match
	 */
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
	
	
	/**
	 * Returns object User, after Facebook or Google LogIn      
	 *      
	 * @param email
	 * 			email of user
	 * 
	 * @param password
	 *			password Entered By user
	 * 
	 * @return user
	 * 			null if password or email doesn't match
	 */
	public User getUserFBGoogle(String email, String password) {
		User user = null;
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

	
	/**
	 * Returns object User with Given ID     
	 *      
	 * @param ID
	 *			ID of User
	 * 
	 * @return user
	 * 			null if ID doesn't exists 
	 */
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
	/**
	 * Returns List of Requests, Sent To one User   
	 *      
	 * @param userID
	 *			ID of user, whom requests are sent
	 * 
	 * @return ArrayList<Request>
	 * 			List of recieved requests
	 */
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
	/**
	 * Returns List of Requests, Sent By one User   
	 *      
	 * @param userID
	 *			ID of user, who sent requests
	 * 
	 * @return ArrayList<Request>
	 * 			List of sent requests
	 */
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

	/**
	 * Inserts After adding status to user Profile
	 *      
	 * @param id
	 *			ID of user who changed status
	 *
	 * @param about 
	 * 			String of status
	 * 
	 */
	public void insertIntoStatuses(String about, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.insert("Statuses", "status", about, id);
		connect.CloseConnection();
	}

	/**
	 * Inserts After adding phone number to user Proifle
	 *      
	 * @param id
	 *			ID of user who added phone Number
	 *
	 * @param tel
	 * 			String of phone number
	 * 
	 */
	public void insertIntoTel(String tel, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.insert("Tel", "PhoneNumber", tel, id);
		connect.CloseConnection();
	}

	/**
	 * Updates Base After Updating status to user Proifle
	 *      
	 * @param id
	 *			ID of user who changed status
	 *
	 * @param about 
	 * 			String of status
	 * 
	 */
	public void updateStatuses(String about, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.update("Statuses", "status", about, id);
		connect.CloseConnection();
	}
	/**
	 * Updates Base After updating phone number to user Proifle
	 *      
	 * @param id
	 *			ID of user who added phone Number
	 *
	 * @param tel
	 * 			String of phone number
	 * 
	 */
	public void updateTel(String tel, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.update("Tel", "PhoneNumber", tel, id);
		connect.CloseConnection();
	}
	
	private boolean rateHelper( ResultSet rs) throws SQLException {
		while (rs.next()) {
			if ((rs.getBoolean("Events.Type") && !rs.getBoolean("Events.Validation")) || !rs.getBoolean("Events.Type"))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns boolean if User can be rated
	 *      
	 * @param firstUserID
	 *			user who rates
	 *
	 *	@param SecondUserID
	 *			user who is rated
	 *
	 * @return boolean
	 * 			true if can rate, else false
	 */
	public boolean canRate(int firstUserID, int secondUserID) throws SQLException {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		UserConnection con = new UserConnection((BasicDataSource) source);
		ResultSet rs1 = connect.getRaitingBoolean(firstUserID, secondUserID); 
		ResultSet rs2 = con.getRaitingBoolean(secondUserID, firstUserID);
		boolean timeFactor = false;		
		if (rateHelper(rs1) || rateHelper(rs2))
			timeFactor = true;
		con.CloseConnection();
		connect.CloseConnection();
		return timeFactor;	
	}
			
	/**
	 * Returns Integer of current rating for user
	 *      
	 * @param UserID
	 *			User whose rates are displayed
	 *
	 * @return Integer
	 * 			user's rating
	 */
	public Integer getRating(int userID) throws SQLException {
		double rate = 0; 
		Integer forReturn = null;
		BaseConnection connect = new BaseConnection((BasicDataSource) source); 
		ResultSet rs = connect.selectByID("Ratings", userID, "SecondID");
		int sum = 0; int count = 0;
		while (rs.next()) {
			int temp = rs.getInt("Rating"); 
			sum+=temp;
			count++;
		} 
		if (count>0){
			rate = (double)sum/(double)count;
			forReturn = (int) Math.round(rate);
		}
		connect.CloseConnection();
		return forReturn;
	}
	
	public Integer getRatingID(int FirstID,int SecondID)  { 
		Integer ret = null;
		UserConnection connect = new UserConnection((BasicDataSource) source); 
		ResultSet rs;
		try {
			rs = connect.getRating(FirstID, SecondID);
			if (rs.next()) 
				ret = rs.getInt("ID");
			connect.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Returns List of similar users after search
	 *      
	 * @param firstName
	 *			first name of user
	 *
	 *	@param LastName
	 *			last name of user
	 *
	 * @return ArrayList<User>
	 * 			Users similar to firstName or LastName
	 */
	public ArrayList<User> getUsersBySearch(String firstName, String LastName){
		ArrayList<User> result = new ArrayList<User>();
		UserConnection connect = new UserConnection((BasicDataSource) source);
		ResultSet rs = connect.getUsersByName(firstName, LastName);
		try {
			while(rs.next()){
				Integer id = rs.getInt("ID");
				String first = rs.getString("FirstName");
				String last = rs.getString("LastName");
				boolean gender = rs.getBoolean("Gender");
				Date birthdate = rs.getDate("BirthDate");
				String email = rs.getString("EMail");
				User user = new User(id, first, last, gender, birthdate, email);
				result.add(user);
			}
			connect.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertIntoAvatars(String avatar, int id){
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.insert("Avatars", "Image", avatar, id);
		connect.CloseConnection();
	}
	public String selectFromAvatars(int id){
		String res = "";
		ParseInfo parse = new ParseInfo((BasicDataSource) source);
		res = parse.getPrivateInfo(id, "image", "Avatars");
		return res;
	}
	public void updateAvatars(String avatar, int id) {
		UserConnection connect = new UserConnection((BasicDataSource) source);
		connect.update("Avatars", "Image", avatar, id);
		connect.CloseConnection();
	}
}
