package nl.tinoc.bonnetje.data.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderSummaryDTO {
    private BigDecimal totalAmount;
    private BigDecimal vatHigh;
    private BigDecimal vatLow;
    private BigDecimal vatTotalHigh;
    private BigDecimal vatTotalLow;
    private BigDecimal finalPrice;
    private List<ProductSummaryDTO> productSummaries;

    public OrderSummaryDTO(BigDecimal totalAmount, BigDecimal vatLow, BigDecimal vatHigh, BigDecimal vatTotalHigh, BigDecimal vatTotalLow, BigDecimal finalPrice, List<ProductSummaryDTO> productSummaries) {
        this.totalAmount = totalAmount;
        this.vatHigh = vatHigh;
        this.vatLow = vatLow;
        this.vatTotalHigh = vatTotalHigh;
        this.vatTotalLow = vatTotalLow;
        this.finalPrice = finalPrice;
        this.productSummaries = productSummaries;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getVatHigh() {
        return vatHigh;
    }

    public void setVatHigh(BigDecimal vatHigh) {
        this.vatHigh = vatHigh;
    }

    public BigDecimal getVatLow() {
        return vatLow;
    }

    public void setVatLow(BigDecimal vatLow) {
        this.vatLow = vatLow;
    }

    public BigDecimal getVatTotalHigh() {
        return vatTotalHigh;
    }

    public void setVatTotalHigh(BigDecimal vatTotalHigh) {
        this.vatTotalHigh = vatTotalHigh;
    }

    public BigDecimal getVatTotalLow() {
        return vatTotalLow;
    }

    public void setVatTotalLow(BigDecimal vatTotalLow) {
        this.vatTotalLow = vatTotalLow;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<ProductSummaryDTO> getProductSummaries() {
        return productSummaries;
    }

    public void setProductSummaries(List<ProductSummaryDTO> productSummaries) {
        this.productSummaries = productSummaries;
    }
}
