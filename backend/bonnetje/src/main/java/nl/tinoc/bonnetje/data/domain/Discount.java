package nl.tinoc.bonnetje.data.domain;

public class Discount {
    private int productId;
    private double discountPercentage;
    private int quantity;

    public Discount() {
    }

    public Discount(int productId, double discountPercentage, int quantity) {
        this.productId = productId;
        this.discountPercentage = discountPercentage;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}