package nl.tinoc.covadistributie;

public class ProductDTO {
    private String name;
    private double price;
    private int quantity;
    private int id;
    private int vat;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, double price, int vat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
}
