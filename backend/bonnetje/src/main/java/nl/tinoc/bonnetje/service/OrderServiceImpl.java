package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.dto.OrderDTO;
import nl.tinoc.bonnetje.data.dto.OrderSummaryDTO;
import nl.tinoc.bonnetje.data.dto.ProductDTO;
import nl.tinoc.bonnetje.data.dto.ProductSummaryDTO;
import nl.tinoc.bonnetje.exception.OrderProcessingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

    private static final int VAT_HIGH = 21;
    private static final int VAT_LOW = 9;

    public OrderSummaryDTO calculateOrder(OrderDTO orderDTO) {
        try {
            List<ProductSummaryDTO> products = new ArrayList<>();
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal vatHigh = BigDecimal.ZERO;
            BigDecimal vatLow = BigDecimal.ZERO;
            BigDecimal vatTotalHigh = BigDecimal.ZERO;
            BigDecimal vatTotalLow = BigDecimal.ZERO;

            for (ProductDTO productDTO : orderDTO.getProducts()) {
                ProductSummaryDTO summary = calculateProductSummary(productDTO);

                totalAmount = totalAmount.add(BigDecimal.valueOf(summary.getSubTotal()));
                if (productDTO.getVat() == VAT_HIGH) {
                    vatHigh = vatHigh.add(BigDecimal.valueOf(summary.getVatAmount()));
                    vatTotalHigh = vatTotalHigh.add(BigDecimal.valueOf(summary.getSubTotal()));
                } else if (productDTO.getVat() == VAT_LOW) {
                    vatLow = vatLow.add(BigDecimal.valueOf(summary.getVatAmount()));
                    vatTotalLow = vatTotalLow.add(BigDecimal.valueOf(summary.getSubTotal()));
                }

                products.add(summary);
            }

            BigDecimal finalPrice = totalAmount.add(vatHigh).add(vatLow).setScale(2, RoundingMode.HALF_UP);

            return new OrderSummaryDTO(
                    totalAmount.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                    vatLow.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                    vatHigh.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                    vatTotalHigh.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                    vatTotalLow.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                    finalPrice.doubleValue(),
                    products
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrderProcessingException("Unable to process order", e);
        }
    }

    private ProductSummaryDTO calculateProductSummary(ProductDTO product) {
        BigDecimal price = BigDecimal.valueOf(product.getPrice());
        BigDecimal originalTotal = price.multiply(BigDecimal.valueOf(product.getQuantity()));

        BigDecimal discountAmount = calculateDiscount(product, originalTotal);
        BigDecimal subTotal = originalTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

        BigDecimal vatRate = BigDecimal.valueOf(product.getVat()).divide(BigDecimal.valueOf(100));

        BigDecimal vatAmount = subTotal.multiply(vatRate).setScale(2, RoundingMode.HALF_UP);

        return new ProductSummaryDTO(
                product.getId(),
                product.getName(),
                subTotal.doubleValue(),
                product.getQuantity(),
                product.getVat(),
                discountAmount.doubleValue(),
                vatAmount.doubleValue()
        );
    }

    private BigDecimal calculateDiscount(ProductDTO product, BigDecimal originalTotal) {
        if (product.getDiscount() != null && product.getQuantity() >= product.getDiscount().getQuantity()) {
            BigDecimal discount = BigDecimal.valueOf(product.getDiscount().getDiscount()).divide(BigDecimal.valueOf(100));
            return originalTotal.multiply(discount).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
