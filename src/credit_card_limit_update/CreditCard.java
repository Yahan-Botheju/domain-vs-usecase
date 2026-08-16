package credit_card_limit_update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreditCard {
    private final UUID cardId;
    private final UUID userId;
    private BigDecimal currentLimit;
    private BigDecimal accountBalance;
    private boolean isBlocked;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CreditCard(UUID cardId, UUID userId, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isBlocked, BigDecimal accountBalance, BigDecimal currentLimit) {
        this.cardId = cardId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isBlocked = isBlocked;
        this.accountBalance = accountBalance;
        this.currentLimit = currentLimit;
    }

    public UUID getCardId() { return cardId; }
    public UUID getUserId() { return userId; }
    public BigDecimal getCurrentLimit() { return currentLimit; }
    public BigDecimal getAccountBalance() { return accountBalance; }
    public boolean isBlocked() { return isBlocked; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

}
