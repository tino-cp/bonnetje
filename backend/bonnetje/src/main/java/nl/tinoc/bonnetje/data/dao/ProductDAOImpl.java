package nl.tinoc.bonnetje.data.dao;

import nl.tinoc.bonnetje.data.domain.Discount;
import nl.tinoc.bonnetje.data.domain.Product;
import nl.tinoc.bonnetje.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nl.tinoc.bonnetje.util.database.DatabaseService.getConnection;

public class ProductDAOImpl implements ProductDAO {
    private static final String SELECT_PRODUCTS = "SELECT p.product_id, p.product_naam, p.prijs, pb.btw_perc, sk.min_aantal, sk.korting_perc FROM Producten p JOIN PRODUCT_BTW pb ON p.product_id = pb.product_id LEFT JOIN StaffelKorting sk ON p.product_id = sk.product_id";
    private static final Logger LOGGER = Logger.getLogger(ProductDAOImpl.class.getName());

    private final Connection connection;

    public ProductDAOImpl() throws SQLException {
        this.connection = getConnection();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                products.add(createProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new DatabaseException("Error retrieving products from database", e);
        }
        return products;
    }

    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("product_id"));
        product.setName(resultSet.getString("product_naam"));
        product.setPrice(resultSet.getDouble("prijs"));
        product.setVat(resultSet.getInt("btw_perc"));

        Discount discount = createProductDiscountFromResultSet(resultSet);
        product.setDiscount(discount);

        return product;
    }

    private Discount createProductDiscountFromResultSet(ResultSet resultSet) throws SQLException {
        int minQuantity = resultSet.getInt("min_aantal");

        if (minQuantity > 0) {
            Discount discount = new Discount();
            discount.setProductId(resultSet.getInt("product_id"));
            discount.setQuantity(minQuantity);
            discount.setDiscountPercentage(resultSet.getDouble("korting_perc"));
            return discount;
        }
        return null;
    }
}
