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
	private Account userAccount;
	
	public User(String name, String password, String uniqueID){
		this.userName = name;
		this.userPassword = password;
		this.userUnique = uniqueID;
	}
	
	public User(String name, String password, Account account){
		this.userName = name;
		this.userPassword = password;
		this.userAccount = account;
	}
	
	public void changeUserName(String name){
		this.userName = name;
	}
	
	public void changePassword(String password){
		this.userPassword = password;
	}
	
	public void setUniqueId(String givenID){
		this.userUnique = givenID;
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
	
	public Account getAccount(){
		return this.userAccount;
		}
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