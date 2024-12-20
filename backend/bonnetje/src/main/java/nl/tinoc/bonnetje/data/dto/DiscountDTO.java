package nl.tinoc.bonnetje.data.dto;

public class DiscountDTO {
    private int percent;
    private double amount;
    private int minQuantity;

    public DiscountDTO() {
    }

    public DiscountDTO(int percent, double amount, int minQuantity) {
        this.percent = percent;
        this.amount = amount;
        this.minQuantity = minQuantity;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }
}
