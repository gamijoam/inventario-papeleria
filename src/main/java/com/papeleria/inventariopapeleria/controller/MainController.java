package com.papeleria.inventariopapeleria.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private void abrirAgregarProducto() throws IOException {
        abrirVentana("/agregarProducto.fxml", "Agregar Producto");
    }

    @FXML
    private void abrirEliminarProducto() throws IOException {
        abrirVentana("/eliminarProducto.fxml", "Eliminar Producto");
    }

    @FXML
    private void abrirActualizarProducto() throws IOException {
        abrirVentana("/actualizarProducto.fxml", "Actualizar Producto");
    }

    @FXML
    private void abrirVentas() throws IOException {
        abrirVentana("/ventas.fxml", "Ventas");
    }

    @FXML
    private void abrirInventario() throws IOException {
        abrirVentana("/inventario.fxml", "Inventario");
    }

    @FXML
    private void abrirConfiguracionDolar() throws IOException {
        abrirVentana("/precioDolar.fxml","Configuracion");
    }

    private void abrirVentana(String fxml, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(titulo);
        stage.show();
    }
}