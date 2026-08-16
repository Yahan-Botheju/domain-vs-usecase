package credit_card_limit_update;


import bank_account.ResourceNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UpgradeCreditLimitUseCaseImpl implements UpgradeCreditLimitUseCase{

    //inject required dependencies
    private final CreditCardRepositoy creditCardRepositoy;

    public CreditCardRepositoy getCreditCardRepositoy(CreditCardRepositoy creditCardRepositoy) {
        this.creditCardRepositoy = creditCardRepositoy;
    }

    @Override
    public CreditLimitUpgradeResult requestCreditLimit(UUID cardId, BigDecimal requestedLimit) {

        CreditCard checkCard = creditCardRepositoy.findById(cardId)
                .orElseThrow(() -> ResourceNotFoundException("Invalid Credit Card"));

        LocalDateTime currentTime = LocalDateTime.now();
        checkCard.upgradeCreditCardLimit(requestedLimit, currentTime);

        CreditCard savedCard = creditCardRepositoy.save(checkCard);

        return new CreditLimitUpgradeResult(
                savedCard.getCardId(),
                savedCard.getCurrentLimit(),
                savedCard.getUpdatedAt()
        );
    }
}
