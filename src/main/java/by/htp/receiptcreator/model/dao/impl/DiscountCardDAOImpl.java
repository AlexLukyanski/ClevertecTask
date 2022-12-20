package by.htp.receiptcreator.model.dao.impl;

import by.htp.receiptcreator.bean.DiscountCard;
import by.htp.receiptcreator.bean.constant.DiscountCardParam;
import by.htp.receiptcreator.model.dao.DiscountCardDAO;
import by.htp.receiptcreator.model.dao.exception.ProductDAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountCardDAOImpl implements DiscountCardDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public DiscountCardDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final static String SELECT_DISCOUNT_BY_CARD_NUMBER_HQL = "from DiscountCard where number =:discountCardNumber";

    @Override
    public int readDiscountByCardNumber(int cardNumber) throws ProductDAOException {
        try {
            Session session = sessionFactory.getCurrentSession();

            Query<DiscountCard> query = session.createQuery(SELECT_DISCOUNT_BY_CARD_NUMBER_HQL, DiscountCard.class);
            query.setParameter(DiscountCardParam.NUMBER_KEY, cardNumber);

            return query.getSingleResult().getDiscountAmount();

        } catch (HibernateException e) {
            throw new ProductDAOException(e);
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }
    }
}
