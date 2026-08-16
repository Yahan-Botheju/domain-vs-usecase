package product_stock;

import bank_account.ResourceNotFoundException;
import bank_account.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DeductStockUseCaseImpl implements DeductStockUseCase {

    //inject required dependencies
    private final ProductRepository productRepository;

    public DeductStockUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public DeductStockResult buyProduct(UUID productId, int requestQuantity){

        Product checkProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));

        LocalDateTime currentTime = LocalDateTime.now();
        product.deductStock(requestQuantity, currentTime);

        Product savedProduct = productRepository.save(product);

        return new DeductStockResult(
                savedProduct.getProductId(),
                savedProduct.getProductName(),
                requestQuantity
        );
    }
}
