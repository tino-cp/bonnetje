package nl.tinoc.bonnetje;

public interface OrderService {
    OrderResponse calculateOrder(OrderDTO orderDTO) throws Exception;
}
