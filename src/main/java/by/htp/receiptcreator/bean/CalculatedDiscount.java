package by.htp.receiptcreator.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class CalculatedDiscount implements Serializable {

    private Map<Product, Integer> calculatedProductDiscount;
    private int discountFromCard;

    private static final long serialVersionUID = -4461239129630738305L;

    public CalculatedDiscount() {
    }

    public Map<Product, Integer> getCalculatedProductDiscount() {
        return calculatedProductDiscount;
    }

    public void setCalculatedProductDiscount(Map<Product, Integer> calculatedProductDiscount) {
        this.calculatedProductDiscount = calculatedProductDiscount;
    }

    public int getDiscountFromCard() {
        return discountFromCard;
    }

    public void setDiscountFromCard(int discountFromCard) {
        this.discountFromCard = discountFromCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculatedDiscount that = (CalculatedDiscount) o;
        return discountFromCard == that.discountFromCard && Objects.equals(calculatedProductDiscount, that.calculatedProductDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calculatedProductDiscount, discountFromCard);
    }

    @Override
    public String toString() {
        return "CalculatedDiscount{" +
                "calculatedDiscount=" + calculatedProductDiscount +
                ", discountFromCard=" + discountFromCard +
                '}';
    }
}
