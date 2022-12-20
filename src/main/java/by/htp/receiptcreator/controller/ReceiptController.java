package by.htp.receiptcreator.controller;

import by.htp.receiptcreator.bean.*;
import by.htp.receiptcreator.controller.exeption.ReceiptException;
import by.htp.receiptcreator.model.service.CalculateReceiptService;
import by.htp.receiptcreator.model.service.CalculatedDiscountService;
import by.htp.receiptcreator.model.service.CartService;
import by.htp.receiptcreator.model.service.PrintReceiptService;
import by.htp.receiptcreator.model.service.exception.CalculatedDiscountServiceException;
import by.htp.receiptcreator.model.service.exception.CartServiceException;
import by.htp.receiptcreator.model.service.exception.ReceiptServiceException;
import by.htp.receiptcreator.model.service.impl.ReceiptFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReceiptController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CalculatedDiscountService calculatedDiscountService;
    @Autowired
    private CalculateReceiptService calculateReceiptService;
    @Autowired
    private ReceiptFactory receiptFactory;
    @Autowired
    private PrintReceiptService printReceiptService;

    @GetMapping(URLPattern.GET_RECEIPT_WITHOUT_DISCOUNT_CARD)
    public String getReceiptWithoutDiscountCard(@PathVariable("productID") int[] productID) throws ReceiptException {

        //Methods are NOT the same! Here we HAVE NOT discount card
        Cart cart;
        CalculatedDiscount calculatedDiscount;
        Shop shop;
        Receipt receipt;

        try {
            cart = cartService.formCart(productID);
            calculatedDiscount = calculatedDiscountService.calculateDiscount(0, cart);
            shop = fillShop();
            receipt = calculateReceiptService.calculateReceipt(cart, calculatedDiscount, shop);
            receiptFactory.formReceiptText(receipt);
            printReceiptService.printReceiptToTXTFile(receipt);

        } catch (CartServiceException | CalculatedDiscountServiceException | ReceiptServiceException e) {
            throw new ReceiptException(e);
        }
        return receipt.getReceiptTextToPrint().toString();
    }

    @GetMapping(URLPattern.GET_RECEIPT_WITH_DISCOUNT_CARD)
    public String getReceiptWithDiscountCard(@PathVariable("productID") int[] productID,
                                             @PathVariable(name = "cardNumber") int cardNumber) throws ReceiptException {

        //Methods are NOT the same! Here we HAVE discount card
        Cart cart;
        CalculatedDiscount calculatedDiscount;
        Shop shop;
        Receipt receipt;

        try {
            cart = cartService.formCart(productID);
            calculatedDiscount = calculatedDiscountService.calculateDiscount(cardNumber, cart);
            shop = fillShop();
            receipt = calculateReceiptService.calculateReceipt(cart, calculatedDiscount, shop);
            receiptFactory.formReceiptText(receipt);
            printReceiptService.printReceiptToTXTFile(receipt);

        } catch (CartServiceException | CalculatedDiscountServiceException | ReceiptServiceException e) {
            throw new ReceiptException(e);
        }
        return receipt.getReceiptTextToPrint().toString();
    }

    //All stuff below are hardcode just to make it work

    private Shop fillShop() {

        Shop shop = new Shop.ShopBuilder()
                .setName("Who's your Daddy?")
                .setAddress("999, Daddy's drive")
                .setPhoneNumber("+777 77 777-77-77")
                .setVatAmount(20)
                .setCashiers(fillCashiers())
                .build();

        return shop;
    }

    private List<Cashier> fillCashiers() {

        Cashier cashierOne = new Cashier();
        cashierOne.setId(1);
        cashierOne.setName("Daddy Moroz");
        cashierOne.setSurname("HO HO HO!");

        Cashier cashierTwo = new Cashier();
        cashierTwo.setId(2);
        cashierTwo.setName("Daddy Elf");
        cashierTwo.setSurname("HEY HEY HEY!");

        List<Cashier> cashiers = new ArrayList<>();
        cashiers.add(cashierOne);
        cashiers.add(cashierTwo);

        return cashiers;
    }
}
