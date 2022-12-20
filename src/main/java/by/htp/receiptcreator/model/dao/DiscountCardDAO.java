package by.htp.receiptcreator.model.dao;

import by.htp.receiptcreator.model.dao.exception.ProductDAOException;

public interface DiscountCardDAO {

    int readDiscountByCardNumber(int cardNumber) throws ProductDAOException;
}
