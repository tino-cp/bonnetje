package nl.tinoc.bonnetje.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import nl.tinoc.bonnetje.service.OrderService;
import nl.tinoc.bonnetje.data.dto.OrderDTO;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/order")
public class OrderResource {
    private static final Logger LOGGER = Logger.getLogger(OrderResource.class.getName());
    private OrderService orderService;

    @POST
    @Path("/calculate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculateOrder(OrderDTO orderDTO) {
        try {
            return Response.ok(orderService.calculateOrder(orderDTO)).build();
        } catch (Exception e) {
            return handleException(e, "Error calculating order");
        }
    }

    private Response handleException(Exception e, String message) {
        LOGGER.log(Level.SEVERE, message, e);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error: " + e.getMessage()).build();
    }

    @Inject
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
