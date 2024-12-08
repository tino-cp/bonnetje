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
            BigDecimal vatAmountHigh = BigDecimal.ZERO;
            BigDecimal vatAmountLow = BigDecimal.ZERO;
            BigDecimal priceSubTotalHigh = BigDecimal.ZERO;
            BigDecimal priceSubTotalLow = BigDecimal.ZERO;

            for (ProductDTO product : products) {
                BigDecimal price = BigDecimal.valueOf(product.getPrice());
                BigDecimal originalSubTotal = price.multiply(BigDecimal.valueOf(product.getQuantity()));

                BigDecimal discountAmount = calculateDiscount(product, originalSubTotal);
                BigDecimal subTotal = originalSubTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
                product.setSubTotal(subTotal.doubleValue());

                totalAmount = totalAmount.add(subTotal);

                BigDecimal vatRate = BigDecimal.valueOf(product.getVat().getPercent()).divide(BigDecimal.valueOf(100));
                BigDecimal vatAmount = subTotal.multiply(vatRate).setScale(2, RoundingMode.HALF_UP);

                VatDTO vat = product.getVat();
                vat.setAmount(vatAmount.doubleValue());
                product.setVat(vat);

                if (product.getVat().getPercent() == VAT_HIGH) {
                    vatAmountHigh = vatAmountHigh.add(vatAmount);
                    priceSubTotalHigh = priceSubTotalHigh.add(subTotal);
                } else if (product.getVat().getPercent() == VAT_LOW) {
                    vatAmountLow = vatAmountLow.add(vatAmount);
                    priceSubTotalLow = priceSubTotalLow.add(subTotal);
                }
            }

            BigDecimal finalPrice = totalAmount.add(vatAmountHigh).add(vatAmountLow).setScale(2, RoundingMode.HALF_UP);

            VatCalculationDetailsDTO lowVatDetails = new VatCalculationDetailsDTO(
                    vatAmountLow.doubleValue(),
                    priceSubTotalLow.doubleValue()
            );

            VatCalculationDetailsDTO highVatDetails = new VatCalculationDetailsDTO(
                    vatAmountHigh.doubleValue(),
                    priceSubTotalHigh.doubleValue()
            );

            BigDecimal totalVat = vatAmountLow.add(vatAmountHigh);
            BigDecimal priceTotal = priceSubTotalHigh.add(priceSubTotalLow);

            VatCalculationDTO vatSummary = new VatCalculationDTO(
                    lowVatDetails,
                    highVatDetails,
                    totalVat.doubleValue(),
                    priceTotal.doubleValue()
            );

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
            BigDecimal discountAmount = originalTotal.multiply(discount).setScale(2, RoundingMode.HALF_UP);

            DiscountDTO discountDTO = product.getDiscount();
            discountDTO.setAmount(discountAmount.doubleValue());
            product.setDiscount(discountDTO);

            return discountAmount;
        }
        return BigDecimal.ZERO;
    }
}
