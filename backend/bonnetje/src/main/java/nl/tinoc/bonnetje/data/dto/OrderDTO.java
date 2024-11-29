package nl.tinoc.bonnetje.data.dto;

import java.util.List;

public class OrderDTO {
    private List<ProductDTO> productDTOS;

    public List<ProductDTO> getProducts() {
        return productDTOS;
    }

    public void setProducts(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
