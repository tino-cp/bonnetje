package nl.tinoc.bonnetje.data.dto;

public class VatDTO {
    private int percent;
    private double amount;

    public VatDTO() {
    }

    public VatDTO(int percent, double amount) {
        this.percent = percent;
        this.amount = amount;
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
}
