package bank_account;

@Service
public class DeductMaintenanceFeeUseCaseImpl implements DeductMaintenanceFeeUseCase {

    //inject required dependencies
    private final AccountRepository accountRepository;

    public DeductMaintenanceFeeUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public MaintenanceFeeResults monthlyMaintenance(Long accountNumber) {
        //CHECK ACCOUNT AVAILABILITY
        BankAccount checkAccount = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Ïnvalid Account Number"));

        BankAccount updatedFee = BankAccount.MaintenanceFee();

        accountRepository.save(updatedFee);

        return MaintenanceFeeResults(
                "Fee diduction success",
                updatedFee.getAccountNumber(),
                updatedFee.getAccountBalance(),
                updatedFee.getStatus()
        );
    }
}
