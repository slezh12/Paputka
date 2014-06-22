package JavaPackage;

import java.sql.Date;

public class Comment {
	private int userID;
	private String Text;
	private Date date;

	/**
	 * public constructor. Returns new Comment object.
	 * 
	 * @param UserID
	 *            unique ID of user.
	 * @param text
	 *            text which should appear in comment.
	 * @param date1
	 *            date when comment is added.
	 */
	public Comment(int UserID, String text, Date date1) {
		this.userID = UserID;
		this.Text = text;
		this.date = date1;
	}

	/**
	 * Returns userID of user who commented.
	 * 
	 * @return int userID.
	 */
	public int getUserID() {
		return this.userID;
	}

	/**
	 * Returns text of comment
	 * 
	 * @return string text.
	 */
	public String getText() {
		return this.Text;

	}

	/**
	 * Returns date when comment was made.
	 * 
	 * @return date of comment.
	 */
	public Date getDate() {
		return this.date;
	}
}
