package JavaPackage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.activation.DataSource;
import javax.servlet.ServletContext;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;


public class ParseInfo {
	private BasicDataSource source;
	
	public ParseInfo(BasicDataSource source) {
		this.source = source;
	}

	public User getUser(String email, String password) {
		User user = null;
		password = Hash.calculateHashCode(password);
		BaseConnection base = new BaseConnection((BasicDataSource)source);
		try {
			ResultSet rs = base.getInfoByMail(email);
			if (rs.isBeforeFirst()) {
				rs.next();
				if(rs.getString("Password").equals(password)){
					Integer id = rs.getInt("ID");
					String first = rs.getString("FirstName");
					String last = rs.getString("LastName");
					String status = rs.getString("Status");
					boolean gender = rs.getBoolean("Gender");
					Date birthdate = rs.getDate("BirthDate");
					user = new User(id, first, last, gender, birthdate, status, email);
				}
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
