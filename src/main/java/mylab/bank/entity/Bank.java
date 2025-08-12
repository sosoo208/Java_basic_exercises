package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;
import mylab.bank.exception.*;

public class Bank {
	private int nextAccountNumber;
	private List<Account> accounts;
	
	public Bank() {
		accounts = new ArrayList<>();
		nextAccountNumber = 1000;
	}
	
	public String createSavingsAccount(String ownerName, double balance, double interestRate) {
		String accNum = "AC" + nextAccountNumber++;
        accounts.add(new SavingsAccount(accNum, ownerName, balance, interestRate));
        return accNum;
	}
	
	public String createCheckingAccount(String ownerName, double balance, double limit) {
        String accNum = "AC" + nextAccountNumber++;
        accounts.add(new CheckingAccount(accNum, ownerName, balance, limit));
        return accNum;
    }
	
	public Account findAccount(String accountNumber) throws AccountNotFoundException {
        return accounts.stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("���¹�ȣ " + accountNumber + "�� �ش��ϴ� ���¸� ã�� �� �����ϴ�."));
    }
	
	public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }
    
    public void transfer(String fromAcc, String toAcc, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account src = findAccount(fromAcc);
        Account dest = findAccount(toAcc);
        src.withdraw(amount);
        dest.deposit(amount);
        System.out.printf("%.1f���� %s���� %s�� �۱ݵǾ����ϴ�.%n", amount, fromAcc, toAcc);
    }

    public void printAllAccounts() {
        System.out.println("=== ��� ���� ��� ===");
        accounts.forEach(acc -> System.out.println(acc));
        System.out.println("===================");
    }
}
