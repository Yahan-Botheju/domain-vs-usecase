package e_commerce;

import java.time.LocalDateTime;

@Service
public class CancelUseCaseImpl implements CancelUseCase {

    //inject required dependencies
    private final OrderRespository orderRespository;

    @Override
    public CancelOrderResult cancelOrder(UUID orderId){

        //check order
        Order orderExistence = orderRespository.findById(orderId)
                .orElseThrow(() -> ResourceNotFoundException("Order not found"));

        LocalDateTime currentTime = LocalDateTime.now();
        Order.cancel(currentTime);
        Order savedOrder = orderRespository.save(Order);

        return new CancelOrderResult(
                "Order Cancel Success",
                savedOrder.getOrderId(),
                savedOrder.getUpdatedAt(),
                savedOrder.getUpdatedAt()
        );

    }
}
