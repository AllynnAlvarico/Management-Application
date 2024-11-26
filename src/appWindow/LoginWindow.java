package appWindow;
import record.User;
import administrative.ManagementSystem;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener{
	
	private JPanel pnlTop, pnlCentral, pnlBottom;
	private JLabel txtLoginMessage;
	private JTextField fldUsername;
	private JPasswordField fldPassword;
	private JButton btnLogin, btnCancel;
	
	//loading panel
	static JFrame loadDialog;
	static JProgressBar process;
	
	//reference variable 
	ManagementSystem managementSystem;
	User referencedUser;
	
	private final int WIDTH = 450, HEIGHT = 150;
	
	public LoginWindow(ManagementSystem managementSystem){
		super("Login Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.managementSystem = managementSystem;
		
		loginPanel();
		
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		btnLogin.addActionListener(this);
	}
	
	public void loginPanel() {
		pnlTop = new JPanel();
		pnlCentral = new JPanel();
		pnlBottom = new JPanel();
		txtLoginMessage = new JLabel("Login Section");
		fldUsername = new JTextField(10);
		fldPassword = new JPasswordField(10);
		btnLogin = new JButton("Login");
		btnCancel = new JButton("Cancel");
		
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		
//		login();
		cancelButton();
		
		pnlTop.add(txtLoginMessage);
		pnlCentral.add(new JLabel("Username: "));
		pnlCentral.add(fldUsername);
		pnlCentral.add(new JLabel("Password: "));
		pnlCentral.add(fldPassword);
		pnlBottom.add(btnLogin);
		pnlBottom.add(btnCancel);
		
		txtLoginMessage.setFont(new Font("Arial",Font.BOLD,20));
		pnlCentral.setLayout(new GridLayout(2,2));
		
		this.add(pnlTop, BorderLayout.NORTH);
		this.add(pnlCentral, BorderLayout.CENTER);
		this.add(pnlBottom, BorderLayout.SOUTH);
	}
	
	public void login() {
		
		String username = fldUsername.getText();
		String userpassword = String.valueOf(fldPassword.getPassword());
		
		try {
			boolean isFound = managementSystem.isUserNameExists(username);
			referencedUser = managementSystem.getUser(username, userpassword);
			
			if(isFound && referencedUser != null) {
				JOptionPane.showMessageDialog(null, "Account '" + referencedUser.getUserName() + " 'Has Found - You Have Login Successfully","Login Success",JOptionPane.INFORMATION_MESSAGE);
				loadingPanel(); //loading animation
				LoginWindow.this.dispose();
			} else if(!isFound || referencedUser == null)
				errorDialog();
			
		} catch (NullPointerException  n) {
			n.printStackTrace();
		}
	}
	
	public void registerWindow() {
		RegisterWindow register = new RegisterWindow(managementSystem);
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
	            ApplicationWindow mainApp = new ApplicationWindow(managementSystem, referencedUser);
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
	    			process.setString("You Almost There...");
	    		else
	    			process.setString("Start Loading");

	    		process.setValue(i); // Update the progress bar value
	                
	    		//delay time 
	    		Thread.sleep(200);
	                
	    		// Each time around plus 2
	    		i += 2; 
	    	}
	            
	    Thread.sleep(2000);
	            
	    SwingUtilities.invokeLater(() -> loadDialog.dispose());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void cancelButton() {
		btnCancel.addActionListener(new ActionListener() {
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
	
	public void errorDialog() {
		JOptionPane.showMessageDialog(null, "No accounts found, please register first!", "No Accounts", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnLogin) login();
		
	}
}
