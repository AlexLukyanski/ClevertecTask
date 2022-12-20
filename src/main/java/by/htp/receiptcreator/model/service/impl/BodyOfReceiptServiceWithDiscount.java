package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.Product;
import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.bean.constant.ReceiptTextParam;
import by.htp.receiptcreator.model.service.FormReceiptService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

public class BodyOfReceiptServiceWithDiscount implements FormReceiptService {

    private FormReceiptService receiptService;

    public BodyOfReceiptServiceWithDiscount(FormReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public void formReceipt(Receipt receipt) {
        receiptService.formReceipt(receipt);

        StringBuilder receiptText = receipt.getReceiptTextToPrint();

        receiptText.append(ReceiptTextParam.RECEIPT_SEPARATOR)
                .append("\n");

        Set<Map.Entry<Product, Integer>> cart = receipt.getCart().getCart().entrySet();
        Map<Product, Integer> calculatedProductDiscount = receipt.getCalculatedDiscount().getCalculatedProductDiscount();

        for (Map.Entry<Product, Integer> product : cart) {

            StringBuilder lineOne = new StringBuilder(product.getValue().toString())
                    .append(" ")
                    .append(product.getKey().getName())
                    .append(" ")
                    .append(product.getKey().getPrice())
                    .append(" ")
                    .append(product.getKey().getPrice().setScale(2, RoundingMode.CEILING)
                            .multiply(BigDecimal.valueOf(product.getValue())));

            StringBuilder lineTwo = new StringBuilder();

            if (calculatedProductDiscount.get(product.getKey()) != 0) {

                lineTwo.append(ReceiptTextParam.DISCOUNT_PREFIX)
                        .append(calculatedProductDiscount.get(product.getKey()))
                        .append(ReceiptTextParam.DISCOUNT_VALUE_PREFIX)
                        .append(product.getKey().getPrice().setScale(2, RoundingMode.CEILING)
                                .multiply(BigDecimal.valueOf(0.9))
                                .setScale(2, RoundingMode.CEILING))
                        .append(" ")
                        .append(product.getKey().getPrice().setScale(2, RoundingMode.CEILING)
                                .multiply(BigDecimal.valueOf(product.getValue()))
                                .multiply(BigDecimal.valueOf(0.9))
                                .setScale(2, RoundingMode.CEILING));
            }
            receiptText.append(lineOne)
                    .append("\n")
                    .append(lineTwo)
                    .append("\n");
        }
        receipt.setReceiptTextToPrint(receiptText);
    }
}
