package by.htp.receiptcreator.model.service.exception;


public class CalculatedDiscountServiceException extends Exception{

    private static final long serialVersionUID = 1320036293646904947L;

    public CalculatedDiscountServiceException() {
    }

    public CalculatedDiscountServiceException(String message) {
        super(message);
    }

    public CalculatedDiscountServiceException(String message, Exception exception) {
        super(message, exception);
    }

    public CalculatedDiscountServiceException(Exception exception) {
        super(exception);
    }
}
