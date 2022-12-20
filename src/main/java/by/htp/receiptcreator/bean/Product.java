package by.htp.receiptcreator.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int id;
    @Column(name = "p_name")
    private String name;
    @Column(name = "p_price")
    private BigDecimal price;
    @Column(name = "p_discount")
    private boolean discount;
    @Column(name = "p_discountamount")
    private int discountAmount;
    @Column(name = "p_amounttodiscount")
    private int amountToDiscount;

    private static final long serialVersionUID = 3422947331571637560L;

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getAmountToDiscount() {
        return amountToDiscount;
    }

    public void setAmountToDiscount(int amountToDiscount) {
        this.amountToDiscount = amountToDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && discount == product.discount && discountAmount == product.discountAmount && amountToDiscount == product.amountToDiscount && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount, discountAmount, amountToDiscount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", discountAmount=" + discountAmount +
                ", amountToDiscount=" + amountToDiscount +
                '}';
    }
}
