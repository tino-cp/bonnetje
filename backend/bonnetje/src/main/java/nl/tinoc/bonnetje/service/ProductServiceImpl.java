package nl.tinoc.bonnetje.service;

import jakarta.inject.Inject;
import nl.tinoc.bonnetje.data.dao.ProductDAOImpl;
import nl.tinoc.bonnetje.data.domain.Product;
import nl.tinoc.bonnetje.data.dto.ProductDTO;
import nl.tinoc.bonnetje.data.mapper.ProductMapper;
import nl.tinoc.bonnetje.exception.ProductNotFoundException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

    private ProductDAOImpl productDAO;
    private ProductMapper productMapper;

    @Inject
    public ProductServiceImpl(ProductDAOImpl productDAO, ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getProducts() {
        try {
            List<Product> products = productDAO.getProducts();
            return productMapper.toDTOs(products);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new ProductNotFoundException("Unable to fetch products", e);
        }
    }
}
