package by.htp.receiptcreator.model.service.exception;


public class CartServiceException extends Exception {

    private static final long serialVersionUID = -3236426313347845119L;

    public CartServiceException() {
    }

    public CartServiceException(String message) {
        super(message);
    }

    public CartServiceException(Exception exception) {
        super(exception);
    }

    public CartServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
