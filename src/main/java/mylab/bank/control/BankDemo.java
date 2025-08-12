package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
	public static void main(String[] args) {
		Bank bank = new Bank();
		
		 System.out.println("=== ���� ���� ===");
	        String acc1 = bank.createSavingsAccount("ȫ�浿", 10000, 3.0);
	        String acc2 = bank.createCheckingAccount("��ö��", 20000, 5000);
	        String acc3 = bank.createSavingsAccount("�̿���", 30000, 2.0);

	        bank.printAllAccounts();

	        System.out.println("\n=== �Ա�/��� �׽�Ʈ ===");
	        try {
	            bank.deposit(acc1, 5000);
	            bank.withdraw(acc2, 3000);
	        } catch (Exception e) {
	            System.out.println("���� �߻�: " + e.getMessage());
	        }

	        System.out.println("\n=== ���� ���� �׽�Ʈ ===");
	        try {
	            SavingsAccount sa = (SavingsAccount) bank.findAccount(acc1);
	            sa.applyInterest();
	        } catch (Exception e) {
	            System.out.println("���� �߻�: " + e.getMessage());
	        }

	        System.out.println("\n=== ���� ��ü �׽�Ʈ ===");
	        try {
	            bank.transfer(acc3, acc2, 5000);
	        } catch (Exception e) {
	            System.out.println("���� �߻�: " + e.getMessage());
	        }
	        bank.printAllAccounts();

	        System.out.println("\n=== ���� �׽�Ʈ ===");
	        try {
	            bank.withdraw(acc2, 6000);
	        } catch (Exception e) {
	            System.out.println("���� �߻�: " + e.getMessage());
	        }

	        try {
	            bank.findAccount("AC9999");
	        } catch (Exception e) {
	            System.out.println("���� �߻�: " + e.getMessage());
	        }
	}

}
