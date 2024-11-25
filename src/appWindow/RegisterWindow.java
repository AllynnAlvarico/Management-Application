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
	
	private JPanel pnlTop, pnlCentral, pnlBottom;
	private JLabel txtLoginMessage;
	private JTextField fldUsername;
	private JPasswordField fldPassword;
	private JButton btnLogin, btnCancel;
	
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
	
	//search for the user
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
