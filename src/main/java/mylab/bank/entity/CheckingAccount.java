package mylab.bank.entity;

import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    @Override
    public void withdraw(double amount) throws WithdrawalLimitExceededException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다. 한도: " + withdrawalLimit + "원");
        }
        if (amount > balance) {
            throw new WithdrawalLimitExceededException("잔액 부족: 현재 잔액은 " + balance + "원입니다.");
        }
        balance -= amount;
        System.out.printf("%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n", amount, balance);
    }

    @Override
    public String toString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원, 출금 한도: %.1f원",
                getAccountNumber(), getOwnerName(), getBalance(), withdrawalLimit);
    }
}
