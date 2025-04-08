package com.papeleria.inventariopapeleria.controller;

import com.papeleria.inventariopapeleria.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label mensajeError;
    @FXML private Button loginButton;

    public void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String url = "jdbc:mysql://localhost:3306/papeleria_db?useSSL=false";

        // Validar el usuario nativo de MySQL
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Si llegamos aquí, las credenciales son válidas
            System.out.println("¡Login exitoso! Conexión establecida.");
            System.out.println("¡Login exitoso! Conexión establecida.");

            // Configurar Hibernate con las credenciales válidas usando HibernateUtil
            HibernateUtil.buildSessionFactory(username, password);

            // Cargar la pantalla principal
            loadMainScreen();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("USUARIO Y CONTRASEÑA INCORRECTO");
            alert.showAndWait();
            System.out.println("Error en el login: credenciales inválidas o problema de conexión.");
            System.out.println("Error en el login: credenciales inválidas o problema de conexión.");
        }
    }

    private void loadMainScreen() {
        try {
            // Cargar el archivo FXML de la pantalla principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde el botón
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Crear y establecer la nueva escena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Papelería - Pantalla Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la pantalla principal.");
        }
    }
}