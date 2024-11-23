package appWindow;
import record.User;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import administrative.ManagementSystem;

public class RegisterWindow extends JFrame implements ActionListener{
	
	private JPanel tPanel, cPanel, bPanel;
	private JLabel message;
	private JTextField username;
	private JPasswordField password;
	private JButton registerB, cancelB;
	
	//reference variable 
	//ManagementSystem manageSystem = new ManagementSystem();
	LinkedList<User> shareList;
	private final int WIDTH = 450, HEIGHT = 150;
	
	public RegisterWindow(LinkedList<User> shareList) {
		super("Register Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.shareList = shareList;
		
		RegisterPanel();
		
		this.setSize(WIDTH,HEIGHT);
		this.setResizable(false);
	}
	
	public void RegisterPanel() {
		tPanel = new JPanel();
		cPanel = new JPanel();
		bPanel = new JPanel();
		message = new JLabel("Register Here");
		username = new JTextField(10);
		password = new JPasswordField(10);
		registerB = new JButton("Register");
		cancelB = new JButton("Cancel");
		
		registerB.addActionListener(this);
		cancelB.addActionListener(this);
		
		tPanel.add(message);
		cPanel.add(new JLabel("Username: "));
		cPanel.add(username);
		cPanel.add(new JLabel("Password: "));
		cPanel.add(password);
		bPanel.add(registerB);
		bPanel.add(cancelB);
		
		message.setFont(new Font("Arial",Font.BOLD,20));
		cPanel.setLayout(new GridLayout(2,2));
		
		this.add(tPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		this.add(bPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Register")) {
			String name = username.getText();
			String pass = String.valueOf(password.getPassword());
			User user = null;
			boolean isExits = true;
			
			
			if (shareList.isEmpty()) {
				user = new User(name,pass);
				shareList.add(user);
				System.out.println("Debug: " + user.getUserName());
				JOptionPane.showMessageDialog(null, "You have register successfully!","Registration",JOptionPane.INFORMATION_MESSAGE);
				LoginWindow login = new LoginWindow(shareList);
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				RegisterWindow.this.dispose();
			} else {
				user = searchUser(name,pass);
				if (user == null) {
					user = new User(name,pass);
					shareList.add(user);
					JOptionPane.showMessageDialog(null, "You have register successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
					loginWindow();
				} else {
					if (name.equalsIgnoreCase(user.getUserName()) && pass.equals(user.getUserPassword())) {
							JOptionPane.showMessageDialog(null, "The username or password already exits!","Warning",JOptionPane.WARNING_MESSAGE);
					} else {
							
						//adding account
						user = new User(name,pass);
						shareList.add(user);
						JOptionPane.showMessageDialog(null, "You have register successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
						loginWindow();
					}
				}
			}
			
		} else {
			
		}
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
	
	public void loginWindow() {
		LoginWindow login = new LoginWindow(shareList);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		RegisterWindow.this.dispose();
	}
	
}
