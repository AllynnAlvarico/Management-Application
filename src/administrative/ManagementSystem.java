package administrative;

import java.util.LinkedList;
import java.util.Random;

import record.User;

public class ManagementSystem {
	
	private LinkedList<User> users;
	private Random rand;
	
	public ManagementSystem() {
		users = new LinkedList<>();
		rand = new Random();
	}
	
	public void addUser(String inputUserName, String inputPassword) {
		int givenId =  rand.nextInt();
		String result = "MAUN" + givenId;
		//need to add a function that will stop a duplicate number on the accountID
		User userTobeAdded = new User(inputUserName, inputPassword);
		userTobeAdded.setUniqueId(result);
		users.add(userTobeAdded);
		
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
	
	public User getUser(boolean isExists, String inputPassword) {
		if(isExists) {
			for(User user : users) {
				if(user.getUserPassword().equals(inputPassword)) {
					return user;
				}
			}
		}
		
		return null;
	}
	
}

/*
 *Created 22/11/2024 by Allynn
 *			Allynn: the -isUserNameExists() checks the list if the user input exists on the memory
 *					addUser() adds the object I haven't linked the User and the Account Class yet. 
 */
