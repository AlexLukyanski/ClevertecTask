package by.htp.receiptcreator.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Cart implements Serializable {

    private Map<Product, Integer> cart;


    private static final long serialVersionUID = 4486564261407871131L;

    public Cart() {

    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart1 = (Cart) o;
        return Objects.equals(cart, cart1.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}
