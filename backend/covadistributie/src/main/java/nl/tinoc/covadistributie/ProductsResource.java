package nl.tinoc.covadistributie;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/products")
public class ProductsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO("Product 1", 10.0, 0),
                new ProductDTO("Product 2", 20.0, 0),
                new ProductDTO("Product 3", 30.0, 0)
        );

        return Response.ok(products).build();
    }
}
