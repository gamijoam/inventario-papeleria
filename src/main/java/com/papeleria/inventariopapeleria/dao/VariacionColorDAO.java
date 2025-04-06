package com.papeleria.inventariopapeleria.dao;



import com.papeleria.inventariopapeleria.model.VariacionColor;
import com.papeleria.inventariopapeleria.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VariacionColorDAO {
    public void save(VariacionColor variacion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(variacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}