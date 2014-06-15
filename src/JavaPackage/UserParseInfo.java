package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class UserParseInfo extends ParseInfo {

	public UserParseInfo(BasicDataSource source) {
		super(source);
	}

	public User getUser(String email, String password) {
		User user = null;
		password = Hash.calculateHashCode(password);
		UserConnection base = new UserConnection((BasicDataSource)source);
		try {
			ResultSet rs = base.getInfoByMail(email);
			if (rs.isBeforeFirst()) {
				rs.next();
				if(rs.getString("Password").equals(password)){
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
}
