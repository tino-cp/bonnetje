package nl.tinoc.bonnetje.data.dto;

import java.math.BigDecimal;

public class ProductSummaryDTO {
    private int productId;
    private String productName;
    private BigDecimal subTotal;
    private int quantity;
    private int vatRate;
    private BigDecimal discountAmount;
    private BigDecimal vatAmount;

    public ProductSummaryDTO(int productId, String productName, BigDecimal subTotal, int quantity, int vatRate, BigDecimal discountAmount, BigDecimal vatAmount) {
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }
}
