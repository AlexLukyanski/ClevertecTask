package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.bean.constant.ReceiptTextParam;
import by.htp.receiptcreator.model.service.FormReceiptService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TopOfReceiptService implements FormReceiptService {

    private volatile int receiptID = 1;

    @Override
    public void formReceipt(Receipt receipt) {

        StringBuilder receiptText = new StringBuilder();

        List<String> topOfReceipt = formTopOfReceipt(receipt);

        for (String line : topOfReceipt) {
            receiptText.append(line);
            receiptText.append("\n");
        }
        receipt.setReceiptTextToPrint(receiptText);
    }

    private List<String> formTopOfReceipt(Receipt receipt) {

        LocalDateTime dateAndTime = LocalDateTime.now();
        List<String> topOfReceipt = new ArrayList<>();

        String title = receipt.getTitle();
        String shopName = receipt.getShop().getName();
        String shopAddress = receipt.getShop().getAddress();
        String shopPhoneNumber = receipt.getShop().getPhoneNumber();

        String cashierAndDate = new StringBuilder(ReceiptTextParam.CASHIER_PREFIX)
                .append(receipt.getCashier().getId())
                .append("\n")
                .append(ReceiptTextParam.DATE_PREFIX)
                .append(dateAndTime.getDayOfMonth())
                .append(ReceiptTextParam.DATE_SEPARATOR)
                .append(dateAndTime.getMonthValue())
                .append(ReceiptTextParam.DATE_SEPARATOR)
                .append(dateAndTime.getYear())
                .toString();
        String time = new StringBuilder(ReceiptTextParam.TIME_PREFIX)
                .append(dateAndTime.getHour())
                .append(ReceiptTextParam.TIME_SEPARATOR)
                .append(dateAndTime.getMinute())
                .append(ReceiptTextParam.TIME_SEPARATOR)
                .append(dateAndTime.getSecond())
                .toString();

        topOfReceipt.add(title);
        topOfReceipt.add(shopName);
        topOfReceipt.add(shopAddress);
        topOfReceipt.add(shopPhoneNumber);
        topOfReceipt.add(cashierAndDate);
        topOfReceipt.add(time);
        return topOfReceipt;
    }
}
