package nl.tinoc.bonnetje.resource;

import nl.tinoc.bonnetje.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductsResourceTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductsResource productsResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productsResource.setProductService(productService);

        Logger.getLogger(ProductsResource.class.getName()).setLevel(Level.OFF);
    }

    @Test
    void getProductsReturnsOkResponseWithProducts() {
        // Arrange
        when(productService.getProducts()).thenReturn(Collections.emptyList());

        // Act
        Response response = productsResource.getProducts();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(Collections.emptyList(), response.getEntity());
    }

    @Test
    void getProductsHandlesExceptionAndReturnsInternalServerError() {
        // Arrange
        when(productService.getProducts()).thenThrow(new RuntimeException("Database error"));

        // Act
        Response response = productsResource.getProducts();

        // Assert
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("Error: Database error", response.getEntity());
    }

    @AfterEach
    void tearDown() {
        Logger.getLogger(ProductsResource.class.getName()).setLevel(Level.INFO);
    }
}
