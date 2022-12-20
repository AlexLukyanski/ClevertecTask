package by.htp.receiptcreator.model.service;

import by.htp.receiptcreator.bean.CalculatedDiscount;
import by.htp.receiptcreator.bean.Cart;
import by.htp.receiptcreator.model.service.exception.CalculatedDiscountServiceException;

public interface CalculatedDiscountService {

    CalculatedDiscount calculateDiscount(int discountCardNumber, Cart cart) throws CalculatedDiscountServiceException;
}
