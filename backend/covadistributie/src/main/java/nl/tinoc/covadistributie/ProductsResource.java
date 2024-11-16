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
                new ProductDTO(1, "Wood", 10.0, 0),
                new ProductDTO(2, "Brick", 20.0, 0),
                new ProductDTO(3, "Metal", 30.0, 0)
        );

        return Response.ok(products).build();
    }
}
