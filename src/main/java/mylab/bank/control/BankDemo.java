package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        String acc1 = bank.createSavingsAccount("홍길동", 10000, 3.0);
        String acc2 = bank.createCheckingAccount("김철수", 20000, 5000);
        String acc3 = bank.createSavingsAccount("이영희", 30000, 2.0);

        bank.printAllAccounts();

        System.out.println("\n=== 입금/출금 테스트 ===");
        try {
            bank.deposit(acc1, 5000);
            bank.withdraw(acc2, 3000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 이자 적용 테스트 ===");
        try {
            SavingsAccount sa = (SavingsAccount) bank.findAccount(acc1);
            sa.applyInterest();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 계좌 이체 테스트 ===");
        try {
            bank.transfer(acc3, acc2, 5000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        bank.printAllAccounts();

        System.out.println("\n=== 예외 테스트 ===");
        try {
            bank.withdraw(acc2, 6000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.findAccount("AC9999");
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
