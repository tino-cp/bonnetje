package nl.tinoc.covadistributie;

public class OrderResponse {
    private double totalAmount;
    private double vatHigh;
    private double vatLow;
    private double finalPrice;

    public OrderResponse(double totalAmount, double vatHigh, double vatLow, double finalPrice) {
        this.totalAmount = totalAmount;
        this.vatHigh = vatHigh;
        this.vatLow = vatLow;
        this.finalPrice = finalPrice;
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

    public double getFinalPrice() {
        return finalPrice;
    }
}