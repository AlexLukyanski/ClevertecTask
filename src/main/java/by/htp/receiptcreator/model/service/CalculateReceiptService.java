package by.htp.receiptcreator.model.service;

import by.htp.receiptcreator.bean.CalculatedDiscount;
import by.htp.receiptcreator.bean.Cart;
import by.htp.receiptcreator.bean.Receipt;
import by.htp.receiptcreator.bean.Shop;

public interface CalculateReceiptService {

    Receipt calculateReceipt (Cart cart, CalculatedDiscount calculatedDiscount, Shop shop);
}
