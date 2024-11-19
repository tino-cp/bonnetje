package nl.tinoc.bonnetje;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.*;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

    @Inject
    public OrderServiceImpl() {
    }

    public Response calculateOrder(OrderDTO orderDTO) throws Exception {
        try {
            double totalAmount = 0;
            BigDecimal vatHigh = BigDecimal.ZERO;
            BigDecimal vatLow = BigDecimal.ZERO;
            double vatTotalLow = 0;
            double vatTotalHigh = 0;

            for (ProductDTO product : orderDTO.getProducts()) {
                double productTotal = product.getPrice() * product.getQuantity();
                totalAmount += productTotal;

                BigDecimal productVat = BigDecimal.valueOf(productTotal);
                if (product.getVat() == 21) {
                    vatHigh = vatHigh
                            .add(productVat.multiply(BigDecimal.valueOf(0.21))
                                    .setScale(2, RoundingMode.DOWN));

                    vatTotalHigh += productTotal;
                } else if (product.getVat() == 9) {
                    vatLow = vatLow
                            .add(productVat.multiply(BigDecimal.valueOf(0.09))
                                    .setScale(2, RoundingMode.DOWN));

                    vatTotalLow += productTotal;
                }
            }

            BigDecimal finalPrice = BigDecimal.valueOf(totalAmount)
                    .add(vatHigh)
                    .add(vatLow)
                    .setScale(2, RoundingMode.DOWN);

            return Response.ok(new OrderResponse(totalAmount, vatHigh.doubleValue(), vatLow.doubleValue(), vatTotalHigh, vatTotalLow, finalPrice.doubleValue())).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calculating order", e);
            throw new Exception();
        }
    }
}
