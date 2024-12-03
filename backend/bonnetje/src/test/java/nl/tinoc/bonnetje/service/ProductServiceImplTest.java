package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.dao.ProductDAOImpl;
import nl.tinoc.bonnetje.data.domain.Discount;
import nl.tinoc.bonnetje.data.domain.Product;
import nl.tinoc.bonnetje.data.domain.Vat;
import nl.tinoc.bonnetje.data.dto.DiscountDTO;
import nl.tinoc.bonnetje.data.dto.ProductDTO;
import nl.tinoc.bonnetje.data.dto.VatDTO;
import nl.tinoc.bonnetje.data.mapper.ProductMapper;
import nl.tinoc.bonnetje.exception.ProductNotFoundException;
import nl.tinoc.bonnetje.resource.ProductsResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductDAOImpl productDAO;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Logger.getLogger(ProductServiceImpl.class.getName()).setLevel(Level.OFF);
    }

    @Test
    void getProductsReturnsListOfProductDTOs() {
        List<Product> products = List.of(new Product(
                1,
                "AH Biologische Havermout",
                1.95,
                5,
                new Vat(9, 0.79),
                new Discount(10, 0.98, 5)
        ));

        List<ProductDTO> productDTOs = List.of(new ProductDTO(
                1,
                "AH Biologische Havermout",
                1.95,
                5,
                new VatDTO(9, 0.79),
                new DiscountDTO(10, 0.98, 5)
        ));

        when(productDAO.getProducts()).thenReturn(products);
        when(productMapper.toDTOs(products)).thenReturn(productDTOs);

        List<ProductDTO> result = productService.getProducts();

        assertEquals(productDTOs, result);
        assertEquals(1, result.size());
        assertEquals(productDTOs.get(0).getProductId(), result.get(0).getProductId());
        assertEquals(productDTOs.get(0).getName(), result.get(0).getName());
        assertEquals(productDTOs.get(0).getPrice(), result.get(0).getPrice());
        assertEquals(productDTOs.get(0).getPrice(), result.get(0).getPrice());

        verify(productDAO).getProducts();
        verify(productMapper).toDTOs(products);
    }

    @Test
    void getProductsThrowsProductNotFoundExceptionWhenDAOFails() {
        when(productDAO.getProducts()).thenThrow(new RuntimeException("Database error"));

        assertThrows(ProductNotFoundException.class, () -> productService.getProducts());
        verify(productDAO).getProducts();
    }

    @Test
    void getProductsReturnsEmptyListWhenNoProductsFound() {
        when(productDAO.getProducts()).thenReturn(Collections.emptyList());
        when(productMapper.toDTOs(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<ProductDTO> result = productService.getProducts();

        assertEquals(Collections.emptyList(), result);
        verify(productDAO).getProducts();
        verify(productMapper).toDTOs(Collections.emptyList());
    }

    @AfterEach
    void tearDown() {
        Logger.getLogger(ProductServiceImpl.class.getName()).setLevel(Level.INFO);
    }
}