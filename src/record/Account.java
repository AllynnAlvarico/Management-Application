package record;

public class Account {
	
	private String accountID;
	private String customerID;
	private double balance;
	private String accountType;
	private String currency;
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
}
