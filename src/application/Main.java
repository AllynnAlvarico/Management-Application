package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import administrative.ManagementSystem;
import appWindow.*;

public class Main implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	static ManagementSystem sharedSystem;
	public Main() {
		sharedSystem = new ManagementSystem();
	}
	
	    public static void main(String[] args) {
	    	 new Main();
	    	SwingUtilities.invokeLater(() -> {
	    	    RegisterWindow ui = new RegisterWindow(sharedSystem);
	    	    ui.setVisible(true);
	    	});
	    }

		
	

}
