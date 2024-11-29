package nl.tinoc.bonnetje.data.domain;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int vat;
    private Discount discount;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity, int vat, Discount discount) {
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
