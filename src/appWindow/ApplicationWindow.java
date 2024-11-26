package appWindow;

import record.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import administrative.ManagementSystem;

import java.util.Date;
import java.util.LinkedList;

/**
 * Author: Anson Ling Guang Cheng
 * Major in: Business Computing / year 2
 * Technological University Dublin 
 */

public class ApplicationWindow extends JFrame implements ActionListener{
	
	//variable for menu
	private JMenu user,view,placeOrder;
	private JMenuItem logOut, quit;
	private JMenuBar menuBar;
	
	//variable for account details 
	private JLabel txtAccountID, txtCustomerID, txtBalance, txtAccountType, txtCurrency, txtStatus;
	private JFormattedTextField fldAccountID, fldCustomerID, fldBalance, fldAccType, fldCurrency, fldStatus;
	
	//variable for main UI 
	private JPanel pnlTop, pnlBottom;
	private JPanel pnlPurchaseList, pnlAccountDetail;
	private JPanel pnlMainTop, pnlMainCentral,pnlMainBottom;
	private JLabel txtAccountName;
	private JTabbedPane tbpPurchaseList;
	private Date date;
	
	//variable for inventory purchase function
	private JPanel topInventoryPanel, centralInventoryPanel, bottomInventoryPanel;
	private JTextField fldSearch, fldQuantity;
	private JLabel txtSearchProduct, txtQuantity;
	private JButton btnSearch, btnPurcase;
	private JTable table;
	private JScrollPane scrollTable;
	private DefaultTableModel tableModel;
	private String[] head = {"Product ID","Product Name","Product Type","Price","Quantity"};
	
//	private LinkedList<User> list;
	private ManagementSystem managementSys;
	private User authorisedUser;
	
	//set the width and height
	private final int WIDTH = 1000, HEIGHT = 500;
	
	public ApplicationWindow(ManagementSystem ms, User passedDataUser) {
		super("Management Application");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		list = new LinkedList<>();
		this.managementSys = ms;
		this.authorisedUser = passedDataUser;
		setMenu();
		mainUI();
		
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
	}
	
	public void setMenu() {
		menuBar = new JMenuBar();
		
		//defining the object
		user = new JMenu("User");
		logOut = new JMenuItem("Log Out");
		user.addSeparator(); //adding line between
		quit = new JMenuItem("Quit");
		
		view = new JMenu("Review");
		
		placeOrder = new JMenu("Order");
		
		//set the short cut for the function
		logOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		
		//adding to the user menu
		user.add(logOut);
		user.add(quit);
		
		//adding action to the menu
		logOut.addActionListener(this);
		quit.addActionListener(this);
		
		//adding to the menu bar object
		menuBar.add(user);
		menuBar.add(view);
		menuBar.add(placeOrder);
		
		//adding it to the frame
		this.setJMenuBar(menuBar);
	}
	
	public void mainUI() {
		String accountName = authorisedUser.getUserName();
		String accountId = authorisedUser.getID();
		Double accountBalance = authorisedUser.getAccount().getBalance();
		
		
		
		//defining the reference variable
		pnlTop = new JPanel();
		pnlMainTop = new JPanel();
		pnlMainCentral = new JPanel();
		pnlMainBottom = new JPanel();
		txtAccountName = new JLabel("Hi " + accountName + ", it's good to see you back!");
		txtAccountID = new JLabel("Account ID:");
		fldAccountID = new JFormattedTextField(accountId);
		txtBalance = new JLabel("Current Balance: ");
		fldBalance = new JFormattedTextField(accountBalance);
		
		//change font and color
		txtAccountName.setFont(new Font("Arial",Font.BOLD,16));
		
		//add to the panel
		pnlMainTop.add(txtAccountName);
		pnlMainCentral.add(txtAccountID);
		pnlMainCentral.add(fldAccountID);
		pnlMainBottom.add(txtBalance);
		pnlMainBottom.add(fldBalance);
		
		//set the text field not editable
		fldAccountID.setEditable(false);
		fldBalance.setEditable(false);
		
		//set the layout
		pnlTop.setLayout(new GridLayout(3,0));
		pnlMainTop.setLayout(new GridLayout(0,1));
		pnlMainCentral.setLayout(new GridLayout(0,6));
		pnlMainBottom.setLayout(new GridLayout(0,6));
		
		//set the size of the text field
		fldAccountID.setPreferredSize(new Dimension(200,25));
		fldBalance.setPreferredSize(new Dimension(200,25));
		
		//add to other panel with position
		pnlTop.add(pnlMainTop);
		pnlTop.add(pnlMainCentral);
		pnlTop.add(pnlMainCentral);
		pnlTop.add(pnlMainBottom);
		pnlTop.add(pnlMainBottom);
		
		//add to the main frame
		this.add(pnlTop, BorderLayout.NORTH);
		
		tabbedPane();
	}
	
