package nl.tinoc.bonnetje.data.mapper;

import nl.tinoc.bonnetje.data.domain.Discount;
import nl.tinoc.bonnetje.data.domain.Product;
import nl.tinoc.bonnetje.data.dto.DiscountDTO;
import nl.tinoc.bonnetje.data.dto.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public Discount toDomain(DiscountDTO discountDTO) {
        return new Discount(discountDTO.getProductId(), discountDTO.getDiscount(), discountDTO.getQuantity());
    }

    public DiscountDTO toDTO(Discount discount) {
        return new DiscountDTO(discount.getProductId(), discount.getDiscountPercentage(), discount.getQuantity());
    }

    public Product toDomain(ProductDTO productDTO) {
        Discount discount = productDTO.getDiscount() != null ? toDomain(productDTO.getDiscount()) : null;
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getVat(),
                discount
        );
    }

    public ProductDTO toDTO(Product product) {
        DiscountDTO discountDTO = product.getDiscount() != null ? toDTO(product.getDiscount()) : null;
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getVat(),
                discountDTO
        );
    }

    public List<ProductDTO> toDTOs(List<Product> products) {
        return products.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<Product> toDomains(List<ProductDTO> productDTOS) {
        return productDTOS.stream()
                .map(this::toDomain)
                .toList();
    }
}
