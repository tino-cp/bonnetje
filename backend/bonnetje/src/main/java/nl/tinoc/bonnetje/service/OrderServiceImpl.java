package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.dto.OrderDTO;
import nl.tinoc.bonnetje.data.dto.OrderSummaryDTO;
import nl.tinoc.bonnetje.data.dto.ProductDTO;
import nl.tinoc.bonnetje.data.dto.ProductSummaryDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

    public OrderSummaryDTO calculateOrder(OrderDTO orderDTO) throws Exception {
        try {
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal vatHigh = BigDecimal.ZERO;
            BigDecimal vatLow = BigDecimal.ZERO;
            BigDecimal vatTotalHigh = BigDecimal.ZERO;
            BigDecimal vatTotalLow = BigDecimal.ZERO;
            List<ProductSummaryDTO> products = new ArrayList<>();

            for (ProductDTO product : orderDTO.getProducts()) {
                ProductSummaryDTO productSummary = calculateProductSummary(product);
                totalAmount = totalAmount.add(productSummary.getSubTotal());

                if (product.getVat() == 21) {
                    vatHigh = vatHigh.add(productSummary.getVatAmount());
                    vatTotalHigh = vatTotalHigh.add(productSummary.getSubTotal());
                } else if (product.getVat() == 9) {
                    vatLow = vatLow.add(productSummary.getVatAmount());
                    vatTotalLow = vatTotalLow.add(productSummary.getSubTotal());
                }

                products.add(productSummary);
            }

            totalAmount = totalAmount.setScale(2, RoundingMode.HALF_UP);
            vatHigh = vatHigh.setScale(2, RoundingMode.HALF_UP);
            vatLow = vatLow.setScale(2, RoundingMode.HALF_UP);
            vatTotalHigh = vatTotalHigh.setScale(2, RoundingMode.HALF_UP);
            vatTotalLow = vatTotalLow.setScale(2, RoundingMode.HALF_UP);

            BigDecimal finalPrice = totalAmount.add(vatHigh).add(vatLow).setScale(2, RoundingMode.HALF_UP);

            return new OrderSummaryDTO(
                    totalAmount,
                    vatLow,
                    vatHigh,
                    vatTotalHigh,
                    vatTotalLow,
                    finalPrice,
                    products
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calculating order", e);
            throw new Exception("Error while calculating order", e);
        }
    }

    private ProductSummaryDTO calculateProductSummary(ProductDTO product) {
        BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
        BigDecimal originalTotal = productPrice.multiply(BigDecimal.valueOf(product.getQuantity()));

        BigDecimal discountAmount = calculateDiscount(product, originalTotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal subTotal = originalTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

        BigDecimal vatRate = BigDecimal.valueOf(product.getVat()).divide(BigDecimal.valueOf(100));
        BigDecimal vatAmount = subTotal.multiply(vatRate).setScale(2, RoundingMode.HALF_UP);

        return new ProductSummaryDTO(
                product.getId(),
                product.getName(),
                subTotal,
                product.getQuantity(),
                product.getVat(),
                discountAmount,
                vatAmount
        );
    }

    private BigDecimal calculateDiscount(ProductDTO product, BigDecimal originalTotal) {
        if (product.getDiscount() != null && product.getQuantity() >= product.getDiscount().getQuantity()) {
            BigDecimal discount = BigDecimal.valueOf(product.getDiscount().getDiscount()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
            return originalTotal.multiply(discount);
        }
        return BigDecimal.ZERO;
    }
}
