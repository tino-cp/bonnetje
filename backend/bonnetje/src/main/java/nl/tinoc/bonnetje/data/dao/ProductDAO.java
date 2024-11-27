package nl.tinoc.bonnetje.data.dao;

import nl.tinoc.bonnetje.data.dto.ProductDTO;
import nl.tinoc.bonnetje.data.dto.ProductDiscountDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.tinoc.bonnetje.util.database.DatabaseService.getConnection;

public class ProductDAO {

    private static final String SELECT_PRODUCTS = "SELECT p.product_id, p.product_naam, p.prijs, pb.btw_perc, sk.min_aantal, sk.korting_perc FROM Producten p JOIN PRODUCT_BTW pb ON p.product_id = pb.product_id LEFT JOIN StaffelKorting sk ON p.product_id = sk.product_id";
    private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());

    private final Connection connection;

    public ProductDAO() throws SQLException {
        connection = getConnection();
    }

    public List<ProductDTO> getProducts() {
        List<ProductDTO> products = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("product_naam"));
                product.setPrice(resultSet.getDouble("prijs"));
                product.setVat(resultSet.getInt("btw_perc"));

                ProductDiscountDTO productDiscountDTO = null;
                if (resultSet.getInt("min_aantal") > 0) {
                    productDiscountDTO = new ProductDiscountDTO();
                    productDiscountDTO.setProductId(resultSet.getInt("product_id"));
                    productDiscountDTO.setQuantity(resultSet.getInt("min_aantal"));
                    productDiscountDTO.setDiscount(resultSet.getDouble("korting_perc"));
                }
                product.setDiscount(productDiscountDTO);

                products.add(product);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database", e);
            throw new RuntimeException("Database error occurred");
        }
        return products;
    }
}
