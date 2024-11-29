package nl.tinoc.bonnetje.data.dao;

import nl.tinoc.bonnetje.data.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts();
}
