package administrative;

import java.util.LinkedList;
//import java.util.Random;

import record.*;

public class ManagementSystem {
	
	private LinkedList<User> users;
	private Account accountUser;
	
	public ManagementSystem() {
		users = new LinkedList<>();
		
	}
	
	public void addUser(String inputUserName, String inputPassword) {
		int givenId = (int)(Math.random() * 99999) + 1;
		String result = "ACC" + givenId;
		//need to add a function that will stop a duplicate number on the accountID
		accountUser = new Account(result, result, "Saving");
		User userTobeAdded = new User(inputUserName, inputPassword, result, accountUser);
		users.add(userTobeAdded);
	}
	
	public void setUserAccountDetails() {
		
	}
	
	public boolean isUserNameExists(String element) {
		if(element == null || element.isEmpty() || element.isBlank()) return false;
		for(User user : users) {
			if(user.getUserName().equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	public User getUser(String username, String inputPassword) {
		for(User user : users) {
			if(user.getUserName().equals(username) && user.getUserPassword().equals(inputPassword)) {
				return user;
			}
		}
		return null;
	}
	
	// Anson - Commit change on line 46 - 23/11/2024 for create a method to check is
	public boolean isListEmpty(boolean isExists) {
		if (users.isEmpty()) return isExists = true; else return isExists = false;	
	}
	
	// Anson - Commit change on line 51 - 23/11/2024 for create a method to search user name and password
	
//	public User searchFunction(String name, String pass) {
//		User user = null;
//		for (User u: users) {
//			if (name.equalsIgnoreCase(u.getUserName()) && pass.equals(u.getUserPassword())) {
//				user = u;
//				return user;
//			}
//		}
//		return null;
//	}
	
	public LinkedList<User> getListUsers(){
//		System.out.println("Line 68Users Lists number: " + users.size());
		return this.users;
	}
	
}

/*
 *Created 22/11/2024 by Allynn
 *			Allynn: the -isUserNameExists() checks the list if the user input exists on the memory
 *					addUser() adds the object I haven't linked the User and the Account Class yet. 
 *Modified 26/11/2024 by Allynn
 *			Allynn: I have manage to assigned an account number to a user, however I cannot limit the number
 */
