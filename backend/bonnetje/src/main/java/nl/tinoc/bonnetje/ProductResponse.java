package nl.tinoc.bonnetje;

public class ProductResponse {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int vat;
    private double discount;

    public ProductResponse() {
    }

    public ProductResponse(int id, String name, double price, int quantity, int vat, double discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vat = vat;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getVat() {
        return vat;
    }

    public double getDiscount() {
        return discount;
    }
}
