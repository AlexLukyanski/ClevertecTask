package by.htp.receiptcreator.model.service;

import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.model.service.exception.ReceiptServiceException;


public interface PrintReceiptService {

    void printReceiptToTXTFile(Receipt receipt) throws ReceiptServiceException;
}
