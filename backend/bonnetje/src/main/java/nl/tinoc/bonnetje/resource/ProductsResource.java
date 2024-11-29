package nl.tinoc.bonnetje.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import nl.tinoc.bonnetje.service.ProductService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/products")
public class ProductsResource {
    private static final Logger LOGGER = Logger.getLogger(ProductsResource.class.getName());

    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        try {
            return Response.ok(productService.getProducts()).build();
        } catch (Exception e) {
            return handleException(e, "Error getting products");
        }
    }

    private Response handleException(Exception e, String message) {
        LOGGER.log(Level.SEVERE, message, e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error: " + e.getMessage()).build();
    }

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
