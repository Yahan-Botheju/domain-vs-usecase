package bank_account_found_transfer_between_accounts;

import bank_account.*;

import java.math.BigDecimal;

@Service
public class DeductMaintenanceFeeUseCaseImpl implements DeductMaintenanceFeeUseCase {

    //inject required dependencies
    private final AccountRepository accountRepository;
    private final AuditLogService auditLogService;

    public DeductMaintenanceFeeUseCaseImpl(AccountRepository accountRepository, AuditLogService auditLogService) {
        this.accountRepository = accountRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    public TransferMoneyResult transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount){

        if(fromAccountId.equals(toAccountId)){
            throw new IllegalArgumentException("From account id cannot be the same as to account id");
        }

        BankAccount checkFromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));

        BankAccount checkToAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));

        BankAccount fromAccount = BankAccount.transferMoney(amount);
        BankAccount toAccount = BankAccount.depositMoney(amount);

        BankAccount savedFromAccount = accountRepository.save(fromAccount);
        BankAccount savedToAccount = accountRepository.save(toAccount);

        auditLogService.log(savedFromAccount, savedToAccount);

        return new TransferMoneyResult(
                "Money Transfer successful",
                savedFromAccount.getAccountNumber(),
                savedFromAccount.getAccountBalance(),
                savedToAccount.getAccountNumber(),
                savedToAccount.getAccountBalance()
        );
    }

}
