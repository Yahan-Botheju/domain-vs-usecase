package product_stock;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private int stockQuantity;
    private boolean isActive;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(UUID productId, String productName, int stockQuantity, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getStockQuantity() { return stockQuantity; }
    public boolean getIsActive() { return isActive; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void deductStock(int requestQuantity, LocalDateTime currentTime){
        //check product is active to buy
        if(!this.isActive){
            throw new IlligalStateException("Product is inactive");
        }
        //check quantity available to buy
        if(this.stockQuantity < requestQuantity){
            throw new IllegalArgumentException("Insufficient stock quantity");
        }
        //check request quantity has actual quantity
        if(requestQuantity <= 0){
            throw new IllegalArgumentException("Request quantity must be greater than 0");
        }
        //update stock
        this.stockQuantity -= requestQuantity;
        this.updatedAt = currentTime;

    }

}
