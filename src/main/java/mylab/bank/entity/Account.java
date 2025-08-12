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
		System.out.printf("%.1f���� �ԱݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, balance);
	}
	
	public void withdraw(double amount) throws InsufficientBalanceException{
		if(amount > balance) {
			String errMessage = String.format("�ܾ� ����: ���� �ܾ��� " + balance + "���Դϴ�.");
			throw new InsufficientBalanceException(errMessage);
		}
		balance -= amount;
		System.out.printf("%.1f���� ��ݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, balance);
	}
	
	@Override
    public abstract String toString();
	
}
