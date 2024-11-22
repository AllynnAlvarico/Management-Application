package appWindow;
import record.User;
import administrative.ManagementSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

public class LoginWindow extends JFrame{
	
	private JPanel tLoginPanel, cLoginPanel, bLoginPanel;
	private JLabel message;
	private JTextField username;
	private JPasswordField password;
	private JButton loginB, cancelB;
	
	//loading panel
	static JFrame loadDialog;
	static JProgressBar process;
	
	//reference variable 
	//ManagementSystem manageSystem = new ManagementSystem();
	LinkedList<User> shareList;
	
	private final int WIDTH = 450, HEIGHT = 150;
	
	public LoginWindow(LinkedList<User> shareList) {
		super("Login Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.shareList = shareList;
		
		loginPanel();
		
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public void loginPanel() {
		tLoginPanel = new JPanel();
		cLoginPanel = new JPanel();
		bLoginPanel = new JPanel();
		message = new JLabel("Login Section");
		username = new JTextField(10);
		password = new JPasswordField(10);
		loginB = new JButton("Login");
		cancelB = new JButton("Cancel");
		
		loginButton();
		cancelButton();
		
		tLoginPanel.add(message);
		cLoginPanel.add(new JLabel("Username: "));
		cLoginPanel.add(username);
		cLoginPanel.add(new JLabel("Password: "));
		cLoginPanel.add(password);
		bLoginPanel.add(loginB);
		bLoginPanel.add(cancelB);
		
		message.setFont(new Font("Arial",Font.BOLD,20));
		cLoginPanel.setLayout(new GridLayout(2,2));
		
		this.add(tLoginPanel, BorderLayout.NORTH);
		this.add(cLoginPanel, BorderLayout.CENTER);
		this.add(bLoginPanel, BorderLayout.SOUTH);
	}
	
	public void loginButton() {
		loginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = null;
				if (e.getActionCommand().equals("Login")) {
					
					String name = username.getText();
					String pass = String.valueOf(password.getPassword());
					
					try {
						if (shareList.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No accounts found, please register first!", "No Accounts", JOptionPane.INFORMATION_MESSAGE);
							registerWindow();
						} else {
							
							user = searchUser(name,pass);
							
							if (user == null) {
								JOptionPane.showMessageDialog(null, "No accounts found, please register first!", "No Accounts", JOptionPane.INFORMATION_MESSAGE);
								registerWindow();
							} else {
								if (name.equalsIgnoreCase(user.getUserName()) && pass.equals(user.getUserPassword())) {
									JOptionPane.showMessageDialog(null, "Account '" + user.getUserName() + " 'Has Found - You Have Login Successfully","Login Success",JOptionPane.INFORMATION_MESSAGE);
									loadingPanel(); //loading animation
									user.setAttempt(1);
								} else {
									registerWindow();
								}
							}
								
						}
					} catch (NullPointerException  n) {
						n.printStackTrace();
					}
				}
			}
		});
	}
	
	public User searchUser(String name, String pass) {
		User user = null;
		for (User each: shareList) {
			if (name.equalsIgnoreCase(each.getUserName()) && pass.equals(each.getUserPassword())) {
				user = each;
				return user;
			}
		}
		return null;
	}
	
	public void registerWindow() {
		RegisterWindow register = new RegisterWindow(shareList);
		register.setLocationRelativeTo(null);
		register.setVisible(true);
		LoginWindow.this.dispose();
	}
	
	// original source [https://www.geeksforgeeks.org/java-swing-jprogressbar/]
	public void loadingPanel() {
		loadDialog = new JFrame("Loading...");
		JPanel loadPanel = new JPanel();
		process = new JProgressBar();
		
		process.setValue(0);
		process.setStringPainted(true);
		
		loadPanel.add(process);
		loadDialog.add(loadPanel);
		
		loadDialog.setSize(WIDTH,HEIGHT);
		loadDialog.setLocationRelativeTo(null);
		loadDialog.setVisible(true);
		
		// only from here 
		new Thread(() -> {
	        fill();
	        // After loading is complete, transition to the main application
	        SwingUtilities.invokeLater(() -> {
	            loadDialog.dispose(); // Close the loading dialog
	            
	            // here
	            ApplicationWindow mainApp = new ApplicationWindow();
	            mainApp.setLocationRelativeTo(null);
	            mainApp.setVisible(true);
	            LoginWindow.this.dispose(); // Close the login window
	            //until here is my won work
	            
	        });
	    }).start();
		// until here are are the chatgpt work just because I got trouble on the process bar not showing
	}
	
	public static void fill() {
	    int i = 0;
	    try {
	    	while (i <= 100) {
	    		if (i > 20 && i < 50)
	    			process.setString("Retrieve Data...");
	    		else if (i > 50 && i < 80)
	    			process.setString("You Are Reaching Half...");
	    		else if (i > 80)
	    			process.setString("You Are Almost There...");
	    		else
	    			process.setString("Start Loading");

	    		process.setValue(i); // Update the progress bar value
	                
	    		//delay time 
	    		Thread.sleep(200);
	                
	    		// Each time around plus 20
	    		i += 2; 
	    	}
	            
	    Thread.sleep(2000);
	            
	    SwingUtilities.invokeLater(() -> loadDialog.dispose());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void cancelButton() {
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Cancel")) {
					cancelOption();
				}
			}
		});
	}
	
	public void cancelOption() {
		
		JPanel ask = new JPanel();
		ask.add(new JLabel("Do you sure you wish to log out?"));	
			
		int result = JOptionPane.showConfirmDialog(this, ask, "Are you sure?",JOptionPane.OK_CANCEL_OPTION);
				
		if (result == JOptionPane.OK_OPTION) {
			 JOptionPane.showMessageDialog(null, "See you later ~ ~","Info", JOptionPane.INFORMATION_MESSAGE);
			 System.exit(0);
		}
		
	}
}
