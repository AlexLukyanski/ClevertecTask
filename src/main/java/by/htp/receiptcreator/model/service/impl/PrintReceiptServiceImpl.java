package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.model.service.PrintReceiptService;
import by.htp.receiptcreator.model.service.exception.ReceiptServiceException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class PrintReceiptServiceImpl implements PrintReceiptService {

    @Override
    public void printReceiptToTXTFile(Receipt receipt) throws ReceiptServiceException {

        File file = createFile(receipt);
        String textToPrint = receipt.getReceiptTextToPrint().toString();

        try (FileWriter fileWriter = new FileWriter(file)) {

            fileWriter.write(textToPrint);
            fileWriter.flush();

        } catch (IOException e) {
            throw new ReceiptServiceException(e);
        }
    }

    private File createFile(Receipt receipt) {

        String pathName;
        File file;

        pathName = new StringBuilder(receipt.getTitle())
                .append(".txt")
                .toString();

        return new File(pathName);
    }
}
