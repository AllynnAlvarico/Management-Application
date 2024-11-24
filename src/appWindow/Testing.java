package appWindow;
import java.util.LinkedList;

import javax.swing.*;

import record.User;

public class Testing {
	
		public static void main(String[] args) {
			LinkedList<User> sharedUserList = new LinkedList<>();
			
			SwingUtilities.invokeLater(new Runnable() {
					@Override
					
				public void run() {
					// TODO Auto-generated method stub
					new LoginWindow(sharedUserList).setVisible(true);
				}
				
		});
	}
}

