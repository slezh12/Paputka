package JavaPackage;

public class User {

	private int ID;
	private String FirstName;
	private String LastName;
	private boolean gender;
	private String birthdate;
	private String status;
	private String email;
	
	public User(int ID,String FirstName,String LastName,boolean gender,String birthdate,String status,String email) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.status = status;
		this.ID = ID;
		this.email = email;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean getGender() {
		return gender;
	}
	
}
