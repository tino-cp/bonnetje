package nl.tinoc.bonnetje.resource;

import nl.tinoc.bonnetje.data.dto.OrderSummaryDTO;
import nl.tinoc.bonnetje.service.OrderService;
import nl.tinoc.bonnetje.data.dto.OrderDTO;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderResourceTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderResource orderResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderResource.setOrderService(orderService);

        Logger.getLogger(ProductsResource.class.getName()).setLevel(Level.OFF);
    }

    @Test
    void calculateOrderReturnsOkResponseWithCalculatedOrder() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        OrderSummaryDTO orderSummaryDTO = new OrderSummaryDTO();
        when(orderService.calculateOrder(orderDTO)).thenReturn(orderSummaryDTO);

        // Act
        Response response = orderResource.calculateOrder(orderDTO);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(orderSummaryDTO, response.getEntity());
    }

    @Test
    void calculateOrderHandlesExceptionAndReturnsBadRequest() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.calculateOrder(orderDTO)).thenThrow(new RuntimeException("Calculation error"));

        // Act
        Response response = orderResource.calculateOrder(orderDTO);

        // Assert
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Error: Calculation error", response.getEntity());
    }

    @AfterEach
    public void tearDown() {
        Logger.getLogger(ProductsResource.class.getName()).setLevel(Level.INFO);
    }
}
