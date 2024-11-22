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
		String result;
		
		users.add(new User(inputUserName, inputPassword, null))
	}
	
}
