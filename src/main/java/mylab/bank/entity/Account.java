package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
	private String accountNumber;
	private String ownerName;
	protected double balance;

	public Account(String accountNumber, String ownerName, double balance) {
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		balance += amount;
		System.out.printf("%.1f원이 입금되었습니다. 현재 잔액: %.1f원%n", amount, balance);
	}
	
	public void withdraw(double amount) throws InsufficientBalanceException{
		if(amount > balance) {
			String errMessage = String.format("잔액 부족: 현재 잔액은 " + balance + "원입니다.");
			throw new InsufficientBalanceException(errMessage);
		}
		balance -= amount;
		System.out.printf("%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n", amount, balance);
	}
	
	@Override
    public abstract String toString();
	
}
