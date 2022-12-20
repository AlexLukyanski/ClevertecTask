package by.htp.receiptcreator.bean;

import java.util.List;


public class Shop {

    private final String name;
    private final String address;
    private final String phoneNumber;
    private final List<Cashier> cashiers;
    private final int vatAmount;

    public Shop(ShopBuilder builder) {

        this.name = builder.name;
        this.address = builder.address;
        this.cashiers = builder.cashiers;
        this.vatAmount = builder.vatAmount;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public int getVatAmount() {
        return vatAmount;
    }

    public static class ShopBuilder {

        private String name;
        private String address;
        private String phoneNumber;
        private List<Cashier> cashiers;
        private int vatAmount;

        public ShopBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ShopBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public ShopBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ShopBuilder setCashiers(List<Cashier> cashiers) {
            this.cashiers = cashiers;
            return this;
        }

        public ShopBuilder setVatAmount(int vatAmount) {
            this.vatAmount = vatAmount;
            return this;
        }

        public Shop build(){

            return new Shop(this);
        }
    }

}
