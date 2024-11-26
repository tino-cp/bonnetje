package nl.tinoc.bonnetje;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/products")
public class ProductsResource {
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        try {
            return Response.ok(productService.getProducts()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
