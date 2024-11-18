package nl.tinoc.covadistributie;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Path("/order")
public class OrderResource {

    @POST
    @Path("/calculate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculateOrder(OrderDTO orderDTO) {
        double totalAmount = 0;
        BigDecimal vatHigh = BigDecimal.ZERO;
        BigDecimal vatLow = BigDecimal.ZERO;

        for (ProductDTO product : orderDTO.getProducts()) {
            double productTotal = product.getPrice() * product.getQuantity();
            totalAmount += productTotal;

            BigDecimal productVat = BigDecimal.valueOf(productTotal);
            if (product.getVat() == 21) {
                vatHigh = vatHigh
                        .add(productVat.multiply(BigDecimal.valueOf(0.21))
                        .setScale(2, RoundingMode.DOWN));
            } else if (product.getVat() == 9) {
                vatLow = vatLow
                        .add(productVat.multiply(BigDecimal.valueOf(0.09))
                        .setScale(2, RoundingMode.DOWN));
            }
        }

        BigDecimal finalPrice = BigDecimal.valueOf(totalAmount)
                .add(vatHigh)
                .add(vatLow)
                .setScale(2, RoundingMode.DOWN);

        OrderResponse response = new OrderResponse(totalAmount, vatHigh.doubleValue(), vatLow.doubleValue(), finalPrice.doubleValue());
        return Response.ok(response).build();
    }
}
