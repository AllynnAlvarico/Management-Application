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
	private JMenu user,loan,help;
	private JMenuItem loanApplication,logOut, quit;
	private JMenuBar menuBar;
	
	//variable for account details 
	private JLabel lblAccountName, lblAccountID, lblBalance, lblAccountType, lblCurrency, lblStatus;
	private JFormattedTextField txtAccountID, txtBalance, txtAccountType, txtCurrency, txtStatus;
	
	//variable for personal details
	private JLabel lblCustomerID, lblCustomerName, lblCustomerEmail, lblCustomerPhoneNo;
	private JFormattedTextField txtCustomerID, txtCustomerName, txtCustomerEmail, txtCustomerPhoneNo;
	
	//variable for main UI 
	private JPanel pnlTop, pnlBottom;
	private JPanel pnlPersonalDetail,pnlAdd_Withdraw_List, pnlTransfer, pnlExchange, pnlAccountDetail;
	private JPanel pnlMainTop, pnlMainCentral,pnlMainBottom;
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
		
		loan = new JMenu("Loan");
		loanApplication = new JMenuItem("Loan Application");
		
		help = new JMenu("Help");
		
		//set the short cut for the function
		logOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK));
		
		//adding to the user menu
		user.add(logOut);
		user.add(quit);
		loan.add(loanApplication);
		
		//adding action to the menu
		loanApplication.addActionListener(this);
		logOut.addActionListener(this);
		quit.addActionListener(this);
		
		
		//adding to the menu bar object
		menuBar.add(user);
		menuBar.add(loan);
		menuBar.add(help);
		
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
		
		lblAccountName = new JLabel("Hi " + accountName + ", it's good to see you back!");
		lblAccountID = new JLabel("Account ID:");
		txtAccountID = new JFormattedTextField(accountId);
		lblBalance = new JLabel("Current Balance: ");
		txtBalance = new JFormattedTextField(accountBalance);
		btnLogOut = new JButton("Log Out");
		
		//change font and color
		lblAccountName.setFont(new Font("Arial",Font.BOLD,16));
		
		//set the layout
		pnlTop.setLayout(new GridLayout(4,0));
		pnlMainTop.setLayout(new BorderLayout());
		pnlMainCentral.setLayout(new GridLayout(0,6));
		pnlMainBottom.setLayout(new GridLayout(0,6));
		
		//add to the panel
		pnlMainTop.add(lblAccountName, BorderLayout.WEST);
		pnlMainTop.add(btnLogOut, BorderLayout.EAST);
		pnlMainCentral.add(lblAccountID);
		pnlMainCentral.add(txtAccountID);
		pnlMainBottom.add(lblBalance);
		pnlMainBottom.add(txtBalance);
		
		//adding action to the log out button
		btnLogOut.addActionListener(this);
		
		//set the text field not editable
		txtAccountID.setEditable(false);
		txtBalance.setEditable(false);
		
		//set the size of the text field
		txtAccountID.setPreferredSize(new Dimension(200,25));
		txtBalance.setPreferredSize(new Dimension(200,25));
		
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
		pnlPersonalDetail = new JPanel();
		pnlAdd_Withdraw_List = new JPanel();
		pnlTransfer = new JPanel();
		pnlExchange = new JPanel();
		tbpList = new JTabbedPane();
		
		//calling method
		accountTable();
		personalDetailPanel();
		addWithdrawPanel();
		transactionPanel();
		exchangePanel();
				
		//add to the panel
		tbpList.add(pnlAccountDetail, "Account");
		tbpList.add(pnlPersonalDetail, "Personal");
		tbpList.add(pnlAdd_Withdraw_List, "Add/Withdraw Money");
		tbpList.add(pnlTransfer, "Transaction");
		tbpList.add(pnlExchange, "Exchange");
		pnlBottom.add(tbpList);
		pnlBottom.setLayout(new GridLayout(1,0));
		this.add(pnlBottom, BorderLayout.CENTER);
	}
	
	public void addWithdrawPanel() {
		String accountId = authorisedUser.getID();
		
		pnlAdd_Withdraw_List = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel topAddWithdrawPanel = new JPanel();
		JPanel centralAddWithdrawPanel = new JPanel();
		JPanel bottomAddWithdrawPanel = new JPanel();
		lblAccountID = new JLabel("Account ID");
		JFormattedTextField txtAccountID = new JFormattedTextField(accountId);
		JLabel txtAmount = new JLabel("Amount ");
		JFormattedTextField fldAmount = new JFormattedTextField(0.00);
		JButton btnAdd = new JButton("Add");
		JButton btnWithdrawal = new JButton("Withdraw");
		JTextArea txtResult = new JTextArea();
		
		//set the account ID not editable
		txtAccountID.setEditable(false);
		
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
	protected void accountTable() {
		String accountId = authorisedUser.getID();
		Double accountBalance = authorisedUser.getAccount().getBalance();
		
		pnlAccountDetail = new JPanel();
		pnlAccount = new JPanel();
		lblAccountID = new JLabel("Account ID ");
		txtAccountID = new JFormattedTextField(accountId);
		lblBalance = new JLabel("Current Balance  ");
		txtBalance = new JFormattedTextField(accountBalance);
		lblCurrency = new JLabel("Currency  ");
		txtCurrency = new JFormattedTextField("EUR");
		lblAccountType = new JLabel("Account Type  ");
		txtAccountType = new JFormattedTextField("Advance");
		lblStatus = new JLabel("Status  ");
		txtStatus = new JFormattedTextField("Active");
		
		//set it to grid layout
		pnlAccount.setLayout(new GridLayout(5,2));
		
		//set the account ID not editable
		txtAccountID.setEditable(false);
		
		//set the status to green if is active
		txtStatus.setForeground(Color.GREEN);
		
		//set the text field not editable
		txtBalance.setEditable(false);
		txtCurrency.setEditable(false);
		txtAccountType.setEditable(false);
		txtStatus.setEditable(false);
		
		//set the preferred size of text field
		txtAccountID.setPreferredSize(new Dimension(250,50));
		txtBalance.setPreferredSize(new Dimension(250,50));
		txtCurrency.setPreferredSize(new Dimension(250,50));
		txtAccountType.setPreferredSize(new Dimension(250,50));
		txtStatus.setPreferredSize(new Dimension(250,50));

		//add to the pnlAccount
		pnlAccount.add(lblAccountID);
		pnlAccount.add(txtAccountID);
		pnlAccount.add(lblBalance);
		pnlAccount.add(txtBalance);
		pnlAccount.add(lblCurrency);
		pnlAccount.add(txtCurrency);
		pnlAccount.add(lblAccountType);
		pnlAccount.add(txtAccountType);
		pnlAccount.add(lblStatus);
		pnlAccount.add(txtStatus);
		
		//add to the pnlAccountDetail panel
		pnlAccountDetail.add(pnlAccount, BorderLayout.CENTER);
	}
	
	protected void personalDetailPanel() {
		pnlPersonalDetail = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel topMainPanel = new JPanel();
		JPanel bottomMainPanel = new JPanel();
		lblCustomerID = new JLabel("Customer ID");
		txtCustomerID = new JFormattedTextField("D22124567");
		lblCustomerName = new JLabel("Name");
		txtCustomerName = new JFormattedTextField("Anson Ling");
		lblCustomerEmail = new JLabel("Email");
		txtCustomerEmail  = new JFormattedTextField("D221245672gmail.com");
		lblCustomerPhoneNo = new JLabel("Phone Number");
		txtCustomerPhoneNo = new JFormattedTextField("087-991-7659");
		JButton btnMakeChange = new JButton("Make Change");
		JButton btnConfirmChange = new JButton("Confirm Change");
		
		//set the layout
		mainPanel.setLayout(new BorderLayout());
		topMainPanel.setLayout(new GridLayout(4,2));
		
		//set the preferred size for the text field
		txtCustomerID.setPreferredSize(new Dimension(250,50));
		txtCustomerName.setPreferredSize(new Dimension(250,50));
		txtCustomerEmail.setPreferredSize(new Dimension(250,50));
		txtCustomerPhoneNo.setPreferredSize(new Dimension(250,50));
		
		//set the text field is non editable 
		txtCustomerID.setEditable(false);
		txtCustomerName.setEditable(false);
		txtCustomerEmail.setEditable(false);
		txtCustomerPhoneNo.setEditable(false);
		
		//add to the top main panel
		topMainPanel.add(lblCustomerID);
		topMainPanel.add(txtCustomerID);
		topMainPanel.add(lblCustomerName);
		topMainPanel.add(txtCustomerName);
		topMainPanel.add(lblCustomerEmail);
		topMainPanel.add(txtCustomerEmail);
		topMainPanel.add(lblCustomerPhoneNo);
		topMainPanel.add(txtCustomerPhoneNo);
		
		//add to the bottom main panel
		bottomMainPanel.add(btnMakeChange);
		bottomMainPanel.add(btnConfirmChange);
		
		//add to the main panel
		mainPanel.add(topMainPanel, BorderLayout.NORTH);
		mainPanel.add(bottomMainPanel, BorderLayout.SOUTH);
		
		//add to the personal details tab table panel
		pnlPersonalDetail.add(mainPanel);
	}
	
	protected void transactionPanel() {
		String[] arrAccountType = {"Saving","Business","Investment"};
		
		pnlTransfer = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel topMainPanel = new JPanel();
		JPanel centralMainPanel = new JPanel();
		JPanel bottomMainPanel = new JPanel();
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
		String[] arrExchangeCurrency = {"EUR","USD-Dollar","GBP","SGD","PHP","JPY","MYR"};
		Double accountBalance = authorisedUser.getAccount().getBalance();
		
		pnlExchange = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel topMainPanel = new JPanel();
		JPanel centralMainPanel = new JPanel();
		JPanel bottomMainPanel = new JPanel();
		JLabel lblExchangeMessage = new JLabel("Exchange Here");
		JLabel lblExchangeAmount = new JLabel("Main Amount: ");
		JFormattedTextField txtExchangeAmount = new JFormattedTextField(accountBalance);
		JComboBox<String> cmbExchangeCurrency = new JComboBox<>(arrExchangeCurrency);
		JLabel lblTargetExchangeAmount = new JLabel("Target Amount: ");
		JFormattedTextField txtTargetExchangeCurrency = new JFormattedTextField();
		JComboBox<String> cmbTargetExchangeCurrency = new JComboBox<>(arrExchangeCurrency);
		JButton btnConfirmExchange = new JButton("Confirm");
		
		//set the layout
		mainPanel.setLayout(new BorderLayout());
		centralMainPanel.setLayout(new GridLayout(2,3,30,50));
		
		//set the preferred size for the text field 
		txtExchangeAmount.setPreferredSize(new Dimension(200,25));
		txtTargetExchangeCurrency.setPreferredSize(new Dimension(200,25));
		
		//set the front
		lblExchangeMessage.setFont(new Font("Arial",Font.BOLD,23));
		
		//add to the top main panel
		topMainPanel.add(lblExchangeMessage);
		
		//add to the central main panel
		centralMainPanel.add(lblExchangeAmount);
		centralMainPanel.add(txtExchangeAmount);
		centralMainPanel.add(cmbExchangeCurrency);
		centralMainPanel.add(lblTargetExchangeAmount);
		centralMainPanel.add(txtTargetExchangeCurrency);
		centralMainPanel.add(cmbTargetExchangeCurrency);
		
		//add to the bottom main panel
		bottomMainPanel.add(btnConfirmExchange);
		
		//add to the main panel
		mainPanel.add(topMainPanel, BorderLayout.NORTH);
		mainPanel.add(centralMainPanel, BorderLayout.CENTER);
		mainPanel.add(bottomMainPanel, BorderLayout.SOUTH);
		
		//add to the exchange tab table panel
		pnlExchange.add(mainPanel);
	}
	
	protected void loanMenu() {
		String[] arrCompound = {"Daily(APR)","Weekly(APR)","Monthly(APR)"};
		String[] arrPayBack = {"Every Day","Every Week","Every Month"};
		
		JDialog loanDialog = new JDialog();
		loanDialog.setLayout(new BorderLayout());
		loanDialog.setVisible(true);
		
		JPanel topPanel = new JPanel();
		JPanel westPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		JPanel westNorthPanel = new JPanel();
		
		JLabel lblLoanApplicationForm = new JLabel("Loan Application Form");
		JLabel lblLoanBalance = new JLabel("Loan Balance");
		JFormattedTextField txtLoanBalance = new JFormattedTextField();
		JLabel lblInterestRate = new JLabel("Interest Rate");
		JFormattedTextField txtInterestRate = new JFormattedTextField();
		JLabel lblCompound = new JLabel("Compound");
		JComboBox<String> cmbCompound = new JComboBox<>(arrCompound);
		JLabel lblPayBack = new JLabel("Pay Back");
		JComboBox<String> cmbPayBack = new JComboBox<>(arrPayBack);
		JLabel lblRepayTime = new JLabel("Repay within the fixed time");
		JTextField txtYear = new JTextField("Year");
		JTextField txtMonth = new JTextField("Month");
		JLabel lblResult = new JLabel("Result");
		JTextArea txtResult = new JTextArea();
		JButton btnCalculate = new JButton("Calculate");
		JButton btnClear = new JButton("Clear");
		
		//add to the top panel
		topPanel.add(lblLoanApplicationForm);
		
		//set the layout for west panel
		westPanel.setLayout(new GridLayout(7,2,10,10));
		westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
		
		//add to the west panel
		westPanel.add(lblLoanBalance);
		westPanel.add(txtLoanBalance);
		westPanel.add(lblInterestRate);
		westPanel.add(txtInterestRate);
		westPanel.add(lblCompound);
		westPanel.add(cmbCompound);
		westPanel.add(lblPayBack);
		westPanel.add(cmbPayBack);
		westPanel.add(lblRepayTime);
		westPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)) {{
	        add(txtYear);
	        add(txtMonth);
	    }});
		
		//set the layout for east panel
		eastPanel.setLayout(new BorderLayout());
		
		//add to the west panel
		eastPanel.add(lblResult, BorderLayout.NORTH);
		eastPanel.add(txtResult, BorderLayout.CENTER);
		
		//add to the bottom panel
		bottomPanel.add(btnCalculate);
		bottomPanel.add(btnClear);
		
		//eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
		loanDialog.add(topPanel, BorderLayout.NORTH);
		loanDialog.add(westPanel, BorderLayout.WEST);
		loanDialog.add(eastPanel, BorderLayout.CENTER);
		loanDialog.add(bottomPanel, BorderLayout.SOUTH);
		
		loanDialog.setSize(WIDTH,HEIGHT);
		loanDialog.setLocationRelativeTo(null);
		loanDialog.setResizable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Log Out")) {
			logOut();
		} else if (e.getActionCommand().equals("Add")) {
			
		} else if (e.getActionCommand().equals("Loan Application")) {
			loanMenu();
		}else {
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
		JPanel pnlAsk = new JPanel();
		pnlAsk.add(new JLabel("Do you sure you wish to log out?"));	
		
//<<<<<<< HEAD
//		int result = JOptionPane.showConfirmDialog(this,pnlAsk,"Please process to login:  ",JOptionPane.OK_CANCEL_OPTION);
//			
//		 if (result == JOptionPane.OK_OPTION) {
//		 	view.setEnabled(false);
//		 	placeOrder.setEnabled(false);
//		 	JOptionPane.showMessageDialog(null, "See you later ~ ~","Info", JOptionPane.INFORMATION_MESSAGE);
//=======
		if (authorisedUser.getAttempt() < 1) {
			JOptionPane.showMessageDialog(null, "You haven't log in yet, please log in first!","Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			int result = JOptionPane.showConfirmDialog(this, "Ask" ,"Please process to login:  ",JOptionPane.OK_CANCEL_OPTION);
			
		 	if (result == JOptionPane.OK_OPTION) {
		 		authorisedUser.setAttempt(0);
		 		loan.setEnabled(false);
		 		help.setEnabled(false);
		 		JOptionPane.showMessageDialog(null, "See you later ~ ~","Info", JOptionPane.INFORMATION_MESSAGE);
			}
//>>>>>>> dbf0b35761bf67d3e1b70b3b09389371fd8f7b20
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
