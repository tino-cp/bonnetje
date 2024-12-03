package nl.tinoc.bonnetje.data.dto;

public class VatCalculationDetailsDTO {
    private double amount;
    private double priceSubTotal;

    public VatCalculationDetailsDTO() {
    }

    public VatCalculationDetailsDTO(double amount, double priceSubTotal) {
        this.amount = amount;
        this.priceSubTotal = priceSubTotal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceSubTotal() {
        return priceSubTotal;
    }

    public void setPriceSubTotal(double priceSubTotal) {
        this.priceSubTotal = priceSubTotal;
    }
}
