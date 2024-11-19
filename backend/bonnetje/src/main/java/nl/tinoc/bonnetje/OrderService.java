package nl.tinoc.bonnetje;

import jakarta.ws.rs.core.Response;

public interface OrderService {
    Response calculateOrder(OrderDTO orderDTO) throws Exception;
}
