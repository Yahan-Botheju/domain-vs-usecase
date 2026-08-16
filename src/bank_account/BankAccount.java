package bank_account;

import java.math.BigDecimal;

public class BankAccount {
    private Long accountNumber;
    private AccountStatus status;
    private BigDecimal accountBalance;

    public BankAccount(Long accountNumber, AccountStatus status, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.status = status;
        this.accountBalance = accountBalance;
    }

    public Long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Long accountNumber) { this.accountNumber = accountNumber; }
    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }
    public BigDecimal getAccountBalance() { return accountBalance; }

    public void MaintenanceFee(){
        //CHECK ACCOUNT STATUS BEFORE CHARGE
        if(this.status == AccountStatus.DORMANT){
            throw new AccountDormantException("Cannot charge fee due to account is DORMANT");
        }
        //SUBTRACT 200
        this.accountBalance = this.accountBalance.subtract(BigDecimal.valueOf(200));

        //ACCOUNT BALANCE IS 0, THEN STATUS SET OVERDRAWN
        if(this.accountBalance.compareTo(BigDecimal.ZERO) < 0){
            this.status = AccountStatus.OVERDRAWN;
        }

    }


}