	//
	public void tabbedPane() {
		//defining 
		pnlBottom = new JPanel();
		pnlPurchaseList = new JPanel();
		pnlAccountDetail = new JPanel();
		tbpPurchaseList = new JTabbedPane();
		
		productTable();
				
		//add to the panel
		tbpPurchaseList.add(pnlPurchaseList, "Inventory");
		tbpPurchaseList.add(pnlAccountDetail, "Account");
		pnlBottom.add(tbpPurchaseList);
		pnlBottom.setLayout(new GridLayout(1,0));
		this.add(pnlBottom, BorderLayout.CENTER);
	}
	
	//
	public void productTable() {
		pnlPurchaseList = new JPanel();
		topInventoryPanel = new JPanel();
		centralInventoryPanel = new JPanel(new BorderLayout());
		bottomInventoryPanel = new JPanel();
		txtSearchProduct = new JLabel("Search Product:");
		fldSearch = new JTextField(10);
		btnSearch = new JButton("Search");
		txtQuantity = new JLabel("Quantity: ");
		fldQuantity = new JTextField(10);
		btnPurcase = new JButton("Purchase");
		
		//set the pnlPurchaseList a border layout
		pnlPurchaseList.setLayout(new BorderLayout());
		
		//top panel
		topInventoryPanel.add(txtSearchProduct);
		topInventoryPanel.add(fldSearch);
		topInventoryPanel.add(btnSearch);
		pnlPurchaseList.add(topInventoryPanel, BorderLayout.NORTH);
		
		//center panel
		tableModel = new DefaultTableModel(head,0);
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		scrollTable = new JScrollPane(table);
		centralInventoryPanel.add(scrollTable);
		pnlPurchaseList.add(centralInventoryPanel, BorderLayout.CENTER);
		
		//bottom panel 
		bottomInventoryPanel.add(txtQuantity);
		bottomInventoryPanel.add(fldQuantity);
		bottomInventoryPanel.add(btnPurcase);
		pnlPurchaseList.add(bottomInventoryPanel, BorderLayout.SOUTH);
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
		
		if (authorisedUser.getAttempt() < 1) {
			JOptionPane.showMessageDialog(null, "You haven't log in yet, please log in first!","Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(this,ask,"Please process to login:  ",JOptionPane.OK_CANCEL_OPTION);
			
		 	if (result == JOptionPane.OK_OPTION) {
		 		authorisedUser.setAttempt(0);
		 		view.setEnabled(false);
		 		placeOrder.setEnabled(false);
		 		JOptionPane.showMessageDialog(null, "See you later ~ ~","Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/*
	 * Created class on 22/11/2024 by Anson.
	 * 		Anson: I create a class for GUI that are able to register, login, lot out, and quit the program
	 * 			   Also, I add a short cut for the user to using those action and you can see it on the menu bar
	 * 			   So the register are using the linkedlistt to store the username and password 
	 * 			   Once the user register successfully, then the user can log in to the account 
	 * 			   if the username and password matched.
	 *
	 * 
	 */
}
