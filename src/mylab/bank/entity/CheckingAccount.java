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
	
	public void withdraw(double amount) 
			throws WithdrawalLimitExceededException{
		if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException("��� �ѵ��� �ʰ��߽��ϴ�. �ѵ�: " + withdrawalLimit + "��");
        }
        if (amount > balance) {
            throw new WithdrawalLimitExceededException("�ܾ� ����: ���� �ܾ��� " + balance + "���Դϴ�.");
        }
        balance -= amount;
        System.out.printf("%.1f���� ��ݵǾ����ϴ�. ���� �ܾ�: %.1f��%n", amount, balance);
	}
	
	@Override
    public String toString() {
        return String.format("���¹�ȣ: %s, ������: %s, �ܾ�: %.1f��, ��� �ѵ�: %.1f��",
                getAccountNumber(), getOwnerName(), getBalance(), withdrawalLimit);
    }
}
