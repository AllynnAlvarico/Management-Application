package record;

/*
 * Author: Allynn Alvarico
 * Technological University of Dublin
 * 
 */

public class User {

	private String userName;
	private String userPassword;
	private String userUnique;
	private int attempt;
	private Account userAccount;
	
	public User(String name, String password){
		this.userName = name;
		this.userPassword = password;
		this.userAccount = null;
	}
	
//	public User(String name, String password, String uniqueID){
//		this.userName = name;
//		this.userPassword = password;
//		this.userUnique = uniqueID;
//	}
	
	/**
	 * Anson - I commit a change on adding a constructor that only contains user name, password, and attempt to track is user login or not
	 * Use - This change is only for testing the register and login function
	 * Change Commit Line - 29 until 38
	 */
//	public User(String name, String password){
//		this.userName = name;
//		this.userPassword = password;
//		this.attempt = attempt;
//	}
	
	public void changeUserName(String name){
		this.userName = name;
	}
	
	public void changePassword(String password){
		this.userPassword = password;
	}
	
	public void setUniqueId(String givenID){
		this.userUnique = givenID;
	}
	
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getUserPassword(){
		return this.userPassword;
	}
	
	public String getID(){
		return this.userUnique;
	}
	
	//===============================================================
	// Anson - create a get setter for attempt for keep tracking user
	// Commit change on 23/11/2024
	// On line 64 until 71
	//===============================================================
	public int getAttempt() {
		return this.attempt;
	}
	
	public Account getAccount(){
		return this.userAccount;
	}
	@Override
	public boolean equals(Object o){
		User temp = (User) o;
		if(this.userPassword.equals(temp.userPassword)) return true;
		return false;
		
	}
	
}
/*
 * Created class on 22/11/2024 by Allynn.
 * 		Allynn: I created the user class to hold the details of the user.
 * 				Will be using this to store user accounts and passwords.
 * 				Users will be able to retrieve and gain access to their data
 * 				if user name and password is matched.
 *
 * 
 */