package by.htp.receiptcreator.controller.exeption;


public class ReceiptException extends Exception {

    private static final long serialVersionUID = 627380631659702816L;

    public ReceiptException() {
    }

    public ReceiptException(String message) {
        super(message);
    }

    public ReceiptException(String message, Exception exception) {
        super(message, exception);
    }

    public ReceiptException(Exception exception) {
        super(exception);
    }
}
