package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.model.service.FormReceiptService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReceiptFactory {

    public void formReceiptText(Receipt receipt) {

        BigDecimal discount = receipt.getTotalDiscount();

        if (discount.doubleValue() == 0) {
            FormReceiptService formReceiptService = new BottomOfReceiptService(new BodyOfReceiptServiceWithoutDiscount(new TopOfReceiptService()));
            formReceiptService.formReceipt(receipt);
        } else {
            FormReceiptService formReceiptService = new BottomOfReceiptService(new BodyOfReceiptServiceWithDiscount(new TopOfReceiptService()));
            formReceiptService.formReceipt(receipt);
        }
    }
}
