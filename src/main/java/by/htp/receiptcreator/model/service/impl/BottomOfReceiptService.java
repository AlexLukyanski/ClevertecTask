package by.htp.receiptcreator.model.service.impl;


import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.bean.constant.ReceiptTextParam;
import by.htp.receiptcreator.model.service.FormReceiptService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BottomOfReceiptService implements FormReceiptService {

    private FormReceiptService receiptService;

    public BottomOfReceiptService(FormReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Override
    public void formReceipt(Receipt receipt) {
        receiptService.formReceipt(receipt);

        StringBuilder receiptText = receipt.getReceiptTextToPrint();

        receiptText.append("--------------------------------------").append("\n");

        List<String> bottomText = formBottom(receipt);

        for (String line :
                bottomText) {
            receiptText.append(line).append("\n");
        }
        receipt.setReceiptTextToPrint(receiptText);
    }

    private List<String> formBottom(Receipt receipt) {

        BigDecimal totalPriceWithAllDiscount = receipt.getTotalPriceWithAllDiscount();
        BigDecimal totalDiscount = receipt.getTotalDiscount();
        BigDecimal totalVAT = receipt.getTotalVAT();

        List<String> bottomOfReceipt = new ArrayList<>();

        String line1 = new StringBuilder(ReceiptTextParam.TOTAL_PREFIX)
                .append(totalPriceWithAllDiscount)
                .toString();
        String line2 = new StringBuilder(ReceiptTextParam.TOTAL_DISCOUNT_PREFIX)
                .append(totalDiscount)
                .toString();
        String line3 = new StringBuilder(ReceiptTextParam.TOTAL_VAT_PREFIX)
                .append(totalVAT)
                .toString();

        bottomOfReceipt.add(line1);
        if (totalDiscount.doubleValue() > 0) {
            bottomOfReceipt.add(line2);
        }
        bottomOfReceipt.add(line3);

        return bottomOfReceipt;
    }
}
