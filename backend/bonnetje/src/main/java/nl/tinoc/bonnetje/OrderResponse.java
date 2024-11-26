package nl.tinoc.bonnetje;

import java.util.List;

public class OrderResponse {
    private double totalAmount;
    private double vatHigh;
    private double vatLow;
    private double vatTotalHigh;
    private double vatTotalLow;
    private double finalPrice;
    List<ProductResponse> productResponses;

    public OrderResponse(double totalAmount, double vatHigh, double vatLow, double vatTotalHigh, double vatTotalLow, double finalPrice, List<ProductResponse> productResponses) {
        this.totalAmount = totalAmount;
        this.vatHigh = vatHigh;
        this.vatLow = vatLow;
        this.vatTotalHigh = vatTotalHigh;
        this.vatTotalLow = vatTotalLow;
        this.finalPrice = finalPrice;
        this.productResponses = productResponses;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getVatHigh() {
        return vatHigh;
    }

    public double getVatLow() {
        return vatLow;
    }

    public double getVatTotalHigh() {
        return vatTotalHigh;
    }

    public double getVatTotalLow() {
        return vatTotalLow;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }
}