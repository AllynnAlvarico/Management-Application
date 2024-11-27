package application;

import javax.swing.SwingUtilities;

import administrative.ManagementSystem;
import appWindow.ApplicationWindow;
import record.User;

public class TestApp {
	
	static ManagementSystem mgtSystem = new ManagementSystem();
	boolean isExists;
	static User user;
	
	public TestApp() {
		
		mgtSystem.addUser("Allynn", "123456");
		
		isExists = mgtSystem.isUserNameExists("Allynn");
		
//		user = mgtSystem.getUser(isExists, "123456");
		
		System.out.println(isExists);
		System.out.println(user.getUserName());
		System.out.println(user.getID());
		
	}
	
	public static void main(String[] args) {
		//LinkedList<User> sharedUserList = new LinkedList<>();
		
		SwingUtilities.invokeLater(new Runnable() {
				@Override
				
			public void run() {
				// TODO Auto-generated method stub
				//new LoginWindow(sharedUserList).setVisible(true);
				new ApplicationWindow(mgtSystem, user).setVisible(true);
			}
			
		});
	}
}
