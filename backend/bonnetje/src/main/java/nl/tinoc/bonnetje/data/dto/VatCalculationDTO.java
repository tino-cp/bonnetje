package nl.tinoc.bonnetje.data.dto;

public class VatCalculationDTO {
    private VatCalculationDetailsDTO low;
    private VatCalculationDetailsDTO high;
    private double total;
    private double priceTotal;

    public VatCalculationDTO() {
    }

    public VatCalculationDTO(VatCalculationDetailsDTO low, VatCalculationDetailsDTO high, double total, double priceTotal) {
        this.low = low;
        this.high = high;
        this.total = total;
        this.priceTotal = priceTotal;
    }

    public VatCalculationDetailsDTO getLow() {
        return low;
    }

    public void setLow(VatCalculationDetailsDTO low) {
        this.low = low;
    }

    public VatCalculationDetailsDTO getHigh() {
        return high;
    }

    public void setHigh(VatCalculationDetailsDTO high) {
        this.high = high;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
}
