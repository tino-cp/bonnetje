package nl.tinoc.covadistributie;

import java.util.List;

public class OrderDTO {
    private List<ProductDTO> products;

    public OrderDTO() {
    }

    public OrderDTO(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
