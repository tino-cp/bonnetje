package nl.tinoc.covadistributie;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/order")
public class OrderResource {

    @POST
    @Path("/calculate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculateOrder(OrderDTO orderDTO) {
        double totalAmount = 0;
        double vatAmount = 0;
        double finalPrice = 0;

        double vatRate = 0.21;

        for (ProductDTO product : orderDTO.getProducts()) {
            totalAmount += product.getPrice() * product.getQuantity();
        }

        vatAmount = totalAmount * vatRate;
        finalPrice = totalAmount + vatAmount;

        OrderResponse response = new OrderResponse(totalAmount, vatAmount, finalPrice);
        return Response.ok(response).build();
    }
}
