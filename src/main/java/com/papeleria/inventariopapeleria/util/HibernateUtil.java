package com.papeleria.inventariopapeleria.util;

import com.papeleria.inventariopapeleria.model.PrecioDolarr;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    // Método para construir la SessionFactory con credenciales dinámicas
    public static void buildSessionFactory(String username, String password) {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/papeleria_db?useSSL=false")
                    .setProperty("hibernate.connection.username", username)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("show_sql", "true")
                    .addAnnotatedClass(com.papeleria.inventariopapeleria.model.Producto.class)
                    .addAnnotatedClass(PrecioDolarr.class);

            sessionFactory = configuration.buildSessionFactory();
            System.out.println("Hibernate configurado correctamente en HibernateUtil.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar Hibernate en HibernateUtil");
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory no ha sido inicializada. Debes llamar a buildSessionFactory primero.");
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}