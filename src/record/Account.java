package record;

public class Account {
	
	private String accountID;
	private String customerID;
	
	private double balance;
	private String accountType;
	private String userCurrency;
	
	private String openTime;
	private String closeTime;
	private boolean status;
	
	public Account(String accId, String cusId, String type){
		this.accountID = accId;
		this.customerID = cusId;
		this.accountType = type;
		
		this.balance = 0.0;
		this.status = true;
	}
	
	
	public void setAccountID(String accountID) {this.accountID = accountID;}
	public void setCustomerID(String customerID) {this.customerID = customerID;}
	
	public void setBalance(double userBalance) {this.balance = userBalance;}
	public void setAccountType(String type) {this.accountType = type;}
	
	public void setUserCurrency(String currency) {this.userCurrency = currency;}
	
	public void setOpenTime(String open) {this.openTime = open;}
	public void setCloseTime(String close) {this.closeTime = close;}
	
	public void setStatus(boolean userStatus) {this.status = userStatus;}
	
	public String getAccountID() {return accountID;}
	public String getCustomerID() {return customerID;}

	public double getBalance() {return balance;}

	public String getAccountType() {return accountType;}
	public String getUserCurrency() {return userCurrency;}

	public String getOpenTime() {return openTime;}
	public String getCloseTime() {return closeTime;}

	public boolean isStatus() {return status;}
	
	public void addToBalance(double addAmount) {this.balance += addAmount;}
	public void deductToBalance(double minusAmount) {this.balance -= minusAmount;}
	public boolean isAboveZero() {return this.balance > 0.00;}
	
	
}
