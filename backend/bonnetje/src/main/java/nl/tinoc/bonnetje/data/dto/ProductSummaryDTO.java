package nl.tinoc.bonnetje.data.dto;


public class ProductSummaryDTO {
    private int productId;
    private String productName;
    private double subTotal;
    private int quantity;
    private int vatRate;
    private double discountAmount;
    private double vatAmount;

    public ProductSummaryDTO() {
    }

    public ProductSummaryDTO(int productId, String productName, double subTotal, int quantity, int vatRate, double discountAmount, double vatAmount) {
        this.productId = productId;
        this.productName = productName;
        this.subTotal = subTotal;
        this.quantity = quantity;
        this.vatRate = vatRate;
        this.discountAmount = discountAmount;
        this.vatAmount = vatAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }
}
