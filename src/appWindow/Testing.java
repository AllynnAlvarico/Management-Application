package appWindow;
import javax.swing.*;

public class Testing {
	
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
					@Override
				public void run() {
					// TODO Auto-generated method stub
					new ApplicationWindow().setVisible(true);
				}
				
		});
	}
}

