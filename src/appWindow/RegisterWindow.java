package appWindow;
import record.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

import administrative.ManagementSystem;

public class RegisterWindow extends JFrame implements ActionListener{
	
	private JPanel pnlTop, pnlCentral, pnlBottom;
	private JLabel txtLoginMessage;
	private JTextField fldUsername;
	private JPasswordField fldPassword;
	private JButton btnLogin, btnCancel;
	
	//reference variable 
	ManagementSystem managementSystem;
//	LinkedList<User> shareList;
	private final int WIDTH = 450, HEIGHT = 150;
	private final int x = 750, y = 400;
	
	public RegisterWindow(ManagementSystem ms) {
		super("Register Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.shareList = shareList;
		System.out.println("Line 33 RWindow class");
		this.managementSystem = ms;
		
		System.out.print("Address Line 36 " + this.managementSystem);
		System.out.print("Address Line 37 " + ms);
		
		RegisterPanel();
		
		this.setLocation(x, y);
		
		this.setSize(WIDTH,HEIGHT);
		this.setResizable(false);
	}
	
	public void RegisterPanel() {
		pnlTop = new JPanel();
		pnlCentral = new JPanel();
		pnlBottom = new JPanel();
		txtLoginMessage = new JLabel("Register Here");
		fldUsername = new JTextField(10);
		fldPassword = new JPasswordField(10);
		btnLogin = new JButton("Register");
		btnCancel = new JButton("Cancel");
		
		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Register")) {
			String name = fldUsername.getText();
			String pass = String.valueOf(fldPassword.getPassword());
			User user = null;
			//boolean isExits = true;
//			System.out.print("Line 83 RW List Size " + managementSystem.getListUsers().size());
			managementSystem.getListUsers().add(new User("allynn", "12345"));
			
			
			
			if (managementSystem.getListUsers().isEmpty()) {
				user = new User(name,pass);
				managementSystem.getListUsers().add(user);
				System.out.println("Debug: " + user.getUserName());
				System.out.println("Number in Lists: " + managementSystem.getListUsers().size());
				JOptionPane.showMessageDialog(null, "You have register successfully!","Registration",JOptionPane.INFORMATION_MESSAGE);
				LoginWindow login = new LoginWindow(managementSystem);
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				RegisterWindow.this.dispose();
			} else {
				user = searchUser(name,pass);
				if (user == null) {
					user = new User(name,pass);
					managementSystem.getListUsers().add(user);
					JOptionPane.showMessageDialog(null, "You have register successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
					loginWindow();
				} else {
					if (name.equalsIgnoreCase(user.getUserName()) && pass.equals(user.getUserPassword())) {
							JOptionPane.showMessageDialog(null, "The username or password already exits!","Warning",JOptionPane.WARNING_MESSAGE);
					} else {
							
						//adding account
						user = new User(name,pass);
						managementSystem.getListUsers().add(user);
						JOptionPane.showMessageDialog(null, "You have register successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
						loginWindow();
					}
				}
			}
			
		} else {
			
		}
	}
	
	//search for the user
	public User searchUser(String name, String pass) {
		User user = null;
		for (User each: managementSystem.getListUsers()) {
			if (name.equalsIgnoreCase(each.getUserName()) && pass.equals(each.getUserPassword())) {
				user = each;
				return user;
			}
		}
		return null;
	}
	
	public void loginWindow() {
		LoginWindow login = new LoginWindow(managementSystem);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		RegisterWindow.this.dispose();
	}
	
}
