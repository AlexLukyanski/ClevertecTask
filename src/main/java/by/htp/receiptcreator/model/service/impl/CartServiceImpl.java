package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.Cart;
import by.htp.receiptcreator.bean.Product;
import by.htp.receiptcreator.model.dao.ProductDAO;
import by.htp.receiptcreator.model.dao.exception.ProductDAOException;
import by.htp.receiptcreator.model.service.CartService;
import by.htp.receiptcreator.model.service.exception.CartServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private ProductDAO productDAO;

    @Autowired
    public CartServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public Cart formCart(int[] productID) throws CartServiceException {

        Cart cart = new Cart();

        try {
            Product[] products = getProducts(productID);
            Map<Product, Integer> cartField = fillCart(products);
            cart.setCart(cartField);
            return cart;

        } catch (ProductDAOException e) {
            throw new CartServiceException(e);
        }
    }

    private Product[] getProducts(int[] productID) throws ProductDAOException {

        Product[] products = new Product[productID.length];

        for (int i = 0; i < productID.length; i++) {
            products[i] = productDAO.readProductByID(productID[i]);
        }

        return products;
    }

    private Map<Product, Integer> fillCart(Product[] products) {

        Map<Product, Integer> cartField = new HashMap<>();

        for (Product product :
                products) {

            if (!cartField.containsKey(product)) {
                cartField.put(product, 1);
            } else {
                int productAmount = cartField.get(product) + 1;
                cartField.put(product, productAmount);
            }
        }
        return cartField;
    }
}
