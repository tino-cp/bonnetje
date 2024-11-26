package nl.tinoc.bonnetje;

import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());
    private ProductDAO productDAO;

    @Inject
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductDTO> getProducts() {
        try {
            return productDAO.getProducts();
        } catch (Exception e) {
            LOGGER.severe("Error getting products");
            throw new RuntimeException(e);
        }
    }
}
