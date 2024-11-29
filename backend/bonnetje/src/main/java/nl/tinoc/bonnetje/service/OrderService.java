package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.dto.OrderDTO;
import nl.tinoc.bonnetje.data.dto.OrderSummaryDTO;

public interface OrderService {
    OrderSummaryDTO calculateOrder(OrderDTO orderDTO);
}
