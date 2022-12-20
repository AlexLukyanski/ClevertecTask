package by.htp.receiptcreator.model.dao.impl;

import by.htp.receiptcreator.bean.Product;
import by.htp.receiptcreator.bean.constant.ProductParam;
import by.htp.receiptcreator.model.dao.ProductDAO;
import by.htp.receiptcreator.model.dao.exception.ProductDAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final static String SELECT_ONE_PRODUCT_HQL = "from Product where id =:productID";

    @Override
    public Product readProductByID(int productID) throws ProductDAOException {

        try {
            Session session = sessionFactory.getCurrentSession();

            Query<Product> query = session.createQuery(SELECT_ONE_PRODUCT_HQL, Product.class);
            query.setParameter(ProductParam.ID_KEY, productID);

            return query.getSingleResult();
        } catch (HibernateException e) {
            throw new ProductDAOException(e);
        } catch (Exception e) {
            throw new ProductDAOException(e);
        }
    }
}
