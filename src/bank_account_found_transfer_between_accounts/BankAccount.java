package bank_account_found_transfer_between_accounts;

import bank_account.AccountDormantException;

import java.math.BigDecimal;

public class BankAccount {
    private final Long accountNumber;
    private AccountStatus status;
    private BigDecimal accountBalance;

    public BankAccount(Long accountNumber, AccountStatus status, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.status = status;
        this.accountBalance = accountBalance;
    }

    public Long getAccountNumber() { return accountNumber; }
    public AccountStatus getStatus() { return status; }
    public BigDecimal getAccountBalance() { return accountBalance; }

    //TRANSFER MONEY
    public void transferMoney(BigDecimal amount) {
        //IF ACCOUNT IS DORMANT CANNOT TRANSFER MONEY
        if(this.status == AccountStatus.DORMANT){
            throw new AccountDormantException("Cannot charge fee due to account is DORMANT");
        }
        //IF ACCOUNT BALANCE IS LOWER THAN TRANSFER AMOUNT
        if(this.accountBalance.compareTo(amount) < 0 ){
            throw new InsufficientBalanceException("Insufficient account balance");
        }

        //WITHDRAW MONEY
        this.accountBalance = this.accountBalance.subtract(amount);

        //IF BALANCE IS LOWER THAN 0 SET STATUS
        if (this.accountBalance.compareTo(BigDecimal.ZERO) <= 0 ) {
            this.status = AccountStatus.OVERDRAWN;
        }
    }

    //DEPOSIT MONEY
    public void depositMoney(BigDecimal amount) {

        //WITHDRAW MONEY
        this.accountBalance = this.accountBalance.add(amount);

        //IF BALANCE IS LOWER THAN 0 SET STATUS
        if (this.accountBalance.compareTo(BigDecimal.ZERO) >= 0 && this.status == AccountStatus.OVERDRAWN ) {
            this.status = AccountStatus.ACTIVE;
        }
    }

}
