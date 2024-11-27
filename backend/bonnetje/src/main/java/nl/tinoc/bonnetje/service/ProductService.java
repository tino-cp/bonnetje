package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();
}
