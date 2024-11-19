package nl.tinoc.bonnetje;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/products")
public class ProductsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1, "AH Biologische Havermout", 1.95, 9),
                new ProductDTO(2, "Unox Rookworst", 4.25, 21),
                new ProductDTO(3, "Calvé Pindakaas Crunchy", 3.99, 9),
                new ProductDTO(4, "Douwe Egberts Aroma Rood Koffiebonen", 7.99, 9),
                new ProductDTO(5, "Becel Original Margarine", 2.29, 9),
                new ProductDTO(6, "Grolsch Premium Pilsner 6-pack", 6.99, 21),
                new ProductDTO(7, "Boni Crispy Muesli", 2.79, 9),
                new ProductDTO(8, "FrieslandCampina Vifit Yoghurt Drink", 1.59, 9),
                new ProductDTO(9, "AH Huismerk Mineraalwater 6-pack", 2.49, 9),
                new ProductDTO(10, "Hak Groene Boontjes in Pot", 2.19, 9),
                new ProductDTO(11, "Jumbo Smac Smakelijke Ham", 3.75, 21),
                new ProductDTO(12, "Zonnatura Bio Groene Thee", 2.99, 9)
        );

        return Response.ok(products).build();
    }
}
