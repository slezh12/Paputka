package JavaPackage;

import java.sql.Date;


public class Comment {
	int userID;
	String Text;
	Date date;
	public Comment(int UserID, String text, Date date1){
		this.userID = UserID;
		this.Text=text;
		this.date=date1;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public String getText(){
		return this.Text;
		
	}
	
	public Date getDate(){
		return this.date;
	}
}
