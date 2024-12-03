package nl.tinoc.bonnetje.data.domain;

public class Vat {
    private int percent;
    private double amount;

    public Vat() {
    }

    public Vat(int percent, double amount) {
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
