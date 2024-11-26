package nl.tinoc.bonnetje;

import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());
    private ProductDAO productDAO;

    @Inject
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public OrderResponse calculateOrder(OrderDTO orderDTO) throws Exception {
        try {
            BigDecimal totalAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            BigDecimal vatHigh = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            BigDecimal vatLow = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            BigDecimal vatTotalHigh = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            BigDecimal vatTotalLow = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            List<ProductResponse> productResponses = new ArrayList<>();

            for (ProductDTO product : orderDTO.getProducts()) {
                BigDecimal productPrice = BigDecimal.valueOf(product.getPrice()).setScale(2, RoundingMode.HALF_UP);
                BigDecimal originalTotal = productPrice.multiply(BigDecimal.valueOf(product.getQuantity())).setScale(2, RoundingMode.HALF_UP);
                BigDecimal discountedTotal = originalTotal.setScale(2, RoundingMode.HALF_UP);

                BigDecimal discountAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
                if (product.getDiscount() != null && product.getQuantity() >= product.getDiscount().getQuantity()) {
                    BigDecimal discount = BigDecimal.valueOf(product.getDiscount().getDiscount()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                    discountAmount = originalTotal.multiply(discount).setScale(2, RoundingMode.HALF_UP);
                    discountedTotal = originalTotal.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);
                }

                BigDecimal vatAmount;
                if (product.getVat() == 21) {
                    vatAmount = discountedTotal.multiply(BigDecimal.valueOf(0.21)).setScale(2, RoundingMode.HALF_UP);
                    vatHigh = vatHigh.add(vatAmount).setScale(2, RoundingMode.HALF_UP);
                    vatTotalHigh = vatTotalHigh.add(discountedTotal).setScale(2, RoundingMode.HALF_UP);
                } else if (product.getVat() == 9) {
                    vatAmount = discountedTotal.multiply(BigDecimal.valueOf(0.09)).setScale(2, RoundingMode.HALF_UP);
                    vatLow = vatLow.add(vatAmount).setScale(2, RoundingMode.HALF_UP);
                    vatTotalLow = vatTotalLow.add(discountedTotal).setScale(2, RoundingMode.HALF_UP);
                }

                totalAmount = totalAmount.add(discountedTotal).setScale(2, RoundingMode.HALF_UP);

                ProductResponse productResponse = new ProductResponse(
                        product.getId(),
                        product.getName(),
                        discountedTotal.doubleValue(),
                        product.getQuantity(),
                        product.getVat(),
                        discountAmount.doubleValue()
                );
                productResponses.add(productResponse);
            }

            BigDecimal finalPrice = totalAmount.add(vatHigh).add(vatLow).setScale(2, RoundingMode.HALF_UP);

            return new OrderResponse(
                    totalAmount.doubleValue(),
                    vatHigh.doubleValue(),
                    vatLow.doubleValue(),
                    vatTotalHigh.doubleValue(),
                    vatTotalLow.doubleValue(),
                    finalPrice.doubleValue(),
                    productResponses
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calculating order", e);
            throw new Exception("Error while calculating order", e);
        }
    }
}
