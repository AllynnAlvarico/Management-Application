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
	private JLabel lblAccountID, txtCustomerID, txtBalance, lblAccountType, txtCurrency, txtStatus;
	private JFormattedTextField txtAccountID, fldCustomerID, fldBalance, fldAccountType, fldCurrency, fldStatus;
	
	//variable for main UI 
	private JPanel pnlTop, pnlBottom;
	private JPanel pnlAdd_Withdraw_List, pnlTransfer, pnlExchange, pnlAccountDetail;
	private JPanel pnlMainTop, pnlMainCentral,pnlMainBottom;
	private JLabel txtAccountName;
	private JTabbedPane tbpList;
	private JButton btnLogOut;
	private Date date;
	
	//variable for inventory purchase function
	/*private JPanel topInventoryPanel, centralInventoryPanel, bottomInventoryPanel;
	private JTextField fldSearch, fldSearchproductID, fldQuantity;
	private JLabel txtSearchProduct, txtSearchproductID, txtQuantity;
	private JButton btnSearch, btnPurcase;
	private JTable table;
	private JScrollPane scrollTable;
	private DefaultTableModel tableModel;*/
	//private String[] head = {"Product ID","Product Name","Product Type","Price","Quantity"};
	
	//variable for account details table
	private JPanel pnlAccount;
	
	private LinkedList<User> list;
	private User u;
	
	//set the width and height
	private final int WIDTH = 1000, HEIGHT = 500;
	
	public ApplicationWindow() {
		super("Management Application");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		list = new LinkedList<>();
		
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
		//defining the reference variable
		pnlTop = new JPanel();
		pnlMainTop = new JPanel();
		pnlMainCentral = new JPanel();
		pnlMainBottom = new JPanel();
		txtAccountName = new JLabel("Hi Anson, it's good to see you back!");
		lblAccountID = new JLabel("Account ID:");
		txtAccountID = new JFormattedTextField("D22124534");
		txtBalance = new JLabel("Current Balance: ");
		fldBalance = new JFormattedTextField("1000.00");
		btnLogOut = new JButton("Log Out");
		
		//change font and color
		txtAccountName.setFont(new Font("Arial",Font.BOLD,16));
		
		//set the layout
		pnlTop.setLayout(new GridLayout(4,0));
		pnlMainTop.setLayout(new BorderLayout());
		pnlMainCentral.setLayout(new GridLayout(0,6));
		pnlMainBottom.setLayout(new GridLayout(0,6));
		
		//add to the panel
		pnlMainTop.add(txtAccountName, BorderLayout.WEST);
		pnlMainTop.add(btnLogOut, BorderLayout.EAST);
		pnlMainCentral.add(lblAccountID);
		pnlMainCentral.add(txtAccountID);
		pnlMainBottom.add(txtBalance);
		pnlMainBottom.add(fldBalance);
		
		//adding action to the log out button
		btnLogOut.addActionListener(this);
		
		//set the text field not editable
		txtAccountID.setEditable(false);
		fldBalance.setEditable(false);
		
		//set the size of the text field
		txtAccountID.setPreferredSize(new Dimension(200,25));
		fldBalance.setPreferredSize(new Dimension(200,25));
		
		//add to other panel with position
		pnlTop.add(pnlMainTop);
		pnlTop.add(pnlMainCentral);
		pnlTop.add(pnlMainCentral);
		pnlTop.add(pnlMainBottom);
		pnlTop.add(pnlMainBottom);
		
		//add to the main frame
		this.add(pnlTop, BorderLayout.NORTH);
		
		//calling method for tabbed panel
		tabbedPane();
	}
	
	//
	public void tabbedPane() {
		//defining 
		pnlBottom = new JPanel(); // the bottom of the main panel
		pnlAccountDetail = new JPanel();
		pnlAdd_Withdraw_List = new JPanel();
		pnlTransfer = new JPanel();
		pnlExchange = new JPanel();
		tbpList = new JTabbedPane();
		
		//calling method
		//productTable();
		accountTable();
		addWithdrawPanel();
		transactionPanel();
				
		//add to the panel
		tbpList.add(pnlAccountDetail, "Account");
		tbpList.add(pnlAdd_Withdraw_List, "Add/Withdraw Money");
		tbpList.add(pnlTransfer, "Transaction");
		tbpList.add(pnlExchange, "Exchange");
		pnlBottom.add(tbpList);
		pnlBottom.setLayout(new GridLayout(1,0));
		this.add(pnlBottom, BorderLayout.CENTER);
	}
	
	public void addWithdrawPanel() {
		pnlAdd_Withdraw_List = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel topAddWithdrawPanel = new JPanel();
		JPanel centralAddWithdrawPanel = new JPanel();
		JPanel bottomAddWithdrawPanel = new JPanel();
		lblAccountID = new JLabel("Account ID");
		txtAccountID = new JFormattedTextField("D22124534");
		JLabel txtAmount = new JLabel("Amount ");
		JFormattedTextField fldAmount = new JFormattedTextField(0.00);
		JButton btnAdd = new JButton("Add");
		JButton btnWithdrawal = new JButton("Withdraw");
		JTextArea txtResult = new JTextArea();
		
		//set the layout
		mainPanel.setLayout(new BorderLayout());
		topAddWithdrawPanel.setLayout(new GridLayout(2,4));
		
		//set the preferred size 
		txtAccountID.setPreferredSize(new Dimension(200,25));
		fldAmount.setPreferredSize(new Dimension(200,25));
		txtResult.setPreferredSize(new Dimension(900,180));
		
		//add to the panel for add and withdraw
		topAddWithdrawPanel.add(lblAccountID);
		topAddWithdrawPanel.add(txtAccountID);
		topAddWithdrawPanel.add(txtAmount);
		topAddWithdrawPanel.add(fldAmount);
		centralAddWithdrawPanel.add(btnAdd);
		centralAddWithdrawPanel.add(btnWithdrawal);
		bottomAddWithdrawPanel.add(txtResult);
		
		//add to the panel on main panel 
		mainPanel.add(topAddWithdrawPanel, BorderLayout.NORTH);
		mainPanel.add(centralAddWithdrawPanel, BorderLayout.CENTER);
		mainPanel.add(bottomAddWithdrawPanel, BorderLayout.SOUTH);
		pnlAdd_Withdraw_List.add(mainPanel);
	}
	
	//method that display all the detail of the account
	public void accountTable() {
		pnlAccountDetail = new JPanel();
		pnlAccount = new JPanel();
		lblAccountID = new JLabel("Account ID ");
		txtAccountID = new JFormattedTextField("D22124534");
		txtCustomerID = new JLabel("Customer ID  ");
		fldCustomerID = new JFormattedTextField("D22124456");
		txtBalance = new JLabel("Current Balance  ");
		fldBalance = new JFormattedTextField("1000.00");
		txtCurrency = new JLabel("Currency  ");
		fldCurrency = new JFormattedTextField("EUR");
		lblAccountType = new JLabel("Account Type  ");
		fldAccountType = new JFormattedTextField("Advance");
		txtStatus = new JLabel("Status  ");
		fldStatus = new JFormattedTextField("Active");
		
		//set it to grid layout
		pnlAccount.setLayout(new GridLayout(6,2));
		
		//set the text field not editable
		txtAccountID.setEditable(false);
		fldCustomerID.setEditable(false);
		fldBalance.setEditable(false);
		fldCurrency.setEditable(false);
		fldAccountType.setEditable(false);
		fldStatus.setEditable(false);
		
		//set the preferred size of text field
		txtAccountID.setPreferredSize(new Dimension(250,50));
		fldCustomerID.setPreferredSize(new Dimension(250,50));
		fldBalance.setPreferredSize(new Dimension(250,50));
		fldCurrency.setPreferredSize(new Dimension(250,50));
		fldAccountType.setPreferredSize(new Dimension(250,50));
		fldStatus.setPreferredSize(new Dimension(250,50));
		
		//set the status to green if is active
		fldStatus.setForeground(Color.GREEN);

		//add to the pnlAccount
		pnlAccount.add(lblAccountID);
		pnlAccount.add(txtAccountID);
		pnlAccount.add(txtCustomerID);
		pnlAccount.add(fldCustomerID);
		pnlAccount.add(txtBalance);
		pnlAccount.add(fldBalance);
		pnlAccount.add(txtCurrency);
		pnlAccount.add(fldCurrency);
		pnlAccount.add(lblAccountType);
		pnlAccount.add(fldAccountType);
		pnlAccount.add(txtStatus);
		pnlAccount.add(fldStatus);
		
		//add to the pnlAccountDetail panel
		pnlAccountDetail.add(pnlAccount, BorderLayout.CENTER);
	}
	
	private void transactionPanel() {
		String[] arrAccountType = {"Saving","Business","Investment"};
		
		pnlTransfer = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel topMainPanel = new JPanel();
		JPanel centralMainPanel = new JPanel();
		JPanel bottomMainPanel = new JPanel();
//		JLabel lblEnterAccountDetail = new JLabel("Enter Account Details: ");
//		JLabel lblEnterEmail = new JLabel("Their email (Optional): ");
//		JFormattedTextField txtEmailOption = new JFormattedTextField();
		JLabel lblReceiverDetail = new JLabel("Enter Recipient's Detail");
		JLabel lblUsername = new JLabel("Username of the Account holder:");
		JFormattedTextField txtUsername = new JFormattedTextField();
		lblAccountType = new JLabel("Account Type:");
		JComboBox<String> cbAccountType = new JComboBox<>(arrAccountType);
		lblAccountID = new JLabel("Enter Account ID:");
		txtAccountID = new JFormattedTextField();
		JLabel lblAmountTransfer = new JLabel("Enter the Amount:");
		JFormattedTextField txtAmountTransfer = new JFormattedTextField();
		JButton btnComfirm = new JButton("Comfirm");
		
		//set the border for the panel
		mainPanel.setLayout(new BorderLayout());
		topMainPanel.setLayout(new GridLayout(2,0,3,0));
		centralMainPanel.setLayout(new GridLayout(8,0,3,0));
		//bottomMainPanel.setLayout(new GridLayout(2,0));
		
		//set the preferred size for the text field
		//txtEmailOption.setPreferredSize(new Dimension(200,25));
		txtUsername.setPreferredSize(new Dimension(200,25));
		txtAccountID.setPreferredSize(new Dimension(200,25));
		txtAmountTransfer.setPreferredSize(new Dimension(200,25));
		
		//set label font 
		//lblEnterAccountDetail.setFont(new Font("Arial",Font.BOLD,16));
		lblReceiverDetail.setFont(new Font("Arial",Font.BOLD,23));
		
		//add to the top main panel
		topMainPanel.add(lblReceiverDetail);
//		topMainPanel.add(lblEnterEmail);
//		topMainPanel.add(txtEmailOption);
		
		//add to the central main panel
		//centralMainPanel.add(lblReceiverDetail);
		centralMainPanel.add(lblUsername);
		centralMainPanel.add(txtUsername);
		centralMainPanel.add(lblAccountType);
		centralMainPanel.add(cbAccountType);
		centralMainPanel.add(lblAccountID);
		centralMainPanel.add(txtAccountID);
		centralMainPanel.add(lblAmountTransfer);
		centralMainPanel.add(txtAmountTransfer);
		
		//add to the bottom main panel
		bottomMainPanel.add(btnComfirm);
		
		//add to the main panel
		mainPanel.add(topMainPanel, BorderLayout.NORTH);
		mainPanel.add(centralMainPanel, BorderLayout.CENTER);
		mainPanel.add(bottomMainPanel, BorderLayout.SOUTH);
		
		//add to the tab table
		pnlTransfer.add(mainPanel);
	}
	
	public void exchangePanel() {
		
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
	
	//search for the user
	public User searchUser(String name, String pass) {
		User user = null;
		for (User each: list) {
			if (name.equalsIgnoreCase(each.getUserName()) && pass.equals(each.getUserPassword())) {
				user = each;
				return user;
			}
		}
		return null;
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
		JPanel pnlAsk = new JPanel();
		pnlAsk.add(new JLabel("Do you sure you wish to log out?"));	
		
		int result = JOptionPane.showConfirmDialog(this,pnlAsk,"Please process to login:  ",JOptionPane.OK_CANCEL_OPTION);
			
		 if (result == JOptionPane.OK_OPTION) {
		 	view.setEnabled(false);
		 	placeOrder.setEnabled(false);
		 	JOptionPane.showMessageDialog(null, "See you later ~ ~","Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*
	 * Created class on 22/11/2024 by) Anson.
	 * 		Anson: I create a class for GUI that are able to register, login, lot out, and quit the program
	 * 			   Also, I add a short cut for the user to using those action and you can see it on the menu bar
	 * 			   So the register are using the linkedlistt to store the username and password 
	 * 			   Once the user register successfully, then the user can log in to the account 
	 * 			   if the username and password matched.
	 *
	 * 
	 */
}
