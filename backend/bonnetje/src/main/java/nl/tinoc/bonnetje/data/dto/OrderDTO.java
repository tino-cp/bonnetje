package nl.tinoc.bonnetje.data.dto;

import java.util.List;

public class OrderDTO {
    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}