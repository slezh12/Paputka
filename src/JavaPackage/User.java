package JavaPackage;

import java.sql.Date;

public class User {

	private int ID;
	private String FirstName;
	private String LastName;
	private boolean gender;
	private Date birthdate;
	private String email;

	/**
	 * Public constructor which returns User object.
	 * 
	 * @param ID
	 *            unique ID of user.
	 * @param FirstName
	 *            name of user.
	 * @param LastName
	 *            lastName of user.
	 * @param gender
	 *            gender of user.
	 * @param birthdate
	 *            birth date of user.
	 * @param email
	 *            email of user.
	 */
	public User(int ID, String FirstName, String LastName, boolean gender,
			Date birthdate, String email) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.ID = ID;
		this.email = email;
	}

	/**
	 * Returns unique ID of User.
	 * 
	 * @return int ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Returns name of user.
	 * 
	 * @return string FirstName.
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * Returns last name of user.
	 * 
	 * @return string LastName.
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * Returns birth date of user.
	 * 
	 * @return Date birthdate.
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * Returns email of user.
	 * 
	 * @return string email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns gender of user.
	 * 
	 * @return boolean gender.
	 */
	public boolean getGender() {
		return gender;
	}

}
