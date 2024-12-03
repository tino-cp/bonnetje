package nl.tinoc.bonnetje.data.dto;

import java.util.List;

public class OrderSummaryDTO {
    private double finalPrice;
    private double totalAmount;
    private VatCalculationDTO vatCalculation;
    private List<ProductDTO> products;

    public OrderSummaryDTO() {
    }

    public OrderSummaryDTO(double finalPrice, double totalAmount, VatCalculationDTO vatCalculation, List<ProductDTO> products) {
        this.finalPrice = finalPrice;
        this.totalAmount = totalAmount;
        this.vatCalculation = vatCalculation;
        this.products = products;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public VatCalculationDTO getVatCalculation() {
        return vatCalculation;
    }

    public void setVatCalculation(VatCalculationDTO vatCalculation) {
        this.vatCalculation = vatCalculation;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
