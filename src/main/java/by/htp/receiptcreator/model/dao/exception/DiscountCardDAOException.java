package by.htp.receiptcreator.model.dao.exception;

public class DiscountCardDAOException extends Exception {

    private static final long serialVersionUID = 6849118194997010582L;

    public DiscountCardDAOException() {
    }

    public DiscountCardDAOException(String message) {
        super(message);
    }

    public DiscountCardDAOException(Exception exception) {
        super(exception);
    }

    public DiscountCardDAOException(String message, Exception exception) {
        super(message,exception);
    }
}
