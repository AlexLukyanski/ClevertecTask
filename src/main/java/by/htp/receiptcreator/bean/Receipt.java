package by.htp.receiptcreator.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


public class Receipt implements Serializable {

    private int id;
    private Cart cart;
    private CalculatedDiscount calculatedDiscount;
    private String title;
    private Shop shop;
    private Cashier cashier;
    private BigDecimal totalPriceWithoutDiscount;
    private BigDecimal totalPriceWithAllDiscount;
    private BigDecimal totalDiscount;
    private BigDecimal totalVAT;
    private StringBuilder receiptTextToPrint;

    private static final long serialVersionUID = -113489643938059561L;

    public Receipt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CalculatedDiscount getCalculatedDiscount() {
        return calculatedDiscount;
    }

    public void setCalculatedDiscount(CalculatedDiscount calculatedDiscount) {
        this.calculatedDiscount = calculatedDiscount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public BigDecimal getTotalPriceWithoutDiscount() {
        return totalPriceWithoutDiscount;
    }

    public void setTotalPriceWithoutDiscount(BigDecimal totalPriceWithoutDiscount) {
        this.totalPriceWithoutDiscount = totalPriceWithoutDiscount;
    }

    public BigDecimal getTotalPriceWithAllDiscount() {
        return totalPriceWithAllDiscount;
    }

    public void setTotalPriceWithAllDiscount(BigDecimal totalPriceWithAllDiscount) {
        this.totalPriceWithAllDiscount = totalPriceWithAllDiscount;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalVAT() {
        return totalVAT;
    }

    public void setTotalVAT(BigDecimal totalVAT) {
        this.totalVAT = totalVAT;
    }

    public StringBuilder getReceiptTextToPrint() {
        return receiptTextToPrint;
    }

    public void setReceiptTextToPrint(StringBuilder receiptTextToPrint) {
        this.receiptTextToPrint = receiptTextToPrint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id && Objects.equals(cart, receipt.cart) && Objects.equals(calculatedDiscount, receipt.calculatedDiscount) && Objects.equals(title, receipt.title) && Objects.equals(shop, receipt.shop) && Objects.equals(cashier, receipt.cashier) && Objects.equals(totalPriceWithoutDiscount, receipt.totalPriceWithoutDiscount) && Objects.equals(totalPriceWithAllDiscount, receipt.totalPriceWithAllDiscount) && Objects.equals(totalDiscount, receipt.totalDiscount) && Objects.equals(totalVAT, receipt.totalVAT) && Objects.equals(receiptTextToPrint, receipt.receiptTextToPrint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, calculatedDiscount, title, shop, cashier, totalPriceWithoutDiscount, totalPriceWithAllDiscount, totalDiscount, totalVAT, receiptTextToPrint);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", cart=" + cart +
                ", calculatedDiscount=" + calculatedDiscount +
                ", title='" + title + '\'' +
                ", shop=" + shop +
                ", cashier=" + cashier +
                ", totalPriceWithoutDiscount=" + totalPriceWithoutDiscount +
                ", totalPriceWithAllDiscount=" + totalPriceWithAllDiscount +
                ", totalDiscount=" + totalDiscount +
                ", totalVAT=" + totalVAT +
                ", receiptTextToPrint='" + receiptTextToPrint + '\'' +
                '}';
    }
}
