package nl.tinoc.bonnetje.data.dto;

import java.util.List;

public class OrderSummaryDTO {
    private double totalAmount;
    private double vatHigh;
    private double vatLow;
    private double vatTotalHigh;
    private double vatTotalLow;
    private double finalPrice;
    private List<ProductSummaryDTO> productSummaries;

    public OrderSummaryDTO() {
    }

    public OrderSummaryDTO(double totalAmount, double vatLow, double vatHigh, double vatTotalHigh, double vatTotalLow, double finalPrice, List<ProductSummaryDTO> productSummaries) {
        this.totalAmount = totalAmount;
        this.vatHigh = vatHigh;
        this.vatLow = vatLow;
        this.vatTotalHigh = vatTotalHigh;
        this.vatTotalLow = vatTotalLow;
        this.finalPrice = finalPrice;
        this.productSummaries = productSummaries;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getVatHigh() {
        return vatHigh;
    }

    public void setVatHigh(double vatHigh) {
        this.vatHigh = vatHigh;
    }

    public double getVatLow() {
        return vatLow;
    }

    public void setVatLow(double vatLow) {
        this.vatLow = vatLow;
    }

    public double getVatTotalHigh() {
        return vatTotalHigh;
    }

    public void setVatTotalHigh(double vatTotalHigh) {
        this.vatTotalHigh = vatTotalHigh;
    }

    public double getVatTotalLow() {
        return vatTotalLow;
    }

    public void setVatTotalLow(double vatTotalLow) {
        this.vatTotalLow = vatTotalLow;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<ProductSummaryDTO> getProductSummaries() {
        return productSummaries;
    }

    public void setProductSummaries(List<ProductSummaryDTO> productSummaries) {
        this.productSummaries = productSummaries;
    }
}
