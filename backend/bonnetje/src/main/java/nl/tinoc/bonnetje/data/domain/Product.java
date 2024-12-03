package nl.tinoc.bonnetje.data.domain;

public class Product {
    private int id;
    private int productId;
    private String name;
    private double price;
    private int quantity;
    private Vat vat;
    private Discount discount;

    public Product() {
    }

    public Product(int productId, String name, double price, int quantity, Vat vat, Discount discount) {
        this.productId = productId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
