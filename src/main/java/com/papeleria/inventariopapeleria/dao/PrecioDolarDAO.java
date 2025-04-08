package com.papeleria.inventariopapeleria.dao;

import com.papeleria.inventariopapeleria.controller.PrecioDolar;
import com.papeleria.inventariopapeleria.model.PrecioDolarr;
import com.papeleria.inventariopapeleria.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PrecioDolarDAO {

    public void save(PrecioDolarr precioDolarr) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(precioDolarr);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(PrecioDolarr precioDolarr) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(precioDolarr);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(PrecioDolarr precioDolarr) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(precioDolarr);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public PrecioDolarr obtenerIdPorFecha(Date fecha) {
        try(Session session2 = HibernateUtil.getSessionFactory().openSession()){
                String hql = "FROM PrecioDolarr pd WHERE pd.fechaDolar = :fecha";
                Query<PrecioDolarr> query = session2.createQuery(hql, PrecioDolarr.class);
                query.setParameter("fecha", fecha); // Asignar el parámetro de fecha
                return query.uniqueResult(); // Devuelve el objeto PrecioDolar si hay un resultado único
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Verifica si ya existe un registro de precio del dólar para la fecha dada.
     *
     * @param fecha La fecha a verificar.
     * @return true si existe un registro para la fecha, false en caso contrario.
     */
    public boolean existePrecioParaFecha(LocalDate fecha) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM PrecioDolarr WHERE fechaDolar = :fecha";
            Query<PrecioDolarr> query = session.createQuery(hql, PrecioDolarr.class);
            query.setParameter("fecha", java.sql.Date.valueOf(fecha));

            List<PrecioDolarr> resultados = query.list();
            return !resultados.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // En caso de error, asumimos que no existe un registro
        }
    }
    public PrecioDolarr obtenerUltimoPrecio() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM PrecioDolarr ORDER BY fechaDolar DESC";
            Query<PrecioDolarr> query = session.createQuery(hql, PrecioDolarr.class);
            query.setMaxResults(1); // Solo queremos el registro más reciente
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}