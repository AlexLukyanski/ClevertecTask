package by.htp.receiptcreator.test;

import by.htp.receiptcreator.bean.*;
import by.htp.receiptcreator.model.service.CalculateReceiptService;
import by.htp.receiptcreator.model.service.FormReceiptService;
import by.htp.receiptcreator.model.service.PrintReceiptService;
import by.htp.receiptcreator.model.service.exception.ReceiptServiceException;
import by.htp.receiptcreator.model.service.impl.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormAndCalculateReceiptTextTest {

    @Test
    public void formTopReceiptTest() {

        FormReceiptService formReceiptService = new TopOfReceiptService();
        Receipt receipt = createTestReceipt();

        String textBeforeExecute = receipt.getReceiptTextToPrint().toString();

        formReceiptService.formReceipt(receipt);

        String textAfterExecute = receipt.getReceiptTextToPrint().toString();

        org.junit.jupiter.api.Assertions.assertNotEquals(textBeforeExecute, textAfterExecute);
    }

    @Test
    public void formBodyWithoutDiscountReceiptTest() {

        FormReceiptService formReceiptService = new BodyOfReceiptServiceWithoutDiscount(new TopOfReceiptService());

        Receipt receipt = createTestReceipt();

        String textBeforeExecute = receipt.getReceiptTextToPrint().toString();

        formReceiptService.formReceipt(receipt);

        String textAfterExecute = receipt.getReceiptTextToPrint().toString();

        org.junit.jupiter.api.Assertions.assertNotEquals(textBeforeExecute, textAfterExecute);
    }

    @Test
    public void formBodyWithDiscountReceiptTest() {

        FormReceiptService formReceiptService = new BodyOfReceiptServiceWithDiscount(new TopOfReceiptService());

        Receipt receipt = createTestReceipt();

        String textBeforeExecute = receipt.getReceiptTextToPrint().toString();

        formReceiptService.formReceipt(receipt);

        String textAfterExecute = receipt.getReceiptTextToPrint().toString();

        org.junit.jupiter.api.Assertions.assertNotEquals(textBeforeExecute, textAfterExecute);
    }

    @Test
    public void formBottomReceiptTest() {

        FormReceiptService formReceiptService = new BottomOfReceiptService(new BodyOfReceiptServiceWithDiscount(new TopOfReceiptService()));

        Receipt receipt = createTestReceipt();

        String textBeforeExecute = receipt.getReceiptTextToPrint().toString();

        formReceiptService.formReceipt(receipt);

        String textAfterExecute = receipt.getReceiptTextToPrint().toString();

        org.junit.jupiter.api.Assertions.assertNotEquals(textBeforeExecute, textAfterExecute);
    }

    @Test
    public void calculateReceiptTest() {

        Receipt before = new Receipt();

        Shop shop = fillShop();
        Cart cart = fillCart();
        CalculatedDiscount calculatedDiscount = fillCalculatedDiscount();

        CalculateReceiptService calculateReceiptService = new CalculateReceiptServiceImpl();

        Receipt after = calculateReceiptService.calculateReceipt(cart, calculatedDiscount, shop);

        org.junit.jupiter.api.Assertions.assertNotEquals(before, after);
    }

    @Test
    public void printReceiptToTXTFileTest() throws ReceiptServiceException {

        Receipt receipt = createTestReceipt();
        PrintReceiptService printReceiptService = new PrintReceiptServiceImpl();
        printReceiptService.printReceiptToTXTFile(receipt);

        String textToWrite = receipt.getReceiptTextToPrint().toString();
        StringBuilder temp = new StringBuilder();

        try (FileReader fileReader = new FileReader(receipt.getTitle() + ".txt")) {

            int x;

            while ((x = fileReader.read()) != -1) {
                temp.append((char) x);
            }

            String textFromRead = temp.toString();

            org.junit.jupiter.api.Assertions.assertEquals(textToWrite, textFromRead);

        } catch (FileNotFoundException e) {
            throw new ReceiptServiceException(e);
        } catch (IOException e) {
            throw new ReceiptServiceException(e);
        }
    }

    private Receipt createTestReceipt() {

        Receipt receipt = new Receipt();
        List<Cashier> cashiers = fillCashiers();
        Shop shop = fillShop();
        Cart cart = fillCart();
        CalculatedDiscount calculatedDiscount = fillCalculatedDiscount();

        receipt.setId(1);
        receipt.setTitle("Test");
        receipt.setCashier(cashiers.get(0));
        receipt.setTotalVAT(BigDecimal.valueOf(shop.getVatAmount()));
        receipt.setCalculatedDiscount(calculatedDiscount);
        receipt.setCart(cart);
        receipt.setTotalDiscount(BigDecimal.valueOf(3));
        receipt.setTotalPriceWithoutDiscount(BigDecimal.valueOf(1000));
        receipt.setTotalPriceWithAllDiscount(BigDecimal.valueOf(900));
        receipt.setReceiptTextToPrint(new StringBuilder("zz"));
        receipt.setShop(shop);

        return receipt;
    }

    private Shop fillShop() {

        Shop shop = new Shop.ShopBuilder()
                .setName("adrgaehgaerh")
                .setAddress("aerhaerhaerh")
                .setPhoneNumber("aerharhaerhe")
                .setVatAmount(1111)
                .setCashiers(fillCashiers())
                .build();

        return shop;
    }

    private List<Cashier> fillCashiers() {

        Cashier cashierOne = new Cashier();
        cashierOne.setId(1);
        cashierOne.setName("zdfbdrnhha");
        cashierOne.setSurname("fffff");

        Cashier cashierTwo = new Cashier();
        cashierTwo.setId(2);
        cashierTwo.setName("fnsrtjtrj");
        cashierTwo.setSurname("bbgnmjj");

        List<Cashier> cashiers = new ArrayList<>();
        cashiers.add(cashierOne);
        cashiers.add(cashierTwo);

        return cashiers;
    }

    private Cart fillCart() {

        Map<Product, Integer> map = new HashMap<>();
        map.put(createProduct(), 1);

        Cart cart = new Cart();
        cart.setCart(map);

        return cart;
    }

    private Product createProduct() {

        Product product = new Product();
        product.setId(1);
        product.setName("zdfdabh");
        product.setDiscount(false);
        product.setPrice(BigDecimal.valueOf(100));

        return product;
    }

    private CalculatedDiscount fillCalculatedDiscount() {

        CalculatedDiscount calculatedDiscount = new CalculatedDiscount();

        Map<Product, Integer> map = new HashMap<>();
        map.put(createProduct(), 10);

        calculatedDiscount.setDiscountFromCard(10);
        calculatedDiscount.setCalculatedProductDiscount(map);

        return calculatedDiscount;
    }
}
