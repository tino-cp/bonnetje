package nl.tinoc.bonnetje.data.dto;

public class ProductDTO {
    private int id;
    private int productId;
    private String name;
    private double price;
    private int quantity;
    private VatDTO vat;
    private DiscountDTO discount;

    private double subTotal;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String name, double price, int quantity, VatDTO vat, DiscountDTO discount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vat = vat;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public VatDTO getVat() {
        return vat;
    }

    public void setVat(VatDTO vat) {
        this.vat = vat;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
