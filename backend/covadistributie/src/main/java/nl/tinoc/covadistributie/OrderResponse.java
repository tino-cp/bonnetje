package nl.tinoc.covadistributie;

public class OrderResponse {
    private double totalAmount;
    private double vatAmount;
    private double finalPrice;

    public OrderResponse(double totalAmount, double vatAmount, double finalPrice) {
        this.totalAmount = totalAmount;
        this.vatAmount = vatAmount;
        this.finalPrice = finalPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}