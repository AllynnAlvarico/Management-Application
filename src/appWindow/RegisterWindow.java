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
	
	private ManagementSystem managementSystem;
	
	private JPanel pnlTop, pnlCentral, pnlBottom;
	private JLabel txtLoginMessage;
	private JTextField fldUsername;
	private JPasswordField fldPassword;
	private JButton btnRegister, btnCancel;
	
	//reference variable 
	private final int WIDTH = 450, HEIGHT = 150;
	private final int x = 750, y = 400;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnRegister) register();
			
	}
	
	public RegisterWindow(ManagementSystem ms) {
		super("Register Window");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.managementSystem = ms;
		
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
		btnRegister = new JButton("Register");
		btnCancel = new JButton("Cancel");
		
		btnRegister.addActionListener(this);
		btnCancel.addActionListener(this);
		
		pnlTop.add(txtLoginMessage);
		pnlCentral.add(new JLabel("Username: "));
		pnlCentral.add(fldUsername);
		pnlCentral.add(new JLabel("Password: "));
		pnlCentral.add(fldPassword);
		pnlBottom.add(btnRegister);
		pnlBottom.add(btnCancel);
		
		txtLoginMessage.setFont(new Font("Arial",Font.BOLD,20));
		pnlCentral.setLayout(new GridLayout(2,2));
		
		this.add(pnlTop, BorderLayout.NORTH);
		this.add(pnlCentral, BorderLayout.CENTER);
		this.add(pnlBottom, BorderLayout.SOUTH);
	}
	
	public void register() {
		String username = fldUsername.getText();
		String userpassword = String.valueOf(fldPassword.getPassword());
		
		
		
		if (managementSystem.getListUsers().isEmpty()) {
			if(!username.isEmpty() || !username.isBlank()){
				managementSystem.getListUsers().add(new User(username, userpassword));
				msgDialog("You have register successfully!", "Info");
			}
			 msgDialog("Hey!", "Warning");
			
		} else {
			User inputUser = managementSystem.getUser(username, userpassword);
			if (username.equals(inputUser.getUserName())) {
				msgDialog("The username already exits!", "Warning");
			}
		}
	}

	public void msgDialog(String message, String type) {
		JOptionPane.showMessageDialog(null, message, type,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void loginWindow() {
		LoginWindow login = new LoginWindow(managementSystem);
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		RegisterWindow.this.dispose();
	}
	
}
