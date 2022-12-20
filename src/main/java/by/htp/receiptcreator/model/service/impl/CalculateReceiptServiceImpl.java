package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.*;
import by.htp.receiptcreator.bean.constant.ReceiptTextParam;
import by.htp.receiptcreator.model.service.CalculateReceiptService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

@Service
public class CalculateReceiptServiceImpl implements CalculateReceiptService {

    private volatile int receiptID = 1;

    @Override
    public Receipt calculateReceipt(Cart cart, CalculatedDiscount calculatedDiscount, Shop shop) {

        //Hardcode just to make it work
        int chosenCashierIndex = 1;

        int receiptIDTemp = receiptID;
        receiptID++;
        BigDecimal totalPriceWithoutDiscount = calculateTotalPriceWithoutDiscount(cart, calculatedDiscount);
        BigDecimal totalPriceWithDiscount = calculateTotalPriceWithDiscount(cart, calculatedDiscount);
        BigDecimal receiptDiscount = calculateReceiptDiscount(totalPriceWithoutDiscount, totalPriceWithDiscount);
        BigDecimal totalVAT = calculateTotalVAT(totalPriceWithDiscount, shop.getVatAmount());

        Receipt receipt = new Receipt();
        receipt.setId(receiptIDTemp);
        receipt.setCart(cart);
        receipt.setCalculatedDiscount(calculatedDiscount);
        receipt.setShop(shop);
        receipt.setTitle(createTitle(receiptIDTemp));
        receipt.setCashier(shop.getCashiers().get(chosenCashierIndex));
        receipt.setTotalPriceWithoutDiscount(totalPriceWithoutDiscount);
        receipt.setTotalPriceWithAllDiscount(totalPriceWithDiscount);
        receipt.setTotalDiscount(receiptDiscount);
        receipt.setTotalVAT(totalVAT);

        return receipt;
    }

    private String createTitle(int receiptID) {

        String title = new StringBuilder(ReceiptTextParam.RECEIPT_NAME_PREFIX)
                .append(receiptID)
                .toString();

        return title;
    }

    private BigDecimal calculateTotalPriceWithoutDiscount(Cart cart, CalculatedDiscount calculatedDiscount) {

        BigDecimal totalPrice = new BigDecimal(0);

        int discountFromCard = calculatedDiscount.getDiscountFromCard();
        Map<Product, Integer> cartMap = cart.getCart();
        Set<Map.Entry<Product, Integer>> calculatedProductDiscount = calculatedDiscount.getCalculatedProductDiscount().entrySet();

        for (Map.Entry<Product, Integer> product :
                calculatedProductDiscount) {

            BigDecimal productPrice = product.getKey().getPrice();
            int productAmount = cartMap.get(product.getKey());

            BigDecimal totalProductPrice = productPrice
                    .setScale(2, RoundingMode.CEILING)
                    .multiply(BigDecimal.valueOf(productAmount));

            totalPrice = totalPrice.add(totalProductPrice);

        }
        return totalPrice;
    }

    private BigDecimal calculateTotalPriceWithDiscount(Cart cart, CalculatedDiscount calculatedDiscount) {

        BigDecimal totalProductPriceWithCardDiscount = new BigDecimal(0);

        int discountFromCard = calculatedDiscount.getDiscountFromCard();
        Map<Product, Integer> cartMap = cart.getCart();
        Map<Product, Integer> calculatedDiscountMap = calculatedDiscount.getCalculatedProductDiscount();
        Set<Map.Entry<Product, Integer>> calculatedProductDiscount = calculatedDiscount.getCalculatedProductDiscount().entrySet();

        for (Map.Entry<Product, Integer> product :
                calculatedProductDiscount) {

            BigDecimal productPrice = product.getKey().getPrice();
            int productAmount = cartMap.get(product.getKey());
            int discountAmount = calculatedDiscountMap.get(product.getKey());
            boolean isDiscount = product.getKey().isDiscount();

            BigDecimal productPriceWithDiscount;
            BigDecimal totalProductPriceWithDiscount;

            productPriceWithDiscount = productPrice
                    .setScale(2, RoundingMode.CEILING)
                    .multiply(BigDecimal.valueOf(100 - discountAmount))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING);

            totalProductPriceWithDiscount = productPriceWithDiscount
                    .setScale(2, RoundingMode.CEILING)
                    .multiply(BigDecimal.valueOf(productAmount));

            if (discountFromCard != 0) {

                BigDecimal temp = totalProductPriceWithDiscount
                        .multiply(BigDecimal.valueOf(100 - discountFromCard))
                        .setScale(2, RoundingMode.CEILING)
                        .divide(BigDecimal.valueOf(100),2,RoundingMode.CEILING);

                totalProductPriceWithCardDiscount = totalProductPriceWithCardDiscount
                        .add(temp);

            } else {
                totalProductPriceWithCardDiscount = totalProductPriceWithCardDiscount
                        .add(totalProductPriceWithDiscount);
            }
        }
        return totalProductPriceWithCardDiscount;
    }

    private BigDecimal calculateReceiptDiscount(BigDecimal totalPriceWithoutDiscount, BigDecimal totalPriceWithDiscount) {

        return totalPriceWithoutDiscount
                .subtract(totalPriceWithDiscount);
    }

    private BigDecimal calculateTotalVAT(BigDecimal totalPriceWithDiscount, int vat) {

        return totalPriceWithDiscount
                .multiply(BigDecimal.valueOf(vat))
                .divide(BigDecimal.valueOf(100 + vat), 2, RoundingMode.CEILING);
    }
}