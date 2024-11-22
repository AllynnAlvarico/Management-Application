package appWindow;

import record.User;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Author: Anson Ling Guang Cheng
 * Major in: Business Computing / year 2
 * Technological University Dublin 
 */

public class ApplicationWindow extends JFrame implements ActionListener{
	
	//variable for menu
	private JPanel dialogPane;
	private JMenu user,view,placeOrder;
	private JMenuItem signUp, logIn, logOut, quite;
	private JMenuBar menuBar;
	
	private LinkedList<User> list;
	private User u;
	
	//set the width and height
	private final int WIDTH = 600, HEIGHT = 400;
	private int attempt;
	
	public ApplicationWindow() {
		super("Management Application");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		list = new LinkedList<>();
		
		menu();
		
		this.setSize(WIDTH,HEIGHT);
		this.setResizable(false);
	}
	
	public void menu() {
		menuBar = new JMenuBar();
		
		//defining the object
		user = new JMenu("User");
		logOut = new JMenuItem("Log Out");
		user.addSeparator(); //adding line between
		quite = new JMenuItem("Quite");
		
		view = new JMenu("Review");
		
		placeOrder = new JMenu("Order");
		
		//disable the view menu until user login
		view.setEnabled(false);
		placeOrder.setEnabled(false);
		
		//set the short cut for the function
		logOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		quite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		
		//adding to the user menu
		user.add(logOut);
		user.add(quite);
		
		
		//adding action to the menu
		logOut.addActionListener(this);
		quite.addActionListener(this);
		
		//adding to the menu bar object
		menuBar.add(user);
		menuBar.add(view);
		menuBar.add(placeOrder);
		
		//adding it to the frame
		this.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Log Out")) {
			logOut();
		} else {
			System.exit(0);
		}
	}
	
	//method for register an account
	/**
	public void signUp() {
		dialogPane = new JPanel();
		username = new JTextField(15);
		password = new JPasswordField(15);
		
		dialogPane.add(new JLabel("Username: "));
		dialogPane.add(username);
		dialogPane.add(new JLabel("Password: "));
		dialogPane.add(password);
		dialogPane.setLayout(new GridLayout(2,2));
		
		//add the dialog panel for dialog
		int result = JOptionPane.showConfirmDialog(this,dialogPane,"User registration:",JOptionPane.OK_CANCEL_OPTION);
		
		//once user click on OK option
		if (result == JOptionPane.OK_OPTION) {
			
		    //defining the variable 
			String name = username.getText();
			String pass = String.valueOf(password.getPassword());
			
			//check is the list empty
			if (list.isEmpty()) {
				try {
					
					//adding account
					u = new User(name,pass);
					list.add(u);
					JOptionPane.showMessageDialog(null, "You have register successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
					//welcomeMassage = new JLabel( username.getText() + ", Welcome back!");
				} catch (NullPointerException n) {
					n.printStackTrace();
				}
			} else {
				
				//if is incorrect, warning message pop up
				if (name.equalsIgnoreCase(u.getUserName()) && pass.equals(u.getUserPassword())) {
					JOptionPane.showMessageDialog(null, "The username or password already exits!","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					
					//adding account
					u = new User(name,pass);
					list.add(u);
					JOptionPane.showMessageDialog(null, "You have register successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	**/
	
	//method for log in function
	/**
	public void logIn() {
		User user = null;
		dialogPanel2 = new JPanel();
		usernameL = new JTextField(15);
		passwordL = new JPasswordField(15);
			
		dialogPanel2.add(new JLabel("Username: "));
		dialogPanel2.add(usernameL);
		dialogPanel2.add(new JLabel("Password: "));
		dialogPanel2.add(passwordL);
		dialogPanel2.setLayout(new GridLayout(2,2));
			
		int result = JOptionPane.showConfirmDialog(this,dialogPanel2,"Please process to login:  ",JOptionPane.OK_CANCEL_OPTION);
			
		if (result == JOptionPane.OK_OPTION) {
				
			String name = usernameL.getText();
			String pass = String.valueOf(passwordL.getPassword());
				
			//
			 * Within the login function, it will check first if the list are empty then display message "not yet register"
			 * If the list is not empty, it will check is user is exits, if it no exists, display message
			 * else if the user is exists, it will check if the user has login, 
			 * attempt < 1 mean did not login then process to login, else mean it already login display error message
			 //
			
			if(list.isEmpty()) {
				try {
					JOptionPane.showMessageDialog(null, "No Account Register yet! Please Register First!","Warning",JOptionPane.WARNING_MESSAGE);
				} catch (NullPointerException n) {
					n.printStackTrace();
				}
			} else {
				user = searchUser(name,pass);
				if (user == null) {
					JOptionPane.showMessageDialog(null, name + " are not exists in the system","Not Found!",JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (attempt < 1) {
						if (name.equalsIgnoreCase(user.getUserName()) && pass.equals(user.getUserPassword())) {
							
								JOptionPane.showMessageDialog(null, user.getUserName() + ", Log In Successfully!","Success!",JOptionPane.INFORMATION_MESSAGE);
								view.setEnabled(true);
								placeOrder.setEnabled(true);
								attempt++;
								//welcomeMassage.setText(u.getUserName() + ", Welcome Bank!");
							
						} else {
							JOptionPane.showMessageDialog(null, "The username or password Incorrect! Try Again!","Warning",JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "You have log in an account","Warning",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}**/
	
	//search function for log in
	public User searchUser(String name, String pass) {
		User user = null;
		for (User each: list) {
			if (name.equalsIgnoreCase(each.getUserName()) && pass.equals(each.getUserPassword())) {
				user = each;
				return user;
			}
		}
		return null;
	}
	
	/**
	 * This method is for log out function 
	 * so first if the user haven't log out the account then it will ask user "do you wish to log out"
	 *  - If user confirm to log out, display message "see ya later ~ ~"
	 *  - If not, nothing happen
	 *  
	 * If the user are already log out 
	 *  - Error message display 
	 */
	public void logOut() {
		JPanel ask = new JPanel();
		ask.add(new JLabel("Do you sure you wish to log out?"));	
		
		if (attempt < 1) {
			JOptionPane.showMessageDialog(null, "You haven't log in yet, please log in first!","Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(this,ask,"Please process to login:  ",JOptionPane.OK_CANCEL_OPTION);
			
		 	if (result == JOptionPane.OK_OPTION) {
		 		attempt--;
		 		view.setEnabled(false);
		 		placeOrder.setEnabled(false);
		 		JOptionPane.showMessageDialog(null, "See you later ~ ~","Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/*
	 * Created class on 22/11/2024 by Anson.
	 * 		Anson: I create a class for GUI that are able to register, login, lot out, and quite the program
	 * 			   Also, I add a short cut for the user to using those action and you can see it on the menu bar
	 * 			   So the register are using the linkedlistt to store the username and password 
	 * 			   Once the user register successfully, then the user can log in to the account 
	 * 			   if the username and password matched.
	 *
	 * 
	 */
}
