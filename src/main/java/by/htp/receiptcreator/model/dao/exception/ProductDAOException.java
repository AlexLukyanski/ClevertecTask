package by.htp.receiptcreator.model.dao.exception;

public class ProductDAOException extends Exception {

    private static final long serialVersionUID = 8315228595286038034L;

    public ProductDAOException() {
    }

    public ProductDAOException(String message) {
        super(message);
    }

    public ProductDAOException(Exception exception) {
        super(exception);
    }

    public ProductDAOException(String message, Exception exception) {
        super(message, exception);
    }
}
