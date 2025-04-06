package com.papeleria.inventariopapeleria;

import com.papeleria.inventariopapeleria.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventario Papelería");
        primaryStage.show();
    }

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            launch(args);
        }

    }
}