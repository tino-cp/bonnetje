package nl.tinoc.bonnetje.data.mapper;

import nl.tinoc.bonnetje.data.domain.Discount;
import nl.tinoc.bonnetje.data.domain.Product;
import nl.tinoc.bonnetje.data.domain.Vat;
import nl.tinoc.bonnetje.data.dto.DiscountDTO;
import nl.tinoc.bonnetje.data.dto.ProductDTO;
import nl.tinoc.bonnetje.data.dto.VatDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public Discount toDomain(DiscountDTO discountDTO) {
        return new Discount(discountDTO.getPercent(), discountDTO.getAmount(), discountDTO.getMinQuantity());
    }

    public DiscountDTO toDTO(Discount discount) {
        return new DiscountDTO(discount.getPercent(), discount.getAmount(), discount.getMinQuantity());
    }

    public VatDTO toDTO(Vat vat) {
        return new VatDTO(vat.getPercent(), vat.getAmount());
    }

    public Vat toDomain(VatDTO vatDTO) {
        return new Vat(vatDTO.getPercent(), vatDTO.getAmount());
    }

    public Product toDomain(ProductDTO productDTO) {
        Discount discount = productDTO.getDiscount() != null ? toDomain(productDTO.getDiscount()) : null;
        Vat vat = productDTO.getVat() != null ? toDomain(productDTO.getVat()) : null;
        return new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                vat,
                discount
        );
    }

    public ProductDTO toDTO(Product product) {
        DiscountDTO discountDTO = product.getDiscount() != null ? toDTO(product.getDiscount()) : null;
        VatDTO vatDTO = product.getVat() != null ? toDTO(product.getVat()) : null;
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                vatDTO,
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
