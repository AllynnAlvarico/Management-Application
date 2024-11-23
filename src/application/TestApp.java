package application;

import administrative.ManagementSystem;
import record.User;

public class TestApp {
	
	ManagementSystem mgtSystem = new ManagementSystem();
	boolean isExists;
	User user;
	
	public TestApp() {
		
		mgtSystem.addUser("Allynn", "123456");
		
		isExists = mgtSystem.isUserNameExists("Allynn");
		
		user = mgtSystem.getUser(isExists, "123456");
		
		System.out.println(isExists);
		System.out.println(user.getUserName());
		System.out.println(user.getID());
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestApp();
	}

}
