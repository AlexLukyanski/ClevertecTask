package by.htp.receiptcreator.model.service.exception;

public class ReceiptServiceException extends Exception {

    private static final long serialVersionUID = -3607188337688333239L;

    public ReceiptServiceException() {
    }

    public ReceiptServiceException(String message) {
        super(message);
    }

    public ReceiptServiceException(String message, Exception exception) {
        super(message, exception);
    }

    public ReceiptServiceException(Exception exception) {
        super(exception);
    }
}
