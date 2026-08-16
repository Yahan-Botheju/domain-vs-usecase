package e_commerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final UUID userId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private final LocalDateTime  createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, UUID userId, OrderStatus status, BigDecimal totalAmount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getOrderId() { return orderId; }
    public UUID getUserId() { return userId; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void cancel(LocalDateTime currentTime) {
        //check order status SHIPPED or DELIVERED
        if(this.status == OrderStatus.SHIPPED || this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Order cannot be cancelled once shipped or delivered");
        }

        this.status = OrderStatus.CANCELLED;
        this.updatedAt = currentTime;

    }
}
