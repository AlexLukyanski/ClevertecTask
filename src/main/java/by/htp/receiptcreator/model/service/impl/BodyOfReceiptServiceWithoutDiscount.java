package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.Product;
import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.bean.constant.ReceiptTextParam;
import by.htp.receiptcreator.model.service.FormReceiptService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

public class BodyOfReceiptServiceWithoutDiscount implements FormReceiptService {

    private FormReceiptService receiptService;

    public BodyOfReceiptServiceWithoutDiscount(FormReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public void formReceipt(Receipt receipt) {
        receiptService.formReceipt(receipt);

        StringBuilder receiptText = receipt.getReceiptTextToPrint();

        receiptText.append(ReceiptTextParam.RECEIPT_SEPARATOR).append("\n");

        Set<Map.Entry<Product, Integer>> cart = receipt.getCart().getCart().entrySet();

        for (Map.Entry<Product, Integer> product : cart) {

            StringBuilder line = new StringBuilder(product.getValue().toString())
                    .append(" ")
                    .append(product.getKey().getName())
                    .append("    ")
                    .append(product.getKey().getPrice())
                    .append("    ")
                    .append(product.getKey().getPrice().setScale(2, RoundingMode.CEILING)
                            .multiply(BigDecimal.valueOf(product.getValue())))
                    .append("\n");

            receiptText.append(line);
        }

        receipt.setReceiptTextToPrint(receiptText);
    }
}
