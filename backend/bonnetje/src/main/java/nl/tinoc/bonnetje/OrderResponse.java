package nl.tinoc.bonnetje;

public class OrderResponse {
    private double totalAmount;
    private double vatHigh;
    private double vatLow;
    private double vatTotalHigh;
    private double vatTotalLow;
    private double finalPrice;

    public OrderResponse(double totalAmount, double vatHigh, double vatLow, double vatTotalHigh, double vatTotalLow, double finalPrice) {
        this.totalAmount = totalAmount;
        this.vatHigh = vatHigh;
        this.vatLow = vatLow;
        this.vatTotalHigh = vatTotalHigh;
        this.vatTotalLow = vatTotalLow;
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

    public double getVatTotalHigh() {
        return vatTotalHigh;
    }

    public double getVatTotalLow() {
        return vatTotalLow;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}