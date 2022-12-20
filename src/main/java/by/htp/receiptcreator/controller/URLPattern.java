package by.htp.receiptcreator.controller;

public class URLPattern {

    private URLPattern(){

    }

    public static final String GET_RECEIPT_WITHOUT_DISCOUNT_CARD = "/{productID}";
    public static final String GET_RECEIPT_WITH_DISCOUNT_CARD = "/{productID}/{cardNumber}";

}
