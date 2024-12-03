package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.dto.*;
import nl.tinoc.bonnetje.exception.OrderProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Logger.getLogger(OrderServiceImpl.class.getName()).setLevel(Level.OFF);
    }

    @Test
    void calculateOrderReturnsCorrectOrderSummary() {
        ProductDTO product1 = new ProductDTO(1, "Product 1", 10.0, 2, new VatDTO(21, 0.0), null);
        ProductDTO product2 = new ProductDTO(2, "Product 2", 5.0, 3, new VatDTO(9, 0.0), null);
        OrderDTO orderDTO = new OrderDTO(List.of(product1, product2));

        OrderSummaryDTO result = orderService.calculateOrder(orderDTO);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        assertEquals(40.55, result.getFinalPrice());
    }

    @Test
    void calculateOrderAppliesDiscountCorrectly() {
        ProductDTO product = new ProductDTO(1, "Product 1", 20.0, 5, new VatDTO(21, 0.0), new DiscountDTO(10, 0.0, 5));
        OrderDTO orderDTO = new OrderDTO(List.of(product));

        OrderSummaryDTO result = orderService.calculateOrder(orderDTO);

        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals(108.90, result.getFinalPrice());
    }

    @Test
    void calculateOrderCalculatesVATCorrectly() {
        ProductDTO product1 = new ProductDTO(1, "Product 1", 10.0, 2, new VatDTO(21, 0.0), null);
        ProductDTO product2 = new ProductDTO(2, "Product 2", 5.0, 3, new VatDTO(9, 0.0), new DiscountDTO(10, 0.0, 5));
        OrderDTO orderDTO = new OrderDTO(List.of(product1, product2));

        OrderSummaryDTO result = orderService.calculateOrder(orderDTO);

        assertNotNull(result);

        assertEquals(4.20, product1.getVat().getAmount());
        assertEquals(1.35, product2.getVat().getAmount());
    }

    @Test
    void calculateOrderCalculatesOtherVAT() {
        ProductDTO product = new ProductDTO(1, "Product 1", 15.0, 2, new VatDTO(0, 0.0), null);
        OrderDTO order = new OrderDTO(List.of(product));
        OrderSummaryDTO result = orderService.calculateOrder(order);

        assertNotNull(result);
        assertEquals(2, result.getProducts().get(0).getQuantity());
        assertEquals(30.00, result.getFinalPrice());
    }
    @Test
    void calculateOrderAppliesDiscountWhenConditionsMet() {
        ProductDTO product = new ProductDTO(1, "Product 1", 20.0, 5, new VatDTO(9, 0.0), new DiscountDTO(10, 0.0, 5));
        OrderDTO orderDTO = new OrderDTO(List.of(product));

        OrderSummaryDTO result = orderService.calculateOrder(orderDTO);

        assertNotNull(result);

        assertEquals(10.00, product.getDiscount().getAmount());
        assertEquals(8.10, product.getVat().getAmount());
    }

    @Test
    void calculateOrderHandlesEmptyOrder() {
        OrderDTO orderDTO = new OrderDTO(List.of());

        OrderSummaryDTO result = orderService.calculateOrder(orderDTO);

        assertNotNull(result);
        assertEquals(0, result.getProducts().size());
        assertEquals(0.0, result.getFinalPrice());
    }

    @Test
    void calculateOrderThrowsOrderProcessingExceptionOnError() {
        OrderDTO orderDTO = null;

        assertThrows(OrderProcessingException.class, () -> orderService.calculateOrder(orderDTO));
    }

    @AfterEach
    void tearDown() {
        Logger.getLogger(OrderServiceImpl.class.getName()).setLevel(Level.INFO);
    }
}