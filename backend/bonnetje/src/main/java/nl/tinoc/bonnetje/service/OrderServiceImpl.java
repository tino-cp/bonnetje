package nl.tinoc.bonnetje.service;

import nl.tinoc.bonnetje.data.domain.Discount;
import nl.tinoc.bonnetje.data.dto.*;
import nl.tinoc.bonnetje.exception.OrderProcessingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

    private static final int VAT_HIGH = 21;
    private static final int VAT_LOW = 9;

    public OrderSummaryDTO calculateOrder(OrderDTO orderDTO) {
        try {
            List<ProductDTO> products = orderDTO.getProducts();
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal vatHigh = BigDecimal.ZERO;
            BigDecimal vatLow = BigDecimal.ZERO;
            BigDecimal priceSubTotalHigh = BigDecimal.ZERO;
            BigDecimal priceSubTotalLow = BigDecimal.ZERO;

            for (ProductDTO product : products) {
                BigDecimal price = BigDecimal.valueOf(product.getPrice());
                BigDecimal originalTotal = price.multiply(BigDecimal.valueOf(product.getQuantity()));

                BigDecimal discountAmount = calculateDiscount(product, originalTotal);
                BigDecimal subTotal = originalTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

                BigDecimal vatRate = BigDecimal.valueOf(product.getVat().getPercent()).divide(BigDecimal.valueOf(100));
                BigDecimal vatAmount = subTotal.multiply(vatRate).setScale(2, RoundingMode.HALF_UP);

                product.setSubTotal(subTotal.doubleValue());

                DiscountDTO discount = product.getDiscount();
                if (discount != null) {
                    discount.setAmount(discountAmount.doubleValue());
                    product.setDiscount(discount);
                }

                VatDTO vat = product.getVat();
                vat.setAmount(vatAmount.doubleValue());
                product.setVat(vat);

                totalAmount = totalAmount.add(subTotal);
                if (product.getVat().getPercent() == VAT_HIGH) {
                    vatHigh = vatHigh.add(vatAmount);
                    priceSubTotalHigh = priceSubTotalHigh.add(subTotal);
                } else if (product.getVat().getPercent() == VAT_LOW) {
                    vatLow = vatLow.add(vatAmount);
                    priceSubTotalLow = priceSubTotalLow.add(subTotal);
                }
            }

            BigDecimal finalPrice = totalAmount.add(vatHigh).add(vatLow).setScale(2, RoundingMode.HALF_UP);

            VatCalculationDetailsDTO lowVatDetails = new VatCalculationDetailsDTO(vatLow.doubleValue(), priceSubTotalLow.doubleValue());
            VatCalculationDetailsDTO highVatDetails = new VatCalculationDetailsDTO(vatHigh.doubleValue(), priceSubTotalHigh.doubleValue());
            BigDecimal totalVat = vatLow.add(vatHigh);
            BigDecimal priceTotal = priceSubTotalHigh.add(priceSubTotalLow);

            VatCalculationDTO vatSummary = new VatCalculationDTO(lowVatDetails, highVatDetails, totalVat.doubleValue(), priceTotal.doubleValue());

            return new OrderSummaryDTO(
                    finalPrice.doubleValue(),
                    totalAmount.setScale(2, RoundingMode.HALF_UP).doubleValue(),
                    vatSummary,
                    products
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new OrderProcessingException("Unable to process order", e);
        }
    }

    private BigDecimal calculateDiscount(ProductDTO product, BigDecimal originalTotal) {
        if (product.getDiscount() != null && product.getQuantity() >= product.getDiscount().getMinQuantity()) {
            BigDecimal discount = BigDecimal.valueOf(product.getDiscount().getPercent()).divide(BigDecimal.valueOf(100));
            return originalTotal.multiply(discount).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
