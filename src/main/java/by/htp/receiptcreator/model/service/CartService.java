package by.htp.receiptcreator.model.service;

import by.htp.receiptcreator.bean.Cart;
import by.htp.receiptcreator.model.service.exception.CartServiceException;

public interface CartService {

    Cart formCart(int[] productID) throws CartServiceException;
}
