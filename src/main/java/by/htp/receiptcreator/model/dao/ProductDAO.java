package by.htp.receiptcreator.model.dao;

import by.htp.receiptcreator.bean.Product;
import by.htp.receiptcreator.model.dao.exception.ProductDAOException;

public interface ProductDAO {

    Product readProductByID (int productID) throws ProductDAOException;
}
