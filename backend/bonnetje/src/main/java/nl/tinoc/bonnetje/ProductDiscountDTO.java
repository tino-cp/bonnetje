package nl.tinoc.bonnetje;

public class ProductDiscountDTO {
    private int productId;
    private double discount;
    private int quantity;

    public ProductDiscountDTO() {
    }

    public ProductDiscountDTO(int productId, double discount, int quantity) {
        this.productId = productId;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public double getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
