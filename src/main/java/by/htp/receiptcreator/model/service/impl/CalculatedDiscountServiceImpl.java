package by.htp.receiptcreator.model.service.impl;

import by.htp.receiptcreator.bean.CalculatedDiscount;
import by.htp.receiptcreator.bean.Cart;
import by.htp.receiptcreator.bean.Product;
import by.htp.receiptcreator.model.dao.DiscountCardDAO;
import by.htp.receiptcreator.model.dao.exception.ProductDAOException;
import by.htp.receiptcreator.model.service.CalculatedDiscountService;
import by.htp.receiptcreator.model.service.exception.CalculatedDiscountServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CalculatedDiscountServiceImpl implements CalculatedDiscountService {

    private DiscountCardDAO discountCardDAO;
    private final static int ZERO_DISCOUNT_VALUE = 0;

    @Autowired
    public CalculatedDiscountServiceImpl(DiscountCardDAO discountCardDAO) {
        this.discountCardDAO = discountCardDAO;
    }

    @Override
    @Transactional
    public CalculatedDiscount calculateDiscount(int discountCardNumber, Cart cart) throws CalculatedDiscountServiceException {

        CalculatedDiscount calculatedDiscount = new CalculatedDiscount();
        int discountByCardAmount;

        try {
            if (discountCardNumber == 0) {
                discountByCardAmount = 0;
            } else {
                discountByCardAmount = discountCardDAO.readDiscountByCardNumber(discountCardNumber);
            }
            Map<Product, Integer> calculatedProductDiscount = calculateProductDiscount(cart);

            calculatedDiscount.setDiscountFromCard(discountByCardAmount);
            calculatedDiscount.setCalculatedProductDiscount(calculatedProductDiscount);

            return calculatedDiscount;
        } catch (ProductDAOException e) {
            throw new CalculatedDiscountServiceException(e);
        }
    }

    private Map<Product, Integer> calculateProductDiscount(Cart cart) {

        Map<Product, Integer> calculatedProductDiscount = new HashMap<>();
        Map<Product, Integer> cartField = cart.getCart();
        Set<Map.Entry<Product, Integer>> cartFieldSet = cartField.entrySet();

        for (Map.Entry<Product, Integer> product : cartFieldSet) {

            if (cartField.get(product.getKey()) > product.getKey().getAmountToDiscount() && product.getKey().isDiscount()) {
                calculatedProductDiscount.put(product.getKey(), product.getKey().getDiscountAmount());
            } else {
                calculatedProductDiscount.put(product.getKey(), ZERO_DISCOUNT_VALUE);
            }
        }
        return calculatedProductDiscount;
    }
}