package by.htp.receiptcreator.model.service;

import by.htp.receiptcreator.bean.Receipt;
import org.springframework.stereotype.Service;

@Service
public interface FormReceiptService {

    void formReceipt(Receipt receipt);

}
