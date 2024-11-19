package nl.tinoc.bonnetje;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/order")
public class OrderResource {
    private static final Logger LOGGER = Logger.getLogger(OrderResource.class.getName());

    private OrderService orderService;

    @POST
    @Path("/calculate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculateOrder(OrderDTO orderDTO) {
        try {
            return Response.ok(orderService.calculateOrder(orderDTO)).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while calculating order", e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error:" + e.getMessage()).build();
        }
    }

    @Inject
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
